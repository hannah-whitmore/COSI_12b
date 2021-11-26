/* 
Names: Hannah Whitmore and Akwasi Owusu-Brempong
Email: hwhitmore@brandeis.edu and aowusubrempong@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import main.*;

import org.junit.jupiter.api.Test;

class TableTest {
	
	static String [] columnList_1 = {"studentId<i>", "name", "surname"};
	static String [] rowList1_1 = {"1", "Ben", "Segal"};
	static String [] rowList2_1 = {"2", "Sam", "Stern"};
	static String [] rowList3_1 = {"3", "Hanyu", "Song"};
	static String [] rowList4_1 = {"4", "Hangyu", "Du"};
	static TableColumns columnNames = new TableColumns(columnList_1);
	static Row row1_1 = new Row(rowList1_1);
	static Row row2_1 = new Row(rowList2_1);
	static Row row3_1 = new Row(rowList3_1);
	static Row row4_1 = new Row(rowList4_1);
	
	static ArrayList<Row> rows = new ArrayList<Row>();
	
	/** make a new Table */
	static Table  makeTable(String tablename, TableColumns columnnames) {
		Table table = new Table(tablename, columnnames);
		rows.add(row1_1);
		rows.add(row2_1);
		rows.add(row3_1);
		rows.add(row4_1);
		
		for (int i=0; i<rows.size();i++) {
			table.addRow(rows.get(i));
		}
		return table;
		
	}
	
	static Table table = makeTable("TAS", columnNames);
	
	static String [] columnList_2 = {"class", "professor", "TA_Id<i>"};
	static String [] rowList1_2 = {"12B", "DiLillo", "1"};
	static String [] rowList2_2 = {"21A", "Liu", "4"};
	static String [] rowList3_2 = {"131A", "Papaemmanouil", "2"};
	static String [] rowList4_2 = {"36A", "Patterson", "3"};
	Row row1_2 = new Row(rowList1_2);
	Row row2_2 = new Row(rowList2_2);
	Row row3_2 = new Row(rowList3_2);
	Row row4_2 = new Row(rowList4_2);
	ArrayList<Row> rows2 = new ArrayList<Row>();
	TableColumns columnNames2 = new TableColumns(columnList_2);
	Table table2 = new Table("CLASSES", columnNames2);

	
	
	@Test
	void testTableConstructor() {
		assertEquals(table.getName(),"TAS");
		assertEquals(table.getAllTableColumns(), columnNames);	
	}
	
	
	/**adds a row to a given table */
	@Test 
	void testAddRow() {
		assertEquals(table.getRows(), rows);
	}
	/**if the num of cells in a row exceeds the num of columns for the table, 
	 * should throw an exception */
	@Test
	void testAddRowException() {
		Table test = new Table("TAS", columnNames);
		String [] rowListTest = {"3", "red", "orange", "yellow", "green"};
		Row rowTest = new Row(rowListTest);
		boolean thrown = false;
		try {
			test.addRow(rowTest);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);	
	}
	
	/**selectFrom query logic  */
	@Test 
	void testSelectFrom() {
		String [] selectColumns = {"studentId<i>", "surname"};
		assertEquals(table.selectFrom(selectColumns).toString(), "1 Segal" + '\n' + "2 Stern" + '\n' + "3 Song" + '\n' + "4 Du" );
	}
	
	/**testing selectFrom when passed column names not 
	 * in the same order they exist as the table */
	@Test 
	void testSelectFrom2() {
		String [] selectColumns = {"surname", "name"};		
		assertEquals(table.selectFrom(selectColumns).toString(), "Segal Ben" + '\n' + "Stern Sam" + '\n' + "Song Hanyu" + '\n' + "Du Hangyu");
		String [] selectColumns2 = {"surname", "studentId<i>"};
		assertEquals( table.selectFrom(selectColumns2).toString(), "Segal 1" + '\n' + "Stern 2" + '\n' + "Song 3" + '\n' + "Du 4");

	}
	
	/**s per PDF example  */
	@Test
	void testJoinOn() {
		rows2.add(row1_2);
		rows2.add(row2_2);
		rows2.add(row3_2);
		rows2.add(row4_2);
		
		for (int i=0; i<rows2.size(); i++) {
			table2.addRow(rows2.get(i));
		}
		String [] selectColumns = {"studentId<i>", "name", "surname", "class", "TA_Id<i>"};
	
		
		assertEquals(table.joinOn(selectColumns, table2, "studentId<i>", "TA_Id<i>").toString(), "1 Ben Segal 12B 1" + '\n' + "2 Sam Stern 131A 2" + '\n' + "3 Hanyu Song 36A 3" + '\n' + "4 Hangyu Du 21A 4" );
	}
	
	/**testing the case where the foreign table has duplicate keys and should be joined multiple times
	 *  in this example, the 1 ID repeats, so will be joined twice with the 1 id row of local table
	 *  also added another row to foreign table with no matching key to local table, so it doesn't ever join */
	@Test 
	void testJoinOn2() {
		String [] rowList2_2 = {"21A", "Liu", "1"};
		String [] rowList5_2 = {"164a", "Hickey", "5"};
		Row row2_2 = new Row(rowList2_2);
		Row row5_2 = new Row(rowList5_2);
		ArrayList<Row> rows2 = new ArrayList<Row>();
		TableColumns columnNames2 = new TableColumns(columnList_2);
		Table table2 = new Table("CLASSES", columnNames2);
		rows2.add(row1_2);
		rows2.add(row2_2);
		rows2.add(row3_2);
		rows2.add(row4_2);
		rows2.add(row5_2);
		for (int i=0; i<rows2.size();i++) {
			table2.addRow(rows2.get(i));
		}
		String [] selectColumns = {"studentId<i>", "name", "class", "TA_Id<i>"};
		assertEquals(table.joinOn(selectColumns, table2, "studentId<i>", "TA_Id<i>").toString(), "1 Ben 12B 1" + '\n' + "1 Ben 21A 1" + '\n' + "2 Sam 131A 2" + '\n' + "3 Hanyu 36A 3");
		
	}
	@Test 
	void OrderByTest(){
		String[] columns = {"studentId<i>", "name", "surname"};
		assertEquals(table.orderBy(columns, "studentId<i>", "ASC").toString(),"1 Ben Segal\n2 Sam Stern\n3 Hanyu Song\n4 Hangyu Du" );

	}
	

	
	
}
