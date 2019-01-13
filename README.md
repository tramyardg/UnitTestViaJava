# Review SOEN 345
## Table of Contents
- [Glossary](#glossary)
- [Source Code](#source-code)
- [TDD Algorithm](#tdd-algorithm)
- [Characterization Test Algorithm](#characterization-test-algorithm)
- [Java Stack Iterator Question](#java-stack-iterator-question)
- [Git Bisect](#git-bisect)
- [Parameterize Constructor](#parameterize-constructor)
  - [MailChecker](#mail-checker-example)
  - [Pixel](#pixel-example)

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
|Mockito|---|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/mockito)|


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

## Git Bisect
The idea behind `git bisect` is to perform a binary search in the history to find a particular regression.

You could try to check out each commit, build it, check if the regression is present or not. If there is a large number of commits, this can take a long time. This is a linear search. We can do better by doing a binary search. This is what the `git bisect` command does. At each step it tries to reduce the number of revisions that are potentially bad by half.

The __goal__ is find the first commit that fails a test and this is the procedure for finding that commit:
- split the suspect commits in half
- if test fails, then bug is in the _left half_
- if test passes, then bug is in the _right half_
- repeat

### Example 1
Git bisect on the following sequence of commits. You get `fail/bad`,
`pass/good`, `fail/bad`, which is the culprint of commit?
- 1----2----3----4----5----6----7----8----9
#### Answer > 4

### Example 2
![image](https://user-images.githubusercontent.com/5623994/51081806-611ae900-16c6-11e9-9881-98bfc6f795ab.png)

## Parameterize Constructor
If you are creating an object in a constructor, often the easiest way to replace it is to _externalize its creation_, create the object outside the class, and make clients pass it into the constructor as a parameter. Here are some example.

### Mail checker example
We start with this:
```java
public class MailChecker {
  public MailChecker(int checkPeriodSeconds) {
    this.receiver = new MailReceiver();
    this.checkPeriodSeconds = checkPeriodSecond;
  }
  ...
}
```

Then we make a copy of the constructor, add parameter `MailReceiver receiver`, and assign that parameter to the instance variable getting rid of the `new` expression:
```java
public class MailChecker {
  public MailChecker(int checkPeriodSeconds) {
    this.receiver = new MailReceiver();
    this.checkPeriodSeconds = checkPeriodSecond;
  }
  public MailChecker(MailReceiver receiver, int checkPeriodSeconds) {
    this.receiver = receiver;
    this.checkPeriodSeconds = checkPeriodSeconds;
  }
  ...
}
```

Lastly, we go back to the original constructor and remove its body, replacing it with a call to new constructor. The original constructor uses `new` to create the parameter it needs to pass.
```java
public class MailChecker {
  public MailChecker(int checkPeriodSeconds) {
      this(new MailReceiver(), checkPeriodSeconds);
  }
  public MailChecker(MailReceiver receiver, int checkPeriodSeconds) {
    this.receiver = receiver;
    this.checkPeriodSeconds = checkPeriodSeconds;
  }
  ...
}
```

Steps
To _Parameterize Constructor_, follow these steps:
1. Identify the constructor that you want to parameterize and make a copy of it.
2. Add a parameter to the constructor for the object whose creation you are going to replace. Remove the object creation and add an assignment from the parameter instance variable for the object.
3. If you can call a constructor from a constructor in your language, remove the body of the old constructor and replace it with a call to the old constructor. Add a new expression to the call of the new constructor in the old constructor. If you can't call a constructor from another constructor in your language, you may have to extract any duplication among the constructors to a new method.

### Pixel example
Consider the following constructor:
```java
public Pixel {
  public Pixel(Color color) {
    this.color = color;
    this.position = new Position(23, 4, 52);
  }
  ...
}
```

1. Parameterize the constructor
2. Make the change behavior preserving
3. Mock out the position and color

```java
public Pixel {
  public Pixel(Color color) {
    this(new Position(23, 4, 52), color);
  }
  public Pixel(Position position, Color color) {
    this.color = color;
    this.position = position;
  }
  ...
}
```
For number three:
```java
@Test
public void test() {
    Position position = mock(Position.class);
    Color color = mock(Color.class);
    Pixel pixel = new Pixel(position, color);
}
```
