package proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;

import dependency.breaking.pos.HashStorage;
import dependency.breaking.pos.IDisplay;
import dependency.breaking.pos.Interac;
import dependency.breaking.pos.Sale;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class TestProxy {

    private final Logger logger = Logger.getLogger(TestProxy.class.getName());

    @Test
    public void test() {
        Method[] methods = HashStorage.class.getMethods();
        for (Method m : methods) {
            logger.info(m.getName());
        }
    }

    @Test
    public void testDisplayProxy() {
        HashStorage hashStorage = mock(HashStorage.class);
        when(hashStorage.barcode("1A")).thenReturn("Milk, 3.99");

        MockInvocationHandler mockHandler = new MockInvocationHandler();
        IDisplay display = (IDisplay) Proxy.newProxyInstance(
                IDisplay.class.getClassLoader(),
                new Class[]{IDisplay.class},
                mockHandler);

        Sale sale = new Sale(display, hashStorage);
        sale.scan("1A");

        mockHandler.verify("showLine", "Milk, 3.99");

        verify(hashStorage).barcode("1A");
    }

}
