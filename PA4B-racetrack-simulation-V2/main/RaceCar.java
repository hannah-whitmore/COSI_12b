// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;
public class RaceCar {

	// fields
	int speed;
	int origSpeed;
	int strength;
	int relativeLocation;
	int totalLocation;
	int lapNumber;
	int place;
	int score;
	boolean damaged;
	int pitStopTick;
	int isFormula;
		
	// this is the constructor. initializes the fields and makes sure the strength and speed fields are within the right range
	public RaceCar(int speed, int strength) {
		this.relativeLocation = 0;
		this.totalLocation = 0;
		this.lapNumber = 1;
		this.place = 0;
		this.score = 0;
		this.damaged = false;
		this.origSpeed = 0;
	
		this.speed = speed;
		this.origSpeed = speed;
		this.strength = strength;
		
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
		this.isFormula = 0;
		this.speed = 40;
		this.origSpeed = speed;
		this.strength = 3;
	}
	
	// returns if a car is damaged 
	// getter method 
	public boolean isDamaged() {
		return this.damaged;
	}
	
	// setter method 
	public boolean setDamaged(boolean val) {
		return this.damaged = val;
	}
	
	// setter method for the speed
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// getter for the speed, needed because the cars go back to their original speed after leaving the pitstop
	public int getOrigSpeed() {
		return this.origSpeed;
	}
	
	// getter for strength
	public int getStrength() {
		return this.strength;
	}
	
	// total location returns the total unit the car is on, lap 3 unit 70 would return totalLocation 370
	public int getTotalLocation() {
		return this.totalLocation;
	}
	
	// setter for the score, based on formula 
	public void setScore(int score) {
		this.score = score;
	}
	
	// getter, returns the score 
	public int getScore() {
		return this.score;
	}
	
	// setter, keeps track of the place of each car as they finish
	public void setPlace(int place) {
		this.place = place;
	}
	
	// sets which tick the car is at when it enters the pitstop
	public void setPitTick(int carSpeed) {
		this.pitStopTick = carSpeed;
	}
	// gets the pittick number to make sure 2 ticks have passed
	public int getPitTick() {
		return this.pitStopTick;
	}
	
	// place getter
	public int getPlace() {
		return this.place;
	}

	// setter for the location of the car, moves the car with each tick
	public void setLocation(int location) {
		this.totalLocation = location;
		
	}
	public void moveLocation(int carSpeed) {
		this.totalLocation+=carSpeed;
		this.relativeLocation = this.totalLocation%100;
		
		this.lapNumber = this.totalLocation / 100;
		
	}
	
	
	// total location getter
	public int getLocation() {
		return this.totalLocation;
	}
	
	// in order to use the lap number for location in track 
	public int getStrictLap() {
		return this.totalLocation / 100;
	}
	
	// getter for the speed
	public int getSpeed() {
		return this.speed;
	}
	
	// toString method in order to print out each car 
	
	
	public String toString() {
		return "RaceCar" + this.speed + "/" + this.strength;
		
	}
}
