package data.migration;

import dependency.breaking.pos.HashStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStorage extends HashStorage {
    private final Logger LOG = LogManager.getLogger();

    // create an newStorage to store the hash data
    private String[] newStorage;

    ArrayStorage() {
        this.newStorage = new String[199];
    }

    String[] getNewStorage() {
        return newStorage;
    }

    @Override
    public void put(String barcode, String item) {
        super.put(barcode, item);
    }

    @Override
    public String barcode(String barcode) {
        return super.barcode(barcode);
    }

    void forklift() {
        // getMap() from HashStorage is the old storage
        for (String barcode: getMap().keySet()) {
            // barcode will be the index of newStorage
            newStorage[stringNum2Int(barcode)] = getMap().get(barcode);
            LOG.info("item stored in newStorage: {}", newStorage[stringNum2Int(barcode)]);
        }
    }

    int checkConsistency() {
        // getMap() value vs comparing newStorage value
        // expected is of course getMap() value
        int count = 0;

        for(String barcode: getMap().keySet()) {
            String expected = getMap().get(barcode);
            LOG.info("expected: {}", expected);
            String actual = newStorage[stringNum2Int(barcode)];
            LOG.info("actual: {}", actual);
            if (!expected.equals(actual)) {
                count++;
            }
        }
        return count;
    }

    private int stringNum2Int(String str) {
        return Integer.parseInt(str);
    }
}
