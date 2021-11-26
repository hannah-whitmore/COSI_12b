// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;
public class PitStop {

	RaceCar[] carsPit;
	FormulaOne[] fCarsPit;
 	
	// constructor using the number of cars in the race
	public PitStop(int numCars) {
		this.carsPit = new RaceCar[numCars];
		//TrackLoggerB logger;
	}
	
	public PitStop(int numCars,  int numFCars) {
		this.carsPit = new RaceCar[numCars];
		this.fCarsPit = new FormulaOne[numFCars];
		//TrackLoggerB logger;
	}
	
	//  enters the pitstop, makes the index of the car from null to hold that car, takes it out of the race while in the pitstop
	public void enterPitStop(RaceCar car, int index, TrackLoggerB logger) { 
		car.setSpeed(car.getOrigSpeed()); // car is no longer damaged, back to original speed
		logger.logEnterPit(car); // prints has entered pitstop
		this.carsPit[index] = car; // holds car in pitstop
	}
	
	// this method is used for a test case, same as above, no logger
	public void enterPitStop(RaceCar car, int index) { 
		car.setSpeed(car.getOrigSpeed()); 
		this.carsPit[index] = car;
	}
	
	// same logic of entering pitstop for formulaone cars
	public void enterPitStop(FormulaOne fCar, int index, TrackLoggerB logger) {
		fCar.setSpeed(fCar.getOrigSpeed());
		logger.logEnterPit(fCar);
		this.fCarsPit[index] = fCar;
	}
	
	// exiting the pitstop, making the element null again so that it can be put back into the racetrack
	public void exitPitStopCar(int index, TrackLoggerB logger) {
		logger.logExitPit(this.carsPit[index]); // prints has exited
		this.carsPit[index] = null; 
		
	}
	
	// same exit pit logic for formulaones
	public void exitPitStopFCar(int index, TrackLoggerB logger) {
		logger.logExitPit(this.fCarsPit[index]);
		this.fCarsPit[index] = null;
	}
		
	// getter to know which car to put back into the race in RaceTrack
	public RaceCar getPitCar(int index) {
		return this.carsPit[index];
	}
	
	// same as above but for fomulaone cars
	public FormulaOne getFormPitCar(int index) {
		return this.fCarsPit[index];
	}
}
