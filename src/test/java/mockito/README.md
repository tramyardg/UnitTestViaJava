### Verifying some behavior
The `verify(...).method()` or `verify(...).method()` verifies that the method was called.
```java
  @Test
  public void testVerify() {
    List<String> mockList = mock(List.class);
    mockList.add("one");
    mockList.add("two");
    mockList.clear();
    verify(mockList).add("one");
    // will fail -> verify(mockList).add("zero");
    verify(mockList).clear();
  }
```

### Testing with stubbing
Mocking concrete classes and/or interfaces by `when(...).thenReturn(...)` and/or `when(...).thenThrow(...)`.
```java
  LinkedList<String> mockList = mock(LinkedList.class);
  when(mockList.get(0)).thenReturn("first");
  // method chain can be used to throw an exception
  when(mockList.get(1)).thenThrow(new RuntimeException());
  log.info("{}", mockList.get(0));
  // will throw java.lang.RuntimeException
  // log.info("{}", mockList.get(1));
  log.info("{}", mockList.get(3)); // null
  
  Iterator<String> i = mock(Iterator.class);
  // thenReturn(...) chaining
  when(i.next()).thenReturn("Mockito").thenReturn("rocks");
  String result = i.next() + " " + i.next();
  log.info("mockito rocks? {}", result);
  assertEquals("Mockito rocks", result);
```

### Argument matchers
```java
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
```
