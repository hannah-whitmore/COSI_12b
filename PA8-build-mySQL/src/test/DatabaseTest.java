/* 
Names: Hannah Whitmore and Akwasi Owusu-Brempong
Email: hwhitmore@brandeis.edu and aowusubrempong@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import main.*;
import org.junit.jupiter.api.Test;
import test.StudentOutputTests;

class DatabaseTest {
	Database d1 =new Database();
	static String [] tester = {"studentId<i>", "name", "surname"}; // table columns
	static String [] rowList1 = {"1", "Hannah", "Whitmore"}; // row
	
	TableColumns column_names = new TableColumns(tester);
	
	Table table1 = new Table("classes", column_names); // 2 tables w diff names & same columns
	Table table2 = new Table("classes1", column_names);
	
	Map <String, Table> tables = new HashMap <String, Table>();
	
	
	@Test
	void testAddTable_undo() {
		tables.put("classes", table1); // put tables in database map
		tables.put("classes1", table2);
		
		d1.addTable(table1); // add tables to database
		d1.addTable(table2);
		
		assertTrue(d1.getAllTables().containsValue(table2)); // map contains the added table
		assertTrue(d1.getAllTables().containsKey("classes1")); // map contains key associated with table2
		assertEquals(d1.getAllTables().get("classes1"), table2); // get(key) returns value -- checking key/value relationship of table
		
		d1.undo();
		assertFalse(d1.getAllTables().containsKey("classes1"));
		
	}
	@Test 
	void testRemoveTable() {
		tables.put("classes", table1);
		tables.put("classes1", table2);
	
		d1.addTable(table1);
		d1.addTable(table2);
		
		d1.removeTable(table1);  // add 2 tables to database and remove one
		assertFalse(d1.getAllTables().containsValue(table1));  // assert the removed table does not exist in the database
	}
	
	@Test
	void testAddRowToTable() {
		tables.put("classes", table1);
		d1.addTable(table1);
		
		d1.addRowToTable("classes", rowList1); // add a row to table - assert num of rows in that table = 1
		assertEquals(Arrays.deepToString(d1.getTable("classes").getRows().get(0).getCells()),Arrays.deepToString( rowList1));
		
		String [] newRow = {"2", "Akwasi", "Owusu-Brempong"};
		d1.addRowToTable("classes", newRow); // add another row to same table - assert num of rows is now = 2
		assertEquals(Arrays.deepToString(d1.getTable("classes").getRows().get(1).getCells()),Arrays.deepToString( newRow));
	}
	
	
}
