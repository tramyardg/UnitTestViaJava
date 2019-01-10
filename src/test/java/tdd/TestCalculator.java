package tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCalculator {

    private static final double TOLERANCE = 0.1;
    private final Logger log = LoggerFactory.getLogger(TestCalculator.class);

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

    @Test
    public void testMedian() {
	Calculator calc = new Calculator();
	calc.addElement(4.0);
	calc.addElement(1.0);
	calc.addElement(3.0);
	calc.addElement(2.0);
	log.info("median {}", calc.median());
	assertEquals(3.0, calc.median(), TOLERANCE);
    }

}
