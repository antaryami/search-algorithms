package za.co.frostbyte.search;

/**
 * This ENUM represents different types of search supported by the api
 * 
 */
public enum NodeSearchEnum {

	ASTAR;
	
	public String value() {
        return name();
    }

    public static NodeSearchEnum fromValue(String v) {
        return valueOf(v);
    }
}
