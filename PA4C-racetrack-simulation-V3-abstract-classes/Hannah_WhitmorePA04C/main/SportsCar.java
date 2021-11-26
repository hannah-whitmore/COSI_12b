// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;

public class SportsCar extends Car {
	
	public SportsCar(int speed, int strength) {
		super(speed, strength);
		
		this.speed = speed;
		this.strength = strength;
		this.origSpeed = speed;
		
		// valid parameters entered
		if ((speed > 20 || speed < 45) & (strength > 1 || strength < 3 )) {
			this.speed = speed;
			this.strength = strength;
			this.origSpeed = speed;
		}
		
		// Ensure parameters are in proper range
		if (speed > 45) {
			this.speed = 45;
			this.origSpeed = speed;
		} else if (speed < 20) {
			this.speed = 20;
			this.origSpeed = speed;
		}
		
		if (strength > 3) {
			this.strength = 3;
		} else if (strength < 1) {
			this.strength = 1;
		}
	}
	// generic constructor 
	public SportsCar() {
		this.speed = 30;
		this.strength = 2;
		this.origSpeed = 30;
	}
	
	// returns the type of car as a string, used for score calculation
	public String getType() {
		return "SportsCar";
	}
	
	// toString method in order to print out each car 
	public String toString() {
		return "SportsCar" + this.speed + "/" + this.strength;
	}
	
	
}
