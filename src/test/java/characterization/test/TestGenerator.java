package characterization.test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestGenerator {

    @Test
    void testGenerator() {
	PageGenerator generator = new PageGenerator();
	assertEquals("hello!", generator.generate());
    }
}
