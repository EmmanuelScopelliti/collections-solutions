package ex2;

public class Party {
  String id;

  public Party(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Party#" + id;
  }
}
