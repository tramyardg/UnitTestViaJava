package data.migration;

import org.apache.commons.codec.digest.DigestUtils;

public class NewStorageConsistencyChecker {
    private ArrayStorage arrayStorage;
    private String itemCheck;

    public NewStorageConsistencyChecker() {

    }

    NewStorageConsistencyChecker(ArrayStorage arrayStorage) {
        this.arrayStorage = arrayStorage;
        this.itemCheck = "";
    }

    void updateConsistencyCheck() {
        itemCheck = calculateConsistency();
    }

    private String calculateConsistency() {
        String items = "";
        String[] newStorageClone = this.arrayStorage.getNewStorageClone();

        for (int i = 0; i < newStorageClone.length; i++) {
            if (newStorageClone[i] != null) {
                items += hashValue(newStorageClone[i]) + " ";
            }
        }
        return items;
    }

    boolean isConsistent() {
        // actual from new storage (arrayStorage)
        String actual = calculateConsistency();
        // note itemCheck from updateConsistencyCheck
        System.out.println("expect = " + itemCheck + "\n" + "actual = " + actual + "\n");
        return itemCheck.equals(actual);
    }

    private String hashValue(String str) {
        return DigestUtils.shaHex(str).toUpperCase();
    }


}
