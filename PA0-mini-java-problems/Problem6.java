// Hannah Whitmore
// PA #0
// 1/23/2019
// Printing which quadrent a given coordinate is in 

public class Problem6{
	public static void main(String [] args){
		double x = 1;
		double y = 2;
		if (x == 0 || y == 0){ //coordinate is on one of the axes 
			System.out.println("0");
		}
		else if (x>0){ // if the x coordinate is positive, the point will either be in quad 1 or 4 
			if (y>0){ //both x and y are positive 
				System.out.println("1");
			} else{ //x is positive and y is negative 
				System.out.println("4");
		
			} 
		}
		else { //if the x coordinate is negative, the point will either be in quad 2 or 3 
			if (y>0){ // x is negative and y is positive 
					System.out.println("2");
				} else{ // both x and y are negative 
					System.out.println("3");
				}
			}
		
	}
}