// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package main;
public class FinishLine {
	
	RaceCar[] carsFinished;
	FormulaOne [] fCarsFinished;
	int carsDone;
	int fCarsDone;
	int score;
	
	// constructor for only racecars
	public FinishLine(int numCars) {
		carsFinished = new RaceCar[numCars]; // a new array the size of the number of cars in the race
		// keeps track of which have finished and in what place
		fCarsFinished = new FormulaOne[numCars];
		carsDone = 0; // counter for how many cars have finished
		fCarsDone = 0;
	}
	// constructor for racecars and formulaone cars, same logic as above
	public FinishLine(int numCars, int numFCars) {
		carsFinished = new RaceCar[numCars]; 
		fCarsFinished = new FormulaOne[numFCars];
		carsDone = 0; 
		fCarsDone = 0;
	}
	
	// this method keeps track of finished cars by adding them to the finished array and incrementing cars
	public void enterFinishLine(RaceCar car, int index) { 	
		// the index is used to indicate place
		this.carsFinished[index] = car;
		carsDone++; // cars finished
	}
	
	// this method is the same as above but for formulaone cars  
	public void enterFinishLine(FormulaOne fCar, int index) {
		this.fCarsFinished[index] = fCar;
		fCarsDone++;
	}
	
	// this method returns whether or not the race is over, if all cars have finished or not , racecars and formulaone cars
	public boolean finished() {
		if (carsDone==this.carsFinished.length && fCarsDone == this.fCarsFinished.length) { // if the num of cars finished = length of finished cars array 
			return true;
			
		} else {
			return false;
		}
	}
	// setter for racecars done
	public void incrementCarsDone() {
		this.carsDone ++;
	}
	
	// setter for formulaone cars done
	public void incrementFCarsDone() {
		this.fCarsDone += 1;
	}
}

