// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 26, 2020

package main;
public class PitStop {

	Car[] carsPit;
 	
	// constructor using the number of cars in the race
	public PitStop(int numCars) {
		this.carsPit = new Car[numCars];
	}
	
	//  enters the pitstop, makes the index of the car from null to hold that car, takes it out of the race while in the pitstop
	public void enterPitStop(Car car, int index, TrackLoggerC logger) { 
		car.setSpeed(car.getOrigSpeed()); // car is no longer damaged, back to original speed
		logger.logEnterPit(car);
		this.carsPit[index] = car; 
	}
	
	
	// exiting the pitstop, making the element null again so that it can be put back into the racetrack
	public void exitPitStopCar(int index, TrackLoggerC logger) {
		logger.logExitPit(this.carsPit[index]);
		this.carsPit[index] = null; 
		
	}
	// this method is used for testing
	public void enterPitStop(Car car, int index) { 
		car.setSpeed(car.getOrigSpeed()); 
		this.carsPit[index] = car;
	}
	
	// getter to know which car to put back into the race in RaceTrack
	public Car getPitCar(int index) {
		return this.carsPit[index];
	}
}
	
