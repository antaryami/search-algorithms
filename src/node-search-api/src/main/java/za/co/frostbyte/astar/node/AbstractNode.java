package za.co.frostbyte.astar.node;

/**
 * 
 * This class represents a Node with all the node properties.
 *
 */
public abstract class AbstractNode {
	
	/** Holds the movement cost to this node */
	private int movementCost;
	
    private int xPosition;
    private int yPosition;
    
    /** Indicates if a node is walkable or not */
    private boolean walkable;

    /** Move from previous node to this node is diagonally or not*/
    private boolean diagonally;

    /** costs from start node to this node*/
    private int nodeCost;

    /** costs from this node to end node*/
    private int costFromEndNode;
    
    /**Holds the value of the node*/
    private String nodeVal;
    
    /** the previous AbstractNode of this one on the currently calculated path */
    private AbstractNode previous;

    /**
     * Creates an abstract node
     * 
     * @param xPosition
     * @param yPosition
     * @param walkable
     * @param nodeVal
     */
    public AbstractNode(int xPosition, int yPosition, boolean walkable, String nodeVal) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.walkable = walkable;
        this.nodeVal = nodeVal;
    }

    public boolean isDiagonaly() {
        return diagonally;
    }

    public void setIsDiagonaly(boolean isDiagonaly) {
        this.diagonally = isDiagonaly;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public AbstractNode getPrevious() {
        return previous;
    }

    public void setPrevious(AbstractNode previous) {
        this.previous = previous;
    }


    public int getTotalCost() {
        return nodeCost + costFromEndNode;
    }

    public int getNodeCost() {
        return nodeCost;
    }

    private void setNodeCost(int nodeCost) {
        this.nodeCost = nodeCost ;
    }

//    /**
//     * sets nodeCost to previous AbstractNode nodeCost plus movement cost of the AbstractNode
//     *
//     * @param previousAbstractNode
//     * @param basicCost
//     */
//    public void setNodeCost(AbstractNode previousAbstractNode, int basicCost) {
//        setNodeCost(previousAbstractNode.getNodeCost() + basicCost);
//    }
//
//    /**
//     * sets nodeCost to nodeCost plus movementPanelty
//     * for this AbstractNode given the previous AbstractNode.
//     * <p>
//     * It will assume <code>BASICMOVEMENTCOST</code> as the cost from
//     * <code>previousAbstractNode</code> to itself if the movement is not diagonally,
//     * otherwise it will assume <code>DIAGONALMOVEMENTCOST</code>.
//     * Weather or not it is diagonally is set in the Map class method which
//     * finds the adjacent AbstractNodes.
//     *
//     * @param previousAbstractNode
//     */
    /**
     * sets nodeCost to previous AbstractNode nodeCost plus movement cost of the AbstractNode
     * 
     * @param previousAbstractNode
     */
    public void setNodeCost(AbstractNode previousAbstractNode) {
    	setNodeCost(previousAbstractNode.getNodeCost()+getMovementCost());
    }

    /**
     * Calculates nodeCost by summing up the nodeCost or previous abstract node and the movementCost of this node
     *
     * @param previousAbstractNode
     * @return nodeCost
     */
    public int calculateNodeCost(AbstractNode previousAbstractNode) {
    	return (previousAbstractNode.getNodeCost()
                + getMovementCost());
    }

//    /**
//     * calculates - but does not set - g costs, adding a movementPanelty.
//     *
//     * @param previousAbstractNode
//     * @param movementCost costs from previous AbstractNode to this AbstractNode.
//     * @return gCosts
//     */
//    public int calculategCosts(AbstractNode previousAbstractNode, int movementCost) {
//        return (previousAbstractNode.getNodeCost() + movementCost);
//    }

    /**
     * returns costs to get from this AbstractNode to end AbstractNode.
     *
     * @return the costFromEndNode
     */
    public int getCostFromEndNode() {
        return costFromEndNode;
    }

    /**
     * 
     * @param costFromEndNode
     */
    protected void setCostFromEndNode(int costFromEndNode) {
        this.costFromEndNode = costFromEndNode;
    }

    
//    @Override
//    public String toString() {
//        return "(" + getxPosition() + ", " + getyPosition() + "): h: "
//                + getCostFromEndNode() + " nodeCost: " + getNodeCost() + " TotalCost: " + getTotalCost();
//    }   


	public int getMovementCost() {
		return movementCost;
	}

	public void setMovementCost(int movementCost) {
		this.movementCost = movementCost;
	}

	public String getNodeVal() {
		return nodeVal;
	}

	public void setNodeVal(String nodeVal) {
		this.nodeVal = nodeVal;
	}
	
	/**
     * calculates CostFromEndNode from this AbstractNode to a given end AbstractNode.
     *
     * @param endAbstractNode
     */
    public abstract void setCostFromEndNode(AbstractNode endAbstractNode);
    
    
    /**
     * returns whether the coordinates and value of AbstractNodes are equal.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final AbstractNode other = (AbstractNode) obj;
        if (this.xPosition != other.xPosition || this.yPosition != other.yPosition || !this.nodeVal.equals(other.nodeVal)) {
            return false;
        }
        
        return true;
    }
       	
}
