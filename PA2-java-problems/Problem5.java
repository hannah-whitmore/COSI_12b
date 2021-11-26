// Hannah Whitmore
// PA #2
// 2/9/20
// Mimics the rules from the game show The Price is Right 

import java.util.*;

public class Problem5 {
	public static void main(String [] args) {
		// get user input
		Scanner console = new Scanner(System.in);
		System.out.print("How many bids? "); // size of the array 
		int numOfBids = console.nextInt();
		int [] bids = new int[numOfBids];
		for (int i=0; i<numOfBids; i++){
			System.out.print("Enter bid # " + (i+1) + " ");
			bids[i] = console.nextInt(); // aray storing the bid values 
		}
		System.out.print("What is the correct price? ");
		int correctPrice = console.nextInt();

		int bestBid = priceIsRight(bids, correctPrice, numOfBids);
		
		System.out.println(bestBid);
	}
	public static int priceIsRight(int [] bids, int correctPrice, int numOfBids) {
		int howClose = 0; 
		int [] comparing = new int[numOfBids]; // this array will be used for comparing later to find the smallest bid
		for (int i=0; i<numOfBids; i++) {
			if (bids[i] < correctPrice){ // bid must be lower than the correct price
				howClose = correctPrice-bids[i]; // checks to see how close each bid is to the correct price 
				comparing[i] = howClose; // adds the difference between correct price and bid to the array for comparing
				// only valid bids get added to this list (they are lower than the price)

			} else if (bids[i] == correctPrice) { // if the bid is exactly right, it wins, gets returned
				return bids[i];
			}
		}
		// the following finds the smallest value in the comparing array , which will find the winning bid
		int smallest = 1000000;
		for (int i=0; i<numOfBids; i++) { 
			// bc I initialized the comparing array to be the size of the bid array, if there were any bids higher than the correct price,
			// they didn't get added to the comparing array, so stored as 0. when finding best bid, want to ignore these 0s
			if (comparing[i] != 0 && comparing[i]<smallest) {
				smallest = comparing[i];
			}
		}
		// checking if all of the bids are higher than the corect price, in which case -1 is returned
		int isBigger = 0;
		for (int i=0; i<numOfBids; i++) {
			if (comparing[i] == 0){ // the 0s held in the comparing array meant they were higher than the correct price
				isBigger++; // counting how many bids were above correct price
			}
		}
		if (isBigger == numOfBids) { // every bid was bigger
			return -1;
		}
		return (correctPrice - smallest); // smallest holds the smallest difference between the correct price and bid,
		// so need to subtract it from correct price to return the original bid
	}
}