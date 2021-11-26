// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 26, 2020

package main;


public class RaceTrack {
	
	public static int MAX_CARS = 10;	
	FinishLine finish;
	PitStop pitStop;
	int tickCount;
	int nullCars; 
	int place;	
	int RaceCarCount;
	int FormCarCount;
	int SportsCarCount;
	Car[] allCars;

	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events. For more see the assignment PDF / documentation for TrackLoggerB.java
	 */

	private TrackLoggerC logger;
	
	// generic constructor
	public RaceTrack() {
		this.logger = new TrackLoggerC();
		this.nullCars = 0;
		this.tickCount = 1;
		this.place = 0;
		this.finish = new FinishLine(MAX_CARS);
		this.pitStop = new PitStop(MAX_CARS);

	}
	/**
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	public TrackLoggerC getLogger() {
		return logger;
	}
		
	// sets the cars in the race
	public void setCars(Car[] setCar) {
		this.allCars = new Car[MAX_CARS];		
		int count = 0; // makes sure no more than 10 cars are entered in the race
		for (int i = 0; i < setCar.length; i++) {
			if (count < 10) {
				allCars[i] = setCar[i];
			} else {
				break;
			}
			count++;
			}
		countCars();
	}			
	
	// if less than 10 cars have entered there will be null elements in allCars
	// the nullCount needs to reflect these extra null elements, otherwise the while loop running the race will be infinite 
	// this method increments nullCars and carsDone if necessary to reflect these extra nulls 
	public void countCars() {
		for (int i=0; i< this.allCars.length; i++) {
				if (this.allCars[i]==null) {
					this.nullCars++;
					this.finish.incrementCarsDone();
				} else if (this.allCars[i] != null) {
					String carType = this.allCars[i].getType();
					if (carType.equals("RaceCar")) {
						this.RaceCarCount++; // keeps track how many of each car is in the race for the score 
					} else if (carType.equals("FormulaOne")){
						this.FormCarCount++;
					} else {
						this.SportsCarCount++;
					}
				}	
		}
	}
		
	// this method moves cars in a tick
	public void tick(){
		nullPit();	
		int carSpeed; 
		for (int i = 0; i < this.allCars.length; i++) {
			// Move all cars based on speed
			if (this.allCars[i] != null) {
				carSpeed = allCars[i].getSpeed();  //car's speed determines how much a car will move in each tick
				this.allCars[i].moveLocation(carSpeed); // moves the car
			}
		}
		checkCollision(); 
		checkPitStop(); 
		finishAndScore(); 
	}
	
	// this method checks if cars have collided, and reduces the speed if they are damaged 
	public void checkCollision() {		
		
		int length = this.allCars.length;
		int newSpeedI, newSpeedJ;
		// using i and j in a nested for loop to check each car against each other 
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if (this.allCars[i]!=null && this.allCars[j]!=null) {
					
					if (!this.allCars[i].toString().equals(this.allCars[j].toString()) // not the same car
						&& this.allCars[i].getLocation()%100==this.allCars[j].getLocation()%100) { // same location
												
						// Check if i is damaged. If not, reduce speed
						if (!this.allCars[i].isDamaged()) {
							this.allCars[i].setDamaged(true);
							
							// set new speed
							newSpeedI = this.allCars[i].getSpeed() - (this.allCars[i].getStrength() * 5);
							this.logger.logDamaged(this.allCars[i]); // prints racecar is damaged
							this.allCars[i].setSpeed(newSpeedI);
						}
						
						// Check if j is damaged. If not, reduce speed
						if (!this.allCars[j].isDamaged()) {
							this.allCars[j].setDamaged(true);
							
							// set new speed
							newSpeedJ = this.allCars[j].getSpeed() - (this.allCars[j].getStrength() * 5);
							this.logger.logDamaged(this.allCars[j]);
							this.allCars[j].setSpeed(newSpeedJ);
						}
					}
				}
			}
		}
	}
	
	// this method checks if a car should enter the pitstop based on its damage and location
	public void checkPitStop() {
		
		for (int i=0; i<this.allCars.length; i++) {
			if(this.allCars[i] != null) {
				// Enter pit stop condition: a car is damaged and it has passed the 75th tick
				if (this.allCars[i].isDamaged() && (this.allCars[i].getLocation() %100 - this.allCars[i].getSpeed() < 75) && (this.allCars[i].getLocation() % 100 >= 75)) {
					this.allCars[i].setPitTick(this.tickCount);
					this.pitStop.enterPitStop(this.allCars[i], i, this.logger); // pass in logger to print
					
					// because the car has passed the 75th tick, but it entered the pitstop, it's location should now be
					// at the 75th tick, so needs to move back 
					
					// if the car has entered a new lap, need to adjust location
					if (this.allCars[i].getLocation() / 100 > (this.allCars[i].getLocation() - this.allCars[i].getSpeed()) / 100) {
						this.allCars[i].setLocation((this.allCars[i].getStrictLap() - 1) * 100 + 75);
					} else {
						this.allCars[i].setLocation(this.allCars[i].getStrictLap() * 100 + 75);
					}
					// ^ if the car is at location 580 when went into pitstop, moves back to 575, etc.
					this.allCars[i] = null; // car becomes null in the track because it has entered the pitstop
					
				}
			}
		}
	}
	
	// this method checks if a car has finished based on its location and updates the score 	
	public void finishAndScore() {
		int score = 0;
		for (int i=0; i<this.allCars.length; i++) {
				if (this.allCars[i] != null) { 
					
					if (this.allCars[i].getLocation() >= 1000) { // Check if finished
						this.place++; // keeping track of which car finish in which place
						this.allCars[i].setSpeed(this.allCars[i].getOrigSpeed());
						logger.logFinish(this.allCars[i], this.place); // print racecar finishes
						this.finish.enterFinishLine(this.allCars[i], this.nullCars);
						this.allCars[i] = null; // car becomes null once it has finished the race
						this.nullCars++;
						if (this.finish.finished() == true) { // all cars have finished
							score = calculateScore(this.tickCount, this.finish.getCarFromFinish(i));
							this.logger.logScore(score); // prints score  

						}						
					}
				}
			}	
	}
	
	
	// this method checks if the car should re-enter the race from pitstop, and if so exits it from pitstop
	public void nullPit() {
		for (int i=0; i<this.allCars.length; i++) {
			if (this.pitStop.getPitCar(i) != null) {
				// Exit pit stop
				if (this.pitStop.getPitCar(i).getPitTick()<=(this.tickCount-2)) { // if 2 ticks have passed since it entered the pit stop
					this.allCars[i] = this.pitStop.getPitCar(i); // this is to know which car to put back into the race
					this.allCars[i].setDamaged(false); 
					this.allCars[i].setSpeed(this.allCars[i].getOrigSpeed()); // the car returns to its original, non-damaged speed
					this.pitStop.exitPitStopCar(i, this.logger); 
				}
			}
		}
	}
	
	// this method processes the ticks, runs each car
	public void run() {
		
		// while there are still cars in the race
		while ((this.nullCars) < (this.allCars.length)) {
			logger.logNewTick(); // prints the tick 
			tick();
			
			// edge case 
			if (this.allCars[0] != null) {
				if (this.allCars[0].getSpeed()<0) {
					break;
				}
			}
			tickCount++; // keeping track of each tick 
		}
	}
	
	// calculates the score of the race based on the given formula
	public int calculateScore(int ticks, Car car) {
		return 1000 - (20*ticks) + (150 * this.RaceCarCount) + (100 * FormCarCount) + (200 * SportsCarCount);		
	}
}


