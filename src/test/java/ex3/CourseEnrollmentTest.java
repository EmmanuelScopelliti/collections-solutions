package ex3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CourseEnrollmentTest {
  Course course = new Course("Programming Project");
  Student john = new Student("John Doe", "2020001");
  Student mary = new Student("Mary Jane", "2020002");
  Student paul = new Student("Paul Smith", "2020003");
  Student anna = new Student("Anna Roberts", "2020004");

  @Test
  void shouldEnrollStudent() {
    course.enroll(john);
    assertTrue(course.getEnrolledStudents().contains(john));
  }

  @Test
  void shouldEnrollStudents() {
    course.enroll(john);
    course.enroll(mary);
    course.enroll(paul);
    course.enroll(anna);
    assertTrue(course.getEnrolledStudents().containsAll(List.of(john, mary, paul, anna)));
  }

  @Test
  void shouldEnrollAll() {
    course.enrollAll(john, mary, paul, anna);
    assertTrue(course.getEnrolledStudents().containsAll(List.of(john, mary, paul, anna)));
  }

  @Test
  void shouldBeEnrolled() {
    course.enrollAll(john, mary);
    assertTrue(course.isEnrolled(john));
    assertTrue(course.isEnrolled(mary));
  }

  @Test
  void shouldNotBeEnrolled() {
    course.enrollAll(john, mary);
    assertFalse(course.isEnrolled(anna));
    assertFalse(course.isEnrolled(paul));
  }

  @Test
  void shouldUnenrollStudent() {
    course.enroll(john);
    course.unenroll(john);
    assertFalse(course.getEnrolledStudents().contains(john));
  }

  @Test
  void shouldUnenrollStudents() {
    course.enrollAll(john, mary, paul, anna);
    course.unenroll(john);
    course.unenroll(paul);
    assertTrue(course.getEnrolledStudents().containsAll(List.of(anna, mary)));
    assertFalse(course.getEnrolledStudents().containsAll(List.of(john, paul)));
  }

  @Test
  void shouldUnenrollAll() {
    course.enrollAll(john, mary, paul, anna);
    course.unenrollAll(john, paul);
    assertTrue(course.getEnrolledStudents().containsAll(List.of(anna, mary)));
    assertFalse(course.getEnrolledStudents().containsAll(List.of(john, paul)));
  }

  @Test
  void shouldReturnStudentListSortedByName() {
    course.enrollAll(john, mary, paul, anna);
    List<Student> students = course.getStudentListSortedByName();
    assertEquals(anna, students.get(0));
    assertEquals(john, students.get(1));
    assertEquals(mary, students.get(2));
    assertEquals(paul, students.get(3));
  }

  @Test
  void shouldReturnMutableStudentListSortedByName() {
    course.enrollAll(john, mary, paul, anna);
    List<Student> students = course.getStudentListSortedByName();
    assertDoesNotThrow(() -> students.add(john));
  }

  @Test
  void shouldReturnStudentListSortedByStudentId() {
    course.enrollAll(anna, john, paul, mary);
    List<Student> students = course.getStudentListSortedByStudentId();
    assertEquals(john, students.get(0));
    assertEquals(mary, students.get(1));
    assertEquals(paul, students.get(2));
    assertEquals(anna, students.get(3));
  }

  @Test
  void shouldReturnMutableStudentListSortedByStudentId() {
    course.enrollAll(john, mary, paul, anna);
    List<Student> students = course.getStudentListSortedByStudentId();
    assertDoesNotThrow(() -> students.add(john));
  }

  @Test
  void shouldReturnImmutableStudentSet() {
    course.enrollAll(john, mary, paul, anna);
    Set<Student> students = course.getEnrolledStudents();
    assertThrows(UnsupportedOperationException.class, () -> students.add(john));
  }
}
