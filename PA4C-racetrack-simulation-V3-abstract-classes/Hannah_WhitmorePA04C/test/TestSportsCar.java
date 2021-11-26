// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12B
// March 26, 2020

package test;

import main.SportsCar;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

class TestSportsCar {
	
	@Test
	// testing the constructor, if the user enters a speed less than 20 it should make it 20, 
	// and if entered a strength greater than 3, should make it 3
	void testRaceCarConstructor() {
		int actualSpeed = 20;
		int actualStrength = 3;
		SportsCar s = new SportsCar(10, 4);
		assertEquals(actualSpeed, s.getSpeed());
		assertEquals(actualStrength, s.getStrength());
	}

}
