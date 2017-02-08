package za.co.frostbyte.search.astar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import za.co.frostbyte.astar.node.AbstractNode;
import za.co.frostbyte.astar.util.NodeConstants;
import za.co.frostbyte.astar.util.NodeUtil;
import za.co.frostbyte.search.NodeSearchException;
import za.co.frostbyte.search.NodeSearchInterface;

/**
 * This class represents a map of node.
 * 
 * The node array is constructed from the given text file containing the map.
 * This map contains the operations to find the path in the given map using Astar search algorithm.
 * 
 */
public class AstarNodeSearchImpl<T extends AbstractNode> implements NodeSearchInterface {


    /** This represents the nodes in the map */
    private T[][] nodes;

    /** Width of the map, index start from 0 */
    private int width;
    
    /** Height of the map, index starts from 0*/
    private int higth;

    /** Factory to create the instance of a node */
    private NodeFactory nodeFactory;

    private T startNode;
    
    private T endNode;
    
    /** list containing nodes not visited but adjacent to visited nodes. */
    private List<T> openList;
    
    /** list containing nodes already visited */
    private List<T> closedList;
    
    public AstarNodeSearchImpl(File mapFile, NodeFactory nodeFactory) throws NodeSearchException {
    	if(mapFile==null || !mapFile.exists()){
    		throw new NodeSearchException("Map file not exist, please provide a valid file object");
    	}
    	this.nodeFactory = nodeFactory; 
        initEmptyNodes(mapFile,NodeConstants.START_NODE,NodeConstants.END_NOE);
    }

