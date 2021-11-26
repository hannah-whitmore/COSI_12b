/*
Names: Hannah Whitmore
Email: hwhitmore@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Table {
	String name;
	TableColumns columns; // [name , ID , age]
	ArrayList <Row> rows = new ArrayList <Row>(); // list of type row that holds each row

	public Table(String name, TableColumns columns) {
		this.name = name;
		this.columns = columns;
	}

	/**
	 * adds row to table and throws exception if number of cells
	 * in row exceeds number of table columns
	 */
	public void addRow(Row row) {
		if (row.getNumOfCells() != getAllTableColumns().getSize()) {
			throw new IllegalArgumentException();
		}
		getRows().add(row);
	}

	/**
	 * @return the name of a given table object
	 */
	public String getName() {
		return this.name;
	}


	/**
	 * @return all tableColumn objects that belong to a table
	 */
	public TableColumns getAllTableColumns() {
		return columns;
	}


	/**
	 * @return the internal string array of column names for a given TableColumn
	 */
	public String [] getColumnList(TableColumns column) {
		return column.getColumnList();
	}

	/**
	 * @return list of rows (containing each row object) for a given table
	 */
	public ArrayList <Row> getRows() {
		return rows;
	}


	/**
	 * logic for selectFrom query
	 * creates a temporary table which will hold the final selected table
	 * iterates over each row and grabs only the cells associated with a column we are selecting
	 * after each complete selected row, add that row to the temptable
	 * @return constructed temporary table
	 */
	public Table selectFrom(String [] selectColumns) {
		TableColumns columns = new TableColumns(selectColumns);
		Table tempTable = new Table("tempTable", columns);
		String [] rowList = new String [selectColumns.length];
		int columnIndex = 0;
		Row currRow; // used to grab the current row within a table for printing
		for (int i=0; i< this.getRows().size(); i++) { // loop through arrayList that holds each row
			currRow = this.getRows().get(i); // grab each row
			for (int j=0; j<selectColumns.length; j++) { // only want to print the cells of a row that correspond to the list of columns passed in
				columnIndex = this.getAllTableColumns().getColumn(selectColumns[j]);
				rowList[j] = (currRow.getCell(columnIndex));
				if (j==selectColumns.length-1) {
					Row nextRow = new Row(rowList);
					tempTable.addRow(nextRow);
					rowList = new String [selectColumns.length];
				}
			}
		}
		return tempTable;
	}

	/**
	 * Overridden selectFrom method for when selectFrom is called with a join query,
	 * and is dealing with selecting from 2 tables instead of 1
	 * @return temporary table
	 */
	public Table selectFrom(String [] selectColumns, Table joinTable) {
		TableColumns columns = new TableColumns(selectColumns);
		Table tempTable = new Table("tempTable", columns);
		String [] rowList = new String [selectColumns.length];
		int columnIndex = 0;
		Row currRow;
		for (int i=0; i< this.getRows().size(); i++) {
			currRow = this.getRows().get(i); // grab each row
			for (int j=0; j<selectColumns.length; j++) {
				// getColumn will return -1 if it doesn't find that column associated with a table
				// when it returns -1 in this loop, that means we have moved on to the second table
				// that is being joined
				if (this.getAllTableColumns().getColumn(selectColumns[j]) != -1){
					columnIndex = this.getAllTableColumns().getColumn(selectColumns[j]);
					rowList[j] = (currRow.getCell(columnIndex));

				} else {
					// now instead of this.table, it's the jointTable					// only want selectColumns from the second table now
					columnIndex = joinTable.getAllTableColumns().getColumn(selectColumns[j-(this.columns.getSize())]);
					rowList[j] = (currRow.getCell(columnIndex));

				}
				if (j==selectColumns.length-1) {
					Row nextRow = new Row(rowList);
					tempTable.addRow(nextRow);
					rowList = new String [selectColumns.length];
				}
			}
		}

		return tempTable;
	}

	/**
	 * logic for joinOn query
	 * construct a temporary table to hold merged / sorted data from the 2 tables join is called on
	 * tempTable consists of all columns from both tables (first merge together, than can select)
	 * one row of temptable is : row from first table, iterate over joinTable and find the row that matches based on the key,
	 * then add that row
	 * after merging the 2 tables and ordering by the joinkey, pass it to selectFrom to only see the
	 * columns we want
	 * @return temporary table
	 */
	public Table joinOn(String [] selectColumns, Table joinTable, String fromKey, String joinKey) {
		int fromTableColumnsLength = this.getAllTableColumns().getColumnList().length;
		int joinTableColumnsLength = joinTable.getAllTableColumns().getColumnList().length;
		String [] tempTableColumnsList = new String [fromTableColumnsLength + joinTableColumnsLength];
		// create a new tableColumnsList that consists of all the columns from both tables
		for (int i=0; i< fromTableColumnsLength; i++) {
			tempTableColumnsList [i] = this.getAllTableColumns().getColumnList()[i];
		}
		for (int i=0; i< joinTableColumnsLength; i++){
			tempTableColumnsList[fromTableColumnsLength + i] = joinTable.getAllTableColumns().getColumnList()[i];
		}

		// create a temporary table to hold the merged tables
		TableColumns columns = new TableColumns(tempTableColumnsList);
		Table tempTable = new Table("tempTable", columns);
		Row fromCurrRow;
		Row joinCurrRow;

		// find the index of the column we are joining by
		int fromColumnIndex = 0;
		int joinColumnIndex = 0;
		fromColumnIndex = this.getAllTableColumns().getColumn(fromKey);
		joinColumnIndex = joinTable.getAllTableColumns().getColumn(joinKey);

		// this first loop runs for the rows of the first table
		for (int i=0; i< this.getRows().size(); i++) {
			fromCurrRow = this.getRows().get(i); // grab first row of first table
			int indexCount = 0;
			// string array of joined cells will be size of combined table column sizes
			// rowCells is the list of the content of one row
			String [] rowCells = new String [tempTableColumnsList.length];

			// loop through the rows of the second table and find which row to match with
			for (int k=0; k< joinTable.getRows().size(); k++) {
				joinCurrRow = joinTable.getRows().get(k); // grab first row of second table

				if (joinCurrRow.getCell(joinColumnIndex).equals(fromCurrRow.getCell(fromColumnIndex))){ // they line up
						for (int j=0; j<fromCurrRow.getNumOfCells(); j++) {
							rowCells[j] = fromCurrRow.getCell(j);
							indexCount++;
						}
						for (int l=0; l<joinCurrRow.getNumOfCells(); l++) {
							rowCells[indexCount] = joinCurrRow.getCell(l);
							indexCount++;
						}
						// now the first row of the temp table has been created
						indexCount = 0;
						Row joinedRow = new Row (rowCells);
						tempTable.addRow(joinedRow);
						rowCells = new String[tempTableColumnsList.length];
				} // else , loop continues to find the row that matches
			}

		}
		tempTable = tempTable.selectFrom(selectColumns, joinTable); // limit the temp table to only the columns selected
		return tempTable;
	}

