// Hannah Whitmore
// PA #2
// 2/9/20
// Returns the length of the longest sorted sequence in an array

import java.util.*;

public class Problem4 {
	public static void main(String [] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("How many integers in the array? "); // size of array 
		int numInArray = console.nextInt();
		int [] a1 = new int[numInArray];
		for (int i=0; i<numInArray; i++) {
			System.out.print("Enter integer # " + (i+1) + " "); // contents of array 
			a1[i] = console.nextInt();
		}
		System.out.println(longestSortedSequence(a1, numInArray));
	}

	// returns length of longest sort sequence in an array
	public static int longestSortedSequence(int [] a1, int numInArray) {
		int isSorted = 1; // counts sequences in the array that are sorted, starting at 1 bc its counting the elements
		int longest = 1; // keeps track of the longest sorted sequence
		if (numInArray == 0) { // if passed an empty array, returns 0 
			return 0;
		}
		for (int i=0; i<numInArray-1; i++) { 
			if (a1[i] <= a1[i+1]){ // compares each adjacent element, and the previous is less than the next, increments sorted variable
				isSorted ++;
			} else {
				if (longest<isSorted) { // once the array is not in order, it goes into this else statement, and makes longest isSorted as long as it is less
					longest = isSorted; // longest always will hold the longest sequence at the moment in the array
				}
				isSorted = 1; // once the array isnt sorted at any point, isSorted gets reset 
			}
		}
		return longest;
	}
}