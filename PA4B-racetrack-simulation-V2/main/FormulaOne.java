// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;

// essentially this is the same as the racecar class, because it behaves in the same way
public class FormulaOne {
	int speed;
	int origSpeed;
	int strength;
	int isFormula;
	int totalLocation;
	int relativeLocation;
	int lapNumber;
	boolean damaged;
	int pitStopTick;
	int fPlace;

	
	// constructor that gives preset values of strength and speed within the ranges
	public FormulaOne(int speed, int strength) {
		
		this.isFormula = 1;
		
		if ((speed > 30 || speed < 70) &
			(strength > 3 || strength < 5)) {
			this.speed = speed;
			this.origSpeed = speed;
			this.strength = strength;
			this.totalLocation = 0;
			this.relativeLocation = 0;
			this.lapNumber = 0;
			this.damaged = false;
			this.fPlace =0;
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
		this.isFormula = 1;
		int origSpeed = 50;
		this.speed = 50;
		this.strength = 4;

	}
	// getter for the speed
	public int getSpeed() {
		return this.speed;
	}
	// setter for speed
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	// getter for strength
	public int getStrength() {
		return this.strength;
	}
	// setter, moves the formone car each tick 
	public void moveLocation(int fcarSpeed) {
		this.totalLocation+=fcarSpeed;
		this.relativeLocation = this.totalLocation%100;
		
		this.lapNumber = this.totalLocation / 100;
		
	}
	// setter for location
	public void setLocation(int location) {
		this.totalLocation = location;
		
	}
	// returns if a car is damaged 
	// getter
	public boolean isDamaged() {
		return this.damaged;
	}
	
	//  setter for damage 
	public boolean setDamaged(boolean val) {
		return this.damaged = val;
	}
	
	// getter for original speed, need to retain this value for when the speed is reduced in a collision
	public int getOrigSpeed() {
		return this.origSpeed;
	}
	// getter for the lap number
	public int getStrictLap() {
		return this.totalLocation / 100;
	}
	
	// getter for location
	public int getTotalLocation() {
		return totalLocation;
	}
	
	// sets which tick the car is at when it enters the pitstop
	public void setPitTick(int fcarSpeed) {
		this.pitStopTick = fcarSpeed;
	}
	// gets the pittick number to make sure 2 ticks have passed
	public int getFormPitTick() {
		return this.pitStopTick;
	}
	// tostring method to print the car out
	public String toString() {
		return "FormulaOne" + this.speed + "/" + this.strength;
	}
}
