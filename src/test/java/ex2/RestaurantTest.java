package ex2;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

  private Restaurant restaurant;
  private final Party p1 = new Party("p1");
  private final Party p2 = new Party("p2");
  private final Party p3 = new Party("p3");
  private final Party p4 = new Party("p4");

  @Test
  void restaurantCreatedEmpty() {
    restaurant = new Restaurant(2);
    assertEquals(2, restaurant.getAvailableTables());
  }

  @Test
  void partyArrivingAtEmptyRestaurantIsImmediatelyServed() {
    restaurant = new Restaurant(2);
    restaurant.arrive(p1);
    assertTrue(restaurant.getSeated().contains(p1));
    restaurant.arrive(p2);
    assertTrue(restaurant.getSeated().contains(p2));
  }

  @Test
  void partyArrivingAtFullRestaurantIsPutOnWaitingLine() {
    restaurant = new Restaurant(1);
    restaurant.arrive(p1);
    restaurant.arrive(p2);
    assertTrue(restaurant.getWaitingList().contains(p2));
    restaurant.arrive(p3);
    assertTrue(restaurant.getWaitingList().contains(p3));
  }

  @Test
  void tablesAreAvailableAfterAPartyPaysAndLeaves() {
    restaurant = new Restaurant(1);
    restaurant.arrive(p1);
    restaurant.payAndLeave(p1);
    assertFalse(restaurant.getSeated().contains(p1));
  }

  @Test
  void partiesWhoGiveUpShouldBeRemovedFromWaitingLine() {
    restaurant = new Restaurant(1);
    restaurant.arrive(p1);
    restaurant.arrive(p2);
    restaurant.arrive(p3);
    restaurant.arrive(p4);
    assertTrue(restaurant.getWaitingList().containsAll(List.of(p2, p3, p4)));
    restaurant.giveUp(p3);
    assertFalse(restaurant.getWaitingList().contains(p3));
  }

  @Test
  void firstToBeServedOnTheWaitingLineIsTheFirstToHaveArrived() {
    restaurant = new Restaurant(1);
    restaurant.arrive(p1);
    restaurant.arrive(p2);
    restaurant.arrive(p3);
    restaurant.arrive(p4);
    restaurant.payAndLeave(p1);
    restaurant.serveNext();

    assertTrue(restaurant.getSeated().contains(p2));
    assertFalse(restaurant.getWaitingList().contains(p2));
    assertTrue(restaurant.getWaitingList().containsAll(List.of(p3, p4)));
  }
}
