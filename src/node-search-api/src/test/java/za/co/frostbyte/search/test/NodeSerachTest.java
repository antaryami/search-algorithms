package za.co.frostbyte.search.test;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.co.frostbyte.search.NodeSearchEnum;
import za.co.frostbyte.search.NodeSearchException;
import za.co.frostbyte.search.NodeSearchFactory;
import za.co.frostbyte.search.NodeSearchInterface;

/**
 * Test class to cover the unit test of the different search algorithms
 *
 */
public class NodeSerachTest {

	private NodeSearchInterface astarSearchInterface;
	
	@Before
	public void setup(){
//		File file = new File("src/test/resources/small_map.txt");
		File file = new File("src/test/resources/large_map.txt");
		try {
			astarSearchInterface = NodeSearchFactory.getInstance().getNodeSearchInterface(NodeSearchEnum.ASTAR, file);
		} catch (NodeSearchException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void searchTest(){
		if(astarSearchInterface != null){
			try {
				Assert.assertTrue(astarSearchInterface.findPath());
			} catch (NodeSearchException e) {
				e.printStackTrace();
				Assert.fail(e.getMessage());
			}
		}
	}
	
	@After
	public void teardown(){
		astarSearchInterface = null;
	}
}
