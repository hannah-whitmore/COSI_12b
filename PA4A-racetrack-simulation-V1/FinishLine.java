public class FinishLine {
	
	RaceCar[] carsFinished;
	int carsDone;
	int score;
	
	public FinishLine(int numCars) {
		carsFinished = new RaceCar[numCars]; // a new array of size the number of cars in the race, to keep track of which have finished and in what place
		carsDone = 0; // counter for how many cars have finished
	}
	
	// keeps track of finished cars by adding them to the finished array and incrementing cars
	public void enterFinishLine(RaceCar car, int index) { // the car which has finished from RaceTrack is passed
															// nullCars in RaceTrack was initialized to 0 and incremented every time a car has finished,
															// this is passed as int index, as the index of the finished array to indicate place
		this.carsFinished[index] = car;
		carsDone++;
	}
	
	// this method returns whether or not the race is over, if all cars have finished or not 
	public boolean finished() {
		if (carsDone==this.carsFinished.length) { // if the num of cars finished = length of finished cars array 
			return true;
			
		} else {
			return false;
		}
	}
}

