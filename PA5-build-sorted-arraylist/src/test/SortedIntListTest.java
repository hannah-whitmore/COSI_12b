// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12b 
// April 12, 2020

package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.*;

class SortedIntListTest {

	// reinitializes the test list so don't have to repeat code in each method
	public static SortedIntList reinitialize() {
		SortedIntList testList = new SortedIntList(5);
		testList.add(1); testList.add(4); testList.add(2); testList.add(7); testList.add(3);	
		// {1, 2, 3, 4, 7} list 1
		return testList;	
	}
	
	@Test // tests that an exception is thrown if a constructor is passed a negative capacity 
	void constructor1() {
		boolean thrown = false;
		try {
			SortedIntList testConstruct = new SortedIntList(-2);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	// tests that the default capacity and given capacity with unique setting work correctly 
	@Test 
	void constructor2() {
		SortedIntList testConstruct2 = new SortedIntList();
		assertEquals(testConstruct2.getCapacity(), 10);
		
		SortedIntList testConstruct3 = new SortedIntList(true, 5);
		assertEquals(testConstruct3.getUnique(), true);
		assertEquals(testConstruct3.getCapacity(), 5);
	}
	
	// tests that adds in the right place to remain sorted
	@Test
	void add1() {
		SortedIntList testAdd = reinitialize();
		testAdd.add(5);
		assertEquals(testAdd.toString(), "S: [1, 2, 3, 4, 5, 7]");
		testAdd.add(10);
		assertEquals(testAdd.toString(), "S: [1, 2, 3, 4, 5, 7, 10]");
	}
	
	// exception should be thrown if try to add at a specific index
	@Test 
	void add2() {
		SortedIntList testAdd2 = reinitialize();
		boolean thrown = false;
		try {
			testAdd2.add(2, 4);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	// testing that max works in different cases - after adding the max and after clearing should throw exception
	@Test
	void max() {
		SortedIntList testMax = reinitialize();
		testMax.add(10);
		assertEquals(testMax.max(), 10);
		
		testMax.clear();
		boolean thrown = false;
		try {
			testMax.max();
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);		
	}
	
	// same test for min as for max
	@Test
	void min() {
		SortedIntList testMin = reinitialize();
		testMin.add(0);
		assertEquals(testMin.min(), 0);
		
		testMin.clear();
		boolean thrown = false;
		try {
			testMin.min();
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	// tests that binary search finds the values in the list, and that index of returns the same index
	@Test void binarySearch() {
		SortedIntList testSearch = reinitialize();
		assertEquals(testSearch.binarySearch(3), 2);
		assertEquals(testSearch.indexOf(3), 2);
	}
	
	
	// making a new list, not the reinitialized one to test unique setting
	// trying to add 5, 7, 7, 5 should result in [5, 7]
	@Test void unique() {
		SortedIntList testUnique = new SortedIntList(true); // unique set to true, capacity 10
		testUnique.add(5); testUnique.add(7); testUnique.add(7); testUnique.add(5);
		assertEquals(testUnique.toString(), "S: [5, 7]U");
	}
	
	// testing that when unique is set to true with duplicates, properly removes them, and won't add any duplicates
	@Test
	void setUnique() {
		SortedIntList testSetUnique = reinitialize();
		testSetUnique.add(4); testSetUnique.add(4); testSetUnique.add(3);
		//  original : [1, 2, 3, 3, 4, 4, 7]
		testSetUnique.setUnique(true);
		assertEquals(testSetUnique.toString(), "S: [1, 2, 3, 4, 7]U");
		
		testSetUnique.add(4); // with unique setting true, shouldn't add this 4
		assertEquals(testSetUnique.toString(), "S: [1, 2, 3, 4, 7]U");
	}
	
	// testing with a new list, not reinitialized
	// initially unique is set to false, so list is [42, 42, 42, 42]
	@Test
	void setUnique2() {
		SortedIntList testSetUnique2 = new SortedIntList(false, 2); // capacity 2, will make bigger
		testSetUnique2.add(42); testSetUnique2.add(42); testSetUnique2.add(42); testSetUnique2.add(42); 
		testSetUnique2.setUnique(true); // after unique set to true, list should only be one 42
		assertEquals(testSetUnique2.toString(), "S: [42]U");
		
	}
	
	// testing toString with new list, not reinitialized
	// [-2, 0, 10, 23] , should appear with S in front and U in back 
	@Test 
	void tostring() {
		SortedIntList testToString = new SortedIntList(true, 4);
		testToString.add(10); testToString.add(10); testToString.add(23); testToString.add(-2); testToString.add(0);
		assertEquals(testToString.toString(), "S: [-2, 0, 10, 23]U");
	}

}
