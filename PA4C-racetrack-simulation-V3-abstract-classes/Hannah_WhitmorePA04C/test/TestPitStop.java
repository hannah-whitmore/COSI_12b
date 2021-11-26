// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 26, 2020

package test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import main.RaceCar;
import main.RaceTrack;
import main.PitStop;
import main.Car;


class TestPitStop {
	
	@Test
	void testEnterPitStop() {
		int numCars = 1;
		int index = 0;
				
		// (1) Make a pitstop and track
		PitStop pitStop = new PitStop(numCars);
		RaceTrack track = new RaceTrack();
		
		// (2) Make a car, put it on an array of cars, and on the track
		RaceCar rCar = new RaceCar(55, 2);
		Car [] cars = new Car[numCars];
		cars[index] = rCar;
		track.setCars(cars);
		
		// (3) This is the test -- does the car leave the track and enter the pitstop properly
		pitStop.enterPitStop(cars[index], index);
		
		// (4) Get the value of the car from the pitstop
		Car pitCar = pitStop.getPitCar(index);
		
		
		// (5) Assert that the car from the race track and pitstop are equal
		assertEquals(cars[0].toString(), pitCar.toString());
	}
}
