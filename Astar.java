package examplefuncsplayer;

import battlecode.common.*;


public abstract class Astar extends RobotPlayer  {
	static MapLocation s;
	public static MapLocation[] ArrayLocation(MapLocation a) {
		
	return Miner.getadj(a);}
	
	public static void RAstar() throws GameActionException {
		   System.out.println("obj "+s);
			MapLocation me=rc.getLocation();
			MapLocation[] A=ArrayLocation(me);
			

		
			Direction dir = me.directionTo(s);
			MapLocation h=me.add(dir);
			if(h==s) { System.out.println("h "+h);
			System.out.println("s "+s);
			return ; }
			
			if(rc.canMove(dir) && !rc.senseFlooding(h)) {
				try {System.out.println("here");
					rc.move(dir);
				} catch (GameActionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}Clock.yield();
				//RAstar();	
				}
	// if the robot cant move initially	move
			
				
			//look if it can't move because of enemy robot ;
			
	else {		// if true detour ;
					for(MapLocation i :A) {
						if(rc.canMove(me.directionTo(i)) && (!rc.senseFlooding(me.add(me.directionTo(i))))) {
							System.out.println("here2");
							try {
								rc.move(me.directionTo(i));
							} catch (GameActionException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();}
							
							Clock.yield();
						//	RAstar();
							
					}}
				//If its something else ''elevation''
				 //If it's a Landcaper forceyour way in;
				       /* if(rc.getType()== RobotType.LANDSCAPER) {
					        while(rc.canDigDirt(dir) && (!rc.canMove(dir))) {try {
						        rc.digDirt(dir);} catch (GameActionException e) {e.printStackTrace();}RAstar();}
					        
					        if(rc.canMove(dir)&&(!rc.senseFlooding(h))) {
					        		try {rc.move(dir);} catch (GameActionException e) {
					        			e.printStackTrace();}Clock.yield();RAstar();}
					        
					        else {       if(rc.canDepositDirt(dir.opposite())) 
					        				{try {rc.depositDirt(dir);} catch (GameActionException e) 
					        				{ e.printStackTrace();}Clock.yield();RAstar();}*/
					
					   /* else {//IF IT'S NOT A LANDSCAPER DETOUR
					    	for(MapLocation i :A) {
								if(rc.canMove(me.directionTo(i))&&(!rc.senseFlooding(me.add(me.directionTo(i))))) {
									try {
										rc.move(dir);
									} catch (GameActionException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								Clock.yield();	
								RAstar();
									}*/
					    }
			RAstar();
					        }
				
			
		
			
		

	
			}
	
