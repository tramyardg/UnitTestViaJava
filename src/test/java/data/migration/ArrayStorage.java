package data.migration;

import dependency.breaking.pos.HashStorage;

public class ArrayStorage extends HashStorage {

    // create an array to store the hash data
    private String[] array;

    ArrayStorage() {
        this.array = new String[199];
    }

    @Override
    public void put(String barcode, String item) {
        super.put(barcode, item);
    }

    @Override
    public String barcode(String barcode) {
        return super.barcode(barcode);
    }
}
