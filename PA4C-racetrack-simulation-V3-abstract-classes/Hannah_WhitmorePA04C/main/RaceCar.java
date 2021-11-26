// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 26, 2020

package main;
public class RaceCar extends Car {

	// this is the constructor. initializes the fields and makes sure the strength and speed fields are within the right range
	public RaceCar(int speed, int strength) {
		super(speed, strength);

		// Valid parameters entered
		if ((speed > 30 || speed < 55) &
			(strength > 2 || strength < 4)) {
			this.speed = speed;
			this.origSpeed = speed;
			this.strength = strength;
		}
		
		// Ensure parameters are in proper range
		if (speed > 55) {
			this.speed = 55;
			this.origSpeed = speed;
		} else if (speed < 30) {
			this.speed = 30;
			this.origSpeed = speed;
		}
		
		if (strength > 4) {
			this.strength = 4;
		} else if (strength < 2) {
			this.strength = 2;
		}
	}
	
	// generic constructor that sets speed and strength to the default values 
	public RaceCar() {		
		this.speed = 40;
		this.origSpeed = speed;
		this.strength = 3;
	}
	
	// returns the type of car as a string, used for score calculation
	public String getType() {
		return "RaceCar";
	}
	
	// toString method in order to print out each car 
	public String toString() {
		return "RaceCar" + this.speed + "/" + this.strength;
	}
}
