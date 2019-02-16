package characterization.test;

import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestStack {

    private final Logger log = LoggerFactory.getLogger(TestStack.class);

    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        // stack [1, 2]
        log.info("stack {}", stack.toString());
        // peek stack 2
        log.info("peek stack {}", stack.peek());

        /*
         * expected behavior but this will fail failure trace: expected: <2> but
         * was: <1> int expectedVal = 2; for (Integer stackElem : stack) {
         * log.info("stack elem {}", stackElem); assertEquals(expectedVal,
         * stackElem.intValue()); expectedVal--; }
         */

        int expectedVal = 1;
        for (Integer stackElem : stack) {
            log.info("stack elem {}", stackElem);
            assertEquals(expectedVal, stackElem.intValue());
            expectedVal++;
        }
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testMockStack() {
        @SuppressWarnings("unchecked")
        Stack<Integer> mockStack = mock(Stack.class);
        when(mockStack.pop()).thenReturn(3, 2, 1);
        log.info("pop {}", mockStack.pop());
        log.info("pop {}", mockStack.pop());
        log.info("pop {}", mockStack.pop());
        log.info("pop {}", mockStack.pop());
        // [main] INFO characterization.test.TestStack - pop 3
        // [main] INFO characterization.test.TestStack - pop 2
        // [main] INFO characterization.test.TestStack - pop 1
        // [main] INFO characterization.test.TestStack - pop 1

        // will fail (NullPointerException)
        // log.info("peek {}", mockStack.peek().intValue());
    }

    @Test
    public void testStackIteratorType() {
        Stack<Integer> stack = new Stack<>();
        Iterator it = stack.iterator();
        log.info("stack iterator type {}", it.getClass().getName());
        // [main] INFO characterization.test.TestStack - stack iterator type
        // java.util.Vector$Itr
        assertEquals(it.getClass(), (new Vector()).iterator().getClass());
    }

    @Test
    public void testStackProper() {
        StackProper<Integer> stack = new StackProper<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        int expectedVal = 4;
        for (Integer elem : stack) {
            log.info("stack elem: {}", elem);
            assertEquals(expectedVal, elem.intValue());
            expectedVal--;
        }
    }
}
