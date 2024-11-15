package mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class TestMocking {

    private final Logger log = LogManager.getLogger();

    @Test
    public void test() {
        List<String> mockList = mock(List.class);

        // stubbing
        when(mockList.get(0)).thenReturn("first");

        mockList.add("one");
        mockList.get(0);


        verify(mockList).get(0);
        verify(mockList).add("one");

        assertEquals("first", mockList.get(0));

    }

    @Test
    public void testStack() {
        Stack<Integer> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(3, 2, 1, null);

        log.info("{}", stack.pop()); // 3
        log.info("{}", stack.pop()); // 2
        log.info("{}", stack.pop()); // 1
        log.info("{}", stack.pop()); // null
        log.info("{}", stack.pop()); // null

    }

    @Test
    public void testArgCaptor() {
        List<String> mockList = mock(List.class);
        mockList.add("test");
        // argument captor of type string
        ArgumentCaptor<String> argCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockList).add(argCaptor.capture());

        assertEquals("test", argCaptor.getValue());
    }

    @Test
    public void testListSpy() {
        List<String> list = new LinkedList<>();
        List<String> listSpy = spy(list);

        when(listSpy.size()).thenReturn(100);

        listSpy.add("first");
        assertEquals(listSpy.get(0), "first");

        assertEquals(listSpy.size(), 100);
    }
}
