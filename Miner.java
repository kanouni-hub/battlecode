package examplefuncsplayer;

import java.util.ArrayList;

import battlecode.common.*;
public class Miner extends Global {
	private static Direction forwarddir;
	private static Direction uprightdir;
	private static Direction upleftdir;
	private static Direction[] checkdir;
	private static int maxdist;
	private static ArrayList<MapLocation> visited=new ArrayList<MapLocation>();
	private static ArrayList<MapLocation> visited1=new ArrayList<MapLocation>();

	
	private static int memdir=0;
	//functions for try first
	public static ArrayList<MapLocation> decidemoveHQ(MapLocation i) {
		ArrayList<MapLocation> childrens=getchildforward(i);
		ArrayList<MapLocation> maxi=new ArrayList<MapLocation>();
		ArrayList<MapLocation> holder;
		maxi.add(i);
		if(myloc.distanceSquaredTo(i)>=Math.max(maxdist,1) || childrens.size()==0) {
			
			return maxi;
		}
		for(MapLocation file:childrens) {
			if(!visited1.contains(file)) {
			visited1.add(file);
			holder=decidemoveHQ(file);
			if(myloc.distanceSquaredTo(holder.get(holder.size()-1))>=Math.max(maxdist,1) && !visited.contains(holder.get(holder.size()-1))) {
				maxi=holder;
				maxi.add(0,i);
				 return maxi;
			}
			if(myloc.distanceSquaredTo(holder.get(holder.size()-1))>myloc.distanceSquaredTo(i)) {
				maxi=holder;
				maxi.add(0,i);
			}}
		}
		return  maxi;
	}
	
	
	
	
	
	public static void moving() {
		int c=0;
		MapLocation last=trypath.get(trypath.size()-1);
		while(trypath.size()!=0) {
		System.out.println("byte moving "+Clock.getBytecodesLeft());
		if(rc.canMove(myloc.directionTo(trypath.get(0)))) {
			try {
				rc.move(myloc.directionTo(trypath.get(0)));
			} catch (GameActionException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			squaredradius=rc.getCurrentSensorRadiusSquared();
			radius=racine[squaredradius];					
			myloc=rc.getLocation();
			System.out.println(rc.getRoundNum()+" bytcode "+Clock.getBytecodesLeft());
			
			System.out.println("update map "+rc.getCurrentSensorRadiusSquared());
			System.out.println("rayon "+radius);
			Nav.moved();
			//Nav.updatemap();
			System.out.println("update map "+rc.getCurrentSensorRadiusSquared());
			System.out.println("rayon "+radius);
			
			System.out.println(rc.getRoundNum()+" byt 2 "+Clock.getBytecodesLeft());

			trypath.remove(0);
			
			Clock.yield();
		}else if(rc.isReady()) {
			memdir++;
			if(memdir>2) {
				break;
			}
			
			Clock.yield();
		}else {
		Clock.yield();
		}
		}
		visited.add(last);
	}

	private static ArrayList<MapLocation> getchildforward(MapLocation s){
		ArrayList<MapLocation> child=new ArrayList<MapLocation>();
		for(Direction dir:checkdir) {
		
		if(rc.canSenseLocation(s.translate(dir.getDeltaX(),dir.getDeltaY()))) {
			if(Math.abs(map[s.x+dir.getDeltaX()][s.y+dir.getDeltaY()]-map[s.x][s.y])<4 && !floodmap[s.x+dir.getDeltaX()][s.y+dir.getDeltaY()] ) {
				child.add(new MapLocation(s.x+dir.getDeltaX(),s.y+dir.getDeltaY()));
			}	
		}
		}
		
		
		return child;
	}

	
	public static void changecheckdir() {
		if(memdir==10) {memdir=0;}
		if(memdir<6) {memdir++;
		forwarddir=checkdir[1];			
		}else {memdir++;
		forwarddir=checkdir[2];		
		}
		checkdir[0]=forwarddir;  checkdir[1]=forwarddir.rotateLeft();  checkdir[2]=forwarddir.rotateRight();
		checkdir[3]=checkdir[1].rotateLeft();    checkdir[4]=checkdir[2].rotateRight();
	}


	//Moving randomly until finding soup;
	public static void firstmove() throws GameActionException {
		visited.add(myloc);
		visited1.add(myloc);
		forwarddir=myHQ.directionTo(myloc);
		uprightdir=forwarddir.rotateRight();
		upleftdir=forwarddir.rotateLeft();
		checkdir= new Direction[] {forwarddir,upleftdir,uprightdir,upleftdir.rotateLeft(),uprightdir.rotateRight()};
		maxdist=carre[Math.min(Math.min(radius,width-myloc.x-1),height-myloc.y-1)];
	
	MapLocation[] h =rc.senseNearbySoup();
	 while(h.length == 0) {
			
		
		
		maxdist=carre[Math.min(Math.min(radius,width-myloc.x-1),height-myloc.y-1)];
		myloc=rc.getLocation();
		if(!rc.canSenseLocation(myloc.translate(3*forwarddir.getDeltaX(), 3*forwarddir.getDeltaY())) ||memdir>1) {
			changecheckdir();
	
		}
		trypath=decidemoveHQ(myloc);
		visited1.clear();
		trypath.remove(0);
		while(trypath.size()==0) {
			changecheckdir();

			trypath=decidemoveHQ(myloc);
			visited1.clear();
			trypath.remove(0);
		}
		if(myloc.directionTo(trypath.get(trypath.size()-1))!=forwarddir) {
			memdir++;
		}else {memdir=0;}
		}moving();}
	
	//Minning until get max soup then going to hq  
	public static void Mine() throws GameActionException {
		
		
		
		MapLocation[] h=rc.senseNearbySoup();
		for(MapLocation u : h) {
			Pathfind.going(u);
			MapLocation me = rc.getLocation();
			Direction dir = me.directionTo(u);
			System.out.println("u"+u);
			while(rc.senseSoup(u)!= 0 && rc.getSoupCarrying()<100) {
			if(rc.canMineSoup(dir)) {
				try {
					rc.mineSoup(dir);
					System.out.println("minning soup"+rc.getSoupCarrying());
				} catch (GameActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}else {
					System.out.println("cnt min in "+dir);
				}
		}
			
		if(rc.getSoupCarrying()==100) {
			System.out.println("i am done ");
			break;
		}
			
		}
			try {
				
				Pathfind.going(myHQ);
				MapLocation futreme = rc.getLocation();
				Direction futurdir = futreme.directionTo(myHQ);
				if(rc.canDepositSoup(futurdir)) {
					rc.depositSoup(futurdir,rc.getSoupCarrying());
					
				}
					
			} catch (GameActionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		

			
		
				
		
	public static void getsoup() throws GameActionException {
		firstmove();
		MapLocation[] h = rc.senseNearbySoup();
		while(!(h.length == 0)) {
		for(MapLocation u:h) {
			Pathfind.going(u);
			Mine();
			
	}}
		getsoup();
	}
}
	
