package test;
import battlecode.common.*;
public class Buildbaby extends Global {
	public static void landscaperkill() throws GameActionException {
		int moved=0;
		 Direction  dir = rc.getLocation().directionTo(opHQ) ;
		for(int i=0;i<2;i++) {
			while(!rc.isReady()) {Clock.yield();}
		MapLocation me = rc.getLocation();
		 dir =me.directionTo(opHQ);
		System.out.println("direction"+dir.opposite());
		MapLocation h = me.add(dir.opposite());
		System.out.println("location:" +h);
		if(rc.canMove(dir.opposite())&&!rc.senseFlooding(h)){
			System.out.println("i can move");
		rc.move(dir.opposite());
			Clock.yield();}}
		while(rc.getTeamSoup()<150) {Clock.yield();}
		while(!rc.isReady()) {Clock.yield();}
		System.out.println("first tring "+rc.getTeamSoup()+"d "+dir.opposite());
			if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, dir.opposite())) {
				System.out.println("building");
				rc.buildRobot(RobotType.DESIGN_SCHOOL, dir.opposite());
				return;
				
				}
	
		else {
			System.out.println("else");
			for(Direction u : Direction.allDirections()) {
				while(!rc.isReady()) {Clock.yield();}
			MapLocation me1 = rc.getLocation();	
			MapLocation h1 = me1.add(u);
				if(rc.canMove(u)&&!rc.senseFlooding(h1)){
					System.out.println("i can walk");
					rc.move(u);
					 moved++;
					 while(!rc.isReady()) {Clock.yield();}
						if(moved>=2) {
							while(rc.getTeamSoup()<150) {Clock.yield();}
							System.out.println("sec tring "+u);
						if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, u)) {
							rc.buildRobot(RobotType.DESIGN_SCHOOL, u);
							return ;
							}
						else{
							for(Direction u1 : Direction.allDirections()) {
								while(rc.getTeamSoup()<150) {Clock.yield();}
								System.out.println("thrd tring "+u1);
								if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, u1)) {
									rc.buildRobot(RobotType.DESIGN_SCHOOL, u1);
									return ;}
							}
						break;}
						
				}
			}
		
		}
	return;}
	}
	
	public static void landscaperwall() throws GameActionException {
		MapLocation me = rc.getLocation();
		MapLocation[] u = { myHQ.translate(3,0), myHQ.translate(0,3),myHQ.translate(0,-3), myHQ.translate(-3,0)};
		for(MapLocation h:u) {
			System.out.println("trying "+h);
			while(!rc.isReady()) {Clock.yield();}
		if(rc.canSenseLocation(h)) {
			if(Pathfind.going(h)) {
				while(rc.getTeamSoup()<150) {Clock.yield();}
			if(rc.senseRobotAtLocation(h)==null &&!rc.senseFlooding(h)) {
				if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, me.directionTo(h))) {
					rc.buildRobot(RobotType.DESIGN_SCHOOL, me.directionTo(h));
					Clock.yield();
					return;
			}
		}
	}
			
		}	
	}
		for(Direction ds:Direction.allDirections()) {
			while(rc.getTeamSoup()<150) {Clock.yield();}
			if(rc.canBuildRobot(RobotType.DESIGN_SCHOOL, ds)) {
				rc.buildRobot(RobotType.DESIGN_SCHOOL, ds);
				Clock.yield();
				return;
		}
		}

	}
		
	public static void netdefence() throws GameActionException {
		
	
		MapLocation me = rc.getLocation();
		MapLocation u = myHQ.translate(4,0);
		if(rc.canSenseLocation(u)) {
			
			if(rc.senseRobotAtLocation(u)==null && !rc.senseFlooding(u)) {
				Pathfind.going(u);
				me=rc.getLocation();
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
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
				while(rc.getTeamSoup()<150) {Clock.yield();}
				if(rc.canBuildRobot(RobotType.FULFILLMENT_CENTER, me3.directionTo(u2))) {
					rc.buildRobot(RobotType.FULFILLMENT_CENTER, me3.directionTo(u2));
					Clock.yield();
					return;
			}
		}
	}
	}
	public static void Refinery() throws GameActionException {
		MapLocation me = rc.getLocation();
		MapLocation[] u = { me.translate(2,0), me.translate(0,2),me.translate(0,-2), me.translate(-2,0)};
		for(MapLocation h:u) {
			System.out.println("trying "+h);
			while(!rc.isReady()) {Clock.yield();}
		if(rc.canSenseLocation(h)) {
			if(Pathfind.going(h)) {
				while(rc.getTeamSoup()<150) {Clock.yield();}
			if(rc.senseRobotAtLocation(h)==null &&!rc.senseFlooding(h)) {
				if(rc.canBuildRobot(RobotType.REFINERY, me.directionTo(h))) {
					rc.buildRobot(RobotType.REFINERY, me.directionTo(h));
					refinery=me.add(me.directionTo(h));
					Message.broadcastrefinery();
					Clock.yield();
					break;
					
				
			}
		}
	}
			
		}	
	}
	
	}
		
	}
