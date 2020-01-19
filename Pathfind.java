package test;

import java.util.LinkedList;

import battlecode.common.*;

public class Pathfind extends Global{

	public static boolean going(MapLocation goal) throws GameActionException {
		System.out.println("going to "+goal);
		int nbrpas=0;
		myloc=rc.getLocation();
		int maxpas=(int)Math.sqrt(myloc.distanceSquaredTo(goal));
		maxpas=maxpas*3;		
		System.out.println(" pasmx "+maxpas);
		boolean moved=false;
		boolean foundborder=false;
		
		
		Direction prevdir=Direction.CENTER;
		while(!rc.getLocation().isAdjacentTo(goal)) {
		myloc=rc.getLocation();
		Direction forward=dirto(goal);


		while(!rc.isReady()) {}
		if(!forward.equals(prevdir) && rc.canMove(forward) && !rc.senseFlooding(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
			moved=true;
			rc.move(forward);
			nbrpas++;
		//	prevdir=forward.opposite();
			prevdir=Direction.CENTER;
			
			Clock.yield();
		
		}
		else {
			while(!moved && !foundborder ) {
				forward=forward.rotateLeft();
				System.out.println("trying "+forward);
				if(rc.canMove(forward) && !rc.senseFlooding(myloc.translate(forward.getDeltaX(),forward.getDeltaY())) && !prevdir.equals(forward)) {

					moved=true;
					System.out.println("picked");
					rc.move(forward);
					nbrpas++;
					prevdir=forward.opposite();

					
					Clock.yield();
					break;
				}else 	if(!rc.onTheMap(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
				foundborder=true;
			}
			}
		//	if(prevdir.equals(forward)) {prevdir=Direction.CENTER; foundborder=!foundborder;}
			while(!moved && foundborder ) {
				forward=forward.rotateRight();
				System.out.println("trynig2 "+forward);
				if(rc.canMove(forward) && !rc.senseFlooding(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
					moved=true;
					System.out.println("pick2 ");
					rc.move(forward);
					nbrpas++;
					prevdir=forward.opposite();
					
					Clock.yield();
					break;
				}else 	if(!rc.onTheMap(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
				foundborder=false;
			}
			}
			
			moved=false;
			
			
		}	if(nbrpas>maxpas) {
			return false;
		}
		}
		return true;
	}
	static Direction dirto(MapLocation goal) {
		myloc=rc.getLocation();
		Direction nor=myloc.directionTo(goal);
		if(nor.getDeltaX()*nor.getDeltaY()!=0) {
			return nor;
		}
		if(myloc.x==goal.x ||myloc.y==goal.y) {
			return nor;
		}
		if(myloc.x>goal.x) {
			if(myloc.y>goal.y) {
				nor=Direction.SOUTHWEST;
			}
			else {nor=Direction.NORTHWEST;
		}
			}else {
				if(myloc.y>goal.y) {
					nor=Direction.SOUTHEAST;
				}
				else {nor=Direction.NORTHEAST;
			}
		}
		return nor;
	}
	
	

}

