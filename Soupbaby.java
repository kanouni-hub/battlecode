package test;
import battlecode.common.*;

public class Soupbaby extends Global {

public static void Mine() throws GameActionException {
		

		
		MapLocation[] h=rc.senseNearbySoup();
		for(MapLocation u : h) {
			if(Pathfind.going(u)) {
			
			MapLocation me = rc.getLocation();
			Direction dir = me.directionTo(u);
		//	System.out.println("u"+u);
			while(rc.senseSoup(u)!= 0 && rc.getSoupCarrying()<100) {
			if(rc.canMineSoup(dir)) {
				
					rc.mineSoup(dir);
			//		System.out.println("minning soup"+rc.getSoupCarrying());
				} 
				}
		}
			
		if(rc.getSoupCarrying()==100) {
			System.out.println("i am done ");
			break;
		}
			
		}
				while(rc.getSoupCarrying()!=0) {
				Message.refineryLocation();
				if(refinery==null) {
				if(Pathfind.going(myHQ)) {
				MapLocation futreme = rc.getLocation();
				Direction futurdir = futreme.directionTo(myHQ);
				if(rc.canDepositSoup(futurdir)) {
					rc.depositSoup(futurdir,rc.getSoupCarrying());
					
				}
				else{
					if(rc.canBuildRobot(RobotType.REFINERY,myHQ.directionTo(rc.getLocation()))) {
						rc.buildRobot(RobotType.REFINERY,myHQ.directionTo(rc.getLocation()));
						refinery = rc.adjacentLocation(myHQ.directionTo(rc.getLocation()));
						Message.broadcastrefinery();
					}
					
					
					}
				}
				else {
					Pathfind.going(refinery);
					MapLocation futreme = rc.getLocation();
					Direction futurdir = futreme.directionTo(myHQ);
					if(rc.canDepositSoup(futurdir)) {
						rc.depositSoup(futurdir,rc.getSoupCarrying());
						
						
					}
					
					
				}
				}
				}return;
					
			} 
		
			
		

			
		
				
		
}
