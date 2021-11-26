/* 
Names: Hannah Whitmore and Akwasi Owusu-Brempong
Email: hwhitmore@brandeis.edu and aowusubrempong@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import main.*;

import org.junit.jupiter.api.Test;

class RowTest {

	static String[] rowConstructor() {
		String [] rowList = {"bird", "true", "false"}; // test row
		return rowList;
	}

	@Test
	void testGetCell() {
		String[] tester = rowConstructor();
		Row row1  = new Row(tester);
	
		assertEquals(row1.getCell(0), "bird"); // cell at index 0 of row = bird 
		assertEquals(row1.getNumOfCells(), tester.length); 
		
	}
	
	@Test
	void testGetRowArray() {  
		String[] tester = rowConstructor();
		Row row1  = new Row(tester);
		assertEquals(row1.getRowArray(), tester);
	
	}
}
