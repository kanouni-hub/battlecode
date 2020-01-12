package examplefuncsplayer;

import java.util.ArrayList;

import battlecode.common.*;
import battlecode.world.control.PlayerControlProvider;

public class Nav extends Global {
	
	public static void first() throws GameActionException {
		myloc=rc.getLocation();
		radius=racine[squaredradius];
		
		updatemap();
		updateRobot(rc.senseNearbyRobots());
		System.out.println("HQ "+myHQ);
		System.out.println("firstmove");
		Miner.firstmove();
		
		
		System.out.println("first move done");
	}
	public static void upturn() throws GameActionException {
		myloc=rc.getLocation();
		if(havebeenpicked()) {
			first();
		}else {
		//	moved();
		}
	}
	public static void updatemap() {
		squaredradius=rc.getCurrentSensorRadiusSquared();
		radius=racine[squaredradius];
		int right=Math.min(radius,width-myloc.x-1);
		int top =Math.min(radius,height-myloc.y-1);
		int buttom=Math.min(radius,myloc.y);
		int left=Math.min(radius,myloc.x);
		int i=0;
		int j=-buttom;
		
		myloc=rc.getLocation();
		while(i<=right) {
			j=-buttom;
			while(carre[Math.abs(j)]>squaredradius-carre[i] && j<=top ) {j++;}
			while(carre[Math.abs(j)]<=squaredradius-carre[i] && j<=top ) {
				updatmapcase(new MapLocation(myloc.x+i,myloc.y+j));			
				j++;
			}i++;
		}
		
		i=1;
		 j=-buttom;
		while(i<=left) {
			j=-buttom;
			while(carre[Math.abs(j)]>squaredradius-carre[i] && j<=top ) {j++;}
			while(carre[Math.abs(j)]<=squaredradius-carre[i] && j<=top ) {
				updatmapcase(new MapLocation(myloc.x-i,myloc.y+j));				
				j++;
			}i++;
		}

		
	}
	
	
	
	
	public static boolean havebeenpicked() {
		System.out.println("picked test");
		return prevloc.isWithinDistanceSquared(myloc, 3);
	}
	
	public static void moved() {
		prevloc=myloc;
		myloc=rc.getLocation();
		squaredradius=rc.getCurrentSensorRadiusSquared();
		radius=racine[squaredradius];
		int right=Math.min(radius,width-myloc.x-1);
		int top =Math.min(radius,height-myloc.y-1);
		int buttom=Math.min(radius,myloc.y);
		int left=Math.min(radius,myloc.x);
		int i=-left+1;
		
		while(i<right) {
			updatmapcase(new MapLocation(myloc.x+i,myloc.y+Math.min(top,racine[squaredradius-carre[Math.abs(i)]])));
			updatmapcase(new MapLocation(myloc.x+i,myloc.y-Math.min(buttom,racine[squaredradius-carre[Math.abs(i)]])));		
			
			updatmapcase(new MapLocation(myloc.x+i,myloc.y-1+Math.min(top,racine[squaredradius-carre[Math.abs(i)]])));
			updatmapcase(new MapLocation(myloc.x+i,myloc.y+1-Math.min(buttom,racine[squaredradius-carre[Math.abs(i)]])));		
		i++;
		}
		
		int j=1;
		while(carre[j]<=squaredradius-carre[left]) {
			updatmapcase(new MapLocation(myloc.x-left,myloc.y+Math.min(top,j)));
			updatmapcase(new MapLocation(myloc.x-left,myloc.y-Math.min(buttom,j)));
			j++;		
		}				
		 j=1;
		while(carre[j]<=squaredradius-carre[right] ) {
			updatmapcase(new MapLocation(myloc.x+right,myloc.y+Math.min(top,j)));
			updatmapcase(new MapLocation(myloc.x+right,myloc.y-Math.min(buttom,j)));
		j++;			
		}
		updatmapcase(new MapLocation(myloc.x+right,myloc.y));
		updatmapcase(new MapLocation(myloc.x-left,myloc.y));		
	}
	
