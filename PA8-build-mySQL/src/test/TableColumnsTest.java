/* 
Names: Hannah Whitmore and Akwasi Owusu-Brempong
Email: hwhitmore@brandeis.edu and aowusubrempong@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package test;

import static org.junit.jupiter.api.Assertions.*;
import main.*;

import org.junit.jupiter.api.Test;

class TableColumnsTest {

	static String[] Array_constructor() {
		String [] columnList = {"studentId<i>", "name", "surname"};
		return columnList;
	}
	
	/**
	 * @return the right num of columns in columnList
	 */
	@Test
	void testgetSize() {
		String [] columnList = Array_constructor();
		TableColumns column_names = new TableColumns(columnList); 
		assertEquals(column_names.getSize(),columnList.length);
	}
	
	@Test 
	void testgetColumn() {
		String [] columnList = Array_constructor();
		TableColumns column_names = new TableColumns(columnList);
		assertEquals(column_names.getColumn("name"), 1); // the column "name" is at index 1 of the columnsList
	}

}
