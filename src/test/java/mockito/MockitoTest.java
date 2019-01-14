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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    @Test
    public void testStubbing() {
	LinkedList<String> mockList = mock(LinkedList.class);
	when(mockList.get(0)).thenReturn("first");
	// method chain can be used to throw an exception
	when(mockList.get(1)).thenThrow(new RuntimeException());

	log.info("{}", mockList.get(0));
	// will throw java.lang.RuntimeException
	// log.info("{}", mockList.get(1));
	log.info("{}", mockList.get(3)); // null
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testMoreThanOneReturnValue() {
	Iterator<String> i = mock(Iterator.class);
	// thenReturn(...) chaining
	when(i.next()).thenReturn("Mockito").thenReturn("rocks");
	String result = i.next() + " " + i.next();
	log.info("mockito rocks? {}", result);
	assertEquals("Mockito rocks", result);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void testReturnValue() {
	Comparable<String> c = mock(Comparable.class);
	when(c.compareTo("one")).thenReturn(1);
	when(c.compareTo("two")).thenReturn(2);
	assertEquals(1, c.compareTo("one"));
	assertEquals(2, c.compareTo("two"));
	
	Comparable<Integer> c2 = mock(Comparable.class);
	when(c2.compareTo(anyInt())).thenReturn(-1);
	assertEquals(-1, c2.compareTo(9));
	
	LinkedList<String> mockList = mock(LinkedList.class);
	when(mockList.get(anyInt())).thenReturn("element");
	// prints element
	log.info("{}", mockList.get(222));
	verify(mockList).get(anyInt());
    }
    
    @Test
    public void testSpy() {
	List list = new LinkedList();
	List spy = spy(list);
	// optionally, you can stub out some methods:
	when(spy.size()).thenReturn(100);
	// using the spy calls *real* objects
	spy.add("1");
	spy.add("2");
	// prints "1" - the first element of the list
	log.info("get first elem using spy -> {}", spy.get(0));
	log.info("get list size using spy -> {}", spy.size());
	
	verify(spy).add("1");
	verify(spy).add("2");
	// will fail
	// verify(spy).add("one");
    }

}
