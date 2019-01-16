package tdd;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCalculator {

    private static final double TOLERANCE = 0.1;
    private final Logger log = LoggerFactory.getLogger(TestCalculator.class);

    private Calculator calculator;
    
    @Before
    public void setUpCalculator() {
	calculator = new Calculator();
	calculator.addElement(1.0);
	calculator.addElement(2.0);
	calculator.addElement(3.0);
    }
    
    @Test
    public void testAverage() {
	try {
	    assertEquals(2.0, calculator.average(), TOLERANCE);
	} catch (EmptyListException e) {
	    log.warn("error {}", e);
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
    public void testMedianBefore() {
	log.info("median setup before {}", calculator.median());
	assertEquals(2.0, calculator.median(), TOLERANCE);
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
