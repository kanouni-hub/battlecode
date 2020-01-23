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

	public static void attackennemy() throws GameActionException {
		if(rc.getDirtCarrying()!=0) {
			int [] message = new int[7];
			message[0]=code;
			message[1]=12;
			message[4]=1;
			if(rc.canSubmitTransaction(message, 5)) {
				rc.submitTransaction(message, 5);
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
		for(RobotInfo robot:rc.senseNearbyRobots(2)) {
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
	//get coordination
	public static void Coordination() throws GameActionException {
		if(rc.getTeamSoup()>=220) {
			int[] message= new int[7];
			message[0]=code;
			message[1]=5;
			message[4]=1;
			if(rc.canSubmitTransaction(message, 2)) {
				rc.submitTransaction(message, 2);
			}
		}
	}
	
	// if the design school is under attaque
	public static void nodesign() throws GameActionException {
		if(rc.getDirtCarrying()>=5) {
			MapLocation Designer = rc.getLocation();
			int[] message= new int[7];
			message[0]=code;
			message[1]=6;
			message[4]=1;
			message[5]=Designer.x;
			message[6]=Designer.y;
			if(rc.canSubmitTransaction(message, 2)) {
				rc.submitTransaction(message, 2);
			}
		}
		else {
			int[] message= new int[7];
			message[0]=code;
			message[1]=6;
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
					return;
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
					return;
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
					 return;
				}
				
			}}
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
	//getcoordination 
	public static void getCoordination() throws GameActionException {
		int i = rc.getRoundNum();
		for(Transaction tx : rc.getBlock(i) ) {
				int [] mess = tx.getMessage();
				if(mess[0]==code &&mess[1]==5) {
					build = mess[4];
					break;
				}
				
			}
		}
	//message urgent 
	public static void urgentmess() throws GameActionException {
		int i = rc.getRoundNum();
		for(Transaction tx : rc.getBlock(i) ) {
				int [] mess = tx.getMessage();
				if(mess[0]==code &&mess[1]==12) {
					urgent = mess[4];
					break;
				}
				
			}
	}
	//get message about the attaque on the design school 
	public static MapLocation getdesign() throws GameActionException {
		int i = rc.getRoundNum();
		MapLocation goal;
		int x=0 ;
		int y=0;
		for(Transaction tx : rc.getBlock(i) ) {
				int [] mess = tx.getMessage();
				if(mess[0]==code &&mess[1]==6) {
					design = mess[4];
					x=mess[5];
					y=mess[6];
					break;
				}
				
			}
		goal=new MapLocation(x,y);
		return(goal);}
	}
	
	


