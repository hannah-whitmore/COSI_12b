// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;
public class FinishLine {
	
	Car[] carsFinished;
	int carsDone;
	int score;
	
	public FinishLine(int numCars) {
		carsFinished = new Car[numCars]; // keeps track of which have finished and in what place
		carsDone = 0; // counter for how many cars have finished
	}

	
	// this method keeps track of finished cars by adding them to the finished array and incrementing cars
	public void enterFinishLine(Car car, int index) { 	
		this.carsFinished[index] = car;
		carsDone++; 
	}
	
	
	// this method returns whether or not the race is over, if all cars have finished or not
	public boolean finished() {
		if (carsDone==this.carsFinished.length) { // if the num of cars finished = length of finished cars array, race is over 
			return true;
			
		} else {
			return false;
		}
	}
	
	// getter for a car that has finished, used for score
	public Car getCarFromFinish(int i) {
		return this.carsFinished[i];
	}
	
	// setter for cars done
	public void incrementCarsDone() {
		this.carsDone ++;
	}
	
}

