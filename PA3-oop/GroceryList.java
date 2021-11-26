// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// February 25, 2020

import java.util.*;

public class GroceryList{
	int currIndex;
	int totalNumOfItems;
	int countQuant;
	GroceryItemOrder [] gList;

	// this is the constructor method, which initializes / declares the object's fields
	public GroceryList(){
		currIndex = 0; // currIndex keeps track of the index to know where to add the next item 
		totalNumOfItems = 0;
		countQuant = 0;
		gList = new GroceryItemOrder[10]; // array of size 10 bc the max amount of items is 10 and if there are less it will store as null
	}

	// this method adds each item object to the grocery list array 
	public void add(GroceryItemOrder item){
		if(currIndex<10){ 
			gList[currIndex] = item; // adds the item at the correct index
			currIndex++; // increases the index for the next iteration / addition of item
		}
	}

	// this method returns the total number of items, so need to add together the quantities of each item
	public int getTotalQuantity(){
		for (int i=0; i<gList.length; i++){
			if (gList[i] != null){
				totalNumOfItems += gList[countQuant].getQuantity(); // need to access each item to get the quantity, and add it to total
			}
			countQuant++; // moves through each item to get the quantity 
		}
		return totalNumOfItems;
	}

	// this is a toSting method which allows to print the grocery list array as a string instead of java printing the array's reference in memory
	public String toString(){
		return Arrays.toString(gList);
	}

	// this method returns the sum of the prices of each item
	public double getTotalCost(){
		double sum = 0;
		for (int i=0; i<gList.length; i++){
			if (gList[i] != null){
				sum += gList[i].getCost(); // accesses each item to get the cost, and add it to the sum 
			}
		}
		return sum;
	}	

}