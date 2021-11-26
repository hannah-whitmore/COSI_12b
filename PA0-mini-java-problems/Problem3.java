// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing 2 to the power of 15, starting with 2^0 up to 2^15

public class Problem3{
	public static void main(String [] args){
		int answer = 1; //keeps track of the previous answer, first answer is 1 (2^0 = 1)
			for (int i = 0; i<16 ; i++){ //16 results
			System.out.println(answer);
			answer *= 2; //the next number will be the previous number times 2
			}
		}
	}
