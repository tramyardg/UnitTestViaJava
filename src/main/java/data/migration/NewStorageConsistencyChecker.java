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
        StringBuilder items = new StringBuilder();
        String[] newStorageClone = this.arrayStorage.getNewStorageClone();
        for (String item : newStorageClone) {
            if (item != null) {
                // string builder append function instead of using "+="
                items.append(hashValue(item));
            }
        }
        return items.toString();
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
