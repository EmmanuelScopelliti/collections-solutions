package ex1;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
  Table<Integer> table;

  @Test
  void shouldInitializeTableWithNullValues() {
    table = new Table<>(2, 3);
    boolean allNull = table.toList().stream().allMatch(Objects::isNull);
    assertTrue(allNull);
  }

  @Test
  void sizeShouldBeNumberOfCells() {
    table = new Table<>(2, 7);
    assertEquals(14, table.size());
  }

  @Test
  void toListShouldReturnAllCells() {
    table = new Table<>(3, 4);
    assertEquals(12, table.toList().size());
  }

  @Test
  void setShouldAlterValue() {
    table = new Table<>(2, 2);
    table.set(0, 0, 96);
    table.set(0, 1, 97);
    table.set(1, 0, 98);
    table.set(1, 1, 99);

    assertEquals(96, table.get(0, 0));
    assertEquals(97, table.get(0, 1));
    assertEquals(98, table.get(1, 0));
    assertEquals(99, table.get(1, 1));
  }

  @Test
  void settingValueRowOutOfBoundsShouldThrowException() {
    table = new Table<>(2, 2);
    assertThrows(IndexOutOfBoundsException.class, () -> table.set(2, 0, 7));
  }

  @Test
  void settingValueColumnOutOfBoundsShouldThrowException() {
    table = new Table<>(2, 2);
    assertThrows(IndexOutOfBoundsException.class, () -> table.set(0, 2, 7));
  }

  @Test
  void toListShouldContainAllItemsInTheTable() {
    table = new Table<>(2, 2);
    table.set(0, 0, 9);
    table.set(0, 1, 99);
    table.set(1, 0, 999);
    table.set(1, 1, 9999);

    assertTrue(table.toList().containsAll(List.of(9, 99, 999, 9999)));
  }

  @Test
  void fillShouldSetValueInEveryCell() {
    table = new Table<>(2, 2);
    table.fill(3);

    assertEquals(3, table.get(0, 0));
    assertEquals(3, table.get(0, 1));
    assertEquals(3, table.get(1, 0));
    assertEquals(3, table.get(1, 1));
  }

  @Test
  void fillRowShouldSetValueInEveryCellInTheRow() {
    table = new Table<>(2, 2);
    table.fillRow(0, 9);

    assertEquals(9, table.get(0, 0));
    assertEquals(9, table.get(0, 1));
    assertNull(table.get(1, 0));
    assertNull(table.get(1, 1));
  }

  @Test
  void fillColumnShouldSetValueInEveryCellInTheRow() {
    table = new Table<>(2, 2);
    table.fillColumn(1, 7);

    assertNull(table.get(0, 0));
    assertEquals(7, table.get(0, 1));
    assertNull(table.get(1, 0));
    assertEquals(7, table.get(1, 1));
  }

  @Test
  void resizeShouldDropLastRows() {
    table = new Table<>(3, 2);
    table.fillRow(0, 10);
    table.fillRow(1, 20);
    table.fillRow(2, 30);
    table.resize(2, 2);

    assertEquals(4, table.size());
    assertEquals(10, table.get(0, 0));
    assertEquals(10, table.get(0, 1));
    assertEquals(20, table.get(1, 0));
    assertEquals(20, table.get(1, 1));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(2, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(2, 1));
  }

  @Test
  void resizeShouldDropLastColumns() {
    table = new Table<>(2, 3);
    table.fillColumn(0, 10);
    table.fillColumn(1, 20);
    table.fillColumn(2, 30);
    table.resize(2, 2);

    assertEquals(4, table.size());
    assertEquals(10, table.get(1, 0));
    assertEquals(10, table.get(0, 0));
    assertEquals(20, table.get(1, 1));
    assertEquals(20, table.get(0, 1));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(0, 2));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(1, 2));
  }

  @Test
  void resizeShouldDropLastRowsAndColumns() {
    table = new Table<>(2, 2);
    table.set(0, 0, 10);
    table.set(0, 1, 20);
    table.set(1, 0, 30);
    table.set(1, 1, 40);
    table.resize(1, 1);

    assertEquals(1, table.size());
    assertEquals(10, table.get(0, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(0, 1));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(1, 0));
    assertThrows(IndexOutOfBoundsException.class, () -> table.get(1, 1));
  }

  @Test
  void resizeToNonPositiveRowNumberShouldThrowException() {
    table = new Table<>(2, 2);
    assertThrows(IllegalArgumentException.class, () -> table.resize(0, 2));
    assertThrows(IllegalArgumentException.class, () -> table.resize(-1, 2));
  }

  @Test
  void resizeToNonPositiveColumnNumberShouldThrowException() {
    table = new Table<>(2, 2);
    assertThrows(IllegalArgumentException.class, () -> table.resize(2, 0));
    assertThrows(IllegalArgumentException.class, () -> table.resize(2, -1));
  }
}
