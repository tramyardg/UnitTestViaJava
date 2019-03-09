# Migrating from HashMap to Array

_How to migrate from HashMap to array?_
```java
private String[] newStorage;
ArrayStorage() {
    this.newStorage = new String[199];
}
@Override
public void put(String barcode, String item) {
    super.put(barcode, item);

    // shadow write asynchronously
    // writing directly to new storage
    newStorage[stringNum2Int(barcode)] = item;

    checkConsistency();
}
```

## Forklift
```java
void forklift() {
    // getMap() from HashStorage is the old storage
    for (String barcode : getMap().keySet()) {
        // barcode will be the index of newStorage
        newStorage[stringNum2Int(barcode)] = getMap().get(barcode);
        // LOG.info("newStorage[{}]: {}", barcode, newStorage[stringNum2Int(barcode)]);
    }
}
```

## Consistency checker
```java
int checkConsistency() {
    // getMap() value vs newStorage value
    // expected is of course getMap() value
    int count = 0;
    for (String barcode : getMap().keySet()) {
        String expected = getMap().get(barcode);
        String actual = newStorage[stringNum2Int(barcode)];
        if (!expected.equals(actual)) {
            count++;
        }
    }
    return count;
}
```