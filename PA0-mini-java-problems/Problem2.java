// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing recipes for any given number of cookies

public class Problem2{
	public static void main(String [] args){
		double sugar = 1.5/48.0; //dividing by 48 gives the amount of cups needed per any amount of cookies 
		double butter = 1.0/48.0;
		double flour = 2.75/48.0;
		int cookies = 48; // first recipe 
		sugar*= cookies; 
		butter*= cookies;
		flour *= cookies;
		System.out.println("Ingredients Recipe 1");
		System.out.println(sugar);
		System.out.println(butter);
		System.out.println(flour);
		System.out.println("");
		cookies = 144; // second recipe
		sugar = 1.5/48.0; 
		butter = 1.0/48.0;
		flour = 2.75/48.0;
		sugar*= cookies; 
		butter*= cookies;
		flour *= cookies;
		System.out.println("Ingredients Recipe 2");
		System.out.println(sugar);
		System.out.println(butter);
		System.out.println(flour);
		System.out.println("");
		cookies = 72; // third recipe 
		sugar = 1.5/48.0; 
		butter = 1.0/48.0;
		flour = 2.75/48.0;
		sugar*= cookies; 
		butter*= cookies;
		flour *= cookies;
		System.out.println("Ingredients Recipe 3");
		System.out.println(sugar);
		System.out.println(butter);
		System.out.println(flour);
		System.out.println("");
		
	}

}
