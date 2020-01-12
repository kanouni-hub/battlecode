package examplefuncsplayer1;
import java.util.*;
import battlecode.common.*;
public class LsTactics extends RobotPlayer {
	
	static Team myteam=rc.getTeam();
	static int[][] Map =new int[rc.getMapWidth()][rc.getMapHeight()];
	static Direction optimalMove (MapLocation loc) {
		/**
		 * Calculates next move direction to location 
		 * 
		 */
		return  Direction.NORTH;
	}
	static MapLocation Souploc = new MapLocation(0,0);
	/**
	 * Returns an array of enemy robots in sight 
	 */
	public RobotInfo[]  opponentInSight() {
		
		return rc.senseNearbyRobots(rc.getCurrentSensorRadiusSquared(),myteam.opponent());
		
	}
	
	static MapLocation HQ;
	static MapLocation EnemyHQ ;
	
	
	public void slowMiner() throws GameActionException {
		ArrayList<Direction> MoveToSoup=optimalMove(Souploc);
		if(rc.canMove(MoveToSoup.get(MoveToSoup.size() -1))) {
		rc.move(MoveToSoup.get(MoveToSoup.size() -1));
		MoveToSoup.remove(MoveToSoup.size()-1);
		}
		/**
		 * The above code executes the movement to the enemy soup  
		 * 
		 */
	while (opponentInSight().length==0) {
		ArrayList<Direction> MoveToEnemy=optimalMove(EnemyHQ);
		if(rc.canMove(MoveToEnemy.get(MoveToEnemy.size() -1))) {
			rc.move(MoveToEnemy.get(MoveToEnemy.size() -1));
		MoveToSoup.remove(MoveToSoup.size()-1);}	
	}
	}
	
	static void GetMaxDirt() {
		if(rc.getDirtCarrying()<RobotType.LANDSCAPER.dirtLimit) 
			return;    
	}
      
	/*---------------------------------------------------------------------------------------------------------------*/
	/**
	 * 
	 * @param loc
	 * @return Carries the movement order of optimal move 
	 */
	static void MoveFast(MapLocation loc) throws GameActionException {
		ArrayList<Direction> Movements=optimalMove(loc);
    	while(Movements.size()!=0) {
    		if(rc.canMove(Movements.get(Movements.size() -1))) {
		       rc.move(Movements.get(Movements.size() -1));
		       Movements.remove(Movements.size()-1);
		       Clock.yield(); 
		}
    		else Movements=optimalMove(loc);
		}
	}
	
	static MapLocation[] BuildersPos = new MapLocation[8];
	
	
	
	/**
	 * 
	 * @param loc
	 * @return Forces the move to location if no ally landscaper in position 
	 */
	static void fixElevation(MapLocation loc) throws GameActionException {
		if(rc.senseElevation(loc)+3<rc.senseElevation(rc.getLocation()))
			while(rc.senseElevation(loc)+3<rc.senseElevation(rc.getLocation())) {
				tryDigDirt(Direction.CENTER);
				Clock.yield();
				tryDeposit(rc.getLocation().directionTo(loc));
				Clock.yield();
			}
		else if(rc.senseElevation(loc)>rc.senseElevation(rc.getLocation())+3) {
			while(rc.senseElevation(loc)>rc.senseElevation(rc.getLocation())+3) {
				tryDigDirt(rc.getLocation().directionTo(loc));
				Clock.yield();
				tryDeposit(Direction.CENTER);
				Clock.yield();
			}
		}
		
		
	}
	
	
	static boolean ForceMove(MapLocation loc) throws GameActionException  {
	   boolean track;
		while(rc.getLocation().distanceSquaredTo(loc)>2) {
		 tryMove(optimalMove(loc));
		}
		 if(rc.senseRobotAtLocation(loc).getType()==RobotType.LANDSCAPER && rc.senseRobotAtLocation(loc).getTeam()==myteam) {
			   track=false;
		   }
		   while(rc.getLocation()!=loc) {
			   fixElevation(loc);
		   tryMove(rc.getLocation().directionTo(loc));
		   }
		   track=true;
		   return track;
		   
	   
	}
	/**
	 * 
	 * 
	 * @param loc
	 * @return array of all adjacent positions 
	 */
	public MapLocation[] adjacentTo(MapLocation loc) {
		return new MapLocation[8];
	}
	
	/**
	 * 
	 * @param loc
	 * @return ArrayList of closest and farthest adjacent to the location by squared distance
	 */
	
	
	public Direction[] closestFarthestAdjacent(MapLocation loc) {
		Direction[] closeFarDir=new Direction[2];
		MapLocation[] adjacentTo =adjacentTo(loc);
		int minDistance=loc.distanceSquaredTo(adjacentTo[adjacentTo.length-1]);
		int maxDistance=loc.distanceSquaredTo(adjacentTo[adjacentTo.length-1]);
		MapLocation closest=adjacentTo[adjacentTo.length-1];
		MapLocation farthest=adjacentTo[adjacentTo.length-1];
		for (int i=adjacentTo.length-1;i<=0;i--) {
			if (loc.distanceSquaredTo(adjacentTo[i])<minDistance) {
				minDistance=loc.distanceSquaredTo(adjacentTo[i]);
				closest=adjacentTo[i];	
			}
			else if (loc.distanceSquaredTo(adjacentTo[i])>maxDistance) {
				maxDistance=loc.distanceSquaredTo(adjacentTo[i]);
				farthest=adjacentTo[i];
			}
		}
		closeFarDir[0]=loc.directionTo(closest);
		closeFarDir[1]=loc.directionTo(farthest);
		return closeFarDir;	
	}
	
    
		
	
	
