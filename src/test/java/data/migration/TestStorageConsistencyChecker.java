package data.migration;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestStorageConsistencyChecker {

    @Test
    public void test() {
        ArrayStorage storage = new ArrayStorage();
        storage.put("1", "Milk, 3.99");
        storage.put("2", "Beer, 4.99");

        NewStorageConsistencyChecker checker = new NewStorageConsistencyChecker(storage);
        checker.updateConsistencyCheck();
        assertTrue(checker.isConsistent());

        storage.put("3", "Wine, 3.99");
        // is not consistent since we don't update the clone storage
        assertFalse(checker.isConsistent());

        // update price of item 2
        storage.put("2", "Beer, 2.99");
        // then update clone storage
        checker.updateConsistencyCheck();
        // now we can assert everything is consistent
        assertTrue(checker.isConsistent());
    }
}
