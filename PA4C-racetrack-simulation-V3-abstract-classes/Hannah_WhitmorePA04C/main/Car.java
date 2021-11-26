// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 26, 2020

package main;

public abstract class Car {
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
	
	// sets car fields that are common to all car types
	public Car(int speed, int strength) {	
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
	}
	
	// Initialized to 0 because all fields being set are specific to sub-classes
	public Car() {		
		this.speed=0;
		this.origSpeed=0;
		this.strength=0;
	}
	
	// returns if a car is damaged 
	public boolean isDamaged() {
		return this.damaged;
	}
	
	// sets damaged value 
	public boolean setDamaged(boolean val) {
		return this.damaged = val;
	}
	
	// sets speed value
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// getter for original speed
	public int getOrigSpeed() {
		return this.origSpeed;
	}
	
	// getter for speed
	public int getSpeed() {
		return this.speed;
	}
	
	// getter for strength
	public int getStrength() {
		return this.strength;
	}
	
	// setter for the score, based on formula 
	public void setScore(int score) {
		this.score = score;
	}
	
	// getter for the score 
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
		
	// gets the pitstop tick number to make sure 2 ticks have passed before a car can exit pitstop
	public int getPitTick() {
		return this.pitStopTick;
	}
	
	// getter for place
	public int getPlace() {
		return this.place;
	}

	// setter for the location of the car, moves the car with each tick
	public void setLocation(int location) {
		this.totalLocation = location;
		
	}
	
	// moves car location based on car speed
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

	// overridden in subclasses for specific type 
	public String getType() {
		return "Car";
	}
	
}
