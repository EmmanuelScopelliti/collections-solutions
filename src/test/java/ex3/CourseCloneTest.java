package ex3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CourseCloneTest {
  static Course course, clone;
  static Student john, mary;

  @BeforeAll
  static void setUp() {
    course = new Course("Programming Project");
    john = new Student("John Doe", "2020001");
    mary = new Student("Mary Jane", "2020002");
    course.enrollAll(john, mary);
    course.awardProjectGrade(john, 10);
    course.awardExamGrade(john, 10);
    clone = new Course(course);
  }

  @Test
  void shouldCloneName() {
    assertEquals(course.getTitle(), clone.getTitle());
  }

  @Test
  void shouldCloneEnrolledStudents() {
    assertEquals(course.getEnrolledStudents(), clone.getEnrolledStudents());
  }

  @Test
  void shouldNotCloneProjectGrades() {
    assertTrue(clone.getProjectGrades().isEmpty());
  }
  @Test
  void shouldNotCloneExamGrades() {
    assertTrue(clone.getExamGrades().isEmpty());
  }
}
