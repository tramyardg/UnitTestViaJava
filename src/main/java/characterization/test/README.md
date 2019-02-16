# Characterization Test
Is a test that characterizes the actual behavior of a piece of code. The tests documents the actual current behavior of the system.

Here is a little algorithm for writing characterization tests:
1. Use a piece of code in a test harness.
2. Write an assertion that you know will fail.
3. Let the failure tell you what the behavior is.
4. Change the test so that it expects the behavior that the code produces.
5. Repeat.

---
In the following example, _PageGenerator_ is not going to generate the string _"hello!"_:

```
void testGenerator() {
	PageGenerator generator = new PageGenerator();
	assertEquals("hello!", generator.generate());
}
```
Run your test and let it fail. When it does, you have found out what the code actually does under the condition. 
For instance, _PageGenerator_ actually generates the string _"hi!"_ when its _generate_ method is called:

```
org.junit.ComparisonFailure: expected:<h[ello]!> but was:<h[i]!>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at characterization.test.TestGenerator.testGenerator(TestGenerator.java:11)
...
```

We can alter the test so it passes:

```
void testGenerator() {
	PageGenerator generator = new PageGenerator();
	assertEquals("hi!", generator.generate());
}
```

## StackProper and IteratorStackProper
We want to fix the stack iterator of Java. Here is how we do it:
1. Find out stack iterator type.
Stack iterator uses vector.
```
@Test
public void testStackIteratorType() {
  Stack<Integer> stack = new Stack<Integer>();
  Iterator it = stack.iterator();
  log.info("stack iterator type {}", it.getClass().getName());
  // [main] INFO characterization.test.TestStack - stack iterator type
  // java.util.Vector$Itr
  assertEquals(it.getClass(), (new Vector()).iterator().getClass());
}
```
2. Create `StackProper<E>` class that extends the original stack class.
3. Create `IteratorStackProper<E>` class that implements the original `Iterator` class.
4. Override the `Iterator` method in `StackProper<E>` and return a new `IteratorStackProper<E>`;

**StackProper**
```
public class StackProper<E> extends Stack<E> {
  @Override
  public synchronized Iterator<E> iterator() {
    // old behaving iterator
    // return super.iterator();
	return new IteratorStackProper<E>(this);
  }
}
```

**IteratorStackProper**

```
public class IteratorStackProper<E> implements Iterator<E> {
  private StackProper<E> stack;
  public IteratorStackProper(StackProper<E> stack) {
	this.stack = stack;
  }
  @Override
  public boolean hasNext() {
	return !stack.isEmpty();
  }
  @Override
  public E next() {
	// when the iterator moves to the next element it pops
	return stack.pop();
  }
}
```

Now the correct behavior is achieved!
```
@Test
public void testStackProper() {
    StackProper<Integer> stack = new StackProper<Integer>();
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    int expectedVal = 4;
    for (Integer elem : stack) {
      log.info("stack elem: {}", elem.intValue());
      assertEquals(expectedVal, elem.intValue());
      expectedVal--;
    }
}
// [main] INFO characterization.test.TestStack - stack elem: 4
// [main] INFO characterization.test.TestStack - stack elem: 3
// [main] INFO characterization.test.TestStack - stack elem: 2
// [main] INFO characterization.test.TestStack - stack elem: 1
```    
    