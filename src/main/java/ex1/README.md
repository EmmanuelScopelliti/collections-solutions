## Exercise 1: `Table<E>`

Implement the missing methods in the class [`Table.java`](Table.java), a table that holds a single type of object and whose number of rows and columns can be changed dynamically.

You should implement the methods below:

- `public Table(int rows, int columns)` 
- `public void set(int row, int column, E value)`
- `public E get(int row, int column)`
- `public List<E> toList()`
- `public int size()`
- `public void fill(E element)`
- `public void fillRow(int i, E value)`
- `public void fillColumn(int j, E element)`
- `public void resize(int rows, int columns)`
- `public String toString()`

You should NOT change any method signature, but you are free to add any additional methods you see fit.

Implement this class using one of the following data structures defined in the Java Collections Framework: `List`, `Set`, `Queue`, `Deque`.

See the description of each method in [`Table.java`](Table.java) and the test methods in [`PairTest`](../../../test/java/ex1/TableTest.java) for details on how each method should behave.

Your code should pass all tests defined in [`TableTest`](../../../test/java/ex1/TableTest.java).