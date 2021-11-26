// Hannah Whitmore
// PA #1
// 1/31/19
// Uses a student's grades on homework, midterm, and final exam to compute overall course grade

import java.util.*;

public class Problem5{
	public static void main(String [] args){
		System.out.println("\n" + "This program accepts your homework and exam" + "\n" + "scores as input, and computes your grade in" + "\n" + "the course or indicates what grade you need" + "\n" + "to earn on the final exam." + "\n");
		double hwWeightedScore = homework();
		System.out.printf("\t" + "Weighted score: %.2f\n" + "\n", hwWeightedScore);
		double midWeightedScore = midtermExam();
		System.out.printf("\t" + "Weighted exam score: %.1f\n" + "\n", midWeightedScore);
		double hwAndMid = hwWeightedScore + midWeightedScore;
		finalExam(hwAndMid);

	}

	public static double homework(){
		Scanner console = new Scanner(System.in);
		System.out.println("Homework: ");
		System.out.print("\t" + "What is its weight (0-100)? ");
		int hwWeight = console.nextInt();
		System.out.print("\t" + "How many homework assignments were there? ");
		int hwNum = console.nextInt();
		double sumEarned = 0;
		double sumPossible = 0;
		int hwScore = 0;
		int hwMax = 0;

		for (int i=1; i<hwNum+1; i++){ // hwNum is the number of homework assignments there were, question is asksed this many times
			System.out.print("\t" + "Homework " + i + " score and max score: ");
			hwScore = console.nextInt(); // the first number is the homework score
			sumEarned += hwScore;
			hwMax = console.nextInt(); // after the space, the second number is the maximum hw score possible
			sumPossible += hwMax;

			// homework grade = (sum of earned points)/(sum of possible points) * homework weight
			// this caculates the numerator (sumEarned) and denominator (sumPossible)
		}
		sumPossible = 1/sumPossible; 
		sumEarned = sumEarned*sumPossible;
		// dividing these two in java would give the wrong answer, and dividing is the same as multiplying by the reciprocal,
		// which gives the right answer in java
		double hwWeightedScore = sumEarned*hwWeight; // final step of the hw grade formula 
		return hwWeightedScore;
	}
	
	public static double midtermExam(){
		Scanner console = new Scanner(System.in);
		System.out.println("Midterm exam: ");
		System.out.print("\t" + "What is its weight (0-100)? ");
		double midWeight = console.nextDouble();
		System.out.print("\t" + "Exam score: ");
		int midScore = console.nextInt();
		System.out.print("\t" + "Was there a curve (1 for yes, 2 for no) ");
		int isCurve = console.nextInt();
		if (isCurve == 1){ // if there was a curve
			System.out.print("\t" + "How much was the curve? ");
			int curve = console.nextInt();
			if (midScore + curve >100){ // curve cannot make the score more than 100%, if this is the case, midScore gets assigned 100
				midScore = 100;
			} else{ // the curve gets added to the midterm score (midScore)
				midScore += curve; 
			}
			
		}
			midWeight = midWeight*.01; // this is the same as dividing by 100 , but in java this would give 0
			// midterm total points is 100
			double midWeightedScore = midWeight*midScore; // last step in midterm grade calculation
		return midWeightedScore;
	}
	public static void finalExam(double hwAndMid){
		Scanner console = new Scanner(System.in);
		System.out.println("Final exam: ");
		System.out.print("\t" + "Have you taken the final exam yet? (1 for yes, 2 for no) ");
		int ifTaken = console.nextInt();
		if (ifTaken == 1){
			System.out.print("\t" + "What is its weight (0-100)? ");
			double finalWeight = console.nextDouble();
			System.out.print("\t" + "Exam score: ");
			int finalScore = console.nextInt();
			System.out.print("\t" + "Was there a curve? (1 for yes, 2 for no) ");
			int isCurve = console.nextInt();
			if (isCurve == 1){
				System.out.print("\t" + "How much was the curve? ");
				int curve = console.nextInt();
				if (finalScore + curve >100){ // if curve makes the grade above 100, score of 100 is used
					finalScore = 100;
				} else{
					finalScore += curve;
				}
			}
			finalWeight = finalWeight*.01; // same as dividing by 100, total points possible is 100
			double finalWeightedScore = finalWeight*finalScore; // last step in final exam grade calculation
			System.out.println("\t" + "Weighted exam score: " + finalWeightedScore);
			// added the weighted homework and midterm scores in other method (this became hwAndMid) and adding that to the final weighted score gives overal course grade
			double courseGrade = hwAndMid + finalWeightedScore; 
			System.out.printf("\n" + "\t" + "Your course grade is %.2f\n",courseGrade);
		}
		else { // if they have not taken the final exam yet
			System.out.print("\t" + "What is its weight (0-100)? ");
			double finalWeight = console.nextDouble();
			System.out.print("\t" + "What percentage would you like to earn in the course? ");
			double percent = console.nextDouble();
			finalWeight = finalWeight*.01; // same as dividing by 100, total points possible is 100
			// formula for percent needed on final is :
			// percent needed = ((grade desired)-(weighed homework + midterm))/fnal weight
			percent = percent - hwAndMid;
			percent = percent / finalWeight;
			System.out.println();
			System.out.printf("\t" + "You need a score of %.2f" + " on the final exam.\n", percent);
			if (percent >100){ // if percent needed for desired grade is greater than 100
				double highestPercent = hwAndMid + (finalWeight*100); // calculates highest possible course grade by using 100% for final grade
				System.out.printf("\t" + "Sorry, it is impossible to achieve this percentage." + "\n" + "\t" + "The highest percentage you can get is %.2f.\n", highestPercent);
			}
			System.out.println();

		}	
	}
	
}