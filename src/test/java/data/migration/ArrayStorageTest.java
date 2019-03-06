package data.migration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

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

}