// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 16, 2020

package test;

import main.RaceCar;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

class RaceCarConstructor {
	
	@Test
	// testing if the user enters a speed greater than 55, it will make it 55, 
	// and if entered a strength greater than 4, will make it 4
	void testRaceCarConstructor() {
		int actualSpeed = 55;
		int actualStrength = 4;
		RaceCar r = new RaceCar(60, 5);
		assertEquals(actualSpeed, r.getSpeed());
		assertEquals(actualStrength, r.getStrength());
	}

}
