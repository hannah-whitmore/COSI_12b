// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// February 25, 2020

public class GroceryItemOrder{
	String name;
	int quantity;
	double price;

	// this method is the constructor, which initialzes each field, eachName, eachQuantity, and eachPrice comes from the user's input in the client code
	public GroceryItemOrder(String eachName, int eachQuantity, double eachPrice){
		name = eachName;
		quantity = eachQuantity;
		price = eachPrice;
	} 

	// this method returns the cost of each item, by multiplying the price times the quantity 
	public double getCost(){
		double cost = 0;
		cost = quantity * price;
		return cost;
	}

	// this method assigns the item's quantity field to eachQuantity (the user's entered quantity) for each item 
	public void setQuantity(int eachQuantity){
		quantity = eachQuantity;
	}

	// this method just returns quantity so that it can be used in the GroceryList class when calculating the total number of items
	public int getQuantity(){
		return quantity;
	}

	// this is a toString method that allows to print each item object instead of java printing it's reference in memory 
	public String toString(){
		return quantity + " " + name;
	}
}

