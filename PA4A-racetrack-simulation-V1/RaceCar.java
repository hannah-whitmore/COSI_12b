public class RaceCar {

	int speed;
	int origSpeed;
	int strength;
	int unitLocation;
	int totalLocation;
	int lapNumber;
	int place;
	int score;
	boolean damaged;
	int pitStopTick;
		
	// this is the constructor. initializes the fields and makes sure the strength and speed fields are within the right range
	public RaceCar(int speed, int strength) {	
		this.unitLocation = 0;
		this.totalLocation = 0;
		this.lapNumber = 1;
		this.place = 0;
		this.score = 0;
		this.damaged = false;
		this.origSpeed = 0;
		
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
	
	// returns if a car is damaged because the speed is only reduced from a collision if a car wasn't previously damaged
	// getter method 
	public boolean isDamaged() {
		return this.damaged;
	}
	
	// setter method 
	public boolean setDamaged(boolean val) {
		return this.damaged = val;
	}
	
	// setter method for the speed, this is needed when the speeds change from a collision
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	// getter for the speed, needed because the cars go back to their original speed after leaving the pitstop
	public int getOrigSpeed() {
		return this.origSpeed;
	}
	
	// getter for strength, needed as part of the formula for the new speed after a collision
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
	
	// this is to keep track of which tick the car is at before entering the ptistop to know when to put it back in the race
	// tick = car speed
	public void setPitTick(int tick) {
		this.pitStopTick = tick;
	}
	// this gets the pittick number to make sure 2 ticks have passed
	public int getPitTick() {
		return this.pitStopTick;
	}
	
	// place getter
	public int getPlace() {
		return this.place;
	}

	// setter for the location of the car, moves the car with each tick
	// TODO: change int tick name
	public void setLocation(int tick) {
		this.unitLocation+=tick;
		this.totalLocation+=tick;
		
		// unit location gets reset after each lap
		if (this.unitLocation >= 100) {
			this.unitLocation = 0;
			this.lapNumber++;
		}
	}
	
	
	// total location getter, used to determine when a new lap has started and also when to enter the pitstop
	public int getLocation() {
		return this.totalLocation;
	}
	
	// in order to use the lap number for location in track 
	public int getStrictLap() {
		return this.lapNumber;
	}
	
	// getter for the speed
	public int getSpeed() {
		return this.speed;
	}
	
	// toString method in order to print out each car with its speed and strength
	public String toString() {
		return "RaceCar" + this.speed + "/" + this.strength;
		// TODO make type of car dynamic
	}
}
