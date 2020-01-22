package test;
import battlecode.common.*;

public class Symetrie extends RobotPlayer{
;
	static MapLocation g ;
	int c ;
	int a ;
	
	// symetrie x
	public static MapLocation verticale( MapLocation h) {
		int xprime;
		int a = rc.getMapWidth() - 2*(h.x) ;
		int u = h.x +a-1;
		if(u>rc.getMapWidth()) {
		 	xprime = h.x -a-1 ;}
		
		else { xprime =u;}
		
		MapLocation hprime = new MapLocation(xprime,h.y);
		return hprime ;}
	// symetrie  y
	public static MapLocation horizantal( MapLocation h) {
		int yprime;
		int a = rc.getMapHeight() - 2*(h.y) ;
		int u = h.y +a-1;
		
		if(u>rc.getMapHeight()) {
		 	yprime = h.y -a-1 ;}
		
		else {  yprime =u;}
		
		MapLocation hprime = new MapLocation(h.x,yprime);
		return hprime ; }
	
	// symetrie  y=x
	public static MapLocation Diagonale(MapLocation h) {
			MapLocation hprime = new MapLocation(h.y,h.x);
			return hprime ;}
	
	// symetrie  inverse diagonale
	public static MapLocation InvDiagonale(MapLocation h) {
		int yprime;
		int xprime ;
		int b = rc.getMapHeight()*(1-(h.x)/rc.getMapWidth());
		int a = rc.getMapWidth()*(1-(h.y)/rc.getMapHeight()); 
		if (h.y > b) { yprime = h.y - (h.y - b);}
		else {yprime = h.y + (b-h.y);}
		if(h.x>a) { xprime = h.x -(h.x-a);}
		else {xprime=h.x+(a-h.x);}
		
		MapLocation hprime = new MapLocation(xprime,yprime);
		return hprime ;}
	public static MapLocation [] Allsymetries(MapLocation u) {
		MapLocation[] h =  {verticale(u), horizantal(verticale(u)),horizantal(u),Diagonale(u)};
		return h;
	}
		
	public static boolean FindHQ(MapLocation h) throws GameActionException {
			
			
			MapLocation[] Possible = {verticale(h), horizantal(verticale(h)),horizantal(h),Diagonale(h)};
			for(MapLocation u : Possible) {
				System.out.println("u "+u);
				
					if(!Pathfind.going(u)) {
					System.out.println("HQ not found" +u);
						MapLocation me= rc.getLocation();
						int d = me.distanceSquaredTo(u);
						if(d<=rc.getCurrentSensorRadiusSquared()) {
							if ( (rc.senseRobotAtLocation(u))!=null){
								if(rc.senseRobotAtLocation(u).getType()==RobotType.HQ && rc.senseRobotAtLocation(u).getTeam()!=rc.getTeam()) {
									opHQ=u;
									Message.broadcastennemyHQ();
									System.out.println("ennemy HQ"+opHQ);
									break ;}	
								}
						}
					}
				else {	
					if ( (rc.senseRobotAtLocation(u))!=null){
						if(rc.senseRobotAtLocation(u).getType()==RobotType.HQ && rc.senseRobotAtLocation(u).getTeam()!=rc.getTeam()) {
							opHQ=u;
							Message.broadcastennemyHQ();
							System.out.println("ennemy HQ"+opHQ);
							break ;
						}}
				
						
					}
			}
		
		
			return true ;}


}