/**
 *logic for orderBy query
 *constructs a temp table with columns of the table it is called on
 *finds the column to sort by and if it's an int or string, sorts accordingly
 *finds the order the rows need to be in based on sorting
 *adds rows to the temp table based on this order either forwards or backwards based on ASC or DSC
 * @return temporary table
 */

public Table orderBy(String []selectColumns, String column, String order) {
		// construct temp table
		String [] tmpTableColumnsList = new String [this.getAllTableColumns().getColumnList().length];
		for (int h=0; h<tmpTableColumnsList.length; h++) {
			tmpTableColumnsList[h] = this.getAllTableColumns().getColumnList()[h];
		}
		TableColumns columns = new TableColumns(tmpTableColumnsList);
		Table tempTable = new Table("tempTable", columns);
		List <Row> tmpRows = new ArrayList<Row>();
		int index=0;

		List<Integer> key = new ArrayList<Integer>();	//list to track the index of each row
		List<Integer> intValues = new ArrayList<Integer>();
		List<String> strValues = new ArrayList<String>();

		//looping through the list of column names to find the column to sort by
		for (int x=0; x< selectColumns.length; x++) {
			if (selectColumns[x].equals(column)) {
				index = x;
			}

		}
		//creating separate list depending on whether the column passed is an integer or a string
		if (selectColumns[index].endsWith("<i>") ) {
			 //list for values to be sorted

			for (int i=0; i<this.getRows().size();i++) {
				intValues.add((Integer.parseInt((this.rows.get(i).getCells()[index]))));
				tmpRows.add(this.rows.get(i));
				key.add(i);
			}
			order_int(key,intValues);

		} else {
			 //List to track the values to be ordered
			for (int i=0; i<this.rows.size();i++) {
				strValues.add(this.rows.get(i).getCells()[index]);
				tmpRows.add(this.rows.get(i));
				key.add(i);
			}
			order_obj(key,strValues);
		}

		if (order.toUpperCase().equals("ASC")) { // add rows in forward order
			for (int i=0; i<key.size();i++) {
				tempTable.addRow(tmpRows.get(key.get(i)));

			}

			tempTable = tempTable.selectFrom(selectColumns);
		}

		else if (order.toUpperCase().equals("DSC")) { // add rows in backwards order
			for (int i=key.size()-1; i>=0;i--) {
				tempTable.addRow(tmpRows.get(key.get(i)));;
			}
			tempTable = tempTable.selectFrom(selectColumns);
		}
		return tempTable;
	}

	/**
	 *if the column ordering by is an integer
	 */
	public void order_int(List<Integer> key, List<Integer> values) {
		int didswap=1; int tmp=0; int tmp_key;
		while (didswap==1) {
			didswap=0;
			for (int i =1;i<values.size();i++) {
				if (values.get(i-1).compareTo(values.get(i))>0) {
					tmp =values.get(i); tmp_key =key.get(i);
					values.set(i, values.get(i-1)); key.set(i, key.get(i-1));
					values.set(i-1, tmp); key.set(i-1, tmp_key);
					didswap=1;
				}
			}
		}

	}

	/**
	 * if the column ordering by is a string
	 * takes two lists as parameter and uses bubble sort to sort the values list in
	 * ascending order while keeping track of the key associated with it
	 */
	public void order_obj(List<Integer> key, List<String> values) {
		int didswap=1; String tmp=null; int tmp_key;
		while (didswap==1) { //checking if a swap was made
			didswap=0;
			for (int i =1;i<values.size();i++) {
				if (values.get(i-1).compareTo(values.get(i))>0) {	//comparing to see if the former is greater than the latter
					tmp =values.get(i); tmp_key =key.get(i); //keeping track of the key after each swap
					values.set(i, values.get(i-1)); key.set(i, key.get(i-1));
					values.set(i-1, tmp); key.set(i-1, tmp_key);
					didswap=1;
				}
			}
		}

	}

	/** toString method for a table
	 * used to print the temp tables within the parser
	 * @return table instance to String
	 */
	public String toString() {
		String result = "";
		int x =0;
		Row currRow;
		for (int i=0; i< getRows().size(); i++) { // loop through arrayList that holds each row
			currRow = getRows().get(i); // grab each row
			result += currRow.getCell(0);
			for (int j=1; j<getAllTableColumns().getColumnList().length; j++) {
				result += " " +currRow.getCell(j);
				if (j==getAllTableColumns().getColumnList().length-1 && x<this.rows.size()-1) {
					result += '\n';
					x++;
				}
			}
		}
		return result;
	}
}
