package za.co.frostbyte.astar.util;


public class NodeUtil {

	/**
	 * Returns whether a node is walkable or not
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isWalkable(String c){
		if(c.equals("~")){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Return the node value for a particular node
	 * 
	 * @param nodeVal
	 * @return
	 */
	public static int getNodecost(String nodeVal){
		return NodeConstants.nodeCosts.get(nodeVal);
	}
	
	/**
	 * Calculates the absolute value of a number 
	 * 
	 * @param val
	 * @return
	 */
	public static int absoluteVal(int val) {
		return val > 0 ? val : -val;
	}
}
