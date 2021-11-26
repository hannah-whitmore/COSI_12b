public class PitStop {

	RaceCar[] carsPit;
	
	// constructor using the number of cars in the race
	public PitStop(int numCars) {
		carsPit = new RaceCar[numCars];
	}
	
	//  enters the pitstop, makes the index of the car from null to hold that car, takes it out of the race while in the pitstop
	public void enterPitStop(RaceCar car, int index) { // TODO you can add parameters here!
		car.setSpeed(car.getOrigSpeed()); // pass in the index from raceTrack, cars[i[, i is passed
		System.out.println(car.toString() + " has entered the pit stop.");
		carsPit[index] = car;
	}
	// exiting the pitstop, making the element null again so that it can be put back into the race track at cars[i]
	public void exitPitStop(int index) {
		System.out.println(carsPit[index].toString() + " has exited the pit stop.");
		carsPit[index] = null; 
	}
	
	// to know which car to put back into the race in RaceTrack
	public RaceCar getPitCar(int index) {
		return this.carsPit[index];
	}
}
