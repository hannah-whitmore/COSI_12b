// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing the factorial of a given number 

public class Problem4{
	public static void main(String [] args){
		int x = 10;
		int factorial = 1;
		for (int i = 1; i<= x; i++){ 
			factorial*=i; // 1*2*3*4*5*6*7*8*9*10
		}

		System.out.println(factorial);
	}
}