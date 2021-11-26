
public class RaceTrack {
	
	/**
	 * DO NOT REMOVE THIS - you should be using this to log this track's events in part B. For more see the assignment PDF / documentation for TrackLoggerA.java
	 */
	// private TrackLoggerA logger;
	
	RaceCar[] cars;
	FinishLine finish;
	PitStop pitStop;
	int tickCount;
	int nullCars; 
	int place;
	
	// constructor
	public RaceTrack() {
		// TODO: PA says to construct cars, finishline, and pitstop, but...
		// we don't know the size of the cars array
		// pitStop = new PitStop[];
		this.nullCars = 0;
		this.tickCount = 1;
		this.place = 0;
		
		
		// logger = new TrackLoggerA(); // DO NOT REMOVE THIS LINE
	}
	
	// sets the cars in the race
	// TODO: no more than 10 cars will participate in a given race
	public void setCars(RaceCar[] raceCars) {
		this.cars = raceCars;
		this.finish = new FinishLine(this.cars.length);
		this.pitStop = new PitStop(this.cars.length);
		// TODO: initialize pitStop
	}
	
	public void tick(){
		int tick; // tick = car's speed
		for (int i = 0; i < this.cars.length; i++) {
			
			// (1) Move all cars based on speed
			if (this.cars[i] != null) {
				tick = cars[i].getSpeed(); // a tick moves every car a distance according to is speed, tick represents that distance
				this.cars[i].setLocation(tick); // this is what actually moves the car, changes its location
			//	System.out.println(cars[i] + " pos: " + cars[i].getLocation());
			}
		}
		checkPitStop();
		finishAndScore();
		nullPit();
		checkCollision();
	}
	// this method checks if a car should enter the pitstop based on its damage and location
	// TODO: damaged car not entering the pitstop, entering at wrong tick
	// TODO: if cars collide at unit 76, they'll immediately go into pit stop but should enter the next time that pass it
	public void checkPitStop() {
			for (int i=0; i<cars.length; i++) {
				if(this.cars[i] != null) {
					// TODO fix pit stop conditions 1 and 2
					// Enter pit stop condition 1: a car is damaged and it has passed the 75th tick
					if (this.cars[i].isDamaged() && (this.cars[i].getTotalLocation() %100 - this.cars[i].getSpeed() < 75) && (this.cars[i].getTotalLocation() % 100 >= 75)) {
						//	System.out.println("In pitstop 1" + this.cars[i]);
						this.cars[i].setPitTick(this.tickCount);
						this.pitStop.enterPitStop(this.cars[i], i);
						this.cars[i] = null;
					}
				// TODO: should not collide right after exiting the pit stop
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
						System.out.println(this.cars[i].toString() + " has finished the "
								+ "race in place " + this.place + ".");
						finish.enterFinishLine(this.cars[i], nullCars);
						this.cars[i] = null; // car becomes null once it has finished the race
						nullCars++; // keeping track of null cars to know when to end the race (after all cars have finished)
						if(this.finish.finished() == true) { // prints the score if all cars have finished
															// boolean finished is checked against nullCars
							score = calculateScore(this.tickCount);
							System.out.println("You scored " + score + " points.");		
						}						
					}
				}
			}
	}
	// this method checks if the pitstop index is null, which means it has exited the pitstop and should now re-enter the race track 
	public void nullPit() {
		for (int i=0; i<cars.length; i++) {
			// Check if pit stop is null
			if (this.pitStop.getPitCar(i) != null) {
				// Exit pit stop
				if (this.pitStop.getPitCar(i).getPitTick()==(this.tickCount-2)) { // if 2 ticks have passed since it entered the pit stop
					this.cars[i] = this.pitStop.getPitCar(i); // this is to know which car to put back into the race
					this.cars[i].setDamaged(false); // car is no longer damaged
					this.cars[i].setSpeed(this.cars[i].getOrigSpeed()); // the car returns to its original, non-damaged speed
					this.pitStop.exitPitStop(i); // car exits the pit stop
					
					
				//	System.out.println("location: " + (75 + ((cars[i].lapNumber -1) * 100)));
				//	this.cars[i].setLocation(75 + ((cars[i].lapNumber -1) * 100));
				}
			}
		}
	}
	
	// checks if cars have collided
	public void checkCollision() {		
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
							System.out.println(this.cars[i] + " has been damaged.");
							this.cars[i].setSpeed(newSpeedI);
						}
						
						// Check if j is damaged. If not, reduce speed
						if (!this.cars[j].isDamaged()) {
							this.cars[j].setDamaged(true);
							
							// set new speed
							newSpeedJ = this.cars[j].getSpeed() - (this.cars[j].getStrength() * 5);
							System.out.println(this.cars[j] + " has been damaged.");
							this.cars[j].setSpeed(newSpeedJ);
						}
					}
				}
			}
		}
	}
	
	// processes the ticks, runs each car
	public void run() {
		// runs while there are still cars in the race, the elements aren't all null
		// once a car finishes its element  becomes null
		while (this.nullCars < this.cars.length) {
			System.out.println("Tick " + this.tickCount);
			tick();
			if (this.cars[0] != null) {
				if (this.cars[0].getSpeed()<0) {
					break;
				}
			}
			tickCount++; // keeping track of each tick 
		}
	}
	
	// calculates the score of the race based on the given formula
	public int calculateScore(int ticks) {
		return 1000 - (20*ticks) + (150 * this.cars.length);
	}
	
	
	/**
	 * Needed for part B
	 * This method returns the logger instance used by this RaceTrack. You <b>SHOULD NOT</b> be using this method. 
	 * @return logger with this track's events 
	 */
	/*
	public TrackLoggerA getLogger() {
		return logger;
	}
	*/
}
