// Hannah Whitmore
// PA #1
// 1/31/19
// Finds a baseball player's batting average

import java.util.*;

public class Problem3{
	public static void main(String [] args){
		Scanner console = new Scanner(System.in);
		System.out.print("Enter number of times the player was at bat: ");
		double atBat = console.nextInt();
		System.out.print("Enter number of hits earned: ");
		double hitsEarned = console.nextInt();
		double battingAverage = hitsEarned/atBat; // average hits = number of hits / total of times at bat
		System.out.printf("Batting average = %.4f\n", battingAverage); // prints it formatted to 4 decimal places
	}
}