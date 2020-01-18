package examplefuncsplayer;

import java.util.LinkedList;

import battlecode.common.*;

public class Pathfind extends Global{

	static boolean going(MapLocation goal) throws GameActionException {
		System.out.println("going to "+goal);
		int nbrpas=0;
		myloc=rc.getLocation();
		int maxpas=(int)Math.sqrt(myloc.distanceSquaredTo(goal));
		maxpas=maxpas*3;		
		System.out.println(" pasmx "+maxpas);
		boolean moved=false;
		boolean foundborder=false;
		LinkedList<MapLocation> visited3=new LinkedList<MapLocation>();
		visited3.clear();
		visited3.add(myloc);
		visited3.add(myloc);
		visited3.add(myloc);
		Direction prevdir=Direction.CENTER;
		int co=0;
		while(!rc.getLocation().isAdjacentTo(goal)) {
		myloc=rc.getLocation();
		Direction forward=myloc.directionTo(goal);
		while(!rc.isReady()) {}
		if(co!=5 && rc.canMove(forward) && !visited3.contains(myloc.translate(forward.getDeltaX(),forward.getDeltaY())) && !rc.senseFlooding(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
			moved=true;
			rc.move(forward);
			nbrpas++;
			prevdir=forward.opposite();
			visited3.pop();
			visited3.add(rc.getLocation());
			Clock.yield();
		
		}
		else {
			co=0;
			while(!moved && !foundborder && !prevdir.equals(forward)) {
				forward=forward.rotateLeft();
				co++;
				if(rc.canMove(forward) && !rc.senseFlooding(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {

					moved=true;
					rc.move(forward);
					nbrpas++;
					prevdir=forward.opposite();

					visited3.pop();
					visited3.add(rc.getLocation());
					Clock.yield();
					break;
				}else 	if(!rc.onTheMap(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
				foundborder=true;
			}
			}
			if(prevdir.equals(forward)) {prevdir=Direction.CENTER; foundborder=!foundborder;}
			while(!moved && foundborder ) {
				forward=forward.rotateRight();
				if(rc.canMove(forward) && !rc.senseFlooding(myloc.translate(forward.getDeltaX(),forward.getDeltaY()))) {
					moved=true;
					rc.move(forward);
					nbrpas++;
					prevdir=forward.opposite();
					visited3.pop();
					visited3.add(rc.getLocation());
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
}
