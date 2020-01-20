package examplefuncsplayer;
import battlecode.common.*;

public class Soupbaby extends Global {

public static void Mine() throws GameActionException {
		
		
		
		MapLocation[] h=rc.senseNearbySoup();
		for(MapLocation u : h) {
			if(Pathfind.going(u)) {
			
			MapLocation me = rc.getLocation();
			Direction dir = me.directionTo(u);
			System.out.println("u"+u);
			while(rc.senseSoup(u)!= 0 && rc.getSoupCarrying()<100) {
			if(rc.canMineSoup(dir)) {
				
					rc.mineSoup(dir);
					System.out.println("minning soup"+rc.getSoupCarrying());
				} 
				}
		}
			
		if(rc.getSoupCarrying()==100) {
			System.out.println("i am done ");
			break;
		}
			
		}
				while(rc.getSoupCarrying()!=0) {
				if(Pathfind.going(myHQ)) {
				MapLocation futreme = rc.getLocation();
				Direction futurdir = futreme.directionTo(myHQ);
				if(rc.canDepositSoup(futurdir)) {
					rc.depositSoup(futurdir,rc.getSoupCarrying());
					
				}}
				}return;
					
			} 
		
			
		

			
		
				
		
	public static void getsoup() throws GameActionException {
		Miner.firstmove();
		MapLocation[] h = rc.senseNearbySoup();
		Pathfind.going(h[0]);
		Mine();
			
	getsoup();}
		
	
}


