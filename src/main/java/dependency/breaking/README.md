## Dependency Breaking with Fake Objects
A _fake object_ is an object that impersonates some collaborator of your class when it is being tested.

### Point of Sale System (POS) Example
In a POS system, we have a class called `Sale`. It has a method called `scan()` that accepts a bar code for some item that a customer wants to buy. Whenever `scan()` is called, the `Sale` object needs to display the name of the item that was scanned, along with its price on a cash register display.

![sale](https://user-images.githubusercontent.com/5623994/51017592-926aac00-1542-11e9-91a8-54acd646af2e.png)

How can we test this to see if the right text shows up on the display? Well, if the calls to the register's display API are buried deep in the `Sale` class, it's going to be hard. It might not be easy to sense the effect on the display. But if we can find the place in the code where the display is updated, we can move to the design shown below.

![sale communicating with display](https://user-images.githubusercontent.com/5623994/51017982-fe99df80-1543-11e9-8bb2-559afe8c62ae.png)

Here we've introduced a new class, `ArtR56Display`. That class contains all of the code needed to talk to particular display device we're using. All we have to do is supply it with a line of text that contains what we want to display. We can move all of the display code in `Sale` over to `ArtR56Display` and have a system that does exactly the same thing that it did before.

![sale with the display hierarchy](https://user-images.githubusercontent.com/5623994/51052684-63395680-15a5-11e9-91a8-13346e927065.png)

The `Sale` class can now hold on to either an `ArtR56Display` or something else, a `FakeDisplay`. The nice thing about having a fake display is that we can write tests against it to find out what the `Sale` does.
