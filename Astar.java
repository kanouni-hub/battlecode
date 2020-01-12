package examplefuncsplayer;
import battlecode.common.*;

public abstract class Astar extends RobotPlayer  {
	static MapLocation s;
	public static MapLocation[] ArrayLocation(MapLocation a) {
		
	return new MapLocation[8];}
	
	public static void RAstar() throws GameActionException {
		   
			MapLocation me=rc.getLocation();
			MapLocation[] A=ArrayLocation(me);

		
			Direction dir = me.directionTo(s);
			MapLocation h=me.add(dir);
			if(rc.canMove(dir)) {
				try {
					rc.move(dir);
				} catch (GameActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Clock.yield();	}
	// if the robot cant move initially	move
			
				
			//look if it can't move because of enemy robot ;
			
	else {		// if true detour ;
					if(rc.senseRobotAtLocation(h)!=null || rc.senseFlooding(h)) {
	
					for(MapLocation i :A) {
						if(rc.canMove(me.directionTo(i))) {
							try {
								rc.move(dir);
							} catch (GameActionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							Clock.yield();
							break;}
					}}
				//If its something else ''elevation''
				else { //If it's a Landcaper forceyour way in;
				        if(rc.getType()== RobotType.LANDSCAPER) {
					        while(rc.canDigDirt(dir) && (!rc.canMove(dir))) {try {
						        rc.digDirt(dir);} catch (GameActionException e) {e.printStackTrace();}}
					        
					        if(rc.canMove(dir)) {
					        		try {rc.move(dir);} catch (GameActionException e) {
					        			e.printStackTrace();}Clock.yield();}
					        
					        else {       if(rc.canDepositDirt(dir.opposite())) 
					        				{try {rc.depositDirt(dir);} catch (GameActionException e) 
					        				{ e.printStackTrace();}Clock.yield();}
					
					    else {//IF IT'S NOT A LANDSCAPER DETOUR
					    	for(MapLocation i :A) {
								if(rc.canMove(me.directionTo(i))) {
									try {
										rc.move(dir);
									} catch (GameActionException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
									Clock.yield();
									break;}
					    }
					        }}
				
			
		}
			
		}

	}}}

	


