package data.migration;

import dependency.breaking.pos.HashStorage;
import dependency.breaking.pos.StoreToggles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayStorage extends HashStorage {
    private final Logger LOG = LogManager.getLogger();

    // create an newStorage to store the hash data
    private String[] newStorage;
    private int size = 199;

    private int readInconsistencies;

    public int getReadInconsistencies() {
        return readInconsistencies;
    }

    ArrayStorage() {
        if (StoreToggles.isArrayStorageEnable) {
            this.newStorage = new String[size];
        }
    }

    String[] getNewStorage() {
        return newStorage;
    }

    void testingHashPut(String barcode, String item) {
        if (StoreToggles.isUnderTest) {
            super.put(barcode, item);
            // no shadow write
        }
    }

    // write from the data store
    @Override
    public void put(String barcode, String item) {
        if (StoreToggles.isHashMapEnable) {
            super.put(barcode, item);
        }

        if (StoreToggles.isArrayStorageEnable) {
            // shadow write asynchronously
            // writing directly to new storage
            newStorage[stringNum2Int(barcode)] = item;
        }
        checkConsistency();
    }

    // read from the data store
    @Override
    public String barcode(String barcode) {
        if (StoreToggles.isHashMapEnable && StoreToggles.isArrayStorageEnable) {
            String expected = super.barcode(barcode);
            String actual = newStorage[stringNum2Int(barcode)];
            // asynchronously
            if (!expected.equals(actual)) {
                readInconsistencies++;
                // fix it
                newStorage[stringNum2Int(barcode)] = expected;
            }
        }

        if (StoreToggles.isHashMapEnable) {
            return super.barcode(barcode);
        }

        // switch to the new data store (return the item stored in the new storage)
        // read from new data store
        return newStorage[stringNum2Int(barcode)];
    }

    void forklift() {
        // you only do forklift when old storage and new storage are enabled
        if (!(StoreToggles.isArrayStorageEnable && StoreToggles.isHashMapEnable)) {
            return;
        }
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

        if (!(StoreToggles.isArrayStorageEnable && StoreToggles.isHashMapEnable)) {
            return 0;
        }

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
        if (StoreToggles.isArrayStorageEnable) {
            return newStorage.clone();
        }
        return null;
    }
}
