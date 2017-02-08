package za.co.frostbyte.astar.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * This class contains the global values that are being used throughout the application
 * 
 * Should be replaced by the logic to read from a resource file
 *
 */
public final class NodeConstants {

    /** Indicates whether it is possible to walk diagonally on the map */
	public static final boolean CANMOVEDIAGONALY = true;
	
	public static final String START_NODE = "@";
	public static final String END_NOE = "X";
	
	/** Holds the cost of the each node */
	public static final Map<String, Integer> nodeCosts;
	static{
		nodeCosts = new HashMap<>();
		nodeCosts.put("@", 1);
		nodeCosts.put("X", 1);
		nodeCosts.put(".", 1);
		nodeCosts.put("*", 2);
		nodeCosts.put("^", 3);
		nodeCosts.put("~", 0);
	}
	
	/** Hold output file location */
	public static final String RESULT_FILE_LOCATIION = "result_map.txt";
}
