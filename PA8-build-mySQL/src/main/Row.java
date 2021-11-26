/*
Names: Hannah Whitmore
Email: hwhitmore@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package main;
public class Row {
	String [] cells; // string array of cells as field

	public Row(String[] cells) {
		this.cells = cells;
	}

	/** returns the cell at a given index
	 * @return a specific row cell as a String
	 */
	public String getCell(int index) {
		return this.getCells()[index];
	}

	/**
	 * @return the string array of cells for a given row
	 */
	public String[] getRowArray() {
		return this.getCells();
	}

	/**
	 * @return the number of cells per row in a given table
	 */
	public int getNumOfCells() {
		return this.getCells().length;
	}

	/**
	 * @return a row object toString
	 */
	public String toString() {
		String rowToString = "" + this.getCells()[0];
		for (int i=1; i<getNumOfCells(); i++) {
			rowToString += " " + this.getCells()[i];
		}
		return rowToString;
	}

	/**
	 * @return internal string array of a row object, containing the cells
	 */
	public String [] getCells() {
		return cells;
	}
}
