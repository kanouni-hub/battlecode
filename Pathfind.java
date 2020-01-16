package examplefuncsplayer;

import battlecode.common.*;

public class Pathfind extends Global{

	static void going(MapLocation goal) throws GameActionException {
		
		boolean moved=false;
		boolean foundborder=false;
		while(!rc.getLocation().isAdjacentTo(goal)) {
		myloc=rc.getLocation();
		Direction forward=myloc.directionTo(goal);
		if(rc.canMove(forward)) {
			moved=true;
			rc.move(forward);
			Clock.yield();

		}
		else {
			int co=0;
			while(!moved && !foundborder) {
				forward=forward.rotateLeft();
				co++;
				if(rc.canMove(forward)) {
					System.out.println(co+" left "+forward);

					moved=true;
					rc.move(forward);
					
					Clock.yield();
					break;
				}else 	if(!rc.onTheMap(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
					System.out.println("border");
				foundborder=true;
			}
			}
			while(!moved && foundborder) {
				forward=forward.rotateRight();
				System.out.println("right");
				if(rc.canMove(forward)) {
					moved=true;
					rc.move(forward);
					Clock.yield();
					break;
				}else 	if(!rc.onTheMap(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
					System.out.println("border");
				foundborder=false;
			}
			}
			moved=false;
		}
		}
	}
}
