## Dependency Breaking with Bank Account

The `BankAccount` code is dependent on the old EFTPOS terminal.

![image](https://user-images.githubusercontent.com/5623994/51068627-e16e1b00-15ee-11e9-8a10-1acdc2b34418.png)

### Procedure
1. Draw a new diagram with the dependency on EFTPOS broken
2. Add a new iPad terminal to your diagram
3. Write Mockito code to check that the BankAccount calls the iPad class to display the balance when a withdrawal is made

![image](https://user-images.githubusercontent.com/5623994/51076259-339d5380-1664-11e9-9336-3fd7ae771c82.png)

```java
IPad ipad = mock(IPad.class);
BankAccount account = new BankAccount(ipad);
account.withdraw(200.0);
verify(ipad).displayLine(200.0);
// syntax:
// verify(object).method(args);
// method can have no arguments
```

**Which design pattern are we using?**

**Adapter Pattern**
- Problem: How to resolve incompatible interfaces, or provide a stable interface to similar components with different interfaces?
- Solution: Convert the interface of a class into another interface clients expect. 
