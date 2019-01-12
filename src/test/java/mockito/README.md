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
