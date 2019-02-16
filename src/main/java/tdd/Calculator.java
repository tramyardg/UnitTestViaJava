package tdd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

class Calculator {

    private ArrayList<Double> values;
    private final Logger log = LogManager.getLogger();

    Calculator() {
        this.values = new ArrayList<>();
    }

    void addElement(double d) {
        log.info("adding element {}", d);
        this.values.add(d);
    }

    double average() throws EmptyListException {
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

    double median() {
        log.info("expected median {}", values.get(values.size() / 2));
        return values.get(values.size() / 2);
    }

}
