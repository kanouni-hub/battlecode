package examplefuncsplayer;
import battlecode.common.*;
public class Buildbaby extends Global {
	public static void landscaperkill() throws GameActionException {
		for(int i=0;i<3;i++) {
		MapLocation me = rc.getLocation();
		Direction dir =me.directionTo(opHQ);
		System.out.println("direction"+dir.opposite());
		MapLocation h = me.add(dir.opposite());
		System.out.println("location:" +h);
		if(rc.canMove(dir.opposite())&&!rc.senseFlooding(h)){
		rc.move(dir.opposite());
			Clock.yield();
			if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, dir.opposite())) {
				rc.buildRobot(RobotType.DESIGN_SCHOOL, dir.opposite());
				
				}
	}
		else {
			for(Direction u : Direction.allDirections()) {
			MapLocation me1 = rc.getLocation();	
			MapLocation h1 = me1.add(u);
				if(rc.canMove(u)&&!rc.senseFlooding(h1)){
					rc.move(u);
						Clock.yield();
						if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, u)) {
							rc.buildRobot(RobotType.DESIGN_SCHOOL, u);
							break ;
							}
				}
			}
		
		}
	}
	}
	public static void landscaperwall() throws GameActionException {
		MapLocation me = rc.getLocation();
		MapLocation u = myHQ.translate(3,0);
		System.out.println("u"+u);
		if(rc.canSenseLocation(u)) {
			Pathfind.going(u);
			if(rc.senseRobotAtLocation(u)==null &&!rc.senseFlooding(u)) {
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me.directionTo(u))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me.directionTo(u));
					Clock.yield();
					return;
			}
		}
	}
		MapLocation u1 = myHQ.translate(0,3);
		if(rc.canSenseLocation(u1)) {
			if(rc.senseRobotAtLocation(u1)==null && !rc.senseFlooding(u1)) {
				Pathfind.going(u1);
				MapLocation me1 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me1.directionTo(u1))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me1.directionTo(u1));
					Clock.yield();
					return;
					
			}
		}
	}
		MapLocation u11 = myHQ.translate(0,-3);
		if(rc.canSenseLocation(u11)) {
			if(rc.senseRobotAtLocation(u11)==null && !rc.senseFlooding(u11)) {
				Pathfind.going(u11);
				MapLocation me2 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me2.directionTo(u11))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me2.directionTo(u11));
					Clock.yield();
					return;
			}
		}
	}
		
		MapLocation u2 = myHQ.translate(-3,0);
		if(rc.canSenseLocation(u2)) {
			if(rc.senseRobotAtLocation(u2)==null && !rc.senseFlooding(u2)) {
				Pathfind.going(u2);
				MapLocation me3 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me3.directionTo(u2))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me3.directionTo(u2));
					Clock.yield();
					return;
			}
		}
	}}
		
	public static void netdefence() throws GameActionException {
		
	
		MapLocation me = rc.getLocation();
		MapLocation u = myHQ.translate(4,0);
		if(rc.canSenseLocation(u)) {
			if(rc.senseRobotAtLocation(u)==null && !rc.senseFlooding(u)) {
				Pathfind.going(u);
				if(rc.canBuildRobot(RobotType.NET_GUN, me.directionTo(u))) {
					rc.buildRobot(RobotType.NET_GUN, me.directionTo(u));
					Clock.yield();
				
			}
		}
	}
		MapLocation u1 = myHQ.translate(0,4);
		if(rc.canSenseLocation(u1)) {
			if(rc.senseRobotAtLocation(u1)==null && !rc.senseFlooding(u1)) {
				Pathfind.going(u1);
				MapLocation me1 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.NET_GUN, me1.directionTo(u1))) {
					rc.buildRobot(RobotType.NET_GUN, me1.directionTo(u1));
					Clock.yield();
			}
		}
	}
		MapLocation u11 = myHQ.translate(0,-4);
		if(rc.canSenseLocation(u11)) {
			if(rc.senseRobotAtLocation(u11)==null && !rc.senseFlooding(u11)) {
				Pathfind.going(u11);
				MapLocation me2 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.NET_GUN, me2.directionTo(u11))) {
					rc.buildRobot(RobotType.NET_GUN, me2.directionTo(u11));
					Clock.yield();
			}
		}
	}
		
		MapLocation u2 = myHQ.translate(-4,0);
		if(rc.canSenseLocation(u2)) {
			if(rc.senseRobotAtLocation(u2)==null && !rc.senseFlooding(u2)) {
				Pathfind.going(u2);
				MapLocation me3 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.NET_GUN, me3.directionTo(u2))) {
					rc.buildRobot(RobotType.NET_GUN, me3.directionTo(u2));
					Clock.yield();
			}
		}
	}
	}
	
		
	public static void fullcenter() throws GameActionException {
		
	
		MapLocation me = rc.getLocation();
		MapLocation u = myHQ.translate(3,1);
		if(rc.canSenseLocation(u)) {
			if(rc.senseRobotAtLocation(u)==null && !rc.senseFlooding(u)) {
				Pathfind.going(u);
				if(rc.canBuildRobot(RobotType.FULFILLMENT_CENTER, me.directionTo(u))) {
					rc.buildRobot(RobotType.FULFILLMENT_CENTER, me.directionTo(u));
					Clock.yield();
					return;
				
			}
		}
	}
		MapLocation u1 = myHQ.translate(1,3);
		if(rc.canSenseLocation(u1)) {
			if(rc.senseRobotAtLocation(u1)==null && !rc.senseFlooding(u1)) {
				Pathfind.going(u1);
				MapLocation me1 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.FULFILLMENT_CENTER, me1.directionTo(u1))) {
					rc.buildRobot(RobotType.FULFILLMENT_CENTER, me1.directionTo(u1));
					Clock.yield();
					return;
			}
		}
	}
		MapLocation u11 = myHQ.translate(1,-3);
		if(rc.canSenseLocation(u11)) {
			if(rc.senseRobotAtLocation(u11)==null && !rc.senseFlooding(u11)) {
				Pathfind.going(u11);
				MapLocation me2 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.FULFILLMENT_CENTER, me2.directionTo(u11))) {
					rc.buildRobot(RobotType.FULFILLMENT_CENTER, me2.directionTo(u11));
					Clock.yield();
					return;
			}
		}
	}
		
		MapLocation u2 = myHQ.translate(-3,1);
		if(rc.canSenseLocation(u2)) {
			if(rc.senseRobotAtLocation(u2)==null && !rc.senseFlooding(u2)) {
				Pathfind.going(u2);
				MapLocation me3 = rc.getLocation();
				if(rc.canBuildRobot(RobotType.FULFILLMENT_CENTER, me3.directionTo(u2))) {
					rc.buildRobot(RobotType.FULFILLMENT_CENTER, me3.directionTo(u2));
					Clock.yield();
					return;
			}
		}
	}
	}}
