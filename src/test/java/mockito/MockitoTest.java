package mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

}
