/*
Names: Hannah Whitmore
Email: hwhitmore@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/

package main;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;



public class Database {
	Table table;
	Map <String, Table> allTables = new HashMap <String, Table>();  // hashmap to hold all the tables
	DatabaseBackup db = new DatabaseBackup();

	/*** This method adds a table to the database
	 * within the hashmap, table name is the key and table is the value*/
	public void addTable(Table table) {
		db.backUp(clone());
		getAllTables().put(table.getName(), table);
	}

	/** This method removes a table from the database */
	public void removeTable(Table table) {
		db.backUp(clone());
		if (!(getAllTables().containsValue(table))){ // the map does not contain table as a value
			throw new NoSuchElementException(); // means it doesn't exist in the database -- throw exception
		}
		getAllTables().remove(table.getName()); // map method removes a value by its key, which is the table name
	}

	/** This method returns a table from the database based on its name
	 * @return instance of a table object
	 */
	public Table getTable(String tableName) {
		return this.allTables.get(tableName);
	}


	/** This method adds a new row to the table */
	public void addRowToTable(String tableName, String[] args) {
		db.backUp(clone());
		if (!(getAllTables().containsKey(tableName))){ // there is no table in the database named tableName -- throw exception
			throw new NoSuchElementException("Table does not exist");
		}
		Table table = getAllTables().get(tableName);
		Row row = new Row(args); // make a new row containing the string list passed i
		table.addRow(row); // add row to table
	}


	/** This method creates a new table from a query*/
	public void create(String name, String [] tableColumns) {
		TableColumns columns = new TableColumns(tableColumns);
		Table table = new Table(name,columns);
		addTable(table);
	}


	/**This method undoes an operation that was done recently by setting the internal map to the one stored in backup */
	public void undo() {
		this.allTables= db.getLatestBackUp().allTables;
	}


	/**
	 * @return all the tables stored in the map
	 */
	public Map <String, Table> getAllTables() {
		return allTables;
	}


	/**This method makes a clone of the tables in the database and returns it*/
	public Database clone() {
		Database clone =new Database();
		clone.allTables.putAll(this.allTables);
		return clone;
	}


}
