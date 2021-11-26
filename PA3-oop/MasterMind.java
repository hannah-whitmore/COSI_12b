// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// February 25, 2020

import java.util.*;

public class Mastermind{
	public static void main(String [] args){
		compNumAndUserGuess();

	}
	// this method essentially sets up the rest of the program. it initializes the arrays for later, calls methods, and continously asks for the user's guess
	public static void compNumAndUserGuess(){
		System.out.println("This is a game of mastermind. Your goal is to correctly guess the computer's 4 digit number." + "\n");
		int [] fourDigitNum = new int[4]; // this array will store the correct answer  
		Random rand = new Random();

		for (int i=0; i<4; i++){
			int randomNumber = rand.nextInt(10); // produces a random digit between 0 and 9 
			fourDigitNum[i] = randomNumber; // adds each random num to the answer array
 		}
		System.out.println(Arrays.toString(fourDigitNum));
		int correctPlace = 0; 
		
		while (correctPlace != 4){ // while the user has not correctly guessed all four digits
			Scanner console = new Scanner(System.in);
			System.out.print("Your gusss? ");
			int userGuess = console.nextInt();
			int [] guessArr = separating(fourDigitNum, userGuess); 
			int [] correctIndexTrack = new int [guessArr.length];
			// correctIndexTrack is initialized to all 0s, and will be used to keep track of the index where a correct num has been found
			int [] wrongIndexTrack =  new int [guessArr.length];
			// wrongIndexTrack is the same as correctIndexTrack but for correct nums in the wrong place
			correctPlace = correctPlace(fourDigitNum, guessArr, correctIndexTrack);
			wrongPlace(fourDigitNum, guessArr, correctPlace, correctIndexTrack, wrongIndexTrack);
		}
				
	}

	// this method separates the user's int and enters each digit into an array 
	// if the user enters less than 4 digits, it store zeros up until the number
	// for example, 6 is stored as 0006 bc it should be in the thousandths place
	 	public static int [] separating(int [] fourDigitNum, int userGuess){	
		int [] guessArray = new int[4]; // guesArray will store each number of the user's guess 
		int divideBy = 1; 

		// the following math isolates the digits from left to right so they are stored properly
		for (int i = 0; i<(3); i++){ 
			divideBy *= 10; // for four digit numbers, divideBy should always start at 1000, but this loop just generalizes my code if it were played with a non four digit number
		}

		for (int i=0; i<4; i++){
			guessArray [i] = userGuess/divideBy; // isolates each digit
			userGuess = userGuess%divideBy; // reduces the user's num for the next digit
			divideBy /=10; 
		}
		System.out.println(Arrays.toString(guessArray));
		return guessArray;
		
	}

	// this method will determine how many digits the user entered that are correct and in the correct place
	public static int correctPlace(int [] fourDigitNum, int [] guessArr, int [] correctIndexTrack){
		int correctPlace = 0;
		
		for (int i=0; i<guessArr.length; i++){
			if(fourDigitNum[i] == guessArr[i]){ // if the elements are equal at the same index, it's correct
				correctPlace ++;
				correctIndexTrack[i]++; // now, instead of a 0, there will be a 1 in the index where a correct num has been found, to eliminate false printing later
			}
		
		}
		if (correctPlace!= 4){ // as long as the user hasn't guessed all 4 digits
			System.out.println(correctPlace + " correct numbers in the correct place.");
		}
		return correctPlace;
	}

	// this method will determine how many digits the user entered that are correct but in the wrong place
	public static void wrongPlace(int [] fourDigitNum, int [] guessArr, int correctPlace, int [] correctIndexTrack, int [] wrongIndexTrack){
		int countInBoth = 0;

		// this nested for loop checks each element of the guess array against each element of the answer array
		// if the user enters multiple of the same one digit that is correct, it will say multiple are correct in the wrong place
		// for example, if the answer is 1234 and the user enters 0111 it will say 3 digits correct in the wrong place
		for(int i = 0; i < fourDigitNum.length; i++){
			for(int j = 0; j < guessArr.length; j++){
				// if the elements match, the correct tracker at both i and j is zero (which prevents falsely counting a correct place as wrong place), and
				// wrong index tracker at j is 0, which is what prevets counting the same number in the guess array twice if there are duplicates of it in the answer array
				if(fourDigitNum[i] == guessArr[j] && correctIndexTrack[j] == 0 && correctIndexTrack[i] == 0 && wrongIndexTrack[j] == 0){
					countInBoth++; 
					wrongIndexTrack[j]++; // now 1 instead of 0 for every right num in wrong place
				}
			}
		}
		if (correctPlace!= 4){ // as long as the user hasn't guessed all 4 numbers
			System.out.println(countInBoth + " digits correct but in the wrong place.");

		} else { 
			System.out.println("You guessed all " + correctPlace + " numbers!");
		}
		
	}	
}
