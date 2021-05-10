package ex4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderTest {

  final Product COMPUTER = new Product("Computer", 1200.00);
  final Product NOTEBOOK = new Product("Notebook", 1800.00);
  final Product KEYBOARD = new Product("Keyboard", 100.99);
  final Product MOUSE = new Product("Mouse", 30.49);
  final Product MONITOR = new Product("Monitor", 249.98);

  Order order = new Order();

  @Test
  void getOrderLineAfterAddingOnce() {
    order.addProduct(COMPUTER, 2);
    OrderLine line = order.getOrderLine(COMPUTER);
    assertEquals(COMPUTER, line.getProduct());
    assertEquals(2, line.getQuantity());
  }

  @Test
  void addProductShouldBeCumulative() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(COMPUTER, 5);
    OrderLine line = order.getOrderLine(COMPUTER);
    assertEquals(7, line.getQuantity());
  }

  @Test
  void addZeroShouldRemoveProductFromOrder() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(COMPUTER, 0);
    assertNull(order.getOrderLine(COMPUTER));
  }

  @Test
  void addNegativeProductShouldThrowException() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> order.addProduct(COMPUTER, -5));
  }

  @Test
  void shouldCalculateOrderTotal() {
    order.addProduct(COMPUTER, 1);
    order.addProduct(MONITOR, 2);
    order.addProduct(MOUSE, 1);
    order.addProduct(KEYBOARD, 1);

    double expectedTotal = 1200 + 249.98 * 2 + 30.49 + 100.99;
    assertEquals(expectedTotal, order.getTotal());
  }

  @Test
  void shouldCalculateEmptyOrderTotal() {
    assertEquals(0, order.getTotal());
  }

  @Test
  void averageProductPriceOfEmptyOrderShouldBeZero() {
    assertEquals(0, order.getAverageProductPrice());
  }

  @Test
  void averagePriceOfOrderWithOneProductTypeIsProductPrice() {
    order.addProduct(COMPUTER, 1);
    assertEquals(1200, order.getAverageProductPrice());
  }

  @Test
  void averagePriceOfOrderWithQuantityOfOneProductIsProductPrice() {
    order.addProduct(COMPUTER, 5);
    assertEquals(1200, order.getAverageProductPrice());
  }

  @Test
  void shouldReturnAveragePriceOfTwoProducts() {
    order.addProduct(COMPUTER, 1);
    order.addProduct(NOTEBOOK, 1);
    assertEquals(1500, order.getAverageProductPrice());
  }

  @Test
  void shouldReturnAveragePriceOfThreeProducts() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(NOTEBOOK, 3);
    order.addProduct(KEYBOARD, 3);

    double expectedAverage = (1800+1200+100.99)/3;
    assertEquals(expectedAverage, order.getAverageProductPrice());
  }


  @Test
  void shouldReturnNotebookAsMostExpensive() {
    order.addProduct(COMPUTER, 2);
    order.addProduct(NOTEBOOK, 3);
    order.addProduct(KEYBOARD, 3);

    assertEquals(NOTEBOOK, order.getMostExpensiveProduct());
  }

  @Test
  void shouldReturnComputerAsMostExpensive() {
    order.addProduct(COMPUTER, 1);
    order.addProduct(KEYBOARD, 1000);

    assertEquals(COMPUTER, order.getMostExpensiveProduct());
  }


}
