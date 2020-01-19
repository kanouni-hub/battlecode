package examplefuncsplayer;
import battlecode.common.*;
public class Drone extends Symetrie {
	public static void droneassault() throws GameActionException {
		if (opHQ != null) {
			int r = 4;
			MapLocation[] H = { new MapLocation(opHQ.x-r,opHQ.y), new MapLocation(opHQ.x-r,opHQ.y+1),new MapLocation(opHQ.x-r,opHQ.y+2),new MapLocation(opHQ.x-r+1,opHQ.y+3),new MapLocation(opHQ.x-r+2,opHQ.y+4),new MapLocation(opHQ.x-r+3,opHQ.y+4),new MapLocation(opHQ.x-r+4,opHQ.y+4),new MapLocation(opHQ.x-r+5,opHQ.y+4),new MapLocation(opHQ.x-r+6,opHQ.y+4),new MapLocation(opHQ.x-r+7,opHQ.y+3),new MapLocation(opHQ.x-r+8,opHQ.y+2),new MapLocation(opHQ.x-r+8,opHQ.y+1),new MapLocation(opHQ.x-r+8,opHQ.y),new MapLocation(opHQ.x-r+8,opHQ.y-1),new MapLocation(opHQ.x-r+8,opHQ.y-2),new MapLocation(opHQ.x-r+5,opHQ.y-2),new MapLocation(opHQ.x-r+7,opHQ.y-3),new MapLocation(opHQ.x-r+6,opHQ.y-4),new MapLocation(opHQ.x-r+5,opHQ.y-4),new MapLocation(opHQ.x-r+4,opHQ.y-4),new MapLocation(opHQ.x-r+3,opHQ.y-4),new MapLocation(opHQ.x-r+2,opHQ.y-4),new MapLocation(opHQ.x-r+1,opHQ.y-3),new MapLocation(opHQ.x-r,opHQ.y-2),new MapLocation(opHQ.x-r,opHQ.y-1)};
			for(MapLocation u : H) {
				MapLocation me = rc.getLocation();
						while(me!=u) {
							if(rc.canMove(me.directionTo(u))) {
								rc.move(me.directionTo(u));
								Clock.yield();
								}
							else {
								boolean moved=false;
								
								while(!moved) {
									Direction dir = me.directionTo(u).rotateLeft();
									if(rc.canMove(dir))
									{rc.move(dir);
									moved=true;
									Clock.yield();}}
							}
					}
							break;}
			
		}
		else {
			Nav.updateRobot(rc.senseNearbyRobots());
			FindHQ(myHQ);
			droneassault();
		}
			
	}

	

}
