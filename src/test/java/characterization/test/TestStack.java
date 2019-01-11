package characterization.test;

import java.util.Stack;

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
	Stack<Integer> stack = new Stack<Integer>();
	stack.push(1);
	stack.push(2);
	log.info("stack {}", stack.toString());
	// stack [1, 2]
	log.info("peek stack {}", stack.peek());
	// peek stack 2
	Integer expectedVal = 1;
	for (Integer stackElem : stack) {
	    log.info("stack elem {}", stackElem);
	    assertEquals(expectedVal, stackElem, 0.1);
	    stackElem++;
	}
	assertFalse(stack.isEmpty());
	// [main] INFO characterization.test.TestStack - stack [1, 2]
	// [main] INFO characterization.test.TestStack - peek stack 2
	// [main] INFO characterization.test.TestStack - stack elem 1
	// [main] INFO characterization.test.TestStack - stack elem 2
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
    }
}