	/** 
	 * List of builders positions 
	 * If position not held by a landscaper : Force move to (If elevation dig )
	 * If the position is held by a landscaper , do this operation on next location on the list 
	 * Dig from closest to HQ and deposit to farthest from HQ 
	 */
	public void wallOfChina() throws GameActionException{
		boolean ForceMove=ForceMove(BuildersPos[0]);
		int i=0;
		while (ForceMove==false && i<=6) {
			i++;
			ForceMove=ForceMove(BuildersPos[i]);
		}
		
		
		int dx=HQ.directionTo(rc.getLocation()).dx;
		int dy=HQ.directionTo(rc.getLocation()).dy;
		
		int[] newDir=new int[2];
		if (dx+dy==0) {
			newDir[0]=0;
			newDir[1]=dy;
		}
		else if(Math.abs(dx+dy)==2) {
			newDir[0]=dx;
			newDir[1]=0;
		}
		else if(dx==0) {
			newDir[0]=dy;
			newDir[1]=dy;
		}
		else if(dy==0) {
			newDir[0]=dx;
			newDir[1]=-dx;
		}
		MapLocation brick =rc.getLocation().translate(newDir[0],newDir[1]);
		
		/**
		 * 
		 */
		while(true) {
		tryDigDirt(Direction.CENTER);
		Clock.yield();
		tryDeposit(HQ.directionTo(rc.getLocation()));
		Clock.yield();
		tryDigDirt(Direction.CENTER);
		Clock.yield();
		tryDigDirt(rc.getLocation().directionTo(brick));
		}
		
		
		
		
		
		
		/**
		 * 
		 */
		
		
	
	}
	/**
	 * 
	 * 
	 * @param dir
	 * @return if the unit can digDirt or not and digs dirt 
	 * @throws GameActionException
	 */
	static boolean tryDigDirt(Direction dir) throws GameActionException {
		if(rc.canDigDirt(dir)) {
			rc.digDirt(dir);
			return true;
			}
		else return false;
	}
	static boolean tryDeposit(Direction dir)throws GameActionException{
		if(rc.canDepositDirt(dir)) {
			rc.depositDirt(dir);
			return true;
		}
		else return false;
	}
	/*-------------------------------------------------------------------------------------------------------*/
	static int symIndex; 
	/**
	 * @param loc1
	 * @param loc2
	 * @return whether the locations are accessible to each other
	 */
	static boolean isAccessible(MapLocation loc1,MapLocation loc2) {
		if (Map[loc1.x][loc1.y]>Map[loc2.x][loc2.y] +3 || Map[loc1.x][loc1.y]+3<Map[loc2.x][loc2.y] ) 
			return false;
		else return true;
	}
	/**
	* case myX<goalX and myY < goalY : verify (x, j in range(myY +1,end of map) 'needs to be true'
	* and verify (i,myY for i in range(myX +1,end of map ) 'needs to be true 
	* retrieve new map at (closest X to myX, closest y to myY ) to line in direction of location
	* recurse on new map until getting to adjacent of location 
			 */
	
	/**
	 * 
	 * @param loc
	 * @return new pivot , if not return (-13,-13) location
	 */
	static MapLocation canGetTo(MapLocation loc,MapLocation pivotLoc) {
		int dirIndX=1;
		int dirIndY=1;
		switch(pivotLoc.directionTo(loc)) {
		case EAST :
		case NORTHEAST : 
		case NORTH :
	        dirIndX=1;
		    dirIndY=1;
		break;
		case WEST:
		case NORTHWEST:
		    dirIndX=-1;
		    dirIndY=1;
		case SOUTH:
		case SOUTHEAST:
		    dirIndX=1;
		    dirIndY=-1;
		case SOUTHWEST:
			dirIndX=-1;
			dirIndY=-1;
		default :
			break;
		}
		
		MapLocation HwallX = new MapLocation(0,pivotLoc.y);
		MapLocation HwallY = new MapLocation(pivotLoc.x,0);
		boolean emptyX=false;
		boolean emptyY=false;
		for (int i=0;i<rc.getMapWidth();i++ ) {
			if(isAccessible(HwallX,HwallX.translate(0,dirIndY))) {
				emptyX=true;
			}
			if(isAccessible(HwallY,HwallY.translate(dirIndX,0))) {
				emptyY=true;
			}
			HwallX=HwallX.translate(1,0);
			HwallY=HwallY.translate(0,1);
		}
		if(emptyX && emptyY) {
			return pivotLoc.translate(dirIndX, dirIndY);
		}
		else return new MapLocation(-13,-13);
		
		
	}
	
	/**
	 *Main Function Verification 
	 * 
	 */
   public boolean verification(MapLocation loc) {
	   MapLocation pivot = rc.getLocation();
	   if (loc.isWithinDistanceSquared(pivot, 2)) {
		   if(isAccessible(pivot,loc)) {
			   return true;
		   }
		   else return false ;   
	   }
	   while(pivot.x>=0) {
	     pivot=canGetTo(loc,pivot);
	   }
	   return false;
   }
	
}
