package ex1;

import java.util.ArrayList;
import java.util.List;

public class Table<E> {

  private int columns;
  private int rows;
  List<List<E>> table = new ArrayList<>();

  /**
   * Creates a table with the given number of rows and columns.
   * All cells are initialized as null.
   *
   * @throws IllegalArgumentException if the number of rows or columns is less or equal to zero.
   * */
  public Table(int rows, int columns) {
    if (rows <= 0 || columns <= 0) throw new IllegalArgumentException();

    this.rows = rows;
    this.columns = columns;

    for (int i = 0; i < rows; i++) {
      addRow();
    }
  }

  private List<E> addRow() {
    List<E> row = new ArrayList<>();
    table.add(row);

    for (int j = 0; j < columns; j++) {
      row.add(null);
    }

    return row;
  }

  private void removeColumn() {
    for (List<E> row : table) {
      row.remove(row.size() - 1);
    }
  }

  private void addColumn() {
    for (List<E> row : table) {
      row.add(null);
    }
  }

  private void removeRow() {
    table.remove(table.size() - 1);
  }

  /**
   * Sets the value in the cell identified by the provided row and column indexes.
   *
   * @throws IndexOutOfBoundsException if the row index is equal or greater than the number of rows in the table
   * or the column index is equal or greater than the number of rows in the table.
   * */
  public void set(int row, int column, E value) {
    table.get(row).set(column, value);
  }

  /**
   * Returns the value in the cell identified by the provided row and column indexes.
   *
   * @throws IndexOutOfBoundsException if the row index is equal or greater than the number of rows in the table
   * or the column index is equal or greater than the number of rows in the table.
   * */
  public E get(int row, int column) {
    return table.get(row).get(column);
  }

  /**
   * Returns a modifiable list containing all elements in the table.
   * */
  public List<E> toList() {
    List<E> list = new ArrayList<>();

    for (List<E> row : table) {
      list.addAll(row);
    }

    return list;
  }

  @Override
  public String toString() {
    StringBuilder value = new StringBuilder();
    table.forEach(row -> value.append(row.toString()).append("\n"));
    return value.toString();
  }

  /**
   * Returns the number of elements that fit in the table
   * */
  public int size() {
    return rows * columns;
  }

  /**
   * Adds the element in every cell of the table.
   * */
  public void fill(E element) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        set(i, j, element);
      }
    }
  }

  /**
   * Adds the element in every cell of row-i.
   * */
  public void fillRow(int i, E value) {
    for (int j = 0; j < columns; j++) {
      set(i, j, value);
    }
  }


  /**
   * Adds the element in every cell of column-j.
   * */
  public void fillColumn(int j, E element) {
    for (int i = 0; i < rows; i++) {
      set(i, j, element);
    }
  }

  /**
   * Resize the table. If the table is reduced, it removes the last rows and columns of the table.
   * If the table is enlarged, the added cells contain null.
   *
   * @throws IllegalArgumentException if the number of rows or columns is less or equal to zero.
   * */
  public void resize(int rows, int columns) {
    if (rows <= 0 || columns <= 0) throw new IllegalArgumentException();

    int rowDiff = rows - this.rows;
    this.rows = rows;

    // should shrink table by removing rows
    if (rowDiff < 0) {
      for (int i = 0; i < Math.abs(rowDiff); i++) {
        removeRow();
      }
    }
    // should enlarge table by adding rows
    else if (rowDiff > 0) {
      for (int i = 0; i < rowDiff; i++) {
        addRow();
      }
    }

    int columnDiff = columns - this.columns;
    this.columns = columns;

    // should shrink table by removing columns
    if (columnDiff < 0) {
      for (int j = 0; j < Math.abs(columnDiff); j++) {
        removeColumn();
      }
    } // should enlarge table by adding columns
    else if (columnDiff > 0) {
      for (int i = 0; i < rowDiff; i++) {
        addColumn();
      }
    }
  }
}
