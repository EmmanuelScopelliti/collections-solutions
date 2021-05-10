package ex3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseConstructorTest {
  Course course = new Course("Programming Project");

  @Test
  void shouldBeCreatedWithoutStudents() {
    assertEquals(0, course.getEnrolledStudents().size());
  }

  @Test
  void shouldBeCreatedWithoutGrades() {
    assertEquals(0, course.getProjectGrades().size());
    assertEquals(0, course.getExamGrades().size());
  }

}
