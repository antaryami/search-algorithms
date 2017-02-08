package za.co.frostbyte.search.astar;

import za.co.frostbyte.astar.node.AbstractNode;
import za.co.frostbyte.astar.node.ManhattanNodeImpl;



/**
 * Factory class to create a node.
 * 
 * At the moments it only contain the logic to instantiate ManhattanNodeImpl.
 * More implementations can be added if we wish to use different method to calculate the distance between two nodes
 * 
 */
public class NodeFactory {

	public AbstractNode createNode(int x, int y, boolean walkable,
			String nodeVal) {
		return new ManhattanNodeImpl(x, y, walkable, nodeVal);
	}

}