	public static ArrayList<MapLocation> getvisionLimit(){
		ArrayList<MapLocation> limits=new ArrayList<MapLocation>();
		myloc=rc.getLocation();
		squaredradius=rc.getCurrentSensorRadiusSquared();
		radius=racine[squaredradius];
		int right=Math.min(radius,width-myloc.x-1);
		int top =Math.min(radius,height-myloc.y-1);
		int buttom=Math.min(radius,myloc.y-1);
		int left=Math.min(radius,myloc.x-1);
		
	
	int i=-left+1;
		
	
		while(i<right) {
				
				
				limits.add(new MapLocation(myloc.x+i,myloc.y+Math.min(top,racine[squaredradius-carre[Math.abs(i)]])));
				limits.add(new MapLocation(myloc.x+i,myloc.y-Math.min(buttom,racine[squaredradius-carre[Math.abs(i)]])));
		

		i++;
		}
		
	
		
		int j=1;
		while(carre[j]<=squaredradius-carre[left]) {
			
	limits.add(new MapLocation(myloc.x-left,myloc.y+Math.min(top,j)));
	limits.add(new MapLocation(myloc.x-left,myloc.y-Math.min(buttom,j)));				
		j++;			
		}
		
		
		 j=1;
		while(carre[j]<=squaredradius-carre[right] ) {
			limits.add(new MapLocation(myloc.x+right,myloc.y+Math.min(top,j)));
			limits.add(new MapLocation(myloc.x+right,myloc.y-Math.min(buttom,j)));

		j++;
			
		}
		limits.add(new MapLocation(myloc.x+right,myloc.y));
		limits.add(new MapLocation(myloc.x-left,myloc.y));
		
		return limits;
	}
	public static ArrayList<MapLocation> getSenseadjacent(MapLocation s){
		ArrayList<MapLocation> adj=new ArrayList<MapLocation>();
		if(rc.canSenseLocation(new MapLocation(s.x-1,s.y+1))) {
			adj.add(new MapLocation(s.x-1,s.y+1));
		}
		if(rc.canSenseLocation(new MapLocation(s.x,s.y+1))) {
			adj.add(new MapLocation(s.x,s.y+1));
		}
		if(rc.canSenseLocation(new MapLocation(s.x+1,s.y+1))) {
			adj.add(new MapLocation(s.x+1,s.y+1));
		}
		
		if(rc.canSenseLocation(new MapLocation(s.x-1,s.y))) {
			adj.add(new MapLocation(s.x-1,s.y));
		}
		
		if(rc.canSenseLocation(new MapLocation(s.x+1,s.y))) {
			adj.add(new MapLocation(s.x+1,s.y));
		}
		
		if(rc.canSenseLocation(new MapLocation(s.x-1,s.y-1))) {
			adj.add(new MapLocation(s.x-1,s.y-1));
		}
		if(rc.canSenseLocation(new MapLocation(s.x,s.y-1))) {
			adj.add(new MapLocation(s.x,s.y-1));
		}
		if(rc.canSenseLocation(new MapLocation(s.x+1,s.y-1))) {
			adj.add(new MapLocation(s.x+1,s.y-1));
		}
		
		return adj;
	}
	public static ArrayList<MapLocation> getpossiblSenseeadj(MapLocation s){
		ArrayList<MapLocation> adj=new ArrayList<MapLocation>();
		if(rc.canSenseLocation(s)) {
		if(rc.canSenseLocation(new MapLocation(s.x-1,s.y+1))) {
			if(Math.abs(map[s.x-1][s.y+1]-map[s.x][s.y])<4 && !floodmap[s.x-1][s.y+1] ) {
				adj.add(new MapLocation(s.x-1,s.y+1));
			}	
		}
		if(rc.canSenseLocation(new MapLocation(s.x,s.y+1))) {
			if(Math.abs(map[s.x][s.y+1]-map[s.x][s.y])<4 && !floodmap[s.x][s.y+1]) {
			adj.add(new MapLocation(s.x,s.y+1));}
		}
		if(rc.canSenseLocation(new MapLocation(s.x+1,s.y+1))) {
			if(Math.abs(map[s.x+1][s.y+1]-map[s.x][s.y])<4 && !floodmap[s.x+1][s.y+1]) {
			adj.add(new MapLocation(s.x+1,s.y+1));}
		}
		
		if(rc.canSenseLocation(new MapLocation(s.x-1,s.y))) {
			if(Math.abs(map[s.x-1][s.y]-map[s.x][s.y])<4 && !floodmap[s.x-1][s.y] ) {
			adj.add(new MapLocation(s.x-1,s.y));}
		}
		
		if(rc.canSenseLocation(new MapLocation(s.x+1,s.y))) {
			if(Math.abs(map[s.x+1][s.y]-map[s.x][s.y])<4 && !floodmap[s.x+1][s.y]) {
			adj.add(new MapLocation(s.x+1,s.y));}
		}
		
		if(rc.canSenseLocation(new MapLocation(s.x-1,s.y-1))) {
			if(Math.abs(map[s.x-1][s.y-1]-map[s.x][s.y])<4 && !floodmap[s.x-1][s.y-1]) {
			adj.add(new MapLocation(s.x-1,s.y-1));}
		}
		if(rc.canSenseLocation(new MapLocation(s.x,s.y-1))) {
			if(Math.abs(map[s.x][s.y-1]-map[s.x][s.y])<4 && !floodmap[s.x][s.y-1]) {
			adj.add(new MapLocation(s.x,s.y-1));}
		}
		if(rc.canSenseLocation(new MapLocation(s.x+1,s.y-1))) {
			if(Math.abs(map[s.x+1][s.y-1]-map[s.x][s.y])<4 && !floodmap[s.x+1][s.y-1]) {
			adj.add(new MapLocation(s.x+1,s.y-1));}
		}
		}
		
		return adj;
	}
	public static void updatmapcase(MapLocation pos) {
		int soupcur=0;
		try {
			soupcur=rc.senseSoup(pos);
			if(soupcur>0) {
				soup.put(pos,soupcur);
			}
			floodmap[pos.x][pos.y]=rc.senseFlooding(pos);
			map[pos.x][pos.y]=rc.senseElevation(pos);
		} catch (GameActionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void  updateRobot(RobotInfo[] nearby) {
		for(RobotInfo rb:nearby) {
			 switch (rb.getType()) {
             case HQ:                 if(rc.getTeam()==rb.getTeam()) {myHQ=rb.getLocation();mybuild.put(rb.getLocation(),RobotType.HQ);
             }else {opHQ=rb.getLocation();
             //doSYME()
             }
            	 break;
            
             case REFINERY:                     break;
             case VAPORATOR:                  break;
             case DESIGN_SCHOOL:            break;
             case FULFILLMENT_CENTER:  break;
             case LANDSCAPER:                 break;
             case DELIVERY_DRONE:          break;
             case NET_GUN:                       break;
             case MINER:                           break;
         }
		}
	}
	public RobotInfo closest(RobotInfo[] nearby) {
		if(nearby.length==0) {return null;}
		RobotInfo mindis=nearby[0];
		for(int i=1;i<nearby.length;i++) {
			if(myloc.distanceSquaredTo(mindis.getLocation())>myloc.distanceSquaredTo(nearby[i].getLocation())) {
				mindis=nearby[i];
			}
		}
		return mindis;
	}
	public static ArrayList<MapLocation> getvision(){
		ArrayList<MapLocation> vision=new ArrayList<MapLocation>();
		myloc=rc.getLocation();
		squaredradius=rc.getCurrentSensorRadiusSquared();
		radius=racine[squaredradius];
		int right=Math.min(radius,width-myloc.x-1);
		int top =Math.min(radius,height-myloc.y-1);
		int buttom=Math.min(radius,myloc.y-1);
		int left=Math.min(radius,myloc.x-1);	
		int i=1,j=1;
		 while(carre[i]<=carre[right]) {
			   j=1;
			   while(carre[j] <=squaredradius-carre[i]) {
				   vision.add(new MapLocation(myloc.x+i,myloc.y+j));
				   j++;
			   }
			
			   i++;
		   }

		 if(left==right && top==buttom) {
			 for(MapLocation e:doXsyme(doYsyme(vision))){
				 vision.add(e);
			 }
		 }else  {
			  i=1;j=1;
			 while(carre[i]<=carre[right]) {
				   j=1;
				   while(carre[j] <=squaredradius-carre[i] &&j<=buttom) {
					   vision.add(new MapLocation(myloc.x+i,myloc.y-j));
					   j++;
				   }i++;
			   }
			 
			 i=1;j=1;
			 while(carre[i]<=carre[left]) {
				   j=1;
				   while(carre[j] <=squaredradius-carre[i] &&j<=top) {
					   vision.add(new MapLocation(myloc.x-i,myloc.y+j));
					   j++;
				   }i++;
			   }
			 
			 i=1;j=1;
			 while(carre[i]<=carre[left]) {
				   j=1;
				   while(carre[j] <=squaredradius-carre[i] &&j<=buttom) {
					   vision.add(new MapLocation(myloc.x-i,myloc.y-j));
					   j++;
				   }i++;
			   }
			 
		 }
		 for(int k=-left;k<=right;k++) {
			 vision.add(new MapLocation(myloc.x+k,myloc.y));
		 }
		 for(int k=-buttom;k<=top;k++) {
			 vision.add(new MapLocation(myloc.x,myloc.y+k));
		 }
		
		return vision;
	}
	
	private static ArrayList<MapLocation> doXsyme(ArrayList<MapLocation> ini){
		ArrayList<MapLocation> sym=new ArrayList<MapLocation>();
		for(MapLocation node :ini) {
			sym.add(node);
			sym.add(new MapLocation(2*myloc.x-node.x,node.y));
		}
		return sym;
	}
	private static ArrayList<MapLocation> doYsyme(ArrayList<MapLocation> ini){
		ArrayList<MapLocation> sym=new ArrayList<MapLocation>();
		for(MapLocation node :ini) {
			sym.add(node);
			sym.add(new MapLocation(node.x,2*myloc.y-node.y));
		}
		return sym;
	}
	

}
