// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing the desired output of stars and slashes 

public class Problem8{
	public static void main(String [] args){
		for (int i = 1; i<7; i++){
			for(int j=0; j<(6-i); j++){
				System.out.print("*"); // left side of decreasing stars 
			}
			for (int j=0; j<i; j++){
				System.out.print(" "); //left side of increasing spaces
			}
			for (int j=0; j<(6-i); j++){ //decreasing forward slashes
				System.out.print("//");
			}
			for (int j=0; j<(i-1); j++){
				System.out.print("\\\\"); //increasing backslashes
			}
			for (int j=0; j<i; j++){
				System.out.print(" "); //right side of increasing spaces
			}
			for(int j=0; j<(6-i); j++){
				System.out.print("*"); //right side of decreasing starss
			}
			System.out.println("");

		}
	}
} 