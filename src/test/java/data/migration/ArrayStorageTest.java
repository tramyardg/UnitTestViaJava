package data.migration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ArrayStorageTest {

    private final Logger LOG = LogManager.getLogger();

    private ArrayStorage commonStorage = new ArrayStorage();

    @Before
    public void setUp() {
        commonStorage.put("5", "Milk, 3.99");
        commonStorage.put("1", "Chocolate, 11.99");
        commonStorage.put("2", "Cigar, 7.99");
        commonStorage.put("3", "Apple, 1.99");
        // don't forget to call the forklift to migrate the data to newStorage
        commonStorage.forklift();
    }

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
        LOG.debug("number of inconsistencies {}", commonStorage.checkConsistency());
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

}