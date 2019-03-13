package data.migration;

import dependency.breaking.pos.HashStorage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStorage extends HashStorage {
    private final Logger LOG = LogManager.getLogger();

    // create an newStorage to store the hash data
    private String[] newStorage;

    private int readInconsistencies;

    public int getReadInconsistencies() {
        return readInconsistencies;
    }

    ArrayStorage() {
        this.newStorage = new String[199];
    }

    String[] getNewStorage() {
        return newStorage;
    }


    void testingHashPut(String barcode, String item) {
        super.put(barcode, item);
        // no shadow write
    }

    @Override
    public void put(String barcode, String item) {
        super.put(barcode, item);

        // shadow write asynchronously
        // writing directly to new storage
        newStorage[stringNum2Int(barcode)] = item;

        checkConsistency();
    }

    @Override
    public String barcode(String barcode) {
        String expected = super.barcode(barcode);
        String actual = newStorage[stringNum2Int(barcode)];
        // asynchronously
        if (!expected.equals(actual)) {
            readInconsistencies++;
            // fix it
            newStorage[stringNum2Int(barcode)] = expected;
        }
        // switch to the new data store (return the item stored in the new storage)
        return actual;
    }

    void forklift() {
        // getMap() from HashStorage is the old storage
        for (String barcode : getMap().keySet()) {
            // barcode will be the index of newStorage
            newStorage[stringNum2Int(barcode)] = getMap().get(barcode);
            // LOG.info("newStorage[{}]: {}", barcode, newStorage[stringNum2Int(barcode)]);
        }
    }

    int checkConsistency() {
        // getMap() value vs newStorage value
        // expected is of course getMap() value
        int count = 0;

        for (String barcode : getMap().keySet()) {
            String expected = getMap().get(barcode);
            String actual = newStorage[stringNum2Int(barcode)];
            if (!expected.equals(actual)) {
                count++;

                // fix it by updating the new storage
                newStorage[stringNum2Int(barcode)] = expected;
            }
        }
        return count;
    }

    private int stringNum2Int(String str) {
        return Integer.parseInt(str);
    }

    // same as new storage getter
    String[] getNewStorageClone() {
        return newStorage.clone();
    }
}
