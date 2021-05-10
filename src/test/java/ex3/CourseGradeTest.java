package ex3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CourseGradeTest {
  Course course;
  Student john, mary, paul, anna, peter;

  @BeforeEach
  void setUp() {
    course = new Course("Programming Project");
    john = new Student("John Doe", "2020001");
    mary = new Student("Mary Jane", "2020002");
    paul = new Student("Paul Smith", "2020003");
    anna = new Student("Anna Roberts", "2020004");
    peter = new Student("Peter Paul", "2020005");
    course.enrollAll(john, mary, paul, anna);
  }

  @Test
  void shouldAwardProjectGrade() {
    course.awardProjectGrade(john, 5);
    assertEquals(5, course.getProjectGrade(john));
  }

  @Test
  void shouldAwardZeroAsProjectGrade() {
    course.awardProjectGrade(john, 0);
    assertEquals(0, course.getProjectGrade(john));
  }

  @Test
  void shouldAwardTenAsProjectGrade() {
    course.awardProjectGrade(john, 10);
    assertEquals(10, course.getProjectGrade(john));
  }

  @Test
  void shouldUpdateProjectGrade() {
    course.awardProjectGrade(john, 5);
    course.updateProjectGrade(john, 9);
    assertEquals(9, course.getProjectGrade(john));
  }

  @Test
  void shouldUpdateProjectGradeToZero() {
    course.awardProjectGrade(john, 3);
    course.updateProjectGrade(john, 0);
    assertEquals(0, course.getProjectGrade(john));
  }

  @Test
  void shouldUpdateProjectGradeToTen() {
    course.awardProjectGrade(john, 7);
    course.updateProjectGrade(john, 10);
    assertEquals(10, course.getProjectGrade(john));
  }

  @Test
  void shouldDeleteProjectGrade() {
    course.awardProjectGrade(john, 8);
    course.deleteProjectGrade(john);
    assertFalse(course.hasProjectGrade(john));
  }

  @Test
  void shouldReturnZeroIfStudentHasNoProjectGrade() {
    assertEquals(0, course.getProjectGrade(john));
    course.awardProjectGrade(john, 8);
    course.deleteProjectGrade(john);
    assertEquals(0, course.getProjectGrade(john));
  }

  @Test
  void shouldFailToAwardNegativeProjectGrade() {
    assertThrows(IllegalArgumentException.class, () -> course.awardProjectGrade(peter, -1));
  }

  @Test
  void shouldFailToAwardProjectGradeHigherThanTen() {
    assertThrows(IllegalArgumentException.class, () -> course.awardProjectGrade(peter, 11));
  }

  @Test
  void shouldFailToAwardProjectGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.awardProjectGrade(peter, 5));
  }

  @Test
  void shouldFailToUpdateProjectGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.updateProjectGrade(peter, 9));
  }

  @Test
  void shouldFailToDeleteProjectGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.deleteProjectGrade(peter));
  }

  @Test
  void shouldFailToGetProjectGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.getProjectGrade(peter));
  }

  @Test
  void shouldAwardExamGrade() {
    course.awardExamGrade(john, 5);
    assertEquals(5, course.getExamGrade(john));
  }

  @Test
  void shouldAwardZeroAsExamGrade() {
    course.awardExamGrade(john, 0);
    assertEquals(0, course.getExamGrade(john));
  }

  @Test
  void shouldAwardTenAsExamGrade() {
    course.awardExamGrade(john, 10);
    assertEquals(10, course.getExamGrade(john));
  }

  @Test
  void shouldUpdateExamGrade() {
    course.awardExamGrade(john, 5);
    course.updateExamGrade(john, 9);
    assertEquals(9, course.getExamGrade(john));
  }

  @Test
  void shouldUpdateExamGradeToZero() {
    course.awardExamGrade(john, 3);
    course.updateExamGrade(john, 0);
    assertEquals(0, course.getExamGrade(john));
  }

  @Test
  void shouldUpdateExamGradeToTen() {
    course.awardExamGrade(john, 7);
    course.updateExamGrade(john, 10);
    assertEquals(10, course.getExamGrade(john));
  }

  @Test
  void shouldDeleteExamGrade() {
    course.awardExamGrade(john, 8);
    course.deleteExamGrade(john);
    assertFalse(course.hasExamGrade(john));
  }

  @Test
  void shouldReturnZeroIfStudentHasNoExamGrade() {
    assertEquals(0, course.getExamGrade(john));
    course.awardExamGrade(john, 8);
    course.deleteExamGrade(john);
    assertEquals(0, course.getExamGrade(john));
  }

  @Test
  void shouldFailToAwardNegativeExamGrade() {
    assertThrows(IllegalArgumentException.class, () -> course.awardExamGrade(peter, -1));
  }

  @Test
  void shouldFailToAwardExamGradeHigherThanTen() {
    assertThrows(IllegalArgumentException.class, () -> course.awardExamGrade(peter, 11));
  }

  @Test
  void shouldFailToAwardExamGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.awardExamGrade(peter, 5));
  }

  @Test
  void shouldFailToUpdateExamGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.updateExamGrade(peter, 9));
  }

  @Test
  void shouldFailToDeleteExamGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.deleteExamGrade(peter));
  }

  @Test
  void shouldFailToGetExamGradeIfStudentIsNotEnrolled() {
    assertThrows(IllegalArgumentException.class, () -> course.getExamGrade(peter));
  }

  @Test
  void shouldCalculateFinalGrade() {
    course.awardProjectGrade(john, 9);
    course.awardExamGrade(john, 7);
    assertEquals(24, course.getFinalGradeOutOfThirty(john));
  }

  @Test
  void shouldCalculateFinalGradeWithMissingProjectGrade() {
    course.awardExamGrade(john, 7);
    assertEquals(11, course.getFinalGradeOutOfThirty(john));
  }

  @Test
  void shouldCalculateFinalGradeWithMissingExamGrade() {
    course.awardProjectGrade(john, 10);
    assertEquals(15, course.getFinalGradeOutOfThirty(john));
  }

  @Test
  void shouldReturnOnlyStudentsWhoPassedTheCourse() {
    course.awardProjectGrade(john, 6);
    course.awardExamGrade(john, 6);
    course.awardProjectGrade(mary, 3);
    course.awardExamGrade(mary, 1);
    course.awardProjectGrade(paul, 10);
    course.awardExamGrade(paul, 1);
    course.awardProjectGrade(anna, 10);
    course.awardExamGrade(anna, 10);

    assertEquals(Set.of(john, anna), course.getApproved().keySet());
  }

  @Test
  void shouldReturnFinalPassingGrade() {
    course.awardProjectGrade(john, 6);
    course.awardExamGrade(john, 6);

    assertEquals(18, course.getApproved().get(john));
  }

  @Test
  void shouldReturnOnlyStudentsWhoFailedTheCourse() {
    course.awardProjectGrade(john, 6);
    course.awardExamGrade(john, 6);
    course.awardProjectGrade(mary, 3);
    course.awardExamGrade(mary, 1);
    course.awardProjectGrade(paul, 10);
    course.awardExamGrade(paul, 1);
    course.awardProjectGrade(anna, 10);
    course.awardExamGrade(anna, 10);

    assertEquals(Set.of(mary, paul), course.getFailed().keySet());
  }


  @Test
  void shouldReturnFinalFailingGrade() {
    course.awardProjectGrade(mary, 1);
    course.awardExamGrade(mary, 1);

    assertEquals(3, course.getFailed().get(mary));
  }
}
