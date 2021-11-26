// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.RaceCar;
import main.RaceTrack;
import main.TrackLoggerB;
import main.PitStop;

class EnterPitStop {
	
	@Test
	void enterPitStop() {
		int numCars = 1;
		int index = 0;
				
		// (1) Make a pitstop and track
		PitStop pitStop = new PitStop(numCars);
		RaceTrack track = new RaceTrack();
		
		// (2) Make a car and put it on an array of cars
		RaceCar car = new RaceCar(55, 2);
		RaceCar[] carArr = new RaceCar[numCars];
		carArr[index] = car;
		
		// (3) Put the array of cars on the track
		track.setCars(carArr);
		
		// (4) *** This is what we are testing. Does the car leave the track properly
		// enter the pitstop
		pitStop.enterPitStop(carArr[index], index);
		
		// (5) Get the value of the car from the pitstop
		RaceCar pitCar = pitStop.getPitCar(index);
		
		// (6) Assert that the car from the race track and pitstop are equal
		assertEquals(car.toString(), pitCar.toString());
		
	}
}
