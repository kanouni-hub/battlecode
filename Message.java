package examplefuncsplayer;
import battlecode.common.*;
public class Message extends Global{
static final int code=301997;
static int l1=0;
//utilisé par HQ pour que tout le monde sache ca position.
public void broadcastHQ() throws GameActionException {
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
public void broadcastennemyHQ() throws GameActionException {
	if(opHQ!=null) {
		int [] message = new int[7];
		message[0]=code;
		message[1]=2;
		message[4]=opHQ.x;
		message[5]=opHQ.y;
		if(rc.canSubmitTransaction(message, 5)) {
			rc.submitTransaction(message, 5);
		}
		}
}
//HQ attaqué
public void ennemy() throws GameActionException {
	RobotInfo[] Robots=rc.senseNearbyRobots();
	for(RobotInfo rb:Robots) {
		if(rb.getTeam()!=rc.getTeam()) {
			 switch (rb.getType()){
			 case LANDSCAPER: 
				 int [] message = new int[7] ;
				 message[0]=code;
				 message[1]=10;
				 if(rc.canSubmitTransaction(message, 5)) {
						rc.submitTransaction(message, 5);
					}
				 
			 case DELIVERY_DRONE:
				 int[] message1= new int[7];
				 message1[0]=code;
				 message1[1]=11;
				 if(rc.canSubmitTransaction(message1, 5)) {
						rc.submitTransaction(message1, 5);
					}
				 
			}
		}
	}
	
}
public void HQLocation() throws GameActionException {
	for(int i=1; i<rc.getRoundNum();i++) {
		for(Transaction tx : rc.getBlock(i)) {
			int [] mess =tx.getMessage();
			if(mess[0]==code &&mess[1]==1) {
				myHQ= new MapLocation(mess[4],mess[5]);
			}
			
		}
	}
}
public void OpHQLocation() throws GameActionException {
	for(int i=1; i<rc.getRoundNum();i++) {
		for(Transaction tx : rc.getBlock(i)) {
			int [] mess =tx.getMessage();
			if(mess[0]==code &&mess[1]==2) {
				opHQ= new MapLocation(mess[4],mess[5]);
			}
			
		}
	}
}
}
