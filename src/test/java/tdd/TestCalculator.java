package tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalculator {

    private static final double TOLERANCE = 0.1;

    @Test
    public void testAverage() {
	Calculator calc = new Calculator();
	calc.addElement(1.0);
	calc.addElement(2.0);
	try {
	    assertEquals(1.5, calc.average(), TOLERANCE);
	} catch (EmptyListException e) {
	    e.printStackTrace();
	}
    }

    @Test
    public void testEmptyList() {
	Calculator calc = new Calculator();
	try {
	    calc.average();
	} catch (EmptyListException e) {
	    e.printStackTrace();
	}
    }

}
