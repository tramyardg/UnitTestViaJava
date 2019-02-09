package proxy;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
	InvocationHandler handler = new MyInvocationHandler();
	MyInterface proxy = (MyInterface) Proxy.newProxyInstance(MyInterface.class.getClassLoader(),
		new Class[] { MyInterface.class }, (java.lang.reflect.InvocationHandler) handler);
	proxy.run();
    }

}
