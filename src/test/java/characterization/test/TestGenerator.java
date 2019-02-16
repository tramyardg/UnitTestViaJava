package characterization.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGenerator {

    @Test
    public void testGenerator() {
        PageGenerator generator = new PageGenerator();

        // let it fail
        // assertEquals("hello!", generator.generate());

        // correct behavior
        assertEquals("hi!", generator.generate());
    }
}
