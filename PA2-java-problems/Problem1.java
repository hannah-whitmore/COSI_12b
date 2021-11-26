// Hannah Whitmore
// PA #2
// 2/9/20
// Plays a reverse hangman game, user types word/words and the computer tries to guess it 

import java.util.*;

public class Problem1 {
	public static void main(String [] args) {
		String word = getInput();
		playHangman(word);
	}

	public static String getInput() {
		Scanner console = new Scanner(System.in);
		System.out.println("Please enter a word for me to guess (letters only): ");
		String word = console.nextLine();
		word = word.toLowerCase(); // capitalization doesn't matter
		return word;
	}

	// this method elimiates duplicate guesses by the computer
	public static int generateNewNumber(String previousGuesses) { 
		boolean alreadyGuessed = false;
		int randomNum = 0; 
		do {
			alreadyGuessed = false;
			Random rand = new Random();
			randomNum = rand.nextInt(26) + 97;
	 		for (int i=0; i<previousGuesses.length(); i++) {
	 			if (previousGuesses.charAt(i) == randomNum) { // if the letter (ascii value) has been generated already, will generate a new one
	 				alreadyGuessed = true;
	 			}
	 		}
	 		} while (alreadyGuessed == true);
	 	return randomNum;
	}

	// this is the driver function to play the hangman game
	public static void playHangman(String word) {
		int numCorrect = 0;
		String display = "";
		String previousGuesses = "";
		int lettersToGuess = 0;
		
		// making the underscore display for unguessed letters
		for (int i=0; i<word.length(); i++) {
			char ifSpace = word.charAt(i);
			if (word.charAt(i) == ' ') { // if there's a space, it will be shown as a space in the display, not an underscore
				display += " ";
			} else{
				display += "_";
				lettersToGuess++; // counts the letters in the word/words 
			}
		}
		// playing the game/making guesses
	 	while (numCorrect != lettersToGuess) { // num correct is tracking how many correct letters the computer has guessed of the total (which is lettersToGuess)
			String compGuess = "";
			compGuess += (char)generateNewNumber(previousGuesses); // generateNewNumber returns the ascii value, this converts the value to the corresponding letter as a string
			previousGuesses += compGuess;
			// previousGuesses holds all of the comp's guesses in a string (which will be checked later) to ensure guesses are unique
			System.out.print("My guess: " + compGuess + "   ");
			for (int i =0; i<word.length(); i++) {
				char letter = word.charAt(i); // the next three lines convert each char of the word/words to a string so can use the equals method to compare it to compGuess
				String eachLetter = "";
				eachLetter += letter;
				if (eachLetter.equals(compGuess)) { // if the computer guessed correctly, the underscore in the display gets replaced with the correct letter
					display = display.substring(0,i) + eachLetter + display.substring(i+1, display.length());
					numCorrect++; // storing correct guesses
					// this is inside the if statement so that if there is multiple of the same letter, numCorrect gets incrimented multiple times
				}
		}
		System.out.println("current status: " + display);
	}	
}	
	
}

