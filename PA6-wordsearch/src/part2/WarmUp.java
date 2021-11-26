package part2;
/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * COSI 12b
 * April 23, 2020
 */

import java.util.*;

public class WarmUp {

	// runs the program, constructs lists, calls methods, calculates runtime
	public static void main(String[] args) {
		
		List <Integer> arrList = new ArrayList<Integer>();
		long startArr = System.currentTimeMillis();
		arrList = constructList(arrList);
		long endArr = System.currentTimeMillis();
		long buildArr = endArr-startArr;
		System.out.println("It took " + buildArr + " milliseconds to construct the Arraylist.");
		List <Integer> linkedList = new LinkedList <Integer>();
		long startLink = System.currentTimeMillis();
		linkedList = constructList(linkedList);
		long endLink = System.currentTimeMillis();
		long buildLinked = endLink - startLink;
		System.out.println("It took " + buildLinked + " milliseconds to construct the LinkedList." + "\n");
		long startDeleteA = System.currentTimeMillis();
		deleteNums(arrList);
		long endDeleteA = System.currentTimeMillis();
		long arrDelete = endDeleteA - startDeleteA;
		System.out.println("The array list took " + arrDelete + " milliseconds to delete the first 10,000 numbers.");
		long startDeleteL = System.currentTimeMillis();
		deleteNums(linkedList);
		long endDeleteL = System.currentTimeMillis();
		long linkedDelete = endDeleteL - startDeleteL;
		System.out.println("The linked list took " + linkedDelete + " milliseconds to delete the first 10,000 numbers." );
			
	}
	
	// put numbers 1-2000000 into a list , ArrayList and LinkedList / compare run time
	public static List<Integer> constructList(List<Integer> l) {
		for (int i=1 ; i<=2000000 ; i++) {
			l.add(i);	
		}
		return l;
	}
	
	// deletes the first 10000 numbers from both lists / compare run times
	public static void deleteNums(List <Integer> list) {
		// create an iterator to keep track of which digits to remove when shifing occurs
		Iterator <Integer> i = list.iterator();
		int count = 0; // counter for the iterations
		while (i.hasNext()) {
			count++;
			int el = i.next();
			if (count <=10000) { // will remove the first 10000 digits as it corresponds with the first 10000 iterations
				i.remove();
			}
		}
	}		
	
}
