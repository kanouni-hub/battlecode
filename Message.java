package test;

import battlecode.common.*;
	public class Message extends Global{
	static final int code=301997;
	static int l1=0;
	//utilisé par HQ pour que tout le monde sache ca position.
	public static void broadcastHQ() throws GameActionException {
			myHQ=rc.getLocation();
			int [] message = new int[7];
			message[0]=code;
			message[1]=1;
			message[4]=myHQ.x;
			message[5]=myHQ.y;
			if(rc.canSubmitTransaction(message, 5)) {
				rc.submitTransaction(message, 5);
			}
		}


	//Use it in FindHQ()
	public static void broadcastennemyHQ() throws GameActionException {
		if(opHQ!=null) {
			int [] message = new int[7];
			message[0]=code;
			message[1]=2;
			message[4]=opHQ.x;
			message[5]=opHQ.y;
			if(rc.canSubmitTransaction(message, 2)) {
				rc.submitTransaction(message, 2);
			}
			}
	}
	//HQ attaqué
	public static void ennemy() throws GameActionException {
		RobotInfo[] Robots=rc.senseNearbyRobots();
		for(RobotInfo rb:Robots) {
			if(rb.getTeam()!=rc.getTeam()) {
				 switch (rb.getType()){
				 case LANDSCAPER: 
					 int [] message = new int[7] ;
					 message[0]=code;
					 message[1]=10;
					 if(rc.canSubmitTransaction(message, 2)) {
							rc.submitTransaction(message, 2);
						}
					 
				 case DELIVERY_DRONE:
					 int[] message1= new int[7];
					 message1[0]=code;
					 message1[1]=11;
					 if(rc.canSubmitTransaction(message1, 2)) {
							rc.submitTransaction(message1, 2);
						}
					 
				}
			}
		}
		
	}
	//refinery 
	public static void broadcastrefinery() throws GameActionException {
		if(refinery!=null) {
			int [] message = new int[7];
			message[0]=code;
			message[1]=3;
			message[4]=refinery.x;
			message[5]=refinery.y;
			if(rc.canSubmitTransaction(message, 2)) {
				rc.submitTransaction(message, 2);
			}
			}
	}
	// look if there is landscaper
	public static void Wall() throws GameActionException {
		int c=0;
		for(RobotInfo robot:rc.senseNearbyRobots(1)) {
			if(robot.getTeam()==rc.getTeam()) {
				if(robot.getType()==RobotType.LANDSCAPER) {
					c=+1;
				}
			}
		}
		if(c==8) {
			int [] message = new int[7];
			message[0]=code;
			message[1]=4;
			message[4]=1;
			if(rc.canSubmitTransaction(message, 2)) {
				rc.submitTransaction(message, 2);
			}
			}
		else {
			int [] message = new int[7];
			message[0]=code;
			message[1]=4;
			message[4]=0;
			if(rc.canSubmitTransaction(message, 2)) {
				rc.submitTransaction(message, 2);
			}
			
		}
		}
	

	// prendre la location du HQ
	public static void HQLocation() throws GameActionException {
		for(int i=1; i<rc.getRoundNum();i++) {
			for(Transaction tx : rc.getBlock(i)) {
				int [] mess =tx.getMessage();
				if(mess[0]==code &&mess[1]==1) {
					myHQ= new MapLocation(mess[4],mess[5]);
				}
				
			}
		}
	}
	//prendre la location opHQ
	public static void OpHQLocation() throws GameActionException {
		for(int i=1; i<rc.getRoundNum();i++) {
			for(Transaction tx : rc.getBlock(i)) {
				int [] mess =tx.getMessage();
				if(mess[0]==code &&mess[1]==2) {
					opHQ= new MapLocation(mess[4],mess[5]);
				}
				
			}
		}
	}
	//prendre location de refinery
	public static void refineryLocation() throws GameActionException {
		for(int i=1; i<rc.getRoundNum();i++) {
			for(Transaction tx : rc.getBlock(i)) {
				int [] mess =tx.getMessage();
				if(mess[0]==code &&mess[1]==3) {
					 refinery= new MapLocation(mess[4],mess[5]);
				}
				
			}
		}
	}
	// Is there a wall built
	public static void builtwall() throws GameActionException {
		for(int i=1; i<rc.getRoundNum();i++) {
			for(Transaction tx : rc.getBlock(i)) {
				int [] mess =tx.getMessage();
				if(mess[0]==code &&mess[1]==4) {
					pr = mess[4];
				}
				
			}
		}
	}
	}


