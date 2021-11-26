// Hannah Whitmore
// PA #0
// 1/23/2019
// Given a birthyear, prints the year digit by digit, left to right 

public class Problem1{
	public static void main(String [] args){
		int birthYear = 1998;
		int count = 0;
		for (int i=birthYear; i>0; i/=10){
			count++;
		}

		int divideBy = 1;
		for (int i = 0; i<(count-1); i++){
			divideBy *= 10;
		}

		for (int i=0; i<count; i++){
			System.out.println(birthYear/divideBy);
			birthYear = birthYear%divideBy;
			divideBy /=10;
		}
	}
	
}

