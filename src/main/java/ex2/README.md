## Exercise 2: Restaurant

The [`Restaurant`](Restaurant.java) class simulates some interactions one normally experiences in a restaurant. 

A restaurant has a fixed number of tables (aka its `capacity`).

Whenever a party arrives, if there are empty tables, the party is immediately seated, otherwise, it is put in the waiting list.

When a party finishes their meal, it pays and leaves the restaurant, thus opening up a spot for another party to dine. Whenever that happens, the next party to be seated should the one who arrived first at the waiting line.

Parties in the waiting list may simply decide to leave before being seated.

[`Restaurant.java`](Restaurant.java) has the following methods you should implement to simulate the aforementioned interactions:

- `public Restaurant(int capacity)`
- `public void arrive(Party party)`
- `public void giveUp(Party party)`
- `public void serveNext()`
- `public void payAndLeave(Party party)`
- `public List<Party> getWaitingLine()`
- `public List<Party> getSeated()`
- `public int getAvailableTables()`

You should NOT change any method signature, but you are free to add new methods you see fit.

Implement this class using one of the following data structures defined in the Java Collections Framework: `List`, `Set`, `Queue`, `Deque`.

Your code should pass all tests defined in [`RestaurantTest`](../../../test/java/ex2/RestaurantTest.java).
 
 