package ex2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Restaurant {
  int capacity;
  List<Party> seated = new ArrayList<>();
  Queue<Party> waitingLine = new ArrayDeque<>();

  /** Creates a restaurant with the input capacity. */
  public Restaurant(int capacity) {
    this.capacity = capacity;
  }

  /**
   * A party arrived at the restaurant. If there are empty tables, the party is seated and starts to
   * dine. Otherwise, the party is put on the waiting list.
   */
  public void arrive(Party party) {
    if (seated.size() < capacity) {
      seated.add(party);
    } else {
      waitingLine.add(party);
    }
  }

  /**
   * A party on the waiting list decides not to wait anymore and leaves the restaurant. If the input
   * party isn't in the waiting list, this method has no side effects.
   */
  public void giveUp(Party party) {
    waitingLine.remove(party);
  }

  /**
   * Serves the party in the waiting list that arrived first in the restaurant. If there isn't
   * anybody in the waiting list, the method has no side effects.
   */
  public void serveNext() {
    Party next = waitingLine.poll();
    seated.add(next);
  }

  /** The party finished dining, payed, and left the restaurant. */
  public void payAndLeave(Party party) {
    seated.remove(party);
  }

  /** Returns all parties currently in the waiting list. */
  public List<Party> getWaitingList() {
    return new ArrayList<>(waitingLine);
  }

  /** Returns all parties currently dining in the restaurant. */
  public List<Party> getSeated() {
    return new ArrayList<>(seated);
  }

  /** Returns the number of available tables. */
  public int getAvailableTables() {
    return capacity - seated.size();
  }

  @Override
  public String toString() {
    return "Dining: " + seated + "\nWaitingLine: " + waitingLine;
  }
}
