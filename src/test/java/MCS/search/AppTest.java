package MCS.search;

import edu.duke.StorageResource;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	private String dna = "tggcctadtgatggttcagtggattgatttaagttaggtgaggcttatggtcaaggtctaa";
	private int i = 0;
	private boolean trueFalse = true;

	public AppTest(String testName) {
		StringsThirdAssignments sta = new StringsThirdAssignments();
		StorageResource genes = sta.printAllGenes(dna);
		for (String s : genes.data()) {
			
			if(genes == null) {
			trueFalse = false;
			}
			
			System.out.println("genes: " + s);
			

		}
		assertTrue(trueFalse);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		assertTrue(true);
	}
}
