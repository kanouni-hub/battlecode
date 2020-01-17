package examplefuncsplayer;
import battlecode.common.*;
public class Buildbaby extends Global {
	public static void landscaperkill() throws GameActionException {
		MapLocation me = rc.getLocation();
		Direction dir =opHQ.directionTo(me);
		MapLocation h = me.add(dir.opposite());
		if(rc.canMove(dir.opposite())&&rc.senseFlooding(h)) {
			rc.move(dir.opposite());
			
		}
		MapLocation me1 = rc.getLocation();
		Direction dir1 =opHQ.directionTo(me1);
		MapLocation h1 = me1.add(dir1.opposite());
		if(rc.canMove(dir1.opposite())&&rc.senseFlooding(h1)) {
			rc.move(dir1.opposite());
			
		}
		MapLocation me2 = rc.getLocation();
		Direction dir2 =opHQ.directionTo(me2);
		MapLocation h2 = me2.add(dir2.opposite());
		if(rc.canMove(dir2.opposite())&&rc.senseFlooding(h2)) {
			rc.move(dir2.opposite());
			
		}
		if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, dir2.opposite())) {
			rc.buildRobot(RobotType.DESIGN_SCHOOL, dir2.opposite());
		}
	
		}
	public static void landscaperwall() throws GameActionException {
		MapLocation me = rc.getLocation();
		MapLocation u = myHQ.translate(2,0);
		if(rc.canSenseLocation(u)) {
			if(rc.senseRobotAtLocation(u)==null && rc.senseFlooding(u)) {
				Pathfind.going(u);
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me.directionTo(u))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me.directionTo(u));
					return;
			}
		}
	}
		MapLocation u1 = myHQ.translate(0,2);
		if(rc.canSenseLocation(u1)) {
			if(rc.senseRobotAtLocation(u1)==null && rc.senseFlooding(u1)) {
				Pathfind.going(u1);
				MapLocation me1 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me1.directionTo(u1))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me1.directionTo(u1));
					return;
			}
		}
	}
		MapLocation u11 = myHQ.translate(0,-2);
		if(rc.canSenseLocation(u11)) {
			if(rc.senseRobotAtLocation(u11)==null && rc.senseFlooding(u11)) {
				Pathfind.going(u11);
				MapLocation me2 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me2.directionTo(u11))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me2.directionTo(u11));
					return;
			}
		}
	}
		
		MapLocation u2 = myHQ.translate(-2,0);
		if(rc.canSenseLocation(u2)) {
			if(rc.senseRobotAtLocation(u2)==null && rc.senseFlooding(u2)) {
				Pathfind.going(u2);
				MapLocation me3 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me3.directionTo(u2))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me3.directionTo(u2));
					return;
			}
		}
	}}
		
	public static void netdefence() throws GameActionException {
		
	
		MapLocation me = rc.getLocation();
		MapLocation u = myHQ.translate(3,0);
		if(rc.canSenseLocation(u)) {
			if(rc.senseRobotAtLocation(u)==null && rc.senseFlooding(u)) {
				Pathfind.going(u);
				if(rc.canBuildRobot(RobotType.NET_GUN, me.directionTo(u))) {
					rc.buildRobot(RobotType.NET_GUN, me.directionTo(u));
				
			}
		}
	}
		MapLocation u1 = myHQ.translate(0,3);
		if(rc.canSenseLocation(u1)) {
			if(rc.senseRobotAtLocation(u1)==null && rc.senseFlooding(u1)) {
				Pathfind.going(u1);
				MapLocation me1 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.NET_GUN, me1.directionTo(u1))) {
					rc.buildRobot(RobotType.NET_GUN, me1.directionTo(u1));
					
			}
		}
	}
		MapLocation u11 = myHQ.translate(0,-3);
		if(rc.canSenseLocation(u11)) {
			if(rc.senseRobotAtLocation(u11)==null && rc.senseFlooding(u11)) {
				Pathfind.going(u11);
				MapLocation me2 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.NET_GUN, me2.directionTo(u11))) {
					rc.buildRobot(RobotType.NET_GUN, me2.directionTo(u11));
					
			}
		}
	}
		
		MapLocation u2 = myHQ.translate(-3,0);
		if(rc.canSenseLocation(u2)) {
			if(rc.senseRobotAtLocation(u2)==null && rc.senseFlooding(u2)) {
				Pathfind.going(u2);
				MapLocation me3 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.NET_GUN, me3.directionTo(u2))) {
					rc.buildRobot(RobotType.NET_GUN, me3.directionTo(u2));
					
			}
		}
	}
	}
}
