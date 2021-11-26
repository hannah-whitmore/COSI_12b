// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12b
// April 12, 2020

package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.*;

class ArrayIntListTest {
	
	// this method reinitializes the testList so that don't need to repeat this code for each method test
	public static ArrayIntList reinitialize() {
		ArrayIntList testList = new ArrayIntList(5);
		testList.add(1); testList.add(2); testList.add(4); testList.add(7); testList.add(3);	
		// {1, 2, 4, 7, 3}
		return testList;
	}
	
	// testing the default capacity constructor and the specified capacity constructor 
	@Test 
	void constructor1() {
		ArrayIntList testConstruct1 = new ArrayIntList(5);
		assertEquals(testConstruct1.getCapacity(), 5);
		ArrayIntList testConstruct2 = new ArrayIntList();
		assertEquals(testConstruct2.getCapacity(), 10);
	}
	
	// if the constructor is passed a negative capacity, will throw an exception
	@Test 
	void constructor2() {
		boolean thrown = false;
		try {
			ArrayIntList testConstruct3 = new ArrayIntList(-2);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
		
	}
	
	// testing adding when it needs to be resized for the case of 5 and when it doesn't for the case of 2
	@Test
	void add1() {
		ArrayIntList testAdd = reinitialize();
		testAdd.add(5); testAdd.add(2); testAdd.add(0);		
		assertEquals(testAdd.toString(), "[1, 2, 4, 7, 3, 5, 2, 0]");
	}
	
	// testing adding at a specific index and the resulted shift
	@Test
	void add2() {
		ArrayIntList testAdd2 = reinitialize(); 
		testAdd2.add(1, 10); testAdd2.add(3, 20);
		assertEquals(testAdd2.toString(), "[1, 10, 2, 20, 4, 7, 3]");
		
	}
	// testing getting an element at a given index
	@Test
	void get () {
		ArrayIntList testGet = reinitialize();
		assertEquals(testGet.get(3), 7);
		
	// testing that get still works after clearing and adding
		testGet.clear();
		testGet.add(8);
		// {8}
		assertEquals(testGet.get(0), 8);
	}
	
	// checkIndex will throw an exception if out of the bounds 0 and size and is called inside get
	// one way to test CheckIndex is to try to get an element with index out of bounds 
	@Test 
	void testCheckIndex () {
		ArrayIntList testCheckIndex = reinitialize();
		boolean thrown = false;
		try {
			testCheckIndex.get(7);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	// returns the first index of an element, testing with multiple of the same element 
	@Test
	void indexOf() {
		ArrayIntList testIndexOf = reinitialize();
		testIndexOf.add(4); testIndexOf.add(4);
		// {1, 2, 4, 7, 3, 4, 4}
		assertEquals(testIndexOf.indexOf(4), 2);
	}
	
	// testing getCapacity when original capacity is 5 and 5 elements, then an element is added -- should be 10
	@Test
	void getCapacity() {
		// basic test
		ArrayIntList testGetCapacity = reinitialize();
		assertEquals(testGetCapacity.getCapacity(), 5);
		
		// after adding
		testGetCapacity.add(2);
		assertEquals(testGetCapacity.getCapacity(), 10);
	}
	
	// testing the removal and re-size of list
	@Test 
	void remove() {
		ArrayIntList testRemove = reinitialize();
		testRemove.remove(2); 
		// {1, 2, 4, 7, 3} - remove index 2 - {1, 2, 7, 3}
		assertEquals(testRemove.toString(), "[1, 2, 7, 3]");
	}
	
	// testing the cases where size would change -- adding, removing, and clearing
	@Test 
	void size() {
		ArrayIntList testSize = reinitialize();
		testSize.add(8); // size increments after adding
		assertEquals(testSize.size(), 6);
		testSize.remove(8); // size decrements after removing
		assertEquals(testSize.size(), 5);
		testSize.clear(); // size = 0
		assertEquals(testSize.size(), 0);
		
	}
	
	// tests the toString 
	@Test
	void tostring() {
		ArrayIntList testToString = reinitialize();
		assertEquals(testToString.toString(), "[1, 2, 4, 7, 3]");
		testToString.add(17);
		assertEquals(testToString.toString(), "[1, 2, 4, 7, 3, 17]");
	}

	// tests that when clear is called the size of the list is 0
	@Test 
	void clear() {
		ArrayIntList testClear = reinitialize();
		testClear.clear();
		assertEquals(testClear.size(), 0);
	}
	
	// testing contains before and after an element is added or removed 
	@Test 
	void contains() {
		ArrayIntList testContains = reinitialize();
		testContains.add(30);
		assertEquals(testContains.contains(30), true);
		testContains.remove(0);
		assertEquals(testContains.contains(1), false);
	}
	
	// tests that when adding an element to a list which has reached its capacity, it will double in capacity
	@Test 
	void ensureCapacity(){
		ArrayIntList testEnsureCapacity = reinitialize(); // capacity of 5
		testEnsureCapacity.add(10); testEnsureCapacity.add(9);
		assertEquals(testEnsureCapacity.getCapacity(), 10);
	}
	
	// testing isEmpty would be true if the list were cleared and false if it weren't
	@Test
	void isEmpty() {
		ArrayIntList testIsEmpty = reinitialize(); // capacity of 5
		testIsEmpty.clear();
		assertEquals(testIsEmpty.isEmpty(), true);
		
		ArrayIntList testIsEmpty2 = new ArrayIntList(); // new list default capacity of 10, one element added
		testIsEmpty2.add(90);
		assertEquals(testIsEmpty2.isEmpty(), false);
	}
	
	// because unsorted, a call to binarySearch from ArrayIntList should result in throwing an exception
	@Test
	void binarySearch() {
		ArrayIntList testBinary = reinitialize();
		boolean thrown = false;
		try {
			testBinary.binarySearch(2);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
}
