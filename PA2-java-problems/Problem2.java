// Hannah Whitmore
// PA #2
// 2/9/20
// Reads a file about baby name popularity and displays data about an entered name

import java.io.*;
import java.util.*;

public class Problem2 {
	public static void main(String [] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("names.txt"));
		babyNames(input);
	}
	public static void babyNames(Scanner input) {
		boolean isInFile = false; // checking if the name is in the file at all
		System.out.println("\n" + "This program allows you to search through the" + "\n" + "data from the Social Security Administration" + "\n" + "to see how popular a particular name has been" + "\n" + "since 1900." + "\n");
		Scanner console = new Scanner(System.in);
		int year = 1890; // starting before the first value so can later add 10 every iteration, fencepost 
		System.out.print("Name? ");
		String name = console.next();

		// loops through entire file to check for the entered name, name is at the beginning of each line
		while (input.hasNextLine()) {
			String nameInFile = input.next();

			// if capitalization isn't correct/consistent with the file, compares all lower case of both names
			if (name.toLowerCase().equals(nameInFile.toLowerCase())) { 
				isInFile = true; // entered name is in file
				System.out.println("\n" + "Statistics on name " + "\"" + nameInFile + "\"");

				// while there is an int after the name (the stats on it)
				while(input.hasNextInt()) { 
					int nameCount = input.nextInt(); // each stat 
					year += 10; // file provides info per decade, increase year by 10 with iteration
					System.out.println(year + ": " + nameCount);
				}
			} 
		}
		
		// if it has looped through the whole file and hasn't found the name, isFile will still be false
		if (isInFile == false) { 
			year = 1890; // popularity is 0 every year if name not in file
			System.out.println("\n" + "Statistics on name " + "\"" + name + "\"");
			for(int i=0; i<11; i++) {
				year+=10;
				System.out.println(year + ": " + "0");
			}
		}
		
	}
}