package za.co.frostbyte.search;

/**
 * Interface that every Search implementation must implements
 * 
 */
public interface NodeSearchInterface {
	
	/**
	 * This method finds the path and write it to a output file called
	 * result_map.txt
	 * 
	 * @return
	 * @throws NodeSearchException
	 */
	public boolean findPath() throws NodeSearchException;

}
