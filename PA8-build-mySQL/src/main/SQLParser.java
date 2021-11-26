/*
Names: Hannah Whitmore
Email: hwhitmore@brandeis.edu
Class: COSI 12B
Date: May 12, 2020
Programming Assignment: 7
*/
package main;

import java.util.Arrays;

public class SQLParser {
	Database db;

	public SQLParser(Database db) {
		this.db = db;
	}


	/**
	 * driver for parser
	 */
	public void parse(String sql) {
		String [] tokens = sql.split(" "); // split input into tokens (each word)
			if (tokens[0].equals("CREATE")){ // create query
				String tableName = extractStringFromBrackets(tokens[1]);
				String columnNames = "";
				for (int i=2; i<=(tokens.length-1); i++ ) {
					columnNames += tokens[i] + " ";
				}
				String [] tableColumnNames;
				tableColumnNames = extractStringsFromParens(columnNames);
				this.db.create(tableName, tableColumnNames);

			}
			else if (tokens[0].equals("INSERT")) { // insert query
				String tableName = extractStringFromBrackets(tokens[2]);
				String cellnames = "";
				for (int i=3; i<=(tokens.length-1); i++ ) {
					cellnames += tokens[i] + " ";
				}
				String [] rowCells;
				rowCells = extractStringsFromParens(cellnames);
				this.db.addRowToTable(tableName, rowCells); //addRowToTable will throw an exception if the there is no table of tableName
													   // so if a user tried to insert rows into a table that hasn't been created, it will throw an exception

			}
			else if (tokens[0].equals("SELECT")) {
				int selectCount = 0;
				String columnNames = "";
				int i = 0;

				do {
					columnNames += tokens[++i] + " "; // finding the column names from the select query based on finding parenthesis

				}
				while (!(tokens[i].contains(")")));
				String [] selectColumnNames;
				selectColumnNames = extractStringsFromParens(columnNames);

				i+=2; // skip FROM
				String fromTableName = extractStringFromBrackets(tokens[i]);

				if (sql.contains("JOIN")) {
					selectCount++;
					i+=2; // skip JOIN
					String joinTableName = extractStringFromBrackets(tokens[i]);
					i+=2; // skip ON
					String [] fromKeyUnParsed;
					String fromKeyCol = "";
					fromKeyUnParsed = splitStringIntoChars(tokens[i]);
					for (int j=0; j< fromKeyUnParsed.length; j++) {
						if (fromKeyUnParsed[j].equals(".")) {  // [table_name].key -- after period is where the fromKey column is located
							for (int k=(j+1); k<fromKeyUnParsed.length; k++) {
								fromKeyCol+= fromKeyUnParsed[k];
							}
						}
					}
					i+=2; // skip =
					String [] joinKeyUnParsed;
					String joinKeyCol = "";
					joinKeyUnParsed = splitStringIntoChars(tokens[i]);
					for (int j=0; j< joinKeyUnParsed.length; j++) {
						if (joinKeyUnParsed[j].equals(".")) { // same as above to grab joinKey column
							for (int k=(j+1); k<joinKeyUnParsed.length; k++) {
								joinKeyCol+= joinKeyUnParsed[k];
							}
						}
					}

					if (sql.contains("ORDER BY")){ // joinOn query and order by query
						selectCount++;
						Table tmpTable = this.db.getTable(fromTableName).joinOn(selectColumnNames, this.db.getTable(joinTableName), fromKeyCol, joinKeyCol);
						tmpTable = tmpTable.orderBy(selectColumnNames, extractOrderByAlias(sql), tokens[tokens.length-1] );
						System.out.println(tmpTable.toString());

					} else { // only joinOn query
						Table tmpTable = this.db.getTable(fromTableName).joinOn(selectColumnNames, this.db.getTable(joinTableName), fromKeyCol, joinKeyCol);
						System.out.println(tmpTable.toString());
					}
				}

				if ((sql.contains("ORDER BY")) && (!(sql.contains("JOIN")))){ // orderBy query without joinOn
					selectCount++;
					Table tmpTable;
					if (sql.contains("ASC")){
						tmpTable = this.db.getTable(fromTableName).orderBy(selectColumnNames, extractOrderByAlias(sql), "ASC");
					} else {
						tmpTable = this.db.getTable(fromTableName).orderBy(selectColumnNames, extractOrderByAlias(sql), "DSC");
					}
					System.out.println(tmpTable.toString());
			}

				if (selectCount == 0) { // regular select -- no join or order
					Table tmpTable = this.db.getTable(fromTableName).selectFrom(selectColumnNames);
					System.out.println(tmpTable.toString());
				}
			}
			else if (tokens[0].equals("UNDO")) {
				db.undo();

			}
	}
	/**
	 * This method will split a given String into an array of characters
	 * @param str the input String
	 * @return a String array of each character in str
	 */
	public String[] splitStringIntoChars(String str) {
		return str.split("");
	}

	/**
	 * This method will extract a given String from brackets [ ] if it's found inside them
	 * @param str the String to extract
	 * @return str without its brackets
	 * @throws IllegalArgumentException if str does not have brackets
	 */
	public String extractStringFromBrackets(String str) {
		try {
			return str.substring(str.indexOf("[") + 1, str.indexOf("]"));
		} catch (Exception IndexOutOfBoundsException){
			throw new IllegalArgumentException("expected [...] got " + str);
		}
	}

	/**
	 * This method will extract a given String from parens ( ) if it's found inside them
	 * @param str the String to extract
	 * @return a String array of string arguments extracted from parens
	 * @throws IllegalArgumentException if str does not have parens
	 */
	public static String[] extractStringsFromParens(String str) {
		try {
			return str.substring(str.indexOf("(") + 1, str.indexOf(")")).split(", ");
		} catch (Exception IndexOutOfBoundsException){
			throw new IllegalArgumentException("expected (...) got " + str);
		}
	}

	/**
	 * Retrieves the String representation of the "order by" alias from the given String
	 * @param str the given String
	 * @return a String representing the order by alias
	 */
	public String extractOrderByAlias(String str) {
		try {
			return extractStringsFromParens(str.substring(str.toUpperCase().indexOf("ORDER BY")))[0];
		} catch (Exception IndexOutOfBoundsException){
			return null;
		}
	}
}
