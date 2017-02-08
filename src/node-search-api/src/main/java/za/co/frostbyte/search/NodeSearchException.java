package za.co.frostbyte.search;

/**
 * Custom Exception class to translates the Exceptions occurred in the API to
 * Meaningful description
 * 
 */
public class NodeSearchException extends Exception {

	/**
	 * Random serial version id
	 */
	private static final long serialVersionUID = 1L;

	public NodeSearchException(Throwable cause) {
		super(cause);
	}

	public NodeSearchException(String message) {
		super(message);
	}

	public NodeSearchException(String message, Throwable cause) {
		super(message, cause);
	}
}
