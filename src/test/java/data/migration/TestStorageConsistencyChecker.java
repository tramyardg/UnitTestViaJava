package data.migration;

import org.junit.Test;

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
    }
}
