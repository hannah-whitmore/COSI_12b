package part1;
/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * COSI 12b
 * April 23, 2020
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.*;

public class TreasureHunter {
	static long F_SIZE = 0;
	static int PATH = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		for(int i = 1; i <= 8; i++){					//for loop from to 1 to 8, inclusive
			File file = new File("part1_txt/tmap" + i + ".txt");	//creates a new File object using the Strings "tmap" and ".txt" and only changes the map number to 1 through 8
			goForTreasureHunt(file);					//finds the treasure and prints out different bits of information
			System.out.println();						//Prints a line
		}
		System.out.println("Complete");					//When all files are scanned, and the treasures were found, print "Complete"
		
	}
	
	// puts each text file into an array list and a linked list
	// because a general list is passed in, can call this method twice with each type of list 
	public static List<Integer> importFileIntoList(File f, List<Integer> l) throws FileNotFoundException {
		Scanner input = new Scanner(f);
		while (input.hasNext()) { // loops through entire file
			int num = input.nextInt(); // grabs token
			F_SIZE++; // increment the file size counter per each token
			l.add(num); // add to the list
		}
		return l;
	}
	
	// loops through the list to find the treasure 
	public static int findTreasureLocation(List<Integer> l) {
		boolean treasureFound = false;
		int index = 0; 
		PATH = 0;
		while (!(treasureFound)) {
			if (l.get(index) == 0) { // if the list at the index = 0, treasure is found
				treasureFound = true;
			} else {
				PATH++; // keeping track of the path, number of iterations before finding treasure 
				index = l.get(index); // index starts as the element at position 0, then updates throughout
									// the new index becomes the value of the previous element					 
			}
		}
		return index;
	}
	
	// returns the path it took to find treasure, made path a global variable so that it updates within
	// the find treasure method, and returns it here 
	public static int findTreasurePathLength(List<Integer> l) {
		return PATH;
	}
	
	// calls all the methods that run the treasure hunt and calculates run time 
	public static void goForTreasureHunt(File f) throws FileNotFoundException {
		System.out.println("Now processing " + f);
		int path = 0;
		int index = 0; // these will be used to hold the values returned by each method
		
		List <Integer> arrList = new ArrayList<Integer>(); // create new array list 
		long importStartA = System.currentTimeMillis(); // start time
		arrList = importFileIntoList(f, arrList); // import each file into array list
		long importEndA = System.currentTimeMillis(); // end time 
		long importTimeA = importEndA - importStartA; // time it takes to import file into array list
		
		long findingStartA = System.currentTimeMillis();
		index = findTreasureLocation(arrList); // find the treasure, and store in index variable
		long findingEndA = System.currentTimeMillis();
		long findingA = findingEndA - findingStartA; // run time for finding treasure in array list
		
		path = findTreasurePathLength(arrList); // the path will be the same regardless of the list used, 
												// so only call it once per text file
		// same as above but for linked list 
		List <Integer> linkList = new LinkedList<Integer>();
		long importStartL = System.currentTimeMillis();
		linkList = importFileIntoList(f, linkList); // import file into linked list
		long importEndL = System.currentTimeMillis();
		long importTimeL = importEndL - importStartL; // final time for importing
		
		long findingStartL = System.currentTimeMillis();
		findTreasureLocation(linkList); // find treasure with linked list
		// don't need to assign it to index because it will be the same index as with the array list 
		long findingEndL = System.currentTimeMillis();
		long findingL = findingEndL - findingStartL;  // final time for finding 
		
		// printing out all the messages 
		System.out.println("Importing the File took " + importTimeA + " milliseconds in the Array List method");
		System.out.println("Importing the File took " + importTimeL + " milliseconds in the Linked List method");
		System.out.println("Finding the Treasure took the Array List " + findingA + " milliseconds");
		System.out.println("Finding the Treasure took the Linked List " + findingL + " milliseconds");
		System.out.println("Length of Treasure Path: " + path + " Size of File: " + F_SIZE/2); 
		// dividing file size by 2 to get accurate answer because the way I calculated the file size it runs twice for both lists 
		System.out.println("The treasure was found at index: " + index);
	}
	
}
