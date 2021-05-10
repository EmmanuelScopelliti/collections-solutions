package ex4;

import java.util.ArrayList;
import java.util.List;

public class Order {
  Customer customer;
  List<OrderLine> orderLines = new ArrayList<>();

  public Order() {}

  public Order(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }

  public List<OrderLine> getOrderLines() {
    return orderLines;
  }

  /**
   * Adds a quantity of the product to the order. If the product is already listed in the order, sum
   * the previously ordered quantity with the input quantity.
   *
   * <p>If quantity is 0, remove the product from the order.
   *
   * @throws IllegalArgumentException if quantity is negative
   */
  public void addProduct(Product product, int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException();
    }

    if (quantity == 0) {
      removeProduct(product);
      return;
    }

    OrderLine line = getOrderLine(product);

    if (line == null) {
      line = createEmptyOrderLine(product);
    }

    int updatedQuantity = line.getQuantity() + quantity;
    line.setQuantity(updatedQuantity);
  }

  /**
   * Removes the product from the order. After invoking this method, {@link #getOrderLine(Product)}
   * will return null for the removed product.
   */
  public void removeProduct(Product product) {
    OrderLine orderLine = getOrderLine(product);
    if (orderLine != null) {
      orderLines.remove(orderLine);
    }
  }

  private OrderLine createEmptyOrderLine(Product product) {
    OrderLine line = new OrderLine(product);
    orderLines.add(line);
    return line;
  }

  /** Returns the order line for the product. Returns null if no such order line exists. */
  public OrderLine getOrderLine(Product product) {
    for (OrderLine orderLine : orderLines) {
      if (orderLine.getProduct().equals(product)) {
        return orderLine;
      }
    }

    return null;
  }

  /** Calculates the total price of the order. */
  public double getTotal() {
    double total = 0;

    for (OrderLine orderLine : orderLines) {
      total += orderLine.getTotal();
    }

    return total;
  }

  /**
   * Returns the average price of the products in the order. If the order is empty, returns 0.0; The
   * average does not take into account how many products were ordered.
   */
  public double getAverageProductPrice() {
    if (isEmpty()) {
      return 0;
    }

    double totalPrice = 0;

    for (OrderLine orderLine : orderLines) {
      totalPrice += orderLine.getProduct().getPrice();
    }

    return totalPrice / orderLines.size();
  }

  /** Returns the most expensive product in the order. If the order is empty, returns null; */
  public Product getMostExpensiveProduct() {
    if (isEmpty()) {
      return null;
    }

    Product mostExpensive = orderLines.get(0).getProduct();

    for (OrderLine orderLine : orderLines) {
      Product product = orderLine.getProduct();

      if (mostExpensive.getPrice() < product.getPrice()) {
        mostExpensive = product;
      }
    }

    return mostExpensive;
  }

  /** Returns true if there is at least one order line in the order. */
  private boolean isEmpty() {
    return orderLines.size() == 0;
  }

  @Override
  public String toString() {
    StringBuilder orderLinesString = new StringBuilder();
    for (OrderLine orderLine : orderLines) {
      orderLinesString.append(orderLine.toString()).append("\n");
    }

    return String.format("%s---------%nTotal = %5.2f", orderLinesString, getTotal());
  }
}
