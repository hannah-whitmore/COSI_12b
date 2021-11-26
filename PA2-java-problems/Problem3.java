// Hannah Whitmore
// PA #2
// 2/9/20
// Accepts an array of ints and returns whether or not the values in the array are unique

import java.util.*;

public class Problem3 {
	public static void main(String [] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("How many integers in the array? "); // to size the array 
		int numInArray = console.nextInt();
		int [] a1 = new int[numInArray];
		for (int i=0; i<numInArray; i++) {
			System.out.print("Enter integer # " + (i+1) + " "); // array made up of user's inputted integers 
			a1[i] = console.nextInt();
		}
		System.out.println(isUnique(a1, numInArray));
	}
	
	// passing in aray and length of the array (stored as numInArray), alternatively would work with length method
	public static boolean isUnique(int []a1, int numInArray) {
		// these nested for loops will compare every element in the array with each other to check if all are unique
		// starts with first element compared agaist all the rest, then moves to second, etc. to the end of the array
		for (int i=0; i<numInArray; i++) { 
			for(int j=i+1; j<numInArray; j++) {
				if (a1[i] == a1[j]){ // if at any point the two elements being compared are equal, can return false bc the array is not unique
					return false;
				}			
			}
		}
		return true; // if after comparing every element with each other, can return true bc the array is unique
	}
} 