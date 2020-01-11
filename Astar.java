package examplefuncsplayer;
import battlecode.common.*;
import java.util.*;

public abstract class Astar extends RobotPlayer  {
	static MapLocation s;
	public ArrayList<Integer> v;
	 Queue<Direction> Moves = new LinkedList<>();
	public ArrayList<MapLocation> ArrayLocation(MapLocation a) {
		
	return new ArrayList<MapLocation>();
	}
	public int Cost(MapLocation b) {
		int eps = 2; 
		int val= 1+eps*(b.distanceSquaredTo(s));
		return val;}
	public MapLocation ChildNode(MapLocation h) {
		 ArrayList<MapLocation> A = ArrayLocation(h);
		for (MapLocation i : A) {v.add(Cost(i));   }
		for (MapLocation j : A) {
			if(Cost(j)==Collections.min(v)) { return j ;}
		}return null;}

	public void Path(MapLocation c) {
		MapLocation next = ChildNode(c);
		if (next.equals(s) ) { return ;}
			
		Moves.add(c.directionTo(next));
		 Path(ChildNode(next));
		}
     public void RAstar() {
         MapLocation me = rc.getLocation();
    	 Path(me);
    	 while( Moves.size() != 0)  {
    	 Direction Goto= Moves.remove();
    	
    	 if (rc.canMove(Goto)) {
    	 try {
			rc.move(Goto);
			Clock.yield();
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 RAstar();}}
return ;}    	 
    	 
    	 
    	 }


