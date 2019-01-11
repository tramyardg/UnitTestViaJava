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

```java
void testGenerator() {
	PageGenerator generator = new PageGenerator();
	assertEquals("hello!", generator.generate());
}
```
Run your test and let it fail. When it does, you have found out what the code actually does under the condition. 
For instance, _PageGenerator_ actually generates the string _"hi!"_ when its _generate_ method is called:

```java
org.junit.ComparisonFailure: expected:<h[ello]!> but was:<h[i]!>
	at org.junit.Assert.assertEquals(Assert.java:115)
	at org.junit.Assert.assertEquals(Assert.java:144)
	at characterization.test.TestGenerator.testGenerator(TestGenerator.java:11)
...
```

We can alter the test so it passes:

```java
void testGenerator() {
	PageGenerator generator = new PageGenerator();
	assertEquals("hi!", generator.generate());
}
```