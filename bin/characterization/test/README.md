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