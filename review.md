# Review SOEN 345
## Table of Contents
- [Glossary](#glossary)
- [TDD Algorithm](#tdd-algorithm)
- [Characterization Test Algorithm](#characterization-test-algorithm)
- [Java Stack Iterator Question](#java-stack-iterator-question)
- [Git Bisect Question](#git-bisect-question)

## Glossary
- __legacy code__ is simply code without tests.
- __unit test__ is a test that runs in less than 1/10th of a second and is small enough to help you localize problems when it fails.
- __test-driven development__ is a development process that consists of writing failing test cases and satisfying them one at a time. As you do this, you refactor to keep the code as simple as possible. Code developed using TDD has test coverage, by default.
- __test harness__ is a piece of software that enables unit testing.
- __fake object__ is an object that impersonates a collaborator of a class during testing.
- __mock object__ is a fake object that asserts conditions internally.
- __mockito mocks objects using__: reflection and a proxy object.
- __two characteristics of unit test__: test runs fasts and test help localize problems.
- __characterization test__ is a test written to document the current behavior of a piece of code. The tests document the actual behavior of the system.
- There are two reasons to break dependencies: _sensing_ and _separation_
  - __sensing__: we break dependencies to _sense_ when we can't access values our code computes.
  - __separation__: we break dependencies to _separate_ when we can't even get a piece of code into a test harness to run.

## Source Code
|src|Main |Test |
|---|-----|-----|
|TDD|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/tdd)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/tdd)|
|Characterization test|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/characterization/test)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/characterization/test)|
|Dependency breaking|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/dependency/breaking)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/dependency/breaking)|

## TDD Algorithm
1. Write a failing test case.
2. Get it to compile.
3. Make it pass.
4. Remove duplication.
5. Repeat.

## Characterization Test Algorithm
You want to see the code behavior, so you make a failing test which tells you the correct behavior.
1. Use a piece of code in a test harness.
2. Write an assertion that you know will fail.
3. Let the failure tell you what the behavior is.
4. Change the test so that it expects the behavior that the code produces.
5. Repeat.

## Java Stack Iterator Question
Recall that the _iterator_ for the Stack class for Java is incorrect. Write a unit test that would characterize the actual behavior of the Java Stack's iterator.

```java
@Test
public void testStack() {
  Stack<Integer> stack = new Stack<Integer>();
  stack.push(1);
  stack.push(2);
  Integer expectedVal = 1;
  for (Integer stackElem : stack) {
    log.info("stack elem {}", stackElem);
    assertEquals(expectedVal, stackElem, 0.1);
    stackElem++;
  }
  assertFalse(stack.isEmpty());
}
[main] INFO characterization.test.TestStack - stack [1, 2]
[main] INFO characterization.test.TestStack - peek stack 2
[main] INFO characterization.test.TestStack - stack elem 1
[main] INFO characterization.test.TestStack - stack elem 2
```
### What is the output of the following code?

```java
Stack<Integer> mockStack = mock(Stack.class);
when(mockStack.pop()).thenReturn(3, 2, 1);
mockStack.pop();
mockStack.pop();
mockStack.pop();
mockStack.pop();
```
#### Answer > 3, 2, 1, 1

## Git Bisect Question
The idea behind `git bisect` is to perform a binary search in the history to find a particular regression.

You could try to check out each commit, build it, check if the regression is present or not. If there is a large number of commits, this can take a long time. This is a linear search. We can do better by doing a binary search. This is what the `git bisect` command does. At each step it tries to reduce the number of revisions that are potentially bad by half.

Git bisect on the following sequence of commits. You get fail,
pass, fail, which is the culprint of commit?
- 1----2----3----4----5----6----7----8----9

#### Answer > 4
