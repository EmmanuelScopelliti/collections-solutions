package ex4;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

  static final Product COMPUTER = new Product("Computer", 1200.00);
  static final Product NOTEBOOK = new Product("Notebook", 1800.00);
  static final Product TABLET = new Product("Tablet", 900.00);
  static final Product KEYBOARD = new Product("Keyboard", 100.99);
  static final Product MOUSE = new Product("Mouse", 30.49);
  static final Product MONITOR = new Product("Monitor", 249.98);

  @Test
  void shouldReturnComputerNotebookTablet() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(COMPUTER, 3);
    order1.addProduct(NOTEBOOK, 2);

    Order order2 = customer.createOrder();
    order2.addProduct(COMPUTER, 1);
    order2.addProduct(MONITOR, 2);
    order2.addProduct(MOUSE, 1);
    order2.addProduct(KEYBOARD, 3);

    Order order3 = customer.createOrder();
    order3.addProduct(NOTEBOOK, 6);
    order3.addProduct(MONITOR, 6);
    order3.addProduct(MOUSE, 6);

    Order order4 = customer.createOrder();
    order4.addProduct(TABLET, 50);

    List<Product> list = customer.getSortedListOfExpensivePurchasedProducts();
    assertEquals(List.of(COMPUTER, NOTEBOOK, TABLET), list);
  }

  @Test
  void shouldReturnEmptyListIfCustomerHasNoOrders() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));
    List<Product> list = customer.getSortedListOfExpensivePurchasedProducts();
    assertEquals(List.of(), list);
  }

  @Test
  void shouldReturnEmptyListIfCustomerOnlyOrderedCheapProducts() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(MOUSE, 100);
    order1.addProduct(KEYBOARD, 10);

    Order order2 = customer.createOrder();
    order2.addProduct(MONITOR, 3);

    List<Product> list = customer.getSortedListOfExpensivePurchasedProducts();
    assertEquals(List.of(), list);
  }

  @Test
  void shouldReturnAnyCheapProduct() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(MOUSE, 100);
    order1.addProduct(KEYBOARD, 10);
    order1.addProduct(NOTEBOOK, 8);
    order1.addProduct(TABLET, 1);

    Order order2 = customer.createOrder();
    order2.addProduct(MONITOR, 3);
    order2.addProduct(COMPUTER, 3);

    Product cheapProduct = customer.findAnyCheapPurchasedProduct();
    assertTrue(List.of(MOUSE, KEYBOARD, MONITOR).contains(cheapProduct));
  }

  @Test
  void shouldReturnNullIfCustomerHasNoOrders() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));
    Product cheapProduct = customer.findAnyCheapPurchasedProduct();
    assertNull(cheapProduct);
  }

  @Test
  void shouldReturnNullIfCustomerOnlyPurchasedExpensiveProducts() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 8);
    order1.addProduct(TABLET, 1);

    Order order2 = customer.createOrder();
    order2.addProduct(COMPUTER, 3);
    Product cheapProduct = customer.findAnyCheapPurchasedProduct();
    assertNull(cheapProduct);
  }

  @Test
  void shouldReturnMostPurchasedUnique() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 15);
    order1.addProduct(TABLET, 1);
    order1.addProduct(COMPUTER, 3);

    Order order2 = customer.createOrder();
    order2.addProduct(COMPUTER, 3);

    assertEquals(COMPUTER, customer.mostPurchasedProduct());
  }

  @Test
  void shouldReturnMostPurchasedTie() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 15);
    order1.addProduct(TABLET, 7);
    order1.addProduct(COMPUTER, 3);

    Order order2 = customer.createOrder();
    order1.addProduct(TABLET, 4);
    order2.addProduct(COMPUTER, 3);

    Product mostPurchased = customer.mostPurchasedProduct();
    assertTrue(List.of(TABLET, COMPUTER).contains(mostPurchased));
  }

  @Test
  void mostPurchasedShouldReturnNull() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));
    Product mostPurchased = customer.mostPurchasedProduct();
    assertNull(mostPurchased);
  }

  @Test
  void shouldReturnProducts() {
    Customer customer = new Customer("John", "Doe", LocalDate.of(1990, Month.APRIL, 1));

    Order order1 = customer.createOrder();
    order1.addProduct(NOTEBOOK, 15);
    order1.addProduct(TABLET, 7);
    order1.addProduct(COMPUTER, 1);

    Order order2 = customer.createOrder();
    order2.addProduct(TABLET, 1);
    order2.addProduct(COMPUTER, 3);

    List<Product> purchased = customer.getPurchasedProducts();
    assertEquals(5, purchased.size());

    long notebooks = purchased.stream().filter(product -> product == NOTEBOOK).count();
    assertEquals(1, notebooks);

    long tablets = purchased.stream().filter(product -> product == TABLET).count();
    assertEquals(2, tablets);

    long computers = purchased.stream().filter(product -> product == COMPUTER).count();
    assertEquals(2, computers);
  }
}
