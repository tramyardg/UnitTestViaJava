# Adapt Parameter

```java
public class ARMDispatcher {
    ...
    public void populate(HttpServletRequest request) {
        String [] values = request.getParameterValues(pageStateName);
        if (values != null && values.length > 0) {
          marketBindings.put(pageStateName + getDateStamp(), values[0]);
        }
    }
    ...
}
```

Actually, there is. We can wrap the parameter that is coming in and break our dependency on the API interface entirely. When we’ve done that, the code will look like this:
```java
public class ARMDispatcher {
    ...
    public void populate(ParameterSource source) {
        String values = source.getParameterForName(pageStateName);
        if (value != null) {
            marketBindings.put(pageStateName + getDateStamp(), value);
        }
    }
    ...
}
```

Here is a fake class that implements ParameterSource. We can use it in our test:
```java
class FakeParameterSource implements ParameterSource {
    public String value;
    public String getParameterForName(String name) {
        return value;
    }
}
```

And the production parameter source looks like this:
```java
class ServletParameterSource implements ParameterSource {
    private HttpServletRequest request;
    public ServletParameterSource(HttpServletRequest request) {
        this.request = request;
    }
    String getParameterValue(String name) {
        String [] values = request.getParameterValues(name);
        if (values == null || values.length < 1)
            return null;
        return values[0];
    }
}
```