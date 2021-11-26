/*
Names: Hannah Whitmore
Email: hwhitmore@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package main;

public class TableColumns {
	String [] columnList;

	public TableColumns(String[] columnList) {
		this.columnList = columnList;
	}

	/**
	 * Gets the size of the tablecolumns object
	 * @return the number of columns the table has
	 */
	public int getSize() {
		return this.columnList.length;
	}

	/**
	 * @return the index of the columnList at a specific column name
	 */
	public int getColumn(String alias) {
		for (int i=0; i<this.columnList.length; i++) {
			if (alias.equals(this.columnList[i])) {
				return i;
			}
		}
		return -1; // column name not found
	}

	/**
	 * @return the internal string array of a tablecolumn
	 */
	public String [] getColumnList() {
		return this.columnList;
	}


	/**
	 * @return a tableColumn object toString
	 */
	public String toString() {
		String columnToString = "" + this.columnList[0];
		for (int i=1; i<getSize(); i++) {
			columnToString += " " + this.columnList[i];
		}
		return columnToString;
	}



}
