package tdd;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private ArrayList<Double> values;
    private final Logger log = LoggerFactory.getLogger(Calculator.class);

    public Calculator() {
	this.values = new ArrayList<>();
    }

    public void addElement(double d) {
	log.info("adding element {}", d);
	this.values.add(d);
    }

    public double average() throws EmptyListException {
	if (values.isEmpty()) {
	    throw new EmptyListException();
	}
	log.info("calculating average");
	double totalVal = 0.0;
	for (int i = 0; i < values.size(); i++) {
	    totalVal += values.get(i);
	}
	log.info("expected value {}", (totalVal / values.size()));
	return (totalVal / values.size());
    }

    public double median() {
	log.info("expected median {}", values.get(values.size() / 2));
	return values.get(values.size() / 2);
    }

}
