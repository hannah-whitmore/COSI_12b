// Hannah Whitmore
// PA #1
// 1/31/19
// Printing an entered first and last name as a new full name in pig latin 

import java.util.*;

public class Problem2{
	public static void main(String [] args){
		Scanner console = new Scanner(System.in);
		System.out.print("Enter first name: ");
		String first = console.next();
		System.out.print("Enter last name: ");
		String last = console.next();
		first = first.toLowerCase();
		last = last.toLowerCase();
		pigLatin(first); // method called twice bc same operations on both the first and last name
		pigLatin(last);
		System.out.println();
	}
	public static void pigLatin(String name){
		String newName = ""; //blank string for the new pig latin name
		newName += name.charAt(1); // second letter of the original name will be the first of the new pig latin name
		newName = newName.toUpperCase(); // capitalizes new first letter
		for (int i = 2; i<name.length(); i++){ //starts at the third letter of the name to the end
			newName += name.charAt(i); // fills the rest of the new string with the original name 
		}
		newName += name.charAt(0); // adds the first letter of the orignal name to the end of the new strinng
		newName += "ay"; // adds "ay" to the end of the new string 
		System.out.print(newName + " "); 
	}
}