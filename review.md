# Review SOEN 345

- __legacy code__ is simply code without tests.
- __unit test__ is a test that runs in less than 1/10th of a second and is small enough to help you localize problems when it fails.
- __test-driven development__ is a development process that consists of writing failing test cases and satisfying them one at a time. As you do this, you refactor to keep the code as simple as possible. Code developed using TDD has test coverage, by default.
- __test harness__ is a piece of software that enables unit testing.
- __fake object__ is an object that impersonates a collaborator of a class during testing.
- __mock object__ is a fake object that asserts conditions internally.
- __mockito mocks objects using__: reflection and a proxy object.
- __two characteristics of unit test__: test runs fasts and test help localize problems.
## TDD Algorithm
1. Write a failing test case.
2. Get it to compile.
3. Make it pass.
4. Remove duplication.
5. Repeat.
- __characterization test__ is a test written to document the current behavior of a piece of code. The tests document the actual behavior of the system.
## Characterization Test Algorithm
You want to see the code behavior, so you make a failing test which tells you the correct behavior.
1. Use a piece of code in a test harness.
2. Write an assertion that you know will fail.
3. Let the failure tell you what the behavior is.
4. Change the test so that it expects the behavior that the code produces.
5. Repeat.
### Incorrect Java Stack's iterator
Recall that the _iterator_ for the Stack class for Java is incorrect. Write a unit test that would characterize the actual behavior of the Java Stack's iterator.

```java
Stack<Integer> stack = new Stack<Integer>();
stack.push(1);
stack.push(2);
Integer val = 0;
for(Integer i: stack) {
	assertEquals(val, i, 0.1);
	i++
}
assertFalse.isEmpty();
```