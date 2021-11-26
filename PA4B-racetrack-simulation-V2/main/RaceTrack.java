// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;


public class RaceTrack {
	
	// fields 
	public static int MAX_RACE_CARS = 10;	
	RaceCar[] cars;
	FormulaOne[] fCars;
	FinishLine finish;
	PitStop pitStop;
	int tickCount;
	int nullCars; 
	int formNullCars;
	int place;	
	int totalCarCount;
	
	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events. For more see the assignment PDF / documentation for TrackLoggerB.java
	 */

	private TrackLoggerB logger;
	
	// constructor
	public RaceTrack() {
		this.logger = new TrackLoggerB();
		// TODO: PA says to construct cars, finishline, and pitstop, but...
		// we don't know the size of the cars array
		//pitStop = new PitStop[];
		this.nullCars = 0;
		this.tickCount = 1;
		this.place = 0;
		this.formNullCars = 0;
		
		
		// logger = new TrackLoggerA(); // DO NOT REMOVE THIS LINE
	}
	
	/**
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	public TrackLoggerB getLogger() {
		return logger;
	}
	
	// this method sets the cars in the race, and enforces it to be 10 or less cars, only racecars here
	public void setCars(RaceCar[] raceCars) {	

		// Enforce maximum of 10 race cars
		RaceCar[] newRaceCars = new RaceCar[MAX_RACE_CARS];	
		
		
		// if more than 10 cars are entered, only accept 10 cars
		if (raceCars.length > 10) {
			for (int i = 0; i < newRaceCars.length; i++) {
				newRaceCars[i] = raceCars[i]; 
			}
			this.totalCarCount = 10;
		} else {
			this.totalCarCount = newRaceCars.length;
		}
		
		this.cars = newRaceCars;
		this.finish = new FinishLine(this.cars.length);
		this.pitStop = new PitStop(this.cars.length);
	}
	
	// this method sets the cars in the race, and enforces it to be no more than 10, including formulaone cars
	public void setCars(RaceCar[] raceCars, FormulaOne[] formulaOnes) {
		RaceCar[] newRaceCars = new RaceCar[MAX_RACE_CARS];
		FormulaOne[] newFRaceCars = new FormulaOne[MAX_RACE_CARS];
		// racecars get priority, they go in first, and only if there are not 10 cars do formulaones get entered
		
		int count = 0;
		for (int i = 0; i < raceCars.length; i++) {
			if (count < 10) {
				newRaceCars[i] = raceCars[i];
			} else {
				break;
			}	
			count++; // keeping track of cars entered
		}
		// if all racecars went in and car count is less than 10, now formulaones go in
		if (count < 10) {
			for (int i = 0; i < formulaOnes.length; i++) {
				if (count < 10) {
					newFRaceCars[i] = formulaOnes[i];
				} else {
					break;
				}	
				count++;
			}
		}
		this.cars = newRaceCars;
		this.fCars = newFRaceCars;	
		
		this.finish = new FinishLine(this.cars.length, this.fCars.length);
		this.pitStop = new PitStop(this.cars.length, this.fCars.length);
		countCars();
	}
	
	// because i've set each array to be of max length 10, if less than 10 cars have entered there will be null elements.
	// later, when im checking when to exit the race, (all cars finished) it counts the amount of null cars (finished cars) 
	// the nullcount now needs to reflect these extra null elements if less than 10 cars entered, otherwise the loop will be infinite 
	// this method adds to nullCars as needed
	public void countCars() {	
		for (int i = 0; i < this.cars.length; i++) {
			if (this.cars[i]==null) {
				this.nullCars++;
				this.finish.incrementCarsDone();
			} else {
				this.totalCarCount++; // this will be used later to calculate the score
			}
		}
		
		for (int i = 0; i < this.fCars.length; i++) {
			if (this.fCars[i]==null) {
				this.formNullCars++;
				this.finish.incrementFCarsDone();
			} else {
				this.totalCarCount++;
			}
		}	
	}
	
	// this method moves cars in a tick
	public void tick(){
		
		int carSpeed; //car's speed determines how much a car will move in each tick 
		int localNull = 0;
		for (int i = 0; i < this.cars.length; i++) {
			
			// Move all cars based on speed, as long as its not null (still in the race)
			// for race cars
			if (this.cars[i] != null) {
				carSpeed = cars[i].getSpeed(); // a tick moves every car a distance according to is speed, carSpeed represents that distance
				this.cars[i].moveLocation(carSpeed); // this is what actually moves the car, changes its location
		
			} else {
				localNull++;
			}
		}
		// same but for formulaone cars now
		int fCarSpeed;
		for (int i=0; i< this.fCars.length; i++) {
			if (this.fCars[i] != null) {
				fCarSpeed = fCars[i].getSpeed();
				this.fCars[i].moveLocation(fCarSpeed);
			}
		}

		checkPitStop();
		finishAndScore();
		nullPit();
		checkCollision();	
	}
	
	// this method checks if a car should enter the pitstop based on its damage and location
	public void checkPitStop() {
		
		// for racecars : 
		for (int i=0; i<cars.length; i++) {
			if(this.cars[i] != null) {
				// Enter pit stop condition: a car is damaged and it has passed the 75th tick
				if (this.cars[i].isDamaged() && (this.cars[i].getTotalLocation() %100 - this.cars[i].getSpeed() < 75) && (this.cars[i].getTotalLocation() % 100 >= 75)) {
					this.cars[i].setPitTick(this.tickCount);
					this.pitStop.enterPitStop(this.cars[i], i, this.logger); // pass in logger to print
					
					// because the car has passed the 75th tick, but it entered the pitstop, it's location should now be
					// at the 75th tick, so needs to move back 
					
					// if the car has entered a new lap
					if (this.cars[i].getLocation() / 100 > (this.cars[i].getLocation() - this.cars[i].getSpeed()) / 100) {
						this.cars[i].setLocation((this.cars[i].getStrictLap() - 1) * 100 + 75);
					} else {
						this.cars[i].setLocation(this.cars[i].getStrictLap() * 100 + 75);
					}
					// ^ if the car is at location 580 when went into pitstop, moves back to 575, etc.
					this.cars[i] = null; // car becomes null in the track because it has entered the pitstop
					
				}
			}
		}
		// for formulaone cars: 
		// this is all of the same logic as racecar but just for fomula one
		// i know this is super redundant, but we couldn't use inheritance and I don't see a better way :( 
		for (int i=0; i<fCars.length; i++) {
			if(this.fCars[i] != null) {
				if (this.fCars[i].isDamaged() && (this.fCars[i].getTotalLocation() %100 - this.fCars[i].getSpeed() < 75) && 
						(this.fCars[i].getTotalLocation() % 100 >= 75)) {
					this.fCars[i].setPitTick(this.tickCount);
					this.pitStop.enterPitStop(this.fCars[i], i, this.logger);
					
					if (this.fCars[i].getTotalLocation() / 100 > (this.fCars[i].getTotalLocation() - this.fCars[i].getSpeed()) / 100) {
						this.fCars[i].setLocation((this.fCars[i].getStrictLap() - 1) * 100 + 75);
					} else {
						this.fCars[i].setLocation(this.fCars[i].getStrictLap() * 100 + 75);
					}
					this.fCars[i] = null;
				
				}
			}
		}
		
	}
	
	
	// this method checks if a car has finished based on its location and gets the score 	
	public void finishAndScore() {
		int score = 0;
		for (int i=0; i<cars.length; i++) {
				if (this.cars[i] != null) { // car becomes null to remove it from race when its either in pitstop or has finished
					// Check if finished
					if (this.cars[i].getLocation() >= 1000) {
						
						this.place++; // keeping track of which cars finish in which place
						this.cars[i].setSpeed(this.cars[i].getOrigSpeed());
						logger.logFinish(this.cars[i], this.place); // print racecar finishes
						finish.enterFinishLine(this.cars[i], nullCars);
						this.cars[i] = null; // car becomes null once it has finished the race
						this.nullCars++; // keeping track of null cars to know when to end the race (after all cars have finished)
						
						if(this.finish.finished() == true) { 
															// boolean finished is checked against nullCars
							score = calculateScore(this.tickCount);
						}						
					}
				}
			}
		
		// again, same logic but for formulaones, again : ( 
		int fScore = 0;
		for (int i=0; i<fCars.length; i++) {
			if (this.fCars[i] != null) { // car becomes null to remove it from race when its either in pitstop or has finished
				// Check if finished
				if (this.fCars[i].getTotalLocation() >= 1000) {
					this.place++; // keeping track of which cars finish in which place
					this.fCars[i].setSpeed(this.fCars[i].getOrigSpeed());
					logger.logFinish(this.fCars[i], this.place); // print formula car finishes
					finish.enterFinishLine(this.fCars[i], formNullCars);
					this.fCars[i] = null; // car becomes null once it has finished the race
					this.formNullCars++; // keeping track of null cars to know when to end the race (after all cars have finished)
					if(this.finish.finished() == true) { 
														// boolean finished is checked against formNullCars
						fScore = calculateScore(this.tickCount);
					}						
				}
			}
		}
		// the score will only be calculated once the race has finished, aka all cars are null
		// this bit of logic checks if all the cars are null, both racecar and formulaone cars, 
		// and if so, prints the score (which is racecar score + formula one cars score)
		boolean allNull = true;
		for (int i = 0; i < this.cars.length; i++) {
			if (this.cars[i]!=null) {
				allNull = false;
			}
		}
		for (int i = 0; i < this.fCars.length; i++) {
			if (this.fCars[i]!=null) {
				allNull = false;
			}
		}
		if (allNull) {
			this.logger.logScore(score+fScore); // prints score  
		}
		
			
	}
	// this method checks if the pitstop index is null, which means it has exited the pitstop and should now re-enter the race track 
	public void nullPit() {
		for (int i=0; i<cars.length; i++) {
			// Check if pit stop is null
			if (this.pitStop.getPitCar(i) != null) {
				// Exit pit stop
				if (this.pitStop.getPitCar(i).getPitTick()<=(this.tickCount-2)) { // if 2 ticks have passed since it entered the pit stop
					//System.exit(0);
					this.cars[i] = this.pitStop.getPitCar(i); // this is to know which car to put back into the race
					this.cars[i].setDamaged(false); // car is no longer damaged
					this.cars[i].setSpeed(this.cars[i].getOrigSpeed()); // the car returns to its original, non-damaged speed
					this.pitStop.exitPitStopCar(i, this.logger); // prints racecar exits the pit stop					
				}
			}
		}
		// again, same logic but for formulaone cars ...
		for (int i=0; i<fCars.length; i++) {
			if (this.pitStop.getFormPitCar(i) != null) {
			
				if (this.pitStop.getFormPitCar(i).getFormPitTick() == (this.tickCount -2)){
					this.fCars[i] = this.pitStop.getFormPitCar(i);
					this.fCars[i].setDamaged(false);
					this.fCars[i].setSpeed(this.fCars[i].getOrigSpeed());
					this.pitStop.exitPitStopFCar(i, this.logger); // prints formula car exits pit stop
				
				}
			}
		}
	}
	
	// this method checks if cars have collided
	public void checkCollision() {		
		
		// Check if there's a collision within racecars
		int length = this.cars.length;
		int newSpeedI, newSpeedJ;
		// using i and j in a nested for loop to check each car against each other to see if they have collided
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (this.cars[i]!=null && this.cars[j]!=null) {
					
					
					if (!this.cars[i].toString().equals(this.cars[j].toString())
						&& this.cars[i].getTotalLocation()%100==this.cars[j].getTotalLocation()%100) {
												
						// Check if i is damaged. If not, reduce speed
						if (!this.cars[i].isDamaged()) {
							this.cars[i].setDamaged(true);
							
							// set new speed
							newSpeedI = this.cars[i].getSpeed() - (this.cars[i].getStrength() * 5);
							this.logger.logDamaged(this.cars[i]); // prints racecar is damaged
							this.cars[i].setSpeed(newSpeedI);
						}
						
						// Check if j is damaged. If not, reduce speed
						if (!this.cars[j].isDamaged()) {
							this.cars[j].setDamaged(true);
							
							// set new speed
							newSpeedJ = this.cars[j].getSpeed() - (this.cars[j].getStrength() * 5);
							this.logger.logDamaged(this.cars[j]); // prints formula car is damaged
							this.cars[j].setSpeed(newSpeedJ);
						}
					}
				}
			}
		}
		
		// Check if collision within formulaone cars
		int formLength = this.fCars.length;
		int newFormSpeedI, newFormSpeedJ;
		// using i and j in a nested for loop to check each car against each other to see if they have collided
		for (int i = 0; i < formLength; i++) {
			for (int j = 0; j < formLength; j++) {
				if (this.fCars[i]!=null && this.fCars[j]!=null) {
					
					
					if (!this.fCars[i].toString().equals(this.fCars[j].toString())
						&& this.fCars[i].getTotalLocation()%100==this.fCars[j].getTotalLocation()%100) {
												
						// Check if i is damaged. If not, reduce speed
						if (!this.fCars[i].isDamaged()) {
							this.fCars[i].setDamaged(true);
							
							// set new speed
							newFormSpeedI = this.fCars[i].getSpeed() - (this.fCars[i].getStrength() * 5);
							this.logger.logDamaged(this.fCars[i]); // prints damaged
							this.fCars[i].setSpeed(newFormSpeedI);
						}
						
						// Check if j is damaged. If not, reduce speed
						if (!this.fCars[j].isDamaged()) {
							this.fCars[j].setDamaged(true);
							
							// set new speed
							newFormSpeedJ = this.fCars[j].getSpeed() - (this.fCars[j].getStrength() * 5);
							this.logger.logDamaged(this.fCars[j]); // prints damaged
							this.fCars[j].setSpeed(newFormSpeedJ);
						}
					}
				}
			}
		}
		// Now have to check if there's a collision between a racecar and a formula one car.. all the same logic
		int carsLength = this.cars.length;
		int fCarsLength = this.fCars.length;
		int carsNewSpeed, fCarsNewSpeed;
		// using i and j in a nested for loop to check each car against each other to see if they have collided
		for (int i = 0; i < carsLength; i++) {
			for (int j = 0; j < fCarsLength; j++) {
				if (this.cars[i]!=null && this.fCars[j]!=null) {
					
					if (this.cars[i].getTotalLocation()%100==this.fCars[j].getTotalLocation()%100) {
												
						// Check if i is damaged. If not, reduce speed
						if (!this.cars[i].isDamaged()) {
							this.cars[i].setDamaged(true);
							
							// set new speed
							carsNewSpeed = this.cars[i].getSpeed() - (this.cars[i].getStrength() * 5);
							this.logger.logDamaged(this.cars[i]); // prints damaged
							this.cars[i].setSpeed(carsNewSpeed);
						}
						
						// Check if j is damaged. If not, reduce speed
						if (!this.fCars[j].isDamaged()) {
							this.fCars[j].setDamaged(true);
							
							// set new speed
							fCarsNewSpeed = this.fCars[j].getSpeed() - (this.fCars[j].getStrength() * 5);
							this.logger.logDamaged(this.fCars[j]); // prints damaged
							this.fCars[j].setSpeed(fCarsNewSpeed);
						}
					}
				}
			}
		}
	}
	
	
	// this method processes the ticks, runs each car
	public void run() {
		// runs while there are still cars in the race, the elements aren't all null
		// once a car finishes its element  becomes null
		
		// while the amount of null cars is less than the amount of cars in the race
		while ((this.nullCars + this.formNullCars) < (this.cars.length + this.fCars.length)) {
			logger.logNewTick(); // prints the tick 
			tick();
			// edge case 
			if (this.cars[0] != null) {
				if (this.cars[0].getSpeed()<0) {
					break;
				}
			}
			if (this.fCars[0] != null) {
				if (this.fCars[0].getSpeed()<0) {
					break;
				}
			}
			tickCount++; // keeping track of each tick 
		}
	}
	
	// calculates the score of the race based on the given formula
	public int calculateScore(int ticks) {
		return 1000 - (20*ticks) + (150 * totalCarCount);	
	}
	
}
