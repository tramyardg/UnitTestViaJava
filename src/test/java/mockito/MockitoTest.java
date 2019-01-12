package mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockitoTest {

    private final Logger log = LoggerFactory.getLogger(MockitoTest.class);

    @Test
    public void testVerify() {
	List<String> mockList = mock(List.class);
	mockList.add("one");
	mockList.add("two");
	mockList.clear();

	verify(mockList).add("one");
	verify(mockList).add("zero");
	verify(mockList).clear();
    }

    @Test
    public void testStubbing() {
	LinkedList<String> mockList = mock(LinkedList.class);
	when(mockList.get(0)).thenReturn("first");
	when(mockList.get(1)).thenThrow(new RuntimeException());

	log.info("{}", mockList.get(0));
	// will throw java.lang.RuntimeException
	// log.info("{}", mockList.get(1));
	log.info("{}", mockList.get(3)); // null
    }

    @Test
    public void testMoreThanOneReturnValue() {
	Iterator<String> i = mock(Iterator.class);
	// thenReturn(...) chaining
	when(i.next()).thenReturn("Mockito").thenReturn("rocks");
	String result = i.next() + " " + i.next();
	log.info("mockito rocks? {}", result);
	assertEquals("Mockito rocks", result);
    }

}
