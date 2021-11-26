// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing if a given number is prime or not 

public class Problem7{
	public static void main(String [] args){
		int x = 17;
		int isDivisable = 0;
		for (int i = 2; i<x; i++){
			if (x%i == 0){ // checking if the number is divisable by any number smaller than itself 
				isDivisable++;
			}
		}
		if (isDivisable>0){ //needs to run through the whole for loop to determine if its prime or not, so used a count variable (returning would work better)
			System.out.println("False.");
		}
		else{
			System.out.println("True.");
		}
		
	}
}