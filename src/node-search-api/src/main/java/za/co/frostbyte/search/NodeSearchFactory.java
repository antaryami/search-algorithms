package za.co.frostbyte.search;

import java.io.File;

import za.co.frostbyte.search.astar.AstarNodeSearchImpl;
import za.co.frostbyte.search.astar.NodeFactory;

/**
 * 
 * This factory class provides the different search implementation according to
 * the search type provided. The search type must be an ENUM.
 * 
 */
public class NodeSearchFactory {

	private static NodeSearchFactory instance;

	private NodeSearchFactory() {

	}

	/**
	 * Returns singleton instance of NodeSearchFactory. This prevents the user
	 * from creating multiple instances of NodeSearchFactory
	 * 
	 * @return
	 */
	public static NodeSearchFactory getInstance() {

		if (instance == null) {
			synchronized (NodeSearchFactory.class) {
				if (instance == null) {
					instance = new NodeSearchFactory();
				}
			}
		}
		return instance;
	}

	/**
	 * 
	 * @param searchType
	 *        ENUM which defines the search type
	 * @param mapFile
	 *        File object that contains the map
	 * @return This method returns the Search Algorithm implementation according
	 *         to the provided type
	 * @throws NodeSearchException
	 */
	public NodeSearchInterface getNodeSearchInterface(
			NodeSearchEnum searchType, File mapFile) throws NodeSearchException {
		NodeSearchInterface searchInterface = null;
		if (searchType == NodeSearchEnum.ASTAR) {
			searchInterface = new AstarNodeSearchImpl<>(mapFile,
					new NodeFactory());
		}
		return searchInterface;
	}

}
