package ex3;

import java.util.*;

import static java.util.Comparator.comparing;

public class Course {
  public static final int MIN_PASSING_MARK = 18;
  String title;
  Set<Student> students;
  Map<Student, Integer> projectGrades;
  Map<Student, Integer> examGrades;

  /** Creates a course with the input name and no enrolled students. */
  public Course(String title) {
    this.title = title;
    this.students = new HashSet<>();
    this.projectGrades = new HashMap<>();
    this.examGrades = new HashMap<>();
  }

  /**
   * Creates a course using the data of another course, namely the name and the enrolled students.
   * Does NOT copy grades.
   */
  public Course(Course course) {
    this.title = course.title;
    this.students = new HashSet<>(course.students);
    this.projectGrades = new HashMap<>();
    this.examGrades = new HashMap<>();
  }

  /** Returns the name of the course. */
  public String getTitle() {
    return title;
  }

  /**
   * Enrolls a student in the course. Returns true if the student was not already enrolled in the
   * course.
   */
  public boolean enroll(Student student) {
    return students.add(student);
  }

  /**
   * Enrolls all students in the course. Returns true if at least one student in the input array was
   * not already enrolled in the course.
   */
  public boolean enrollAll(Student... students) {
    return this.students.addAll(Set.of(students));
  }

  /**
   * Unenrolls the student from the course. Returns true if the student was enroled in the course.
   */
  public boolean unenroll(Student student) {
    boolean hasRemoved = students.remove(student);
    projectGrades.remove(student);
    examGrades.remove(student);
    return hasRemoved;
  }

  /**
   * Unenrolls all students from the course. Returns true if at least one student in the input array
   * was enrolled in the course.
   */
  public boolean unenrollAll(Student... students) {
    boolean hasRemoved = false;

    for (Student student : students) {
      hasRemoved = unenroll(student) || hasRemoved;
    }

    return hasRemoved;
  }

  /** Checks if the student is enrolled in the course. */
  public boolean isEnrolled(Student student) {
    return students.contains(student);
  }

  /** Returns an immutable copy of the set of enrolled students. */
  public Set<Student> getEnrolledStudents() {
    return Set.copyOf(students);
  }

  /** Returns a mutable list of enrolled students sort by their names in ascending order (a-z). */
  public List<Student> getStudentListSortedByName() {
    List<Student> studentList = new ArrayList<>(students);
    studentList.sort(comparing(Student::getName));
    return studentList;
  }

  /**
   * Returns a mutable list of enrolled students sort by their student ids in ascending order (a-z).
   */
  public List<Student> getStudentListSortedByStudentId() {
    List<Student> studentList = new ArrayList<>(students);
    studentList.sort(comparing(Student::getStudentId));
    return studentList;
  }

  /**
   * Returns an immutable map containing students as keys and their project grades as values. Only
   * students who have been awarded a project grade should be included in the map.
   */
  public Map<Student, Integer> getProjectGrades() {
    return Map.copyOf(projectGrades);
  }

  /**
   * Returns the project grade awarded by the student. If no grade was awarded, returns 0.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course
   */
  public int getProjectGrade(Student john) {
    validateStudent(john);
    return projectGrades.getOrDefault(john, 0);
  }

  /**
   * Awards a project grade to the student.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course or the grade is
   *     not within the valid range (0, 10)
   */
  public void awardProjectGrade(Student student, int grade) {
    validateStudent(student);
    validateGrade(grade);
    projectGrades.put(student, grade);
  }

  private void validateGrade(int grade) {
    if (grade < 0 || grade > 10) {
      throw new IllegalArgumentException("Valid grades are between 0 and 10. Provided: " + grade);
    }
  }

  /**
   * Updates the student's project grade.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course or the grade is
   *     not within the valid range (0, 10)
   */
  public void updateProjectGrade(Student student, int grade) {
    awardProjectGrade(student, grade);
  }

  /**
   * Deletes the student's project grade.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course
   */
  public void deleteProjectGrade(Student student) {
    validateStudent(student);
    projectGrades.remove(student);
  }

  /**
   * Returns true if a student has a project grade, i.e., if {{@link #awardProjectGrade(Student,
   * int)}} was previously invoked for the student.
   */
  public boolean hasProjectGrade(Student student) {
    return projectGrades.containsKey(student);
  }

  /**
   * Returns an unmutable map containing students as keys and their exam grades as values. Only
   * students who have been awarded a project grade should be included in the map.
   */
  public Map<Student, Integer> getExamGrades() {
    return examGrades;
  }

  /** Returns the exam grade awarded by the student. If no grade was awarded, returns 0. */
  public int getExamGrade(Student john) {
    validateStudent(john);
    return examGrades.getOrDefault(john, 0);
  }

  /**
   * Awards an exam grade to the student.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course or the grade is *
   *     not within the valid range (0, 10)
   */
  public void awardExamGrade(Student student, int grade) {
    validateStudent(student);
    validateGrade(grade);
    examGrades.put(student, grade);
  }

  /**
   * Updates the student's exam grade.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course or the grade is
   *     not within the valid range (0, 10)
   */
  public void updateExamGrade(Student student, int grade) {
    awardExamGrade(student, grade);
  }

  /**
   * Deletes the student's exam grade.
   *
   * @throws IllegalArgumentException if the student is not enrolled in the course
   */
  public void deleteExamGrade(Student student) {
    validateStudent(student);
    examGrades.remove(student);
  }

  /**
   * Returns true if a student has an exam grade, i.e., if {{@link #awardExamGrade(Student,
   * int)}} was previously invoked for the student.
   */
  public boolean hasExamGrade(Student student) {
    return examGrades.containsKey(student);
  }

  /**
   * Returns the final grade of the student by averaging the exam and project grades and
   * transforming it to a scale from 0 to 30. If a student does not have a project or exam grade,
   * the missing grades are considered to be zero. Grades are rounded up to the nearest integer.
   */
  public int getFinalGradeOutOfThirty(Student student) {
    double projectGrade = getProjectGrade(student);
    double examGrade = getExamGrade(student);
    double average = (projectGrade + examGrade) / 2;
    return (int) Math.round(average * 3);
  }

  /**
   * Returns a map containing, as keys, students who passed the course, and as values, their
   * respective final grades. The passing mark is 18.
   */
  public Map<Student, Integer> getApproved() {
    Map<Student, Integer> approved = new HashMap<>();

    for (Student student : students) {
      int finalGrade = getFinalGradeOutOfThirty(student);
      if (finalGrade >= MIN_PASSING_MARK) {
        approved.put(student, finalGrade);
      }
    }

    return approved;
  }

  /**
   * Returns a map containing, as keys, students who failed the course, and as values, their
   * respective final grades. The passing mark is 18.
   */
  public Map<Student, Integer> getFailed() {
    Map<Student, Integer> failed = new HashMap<>();

    for (Student student : students) {
      int finalGrade = getFinalGradeOutOfThirty(student);
      if (finalGrade < MIN_PASSING_MARK) {
        failed.put(student, finalGrade);
      }
    }

    return failed;
  }

  private void validateStudent(Student student) {
    if (!isEnrolled(student)) {
      throw new IllegalArgumentException();
    }
  }

}
