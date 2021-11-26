package part2;
/**
 * Hannah Whitmore
 * hwhitmore@brandeis.edu
 * COSI 12b
 * April 23, 2020
 */

import java.io.*;
import java.util.*;

public class WordSearch {
	public static void main(String[] args) throws FileNotFoundException{
		File file = new File("part2_txt/gibberish.txt");
		List <String> wordListL = new LinkedList <String>();
		long startLinked = System.currentTimeMillis();
		wordListL = importStringsIntoList(file, wordListL);
		long endLinked = System.currentTimeMillis();
		long buildLinked = endLinked - startLinked;
		System.out.println("It took the LinkedList " + buildLinked + " milliseconds to import gibberish.txt");
		
		List <String> wordListA = new ArrayList <String>();
		long startArr = System.currentTimeMillis();
		wordListA = importStringsIntoList(file, wordListA);
		long endArr = System.currentTimeMillis();
		long buildArr = endArr - startArr;
		System.out.println("It took the ArrayList " + buildArr + " milliseconds to import gibberish.txt" + "\n");
		
		File file2 = new File("part2_txt/deleteCodes.txt");
		List <Integer> codesList = new LinkedList <Integer>();
		codesList = importCodesIntoList(file2, codesList);
		deleteGibberish(codesList, wordListL);
	}
		
	// putting gibberish.txt into a LinkedList and an ArrayList to compare run time with LinkedList
	public static List<String> importStringsIntoList(File f, List<String> wordList) throws FileNotFoundException {
		Scanner input = new Scanner(f);
		while (input.hasNext()) {
			String word = input.next();
			wordList.add(word);
		}
		return wordList;
	}
	
	// putting deleteCodes.txt into an integer LinkedList to compare run time with ArrayList
	public static List<Integer> importCodesIntoList(File f, List <Integer> codeList) throws FileNotFoundException{
		Scanner input2 = new Scanner(f);
		while (input2.hasNext()) {
			int code = input2.nextInt();
			codeList.add(code);
		}
		return codeList;
	}
	
	// deleting the gibberish strings at the indices from the codesList
	public static void deleteGibberish(List <Integer> codesList, List <String> wordListL) {
		// iterator for deleteCodes int list, and iterator for gibberish string list 
		Iterator <Integer> itrCodes = codesList.iterator();
		Iterator <String> itrWords = wordListL.iterator();
		int count = 0; // counter for the number of iterations to know where to remove bc indices won't be accurate after removing elements
		int previous = 0; // keeping track of previous element in code int list 
		String word = null;
		while (itrCodes.hasNext()) { // looping through entire delete codes list
			int code = itrCodes.next();
			if (code<previous) {  // if current code is less than previous code, that means we have reached the
								// start of the next subsequence, as the codes are no longer in ascending order
				itrWords = wordListL.iterator();  // need to reset the iterator to the beginning of the word list
				count = 0; // reset counter and previous
				previous = 0;
			}
			// while the iterations (aka index) is not equal to the code of removal, continue iterating through the words list
			while ((count-1) != code) {
				word = itrWords.next(); // grab next word
				count++; // keeping track of iterations
			}
			// when count = code, have reached the word to delete 
			itrWords.remove();					
			previous = code;	
			}		
		
		// to check, printing the answers from treasure hunt * 2 
		System.out.println(wordListL.get(9710));
		System.out.println(wordListL.get(168480));
		System.out.println(wordListL.get(242198));
		System.out.println(wordListL.get(264218));
		System.out.println(wordListL.get(272520));
		System.out.println(wordListL.get(619880));
		System.out.println(wordListL.get(649602));
		System.out.println(wordListL.get(1041656));
		
	}
	
}
