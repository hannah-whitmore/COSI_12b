// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// February 25, 2020

import java.util.*;

public class ClientCode{
	public static void main(String[] args){
		// initializing each
		String eachName = "";
		int eachQuantity = 0;
		double eachPrice = 0;
		
		// creates the gList array object
		GroceryList gList = new GroceryList();
		Scanner console = new Scanner(System.in);
		System.out.println("My grocery List (you can only have 10 items in the list)" + "\n");
		System.out.println("Please enter an item to the list (item-name quantity price)" + "\n" + "Enter quit to stop adding items to the list" + "\n");


		while (!(eachName.equals("quit"))){
			eachName = console.next();
			if (eachName.equals("quit") == false){
				eachQuantity = console.nextInt();
				eachPrice = console.nextDouble();
				GroceryItemOrder item = new GroceryItemOrder(eachName, eachQuantity, eachPrice); // creates the item object holding the name, quantity, and price 
				gList.add(item); // calls the add method , which adds each item to the grocerly list array 
			}
		}
			
		System.out.println("My list: " + gList);
		System.out.println("Total grocery amount: $ " + gList.getTotalCost());
		System.out.println("Total number of items: " + gList.getTotalQuantity());

	}

}
	
