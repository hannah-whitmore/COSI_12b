// Hannah Whitmore
// PA #1
// 1/31/19
// Prints the name, quantity, and price of three entered items, then displays subtotal, tax, and total

import java.util.*;

public class Problem1{
	public static void main(String [] args){
		inputAndOperations();
	}
	public static void inputAndOperations(){
		Scanner console = new Scanner(System.in); // all of the input questions
		System.out.print("Input name of item 1: ");
		String nameOne = console.next();
		System.out.print("Input quantity of item 1: ");
		int quantOne = console.nextInt();
		System.out.print("Input price of item 1: ");
		double priceOne = console.nextDouble();
		System.out.print("Input name of item 2: ");
		String nameTwo = console.next();
		System.out.print("Input quantity of item 2: ");
		int quantTwo = console.nextInt();
		System.out.print("Input price of item 2: ");
		double priceTwo = console.nextDouble();
		System.out.print("Input name of item 3: ");
		String nameThree = console.next();
		System.out.print("Input quantity of item 3: ");
		int quantThree = console.nextInt();
		System.out.print("Input price of item 3: ");
		double priceThree = console.nextDouble();

		printHeader(); 

		double subtotal = 0;	
		double eachTotal = 0;
		printingItems(nameOne, quantOne, priceOne); // displays info of item 1
		eachTotal = totals(quantOne, priceOne); // eachTotal holds the total for each item, in this case item 1
		subtotal += eachTotal; // subtotal is calculated by adding each item's individual total
		System.out.printf("%-10s\n", eachTotal);
		printingItems(nameTwo, quantTwo, priceTwo);
		eachTotal = totals(quantTwo, priceTwo);
		subtotal += eachTotal; // each time an item's total is calculated, it gets added to subtotal 
		System.out.printf("%-10s\n", eachTotal);
		printingItems(nameThree, quantThree, priceThree);
		eachTotal = totals(quantThree, priceThree);
		subtotal += eachTotal;
		System.out.printf("%-10s\n", eachTotal);
		
		
		System.out.printf("\n" + "Subtotal %46s\n", subtotal);
		double tax = subtotal*0.0625; // total with tax is calculated by multiplying subtotal by tax decimal 
		System.out.printf("%s sales tax %39.2f\n", "6.25%", tax);
		double overallTotal = subtotal + tax; 
		System.out.printf("Total %49.2f\n" + "\n", overallTotal);
		
	}

	public static void printHeader(){ // prints the header for the categories 
		System.out.println("\n" + "Your bill: ");
		System.out.printf("%-30s", "Item");
		System.out.printf("%-10s", "Quantity");
		System.out.printf("%-10s", "Price");
		System.out.printf("%-10s\n", "Total");
	}

	public static void printingItems(String name, int quant, double price){ // displays the name, quantity, and price of each of the items
		System.out.printf("%-30s", name);
		System.out.printf("%-10s", quant);
		System.out.printf("%-10s", price);
	}
	public static double totals(int quantity, double price){ // calculates the total for each item 
		double total = 0;
		total = quantity * price;
		return total;
	}


}