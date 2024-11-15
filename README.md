# Review SOEN 345
## Table of Contents
- [Glossary](#glossary)
- [Source Code](#source-code)
- [UML Class Diagram](#uml-class-diagram)
- [Adapter Pattern](#adapter-pattern)
- [TDD Algorithm](#tdd-algorithm)
- [Characterization Test Algorithm](#characterization-test-algorithm)
- [Java Stack Iterator Question](#java-stack-iterator-question)
- [Git Bisect](#git-bisect)
- [Parameterize Constructor](#parameterize-constructor)
  - [MailChecker](#mail-checker-example)
  - [Pixel](#pixel-example)
- [Flaky Test](#flaky-test)
- [Construction Blob](#construction-blob)
- [Supersede Instance Variable](#supersede-instance-variable)
  - [Sale](#sale-example)
  - [Pane](#pane-example)
- [Irritating Global Dependency](#irritating-global-dependency)
  - Singleton pattern
  - Reset singleton instance for testing
- [Dependency Injection](#dependency-injection)
- [Feature Toggles](#feature-toggles)
- [Java Reflection](#java-reflection)
- [Proxy](#proxy)
- [Guice Framework](#dependency-injection-with-guice)
  
## Glossary
- __characterization test__ is a test written to document the current behavior of a piece of code. The tests document the actual behavior of the system.
- __fake object__ is an object that impersonates a collaborator of a class during testing.
- __flaky test__ is a non-deterministic test.
- __hidden dependency__: is a testing smell where _the constructor_ in the class under test _uses some resources_ that _we can't access_ in our test harness.
- __inversion of control__: use inversion of control to allow the framework to specify the dependencies
- __legacy code__ is simply code without tests.
- __mock object__ is a fake object that asserts conditions internally.
- __mockito mocks objects using__: **reflection** and a **proxy object**.
- __reflection__ is a language's ability to inspect and dynamically call classes, methods, attributes, etc. at runtime.
- __sensing__: we break dependencies to sense when _we can't access values_ our code computes.
- __separation__: we break dependencies to separate when _we can't even get a piece of code_ into a test harness to run.
- __test harness__ is a piece of software that enables unit testing.
- __test-driven development__ is a development process that consists of writing failing test cases and satisfying them one at a time. As you do this, you refactor to keep the code as simple as possible. Code developed using TDD has test coverage, by default.
- __unit test__ is a **test that runs in less than 1/10th of a second** and is **small enough to help you localize problems** when it fails.

## Source Code
|src|Main |Test |
|---|-----|-----|
|TDD|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/tdd)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/tdd)|
|Characterization test|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/characterization/test)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/characterization/test)|
|Dependency breaking|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/dependency/breaking)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/dependency/breaking)|
|Mockito|---|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/mockito)|
|Proxy|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/main/java/proxy)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/proxy)|
|Singleton|[link](https://github.com/tramyardg/UnitTestViaJava/blob/master/src/main/java/PermitRepository.java)|---|
|Data Migration|[link](https://github.com/tramyardg/UnitTestViaJava/blob/master/src/main/java/data/migration)|[link](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/data/migration)|

## UML Class Diagram
![uml_class_diagram](https://user-images.githubusercontent.com/5623994/51428593-87afb700-1bd3-11e9-9d81-57fdf0f460ee.png)

## Adapter Pattern
![adapter_pattern](https://user-images.githubusercontent.com/5623994/51864628-470e1700-2312-11e9-8098-126c231c11b2.png)


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

**Step by step sample**
1. Get the code in a test harness.
`java.util.Stack;`
2. Write an assertion that you know will fail.
`push(1);`
`push(2);`
`assertEquals(-1, pop());`
3. Let the failure tell you what the behavior is.
`prints 2`
4. Change the test so that it expects the behavior that the code produces.
`assertEquals(2, pop());`
5. Repeat

## Java Stack Iterator Question
Recall that the _iterator_ for the Stack class for Java is incorrect. Write a unit test that would characterize the actual behavior of the Java Stack's iterator. See [test](https://github.com/tramyardg/UnitTestViaJava/blob/master/src/test/java/characterization/test/TestStack.java) for full details.

```java
@Test
public void testStack() {
  Stack<Integer> stack = new Stack<Integer>();
  stack.push(1);
  stack.push(2);
  int expectedVal = 1;
  for (Integer stackElem : stack) {
    // prints like a queue: 1, 2 (iterator of the stack behaves like a queue)
    log.info("stack elem {}", stackElem);
    assertEquals(expectedVal, stackElem.intValue());
    expectedVal++;
  }
  // stack is not empty because we didn't pop
  assertFalse(stack.isEmpty());
}
// [main] INFO characterization.test.TestStack - stack [1, 2]
// [main] INFO characterization.test.TestStack - peek stack 2
// [main] INFO characterization.test.TestStack - stack elem 1
// [main] INFO characterization.test.TestStack - stack elem 2
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
- if test fails, then bug is in the __left half__
- if test passes, then bug is in the __right half__
- repeat

**Example 1**

Git bisect on the following sequence of commits. You get `fail/bad`,
`pass/good`, `fail/bad`, which is the culprit of commit?
- 1----2----3----4----[5]----6----7----8----9
- fail: 1----2----[3]----4----5
- pass: 4----5
- fail: 4

Answer: 4

**Example 2**
![image](https://user-images.githubusercontent.com/5623994/51081806-611ae900-16c6-11e9-9881-98bfc6f795ab.png)


## Parameterize Constructor
Solves the problem of _hidden dependencies_ in constructor, so we can inject dependencies and be able to test. If you are creating an object in a constructor, often the easiest way to replace it is to _externalize its creation_, create the object outside the class, and make clients pass it into the constructor as a parameter. Here are some example.

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

## Flaky Test
A flaky test is a non-deterministic test.
- Test should fail when there is a problem and pass when there is no problem
- Flaky test can pass and fail on the same build!
- Flaky tests fail even when there is no problem. This destroys developer confidence in tests.

**Sources of non-determinism**
- Inherent non-determinism
  - noisy or complex test environment
  - asynchronous calls
- Accidental non-determinism: old and out-of-date tests introduce flakiness

## Construction Blob
The case of construction blob
- The constructor creates many objects.
- We could parameterize constructors but the parameter list would become massive.
- We can supersede instance variable.

### Supersede Instance Variable
Is a solution for construction blob. To _Supersede Instance Variable_, follow these steps:
1. Identify the instance variable that you want to supersede.
2. Create a method named `supersedeXXX`, where `XXX` is the named of the variable you want to supersede.
3. In the method, write whatever code you need to, so that you destroy the previous instance of the variable and set it to the new value. If the variable is a reference, verify that there are not any other references in the class to the object it points to. If there are you might have an additional work to do in superseding method to make sure that replacing the object is safe and has the right effect.

#### Sale Example
```java
public class Sale {
  private Display display;
  private Storage storage;
  private Interact interac;
  
  public Sale(Display display, Storage storage) {
    this(display, storage, new Interac(12));
  }
	
  public Sale(Display display, Storage storage, Interac interac) {
    this.display = display;
    this.storage = storage;
    this.interac = interac;
  }
  
  public void supersedeInteract(Interac interac) {
    this.interac = interac;
  }
}
```
Now the test:
```java
@Test
public void testSupersedeInterac() {
  Display mockDisplay = mock(Display.class);
  Storage mockStorage = mock(Storage.class);
  Interac mockInterac = mock(Interac.class);
  // call to the original constructor (the one without the superseded variable) 
  // and pass in the mocked classes
  Sale sale = new Sale(mockDisplay, mockStorage);
  // supersede interac
  sale.supersedeInterac(mockInterac);
}
```

#### Pane Example
```java
class Pane {
  private FocusWidget cursor;
  public Pane(WashBrush brush, Pattern backdrop) {
    ...
    this.cursor = new FocusWidget(brush, backdrop);
    ...
  }
  // superseding the instance variable cursor
  public void supersedeCursor(FocusWidget newCursor) {
    cursor = newCursor;
  }
}
```
Now the test:
```java
@Test
public void testSupersedeCursor() {
  WashBrush mockBrush = mock(WashBrush.class);
  Pattern mockPattern = mock(Pattern.class);

  Pane pane = new Pane(mockBrush, mockPattern);
  FocusWidget cursor = mock(FocusWidget.class);
  pane.supersedeCursor(cursor);

  // or with fakes
  FakeFocusWidget cursor2 = new FakeFocusWidget();
  pane.supersedeCursor(cursor2);
}
```

## Irritating Global Dependency
- In Java, the _singleton pattern_ is one of the mechanisms people use to make global variables.
- The whole idea of the _singleton pattern_ is to make it impossible to create more than one instance of a singleton in an application.
- That might be fine in production code, but, it is particularly hard to fake and when testing, each test in a suite of tests should be a mini-application, in a way: It should be totally isolated from the other tests.

### Solution 1: static setter for instance variable (static void method with argument)
So, to run code containing singletons in a test harness, we have to relax the singleton property. Here's how we can do it. The first step is to **add a new static method** to the singleton class. This method allows us to replace the static instance in the singleton. We'll call it `setTestingInstance`.

Applying the first step, the singleton class `PermitRepository` becomes:

```java
public class PermitRepository {
  private static PermitRepository instance = null;
  
  private PermitRepository() {}
  
  public static PermitRepository getInstance() {
    if (instance == null) {
      instance = new PermitRepository();
    }
    return instance;
  }
  
  // irritating global dependency solution 1: static instance setter
  public static void setTestingInstance(PermitRepository newInstance) {
    instance = newInstance;
  }
  // ...
}
```

Now, we'd like to write code like this in our test setup.
```java
@Before
public void setUp() {
  PermitRepository repository = null;
  PermitRepository.setTestingInstance(repository);
  ...
}
```

### Solution 2: reset the singleton (static void)
 Introducing static setter is not the only way of handling this situation. Another approach is to add a `resetForTesting()` method to the singleton that looks like this:

```java
public class PermitRepository {
  private static PermitRepository instance = null;
  
  // irritating global dependency solution 2: reset the singleton
  public static void resetForTesting() {
    instance = null;
  }
  // ...
}
```

## Dependency Injection
Dependency injection is basically providing the objects that an object needs (its dependencies) instead of having it construct them itself. It's a very useful technique for testing, since it allows dependencies to be mocked or stubbed out.
- means giving an object its instance variables
- all parameters to be passed in through the constructor
- refactor using parameterize constructor
- [stackoverflow](https://stackoverflow.com/questions/130794/what-is-dependency-injection)

**Without dependency injection**

```java
class Car {
  private Wheel wh;
  private Battery bt;
  Car() {
      // dependency is hidden in this constructor
      // can't test
      // can't inject dependencies or mocks
      // violates the law of demeter by creating an instance of objects in the constructor
      // solutions: parameterize the constructor or use Guice framework
      wh = new NepaliRubberWheel();
      bt = new ExcideBattery();
  }
  // ... more code
}
```

**After using dependency injection**

Here, we are injecting the dependencies (Wheel and Battery) at runtime. Hence the term : Dependency Injection.
```java
class Car {
  private Wheel wh;   // [Inject an Instance of Wheel (dependency of car) at runtime]
  private Battery bt; // [Inject an Instance of Battery (dependency of car) at runtime]
  
  Car(Wheel wh, Battery bt) {
      this.wh = wh;
      this.bt = bt;
  }
  
  // Or we can have setters
  void setWheel(Wheel wh) {
      this.wh = wh;
  }
}
```

## Dependency Injection with Guice

Sale example
```java
class Sale {
    @Inject
    HashStorage lookup;
    
    @Inject
    Display display;
    
    @Inject
    Interac interac;
    
    @Inject
    Sale(Display d, HashStorage h, Interac i) {
        this.display = d;
        this.lookup = h;
        this.interac = i;
    }
    //...
}
```

Sale injector module
```java
public class SaleInjectorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Display.class).to(Display.class);
        
        // you can also use toInstance(instance);
        bind(Interac.clss).toInstance(new Interact(12));
        
        HashStorage hashStorage = new HashStorage();
        hashStorage.put("1B", "Chocolate");
        bind(HashStorage.class).toInstance(hashStorage);
    }
}
```

In a main method or a test method.
```java
class TestSaleInjector {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new SaleInjectorModule());
        Sale sale = injector.getInstance(Sale.class);
        sale.scan("1B");
        // notice that there's no need for a constructor and mocks anymore
        // usually you would have Display, HashStorage, and Interac instances
        // but now SaleInjectorModule handles that
    }
}
```

Class under test
```java
class BillingService {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;
  
    @Inject
    BillingService(CreditCardProcessor processor, TransactionLog log) {
        this.processor = processor;
        this.transactionLog = log;
    }
    // ... more code
}
```

Guice uses _bindings_ to map types to their implementations.
A _module_ is a collection of bindings specified using fluent, English-like method calls:
```java
class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
     /*
      * This tells Guice that whenever it sees a dependency on a TransactionLog,
      * it should satisfy the dependency using a DatabaseTransactionLog.
      */
      bind(TransactionLog.class).to(DatabaseTransactionLog.class);
      
      /*
      * Similarly, this binding tells Guice that when CreditCardProcessor is used in
      * a dependency, that should be satisfied with a PaypalCreditCardProcessor.
      */
      bind(CreditCardProcessor.class).to(PaypalCreditCardProcessor.class);
    }
}
```

Testing
```java
public class TestBillingService {
    @Test
    public void testBilling() {
        /*
         * 3. Create an injector instance and initialize it to Guice.createInjector() 
         * which takes your Modules, and returns a new Injector instance. 
         * Most applications will call this method exactly once, in their main() method.
         */
        Injector injector = Guice.createInjector(new BillingModule());
    
        // 4. Calling the class under test
        BillingService billingService = injector.getInstance(BillingService.class);
    }
}
```

Review
- Use inject annotation `@Inject` in class under test
- Create a module class and override the configure method
- Inside the configure method, use bindings to bind the parameters of constructors 
- Create an `Injector` instance and initialize it with `Guice.createInjector(moduleName)`
- You can now create a new instance of the class under test by initializing it as `injector.getInstance(ClassUnderTest.class)`

## Java Reflection
Is a language's ability to inspect and dynamically call classes, methods, attributes, etc. at runtime.
```java
// Show the methods of BillingService class
Method[] methods = BillingService.class.getMethods();
for (Method method: methods) {
    System.out.println('billing service methods :' + method.getName());
}
```

## Proxy
### Proxy Design Pattern
UML diagram
![proxy design pattern](https://user-images.githubusercontent.com/5623994/52924004-a7341f80-32f8-11e9-95ab-39ba6312a7e4.png)

You create a dynamic proxies using `Proxy.newProxyInstance(...)` method. The newProxyInstance() methods takes 3 parameters:
1. The ClassLoader that is to "load" the dynamic proxy class. <br />
`MyInterface.class.getClassLoader()`
2. An array of interfaces to implement. <br />
`new Class[] {MyInterface.class}`
3. An InvocationHandler to forward all methods calls on the proxy to. <br />
`myHandler`

For example,
```java
public class TestProxy {
    @Test
    void testProxy() {
        MockInvocationHandler mockHandler = new MockInvocationHandler();
        IDisplay display = (IDisplay) Proxy.newProxyInstance(
            IDisplay.class.getClassLoader(),
            new Class[] {IDisplay.class},
            mockHandler
        );
    }
}
```
See the test [here](https://github.com/tramyardg/UnitTestViaJava/tree/master/src/test/java/proxy/TestProxy.java)

## Feature Toggles
[Reading](https://users.encs.concordia.ca/~pcr/paper/Rahman2016MSR.pdf)

### Three Types of Toggles
There are three types of toggles: **development**, **long-term business**, and **release toggles**. Although release toggles are shorter lived than the other types of toggles, 53% still exist after 10 releases indicating that many linger as technical debt.

### Advantages of Toggles

#### Reconciling Rapid Release and Longterm Feature Development:
Therefore, many major companies doing rapid releases prefer to work from a single master branch (trunk) in their version control system and use feature toggles instead of feature branches to isolate feature development. 

#### Flexible Feature Roll-out
In particular, feature toggles provide the flexibility to gradually roll out features and do user experiments (A/B testing) on new features. For example, â€œEvery day, we [Facebook] run hundreds of tests on Facebook, most of which are rolled out to a random sample of people to test their impact. If a new feature does not work well, it is toggled off.  The ability to flexibly enable and disable feature sets to specific groups of users to determine their effectiveness early on, reduces the investment in features that are not profitable.

#### Enabling Fast Context Switches
For example, if a developer working on a given feature in a dedicated branch gets a request to fix an urgent bug, she needs to switch to another branch, fix the bug and test it, then switch back to the original branch to continue feature development. Developers often mistake the branch they are in, leading to commits to the wrong branch. According to Ho, toggling requires less effort than switching branches, hence it reduces the potential of unwanted check-ins and accompanying broken builds.

#### Features are designed to be toggleable
For example, the on-call developers at Facebook responsible for monitoring how new features behave in production (DevOps) need to be able to disable malfunctioning features within seconds to avoid affecting millions of users.
