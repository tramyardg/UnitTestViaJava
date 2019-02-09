![mockito](https://user-images.githubusercontent.com/5623994/51096961-83783980-178e-11e9-9966-e3f0b5ddc390.png)

See [docs here](http://static.javadoc.io/org.mockito/mockito-core/2.13.0/org/mockito/Mockito.html).

### Creating a mock
- A mock has the same method calls as the normal object.
- Mocks can be on interfaces or classes.
- It records how other objects interact with it.
- There is a mock instance of the object but no real object instance.
- Do not mock `Sale.java` class (class under test).
- Use when you don't have fake classes (you no longer need fake classes).

### Basics
**Mocking external dependencies**
![image](https://user-images.githubusercontent.com/5623994/52521689-e1daef80-2c48-11e9-8c8c-fd9233dc5a4b.png)
Note: `EmailSender` is a class under test, do not mock!

**Stubbing**
![image](https://user-images.githubusercontent.com/5623994/52521698-f7e8b000-2c48-11e9-92fd-b99f9ec893f8.png)

**Additional tricks:**
- __doThrow__: to test a failing dependency, e.g. network dependency
- __Spying__: partially stubbing a dependency instead of completely
- __ArgumentCaptor__: ensure that a method was called with a range of possible acceptable arguments

### Verify
- Once created, a mock will remember all interactions. The you can selectively verify whatever interactions you are interested in.

### Verify InOrder
- Want to ensure that the interactions happened in a particular order
- InOrder for the display
```java
InOrder inOrder = inOrder(display);
inOrder.verify(display).showLine("1A");
inOrder.verify(display).showLine("Milk, 3.99");
```

### Definition and Syntax
- __verify__: verifies if method was called
```java
verify(object).method(arg(s));
verify(object).method();
```

### Verifying some behavior
The `verify(...).method()` verifies that the method was called.
```java
@Test
public void testVerify() {
  List<String> mockList = mock(List.class);
  mockList.add("one");
  mockList.add("two");
  mockList.clear();
  verify(mockList).add("one");
  // will fail -> verify(mockList).add("zero");
  // since "zero" is not added in the list
  
  // pass since clear was called
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
  // when i.next() is called the first time "Mockito" is printed
  // when i.next() is called the second time "rocks" is printed
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

### Capturing arguments for further assertions
### Spying on real objects
```java
@Test
public void testSpy() {
  List list = new LinkedList();
  // copies list signature
  List spy = spy(list); 
  
  // optionally, you can stub out some methods:
  when(spy.size()).thenReturn(100);
  
  // using the spy calls *real* objects, in this case it spies list
  spy.add("1");
  spy.add("2");
  
  // prints "1" - the first element of the list
  log.info("get first elem using spy -> {}", spy.get(0));
  log.info("get list size using spy -> {}", spy.size());
	
  verify(spy).add("1");
  verify(spy).add("2");
  // will fail
  // verify(spy).add("one");
}
```

### Verification in InOrder
```java
@Test
public void testInOrder() {
  List<String> mock = mock(List.class);
  mock.add("A");
  mock.add("B");
  
  InOrder orderOfStrAdd = inOrder(mock);
  // if the order is wrong it will fail, for example
  // orderOfAddition.verify(mock).add("B");
  // orderOfAddition.verify(mock).add("A"); 
  orderOfStrAdd.verify(mock).add("A");
  orderOfStrAdd.verify(mock).add("B");
  
  List<Integer> listIntA = mock(List.class);
  List<Integer> listIntB = mock(List.class);
  listIntA.add(1);
  listIntB.add(1);
  
  //create inOrder object passing any mocks that need to be verified in order
  InOrder orderOfIntAdd = inOrder(listIntA, listIntB);
  // this one below also works, as long as `listIntA.add(1)` is done first
  // InOrder orderOfIntAdd = inOrder(listIntB, listIntA);
  
  // see the order of listInt#: A then B
  orderOfIntAdd.verify(listIntA).add(1);
  orderOfIntAdd.verify(listIntB).add(1);
}
```
	