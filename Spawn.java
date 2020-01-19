package examplefuncsplayer;

import battlecode.common.*;


public class Spawn extends RobotPlayer {
	
public Direction goodir() {
	Direction dir = Direction.NORTH;
	MapLocation me=rc.getLocation();
	MapLocation[] h= rc.senseNearbySoup();
	if(h.length!=0) {
		dir=me.directionTo(h[0]);
	}
	 return dir;}
}
