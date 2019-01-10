package tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalculator {

	private static final double TOLERANCE = 0.1;

	@Test
	void testFirstMoment() {
		Calculator ic = new Calculator();
		ic.addElement(0.1);
		ic.addElement(2.0);

		assertEquals(-0.5, ic.average(2.0), TOLERANCE);
	}

}
