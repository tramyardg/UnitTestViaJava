package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.Assert.*;


public class MockInvocationHandler implements InvocationHandler {

    private HashMap<String, String> verifyMap = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // System.out.println(method.getName());
        for (Object obj : args) {
            verifyMap.put(method.getName(), obj.toString());
        }
        return null;
    }

    void verify(String method, String parameter) {
        assertEquals(verifyMap.get(method), parameter);
    }
}
