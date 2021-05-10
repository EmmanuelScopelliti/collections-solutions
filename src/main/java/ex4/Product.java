package ex4;

import java.util.Comparator;

public class Product {

  private String name;
  private double price;

  public Product(String name, double unitPrice) {
    this.name = name;
    this.price = unitPrice;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  boolean isExpensive() {
    return getPrice() > 500;
  }

  boolean isCheap() {
    return !isExpensive();
  }

  @Override
  public String toString() {
    return name + " (" + price + ")";
  }

  public static Comparator<Product> comparingName() {
    return new Comparator<Product>() {
      @Override
      public int compare(Product o1, Product o2) {
        return o1.getName().compareTo(o2.getName());
      }
    };
  }
}
