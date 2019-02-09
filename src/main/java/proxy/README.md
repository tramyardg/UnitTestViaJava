# Creating Proxies
You create dynamic proxies using the `Proxy.newProxyInstance()` method. The `newProxyInstance()` methods takes 3 parameters:

1. The `ClassLoader` that is to "load" the dynamic proxy class.
2. An array of **interfaces** to implement.
3. An `InvocationHandler` to forward all methods calls on the proxy to.

## Proxy Pattern
![image](https://user-images.githubusercontent.com/5623994/52524680-bdddd500-2c6d-11e9-8ee7-3af61b3647d4.png)

For example:
```java
InvocationHandler handler = new MyInvocationHandler();
MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
	MyInterface.class.getClassLoader(),
	new Class[] { MyInterface.class },
	handler
);
```

### `InvocationHandler`
```java
public interface InvocationHandler {
  Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
```

### `MyInvocationHandler`
```java
public class MyInvocationHandler implements InvocationHandler {
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    //do something "dynamic"
  }
}
```
