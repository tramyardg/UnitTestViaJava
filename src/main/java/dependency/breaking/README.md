## Fake Objects
A _fake object_ is an object that impersonates some collaborator of your class when it is being tested.

### Point of Sale System (POS) Example
In a POS system, we have a class called `Sale`. It has a method called `scan()` that accepts a bar code for some item that a customer wants to buy. Whenever `scan()` is called, the `Sale` object needs to display the name of the item that was scanned, along with its price on a cash register display.