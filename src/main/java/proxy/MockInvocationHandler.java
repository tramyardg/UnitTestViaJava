package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MockInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().toString());
        System.out.println(method.getName());
        for (Object obj : args) {
            System.out.println(obj.toString());
        }
        return null;
    }

    public void verify(String method, String parameter) {

    }
}
