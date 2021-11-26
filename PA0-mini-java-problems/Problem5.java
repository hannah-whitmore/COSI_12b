// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing the first x numbers of the Fibonacci sequence 

public class Problem5{
	public static void main(String [] args){
		int x = 10;
		int first = 0;
		int second = 1;
		int temp = 0;
		for (int i = 0; i<x; i++){
			System.out.print(first + " ");
			temp = first; 
			first = second; // temp is now first and first is now second, moving up the sequence
			second = second + temp; // the next number is the sum of the previous 2 numbers
		}
		System.out.println("");
	}
}