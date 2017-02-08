package za.co.frostbyte.astar.node;

import za.co.frostbyte.astar.util.NodeUtil;

/**
 * 
 * This implementation overrides the operation to calculate the cost between two
 * different node using Manhattan formula
 * 
 */
public class ManhattanNodeImpl extends AbstractNode {

	public ManhattanNodeImpl(int xPosition, int yPosition, boolean walkable,
			String nodeVal) {
		super(xPosition, yPosition, walkable, nodeVal);
	}
	
	/**
	 * Set the cost between two nodes using manhatten formula
	 */
	public void setCostFromEndNode(AbstractNode endNode) {
		this.setCostFromEndNode((NodeUtil.absoluteVal(this.getxPosition()
				- endNode.getxPosition()) + NodeUtil.absoluteVal(this.getyPosition()
				- endNode.getyPosition()))
				* getMovementCost());
	}

	

}
