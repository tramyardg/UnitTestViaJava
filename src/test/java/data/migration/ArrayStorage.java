package data.migration;

import dependency.breaking.pos.HashStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

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
            LOG.info("storing HashStorage data into newStorage:array");
            newStorage[Integer.parseInt(barcode)] = getMap().get(barcode);
            LOG.info("item stored in newStorage: {}", newStorage[Integer.parseInt(barcode)]);
        }
    }
}
