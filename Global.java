package examplefuncsplayer;

import java.util.ArrayList;
import java.util.Hashtable;

import battlecode.common.*;

public class Global {
	public static final RobotType[] buildingtype= {RobotType.HQ,RobotType.DESIGN_SCHOOL,RobotType.FULFILLMENT_CENTER,RobotType.REFINERY,RobotType.NET_GUN,RobotType.VAPORATOR};
	public static RobotController rc;
	public static int[][] map;
	public static boolean [][] floodmap;
	public static MapLocation myloc;
	public static int[] carre= {0,1,4,9,16,25,36,49,64,81,100};
	public static int[] racine= {0,1,1,1,2,2,2,2,2,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,6,7};
	public static int squaredradius;
	public static int radius;
	public static int height;
	public static int width;
	public static MapLocation prevloc;
	public static boolean hasbeenpicked=false;
	public static Hashtable<MapLocation,Integer> soup=new Hashtable<MapLocation,Integer>();
	public static Hashtable<MapLocation,RobotType> mybuild=new Hashtable<MapLocation,RobotType>();
	public static Hashtable<MapLocation,RobotType> opbuild=new Hashtable<MapLocation,RobotType>();
	public static MapLocation myHQ;
	public static MapLocation opHQ=null;
	
	public static ArrayList<MapLocation> trypath;
	
	
	public static void init(RobotController theRC) {
		rc=theRC;
		myloc=rc.getLocation();
		height=rc.getMapHeight();
		width=rc.getMapWidth();
		squaredradius=rc.getCurrentSensorRadiusSquared();
		map=new int[width][height];
		floodmap=new boolean[width][height];
		prevloc=myloc;
	}
	}

