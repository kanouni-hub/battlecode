package examplefuncsplayer;
import battlecode.common.*;
public class Drone extends Symetrie {
	public static void droneassault() throws GameActionException {
		if (opHQ != null) {
			int r = 3;
			MapLocation[] H = { new MapLocation(opHQ.x-r,opHQ.y), new MapLocation(opHQ.x-r,opHQ.y+1),new MapLocation(opHQ.x-r,opHQ.y+2),new MapLocation(opHQ.x-r-1,opHQ.y+2),new MapLocation(opHQ.x-r+1,opHQ.y+3),new MapLocation(opHQ.x-r+2,opHQ.y+3),new MapLocation(opHQ.x-r+3,opHQ.y+3),new MapLocation(opHQ.x-r+4,opHQ.y+3),new MapLocation(opHQ.x-r+5,opHQ.y+3),new MapLocation(opHQ.x-r+5,opHQ.y+2),new MapLocation(opHQ.x-r+6,opHQ.y+2),new MapLocation(opHQ.x-r+6,opHQ.y+1),new MapLocation(opHQ.x-r+6,opHQ.y),new MapLocation(opHQ.x-r+6,opHQ.y-1),new MapLocation(opHQ.x-r+6,opHQ.y-2),new MapLocation(opHQ.x-r+5,opHQ.y-2),new MapLocation(opHQ.x-r+5,opHQ.y-3),new MapLocation(opHQ.x-r+4,opHQ.y-3),new MapLocation(opHQ.x-r+3,opHQ.y-3),new MapLocation(opHQ.x-r+2,opHQ.y-3),new MapLocation(opHQ.x-r+1,opHQ.y-3),new MapLocation(opHQ.x-r+1,opHQ.y-2),new MapLocation(opHQ.x-r,opHQ.y-2),new MapLocation(opHQ.x-r,opHQ.y-1)};
			for(MapLocation u : H) {
				if(Pathfind.going(u)) {
					return;
					
				}
			}
			
		}
		else {
			Nav.updateRobot(rc.senseNearbyRobots());
			FindHQ(myHQ.translate(-3, 0));
			Nav.updateRobot(rc.senseNearbyRobots());
			droneassault();
		}
			
	}

	

}
