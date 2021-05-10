package ex4;

import java.time.LocalDate;
import java.util.*;

public class Customer {
  String firstName;
  String lastName;
  LocalDate dateOfBirth;
  List<Order> orders = new ArrayList<>();

  public Customer(String firstName, String lastName, LocalDate dateOfBirth) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public Order createOrder() {
    Order order = new Order(this);
    orders.add(order);
    return order;
  }

  /**
   * Returns all products purchased by the customer. If a product was purchased more than once, it
   * is returned multiple times.
   */
  public List<Product> getPurchasedProducts() {
    List<Product> purchased = new ArrayList<>();

    for (Order order : orders) {
      for (OrderLine orderLine : order.getOrderLines()) {
        purchased.add(orderLine.getProduct());
      }
    }

    return purchased;
  }

  /**
   * Returns a list of unique expensive products, sorted by product name (a-z). Returns an empty
   * list if the customer has not made any orders or never purchased an expensive product.
   *
   * @see Product#isExpensive()
   */
  public List<Product> getSortedListOfExpensivePurchasedProducts() {
    Set<Product> expensiveProductSet = new HashSet<>();

    for (Product purchased : getPurchasedProducts()) {
      if (purchased.isExpensive()) {
        expensiveProductSet.add(purchased);
      }
    }

    List<Product> expensiveProductList = new ArrayList<>(expensiveProductSet);
    expensiveProductList.sort(Product.comparingName());

    return expensiveProductList;
  }

  /**
   * Returns a cheap product purchased by the customer. Returns null if the customer has not made
   * any orders or never purchased a cheap product.
   *
   * @see Product#isCheap()
   */
  public Product findAnyCheapPurchasedProduct() {
    for (Product purchased : getPurchasedProducts()) {
      if (purchased.isCheap()) {
        return purchased;
      }
    }

    return null;
  }

  /**
   * Returns the most purchased product by the customer. If there is a tie, the method returns any
   * of the most purchased products. Returns null if the customer has not made any orders. This
   * method does not consider how quantities in each order, simply if a product was purchased or
   * not.
   */
  public Product mostPurchasedProduct() {
    if (orders.isEmpty()) {
      return null;
    }

    Map<Product, Integer> frequencyMap = new HashMap<>();

    for (Order order : orders) {
      for (OrderLine orderLine : order.getOrderLines()) {
        Product product = orderLine.getProduct();
        int frequency = frequencyMap.getOrDefault(product, 0) + 1;
        frequencyMap.put(product, frequency);
      }
    }

    if (frequencyMap.isEmpty()) {
      return null;
    }

    Set<Map.Entry<Product, Integer>> entries = frequencyMap.entrySet();
    Map.Entry<Product, Integer> mostPurchasedEntry = entries.iterator().next();

    for (Map.Entry<Product, Integer> entry : entries) {
      if (entry.getValue() > mostPurchasedEntry.getValue()) {
        mostPurchasedEntry = entry;
      }
    }

    return mostPurchasedEntry.getKey();
  }

}
