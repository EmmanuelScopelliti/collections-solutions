## Exercise 4: Life Before Streams

This exercise simulates a fragment of a simplified order management system. 

You should implement the methods listed below **WITHOUT USING ANY JAVA 8 FEATURES, SUCH AS STREAMS, LAMBDAS, AND OPTIONALS**. 

To learn what a method is supposed to do, please read its description and the unit tests located in [`test/java/ex4/`](../../../test/java/ex4).

- In the [`Order`](Order.java) class, implement the methods:
    - `public void addProduct(Product product, int quantity)`
    - `public void removeProduct(Product product)`
    - `public OrderLine getOrderLine(Product product)`
    - `public double getTotal()`
    - `public double getAverageProductPrice()`
    - `public Product getMostExpensiveProduct()`
    - `private boolean isEmpty()`
  
- In the [`Customer`](Customer.java) class, implement the methods:
  - `public List<Product> getPurchasedProducts()`
  - `public List<Product> getSortedListOfExpensivePurchasedProducts()`
  - `public Product findAnyCheapPurchasedProduct()`
  - `public Product mostPurchasedProduct()`

You should NOT change any method signature, but you are free to add new methods you see fit.

Your code should pass all tests defined in [`CourseConstructorTest`](../../../test/java/ex2/CourseConstructorTest.java), [`CourseCloneTest`](../../../test/java/ex2/CourseCloneTest.java), [`CourseEnrollmentTest`](../../../test/java/ex2/CourseEnrollmentTest.java), and [`CourseGradingTest`](../../../test/java/ex2/CourseGradingTest.java).
 