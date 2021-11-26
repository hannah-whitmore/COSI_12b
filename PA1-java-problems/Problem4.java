// Hannah Whitmore
// PA #1
// 1/31/19
// Produces a Caesar cipher of a given message string

import java.util.*;

public class Problem4{
	public static void main(String [] args){
		Scanner console = new Scanner(System.in);
		System.out.print("Your message? ");
		String message = console.nextLine();
		System.out.print("Encoding key? ");
		int rotation = console.nextInt();
		String newMessage = caesar(message, rotation);
		System.out.println("Your message: " + newMessage);
	}
	public static String caesar(String message, int rotation){
		message = message.toUpperCase();
		int newRot = 0;
		String newMessage = "";
		for (int i=0; i<message.length(); i++){ // looping through the message
			newRot = rotation;
			char character = message.charAt(i); // each letter of the message
			int value = (int)character; // getting ascii value of each character 
			if (value != 32){ // a space's ascii value is 32, and want spaces to remain the same in the output , so this runs on non-space characters
				newRot = newRot%26; // if the rotation is greater than 26, %26 to get back
				value += newRot;
				if (value>90){ // if rotation gives a value bigger than 90, this wraps it back around the alphabet
					value = value-90 + 65 -1;
				}
			}
			newMessage += (char)value;
		}
		return newMessage;
		

	}
}