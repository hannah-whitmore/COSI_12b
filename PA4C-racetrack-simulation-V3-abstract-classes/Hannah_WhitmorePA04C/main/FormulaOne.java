// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;

// essentially this is the same as the racecar class, because it behaves in the same way
public class FormulaOne extends Car {
	
	// specified constructor
	public FormulaOne(int speed, int strength) {
		
		// Valid parameters entered
		if ((speed > 30 || speed < 70) &
			(strength > 3 || strength < 5)) {
			this.speed = speed;
			this.origSpeed = speed;
			this.strength = strength;

		}
		
		// Ensure parameters are in proper range
		if (speed > 70) {
			this.speed = 70;
			this.origSpeed = speed;
		} else if (speed < 30) {
			this.speed = 30;
			this.origSpeed = speed;
		}
		
		if (strength > 5) {
			this.strength = 5;
		} else if (strength < 3) {
			this.strength = 3;
		}

	}
	
	// generic constructor that sets speed and strength to the default values
	public FormulaOne() {
		this.origSpeed = 50;
		this.speed = 50;
		this.strength = 4;

	}
	
	// returns the type of car as a string, used for score calculation
	public String getType() {
		return "FormulaOne";
	}
	
	
	// tostring method to print the car out
	public String toString() {
		return "FormulaOne" + this.speed + "/" + this.strength;
	}
}
