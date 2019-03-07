package data.migration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayStorageTest {

    private final Logger LOG = LogManager.getLogger();

    @Test
    public void test() {

    }

    @Test
    public void testForklift() {
        ArrayStorage storage = new ArrayStorage();
        storage.put("5", "Milk, 3.99");

        storage.forklift();
        LOG.info(storage.getNewStorage()[5]);
        assertEquals("Milk, 3.99", storage.getNewStorage()[5]);
        assertEquals(199, storage.getNewStorage().length);
    }

    @Test
    public void testCheckConsistency() {
        ArrayStorage s = new ArrayStorage();
        s.put("5", "Milk, 3.99");
        s.put("1", "Chocolate, 11.99");
        s.put("2", "Cigar, 7.99");
        s.put("3", "Apple, 1.99");
        // don't forget to call the forklift to migrate the data to newStorage
        s.forklift();
        LOG.debug("number of inconsistencies {}", s.checkConsistency());
    }

    @Test
    public void testShadowWrite() {
        ArrayStorage storage = new ArrayStorage();
        storage.put("5", "Milk, 3.99");
        storage.put("1", "Chocolate, 11.99");
        storage.put("2", "Cigar, 7.99");
        storage.put("3", "Apple, 1.99");
        // shadow write does not require forklift because when we call put,
        // we are writing to the new commonStorage asynchronously
        assertEquals("Apple, 1.99", storage.getNewStorage()[3]);
        LOG.info("inconsistencies {}", storage.checkConsistency());
    }

    @Test
    public void testCheckConsistencyFail() {
        ArrayStorage s = new ArrayStorage();
        // using put without the shadow write inside it's method
        s.testingHashPut("3", "Milk, 3.99");

        // 1 inconsistency because the newStorage[3] is empty
        assertEquals(1, s.checkConsistency());

        // assert now that it is fixed because checkConsistency was called above
        // inside checkConsistency newStorage[3] = "Milk, 3.99"
        assertEquals("Milk, 3.99", s.getNewStorage()[3]);
        // if s.checkConsistency is never called new storage is always empty
        s.barcode("3");

        // returns Milk, 3.99
        LOG.info("expected item with barcode 3 {}", s.barcode("3"));

        // Read and write from new data store (get rid of old data store)
        s.put("8", "Cigar, 10.99");
        assertEquals("Cigar, 10.99", s.barcode("8"));
    }

}