    /**
     * Initializes the nodes given in the text file
     * 
     * @param filePath
     * @param startPoint
     * @param endPoint
     */
    private void initEmptyNodes(File mapFile, String startPoint, String endPoint) {
    	try{
	    	BufferedReader reader = new BufferedReader(new FileReader(mapFile));	    	
	    	String line;
	    	int index = 0;
	    	while((line = reader.readLine())!=null){
//	    		line = line.trim();
	    		if(nodes==null){
	    			nodes = (T[][]) new AbstractNode[line.length()][line.length()]; 
	    			this.width = line.length()-1;
	    			this.higth = line.length()-1;
	    		}
	    		for (int i = 0; i < line.length(); i++) {
	    			String str = ""+line.charAt(i);
	    			nodes[index][i] = (T) nodeFactory.createNode(index, i,NodeUtil.isWalkable(str),str);
	    			nodes[index][i].setMovementCost(NodeUtil.getNodecost(str));
	    			if(str.equals(startPoint)){
	    				startNode = nodes[index][i];
	    			}else if(str.equals(endPoint)){
	    				endNode = nodes[index][i];
	    			}
	    		}
	    		++index;
	    	}
	    	reader.close();
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }

    /**
     * sets nodes walkable field at given coordinates to given value.
     * <p>
     * x/y must be bigger or equal to 0 and smaller or equal to width/hight.
     *
     * @param x
     * @param y
     * @param bool
     */
    public void setWalkable(int x, int y, boolean bool) {
        nodes[x][y].setWalkable(bool);
    }

    /**
     * returns node at given coordinates.
     * <p>
     * x/y must be bigger or equal to 0 and smaller or equal to width/hight.
     *
     * @param x
     * @param y
     * @return node
     */
    public final T getNode(int x, int y) {
        return nodes[x][y];
    }

    /**
     *
     * Derives the allowed path on the map for given start and end point on the map.
     * This uses Astar algorithm to find the path
     *
     * @return Whether the path has been found or not.
     */       
    public boolean findPath() throws NodeSearchException {

        openList = new LinkedList<T>();
        closedList = new LinkedList<T>();
        openList.add(startNode);
        
        boolean done = false;
        T current;
        while (!done) {
            current = lowestFInOpen(); 
            closedList.add(current); 
            openList.remove(current); 

            if ((current.getxPosition() == endNode.getxPosition())
                    && (current.getyPosition() == endNode.getyPosition())) {
            	List<T> finalPath = calcPath(startNode, current);
            	int length = nodes.length;
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(NodeConstants.RESULT_FILE_LOCATIION));
					for (int i = 0; i < length; i++) {
						StringBuffer buffer = new StringBuffer();
						for (int j = 0; j < length; j++) {
							buffer.append(nodes[i][j].getNodeVal());
						}
						buffer.append("\n");
						writer.write(buffer.toString());
					}
					writer.close();
				} catch (IOException e) {
					throw new NodeSearchException("Exception ocuured during I/O operation, serach algorithm failed", e);
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							throw new NodeSearchException("Exception ocuured during I/O operation, serach algorithm failed", e);
						}
					}
				}
                return true;
            }

            // for all adjacent nodes:
            List<T> adjacentNodes = getAdjacent(current);
            for (int i = 0; i < adjacentNodes.size(); i++) {
                T currentAdj = adjacentNodes.get(i);
                if (!openList.contains(currentAdj)) {
                    currentAdj.setPrevious(current);
                    currentAdj.setCostFromEndNode(endNode);
                    currentAdj.setNodeCost(current);
                    openList.add(currentAdj); 
                } else {
                    if (currentAdj.getNodeCost() > currentAdj.calculateNodeCost(current)) {
                        currentAdj.setPrevious(current);
                        currentAdj.setNodeCost(current);
                    }
                }
            }

            if (openList.isEmpty()) {
                return false;
            }
        }
        return false;
    }

    /**
     * Calculates and returns the path sequence found between two nodes
     *
     *	Uncomment pathSequence and it's related code to number the path sequence on the map
     *
     * @param start
     * @param goal
     * @return
     */
    private List<T> calcPath(T start, T goal) {

        LinkedList<T> path = new LinkedList<T>();
//        int pathSequence = 0;
        T curr = goal;
        boolean done = false;
        while (!done) {
            path.addFirst(curr);
//            curr.setNodeVal("#"+pathSequence++);
            curr.setNodeVal("#");
            curr = (T) curr.getPrevious();

            if (curr.equals(start)) {
                done = true;
            }
        }
//        start.setNodeVal("#"+pathSequence);
        start.setNodeVal("#");
        
        return path;
    }

    /**
     * returns the node with the lowest totalCost
     *
     * @return
     */
    private T lowestFInOpen() {

        T cheapest = openList.get(0);
        for (int i = 0; i < openList.size(); i++) {
            if (openList.get(i).getTotalCost() < cheapest.getTotalCost()) {
                cheapest = openList.get(i);
            }
        }
        return cheapest;
    }

    /**
     * returns a LinkedList with nodes adjacent to the given node.
     * if those exist, are walkable and are not already in the closedList!
     */
    private List<T> getAdjacent(T node) {

        int x = node.getxPosition();
        int y = node.getyPosition();
        List<T> adj = new LinkedList<T>();

        T temp;
        if (x > 0) {
            temp = this.getNode((x - 1), y);
            if (temp.isWalkable() && !closedList.contains(temp)) {
                temp.setIsDiagonaly(false);
                adj.add(temp);
            }
        }

        if (x < width) {
            temp = this.getNode((x + 1), y);
            if (temp.isWalkable() && !closedList.contains(temp)) {
                temp.setIsDiagonaly(false);
                adj.add(temp);
            }
        }

        if (y > 0) {
            temp = this.getNode(x, (y - 1));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                temp.setIsDiagonaly(false);
                adj.add(temp);
            }
        }

        if (y < higth) {
            temp = this.getNode(x, (y + 1));
            if (temp.isWalkable() && !closedList.contains(temp)) {
                temp.setIsDiagonaly(false);
                adj.add(temp);
            }
        }


        // add nodes that are diagonally adjacent 
        if (NodeConstants.CANMOVEDIAGONALY) {
            if (x < width && y < higth) {
                temp = this.getNode((x + 1), (y + 1));
                if (temp.isWalkable() && !closedList.contains(temp)) {
                    temp.setIsDiagonaly(true);
                    adj.add(temp);
                }
            }

            if (x > 0 && y > 0) {
                temp = this.getNode((x - 1), (y - 1));
                if (temp.isWalkable() && !closedList.contains(temp)) {
                    temp.setIsDiagonaly(true);
                    adj.add(temp);
                }
            }

            if (x > 0 && y < higth) {
                temp = this.getNode((x - 1), (y + 1));
                if (temp.isWalkable() && !closedList.contains(temp)) {
                    temp.setIsDiagonaly(true);
                    adj.add(temp);
                }
            }

            if (x < width && y > 0) {
                temp = this.getNode((x + 1), (y - 1));
                if (temp.isWalkable() && !closedList.contains(temp)) {
                    temp.setIsDiagonaly(true);
                    adj.add(temp);
                }
            }
        }
        return adj;
    }

}
