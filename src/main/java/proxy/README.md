# Creating Proxies
You create dynamic proxies using the `Proxy.newProxyInstance()` method. The `newProxyInstance()` methods takes 3 parameters:

1. The `ClassLoader` that is to "load" the dynamic proxy class.
2. An array of **interfaces** to implement.
3. An `InvocationHandler` to forward all methods calls on the proxy to.

## Proxy Pattern
![image](https://user-images.githubusercontent.com/5623994/52524680-bdddd500-2c6d-11e9-8ee7-3af61b3647d4.png)
![image](https://user-images.githubusercontent.com/5623994/52525351-5aa47080-2c76-11e9-8127-29519cac0d15.png)

## Types of Proxies
- A virtual proxy for expensive to create objects
- Remote proxy for distributed objects
- Protective proxy to make sure caller has correct permissions
- Smart proxy
- Reference counting
- Loading in object on first use
- Checking locks on object etc

### `InvocationHandler`
Imported from `import java.lang.reflect.InvocationHandler;`

### `MyInvocationHandler` implements `InvocationHandler`
```
public class MyInvocationHandler implements InvocationHandler {
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println(proxy.getClass());
    System.out.println(method.getName());
    for (Object obj : args) {
        System.out.println(obj.toString());
    }
    return null;
  }
}
```

For example:
```
MyInvocationHandler handler = new MyInvocationHandler();
MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
	MyInterface.class.getClassLoader(),
	new Class[] { MyInterface.class },
	handler
);
```
