### Verifying some behavior`
Verify that the method was called.
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