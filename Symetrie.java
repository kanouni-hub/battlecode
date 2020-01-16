package examplefuncsplayer;
import battlecode.common.*;

public class Symetrie extends RobotPlayer{
;
	static MapLocation g ;
	int c ;
	int a ;
	
	// symetrie x
	public MapLocation verticale( MapLocation h) {
		int xprime;
		int a = rc.getMapWidth() - 2*(h.x) ;
		int u = h.x +a;
		if(u>rc.getMapWidth()) {
		 	xprime = h.x -a ;}
		
		else { xprime =u;}
		
		MapLocation hprime = new MapLocation(xprime,h.y);
		return hprime ;}
	// symetrie  y
	public MapLocation horizantal( MapLocation h) {
		int yprime;
		int a = rc.getMapHeight() - 2*(h.y) ;
		int u = h.y +a;
		
		if(u>rc.getMapHeight()) {
		 	yprime = h.y -a ;}
		
		else {  yprime =u;}
		
		MapLocation hprime = new MapLocation(h.x,yprime);
		return hprime ; }
	
	// symetrie  y=x
	public MapLocation Diagonale(MapLocation h) {
			MapLocation hprime = new MapLocation(h.y,h.x);
			return hprime ;}
	
	// symetrie  inverse diagonale
	public MapLocation InvDiagonale(MapLocation h) {
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
		
	public boolean FindHQ(MapLocation h) {
			
			
			MapLocation[] Possible = {verticale(h), horizantal(h),Diagonale(h),InvDiagonale(h)};
			for(MapLocation u : Possible) {
					try {
					Pathfind.going(u);
				} catch (GameActionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					if ( (rc.senseRobotAtLocation(u)).getType()==RobotType.HQ){
						 
						 break;
						}} catch (GameActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();}
				
			}
		
		
			return true ;}

}
