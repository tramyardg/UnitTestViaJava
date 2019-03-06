package data.migration;

import dependency.breaking.pos.HashStorage;

public class ArrayStorage extends HashStorage {

    public ArrayStorage() {

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
