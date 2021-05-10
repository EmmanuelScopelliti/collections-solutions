## Exercise 3: Course management

The [`Course`](Course.java) class simulates a small fragment of a course management system in which students can enroll in a course, be awarded grades, pass or fail the course, etc. 

Your goal in this exercise is to implement this class using one or more data structures of the Java Collections Library, e.g. `List`, `Set`, `Queue`, `Deque`, `Map`, and their respective methods.

Implement the methods list below, whose expected behavior are described in [`Course`](Course.java) and in the test suites at [`test/java/ex3/`](../../../test/java/ex3):

- `public Course(String title)`
- `public Course(Course course)`
- `public String getTitle()`
- `public boolean enroll(Student student)`
- `public boolean enrollAll(Student... students)`
- `public boolean unenroll(Student student)`
- `public boolean unenrollAll(Student... students)`
- `public boolean isEnrolled(Student student)`
- `public Set<Student> getEnrolledStudents()`
- `public List<Student> getStudentListSortedByName()`
- `public List<Student> getStudentListSortedByStudentId()`
- `public Map<Student, Integer> getProjectGrades()`
- `public int getProjectGrade(Student john)`
- `public void awardProjectGrade(Student student, int grade)`
- `public void updateProjectGrade(Student student, int grade)`
- `public void deleteProjectGrade(Student student)`
- `public boolean hasProjectGrade(Student student)`
- `public Map<Student, Integer> getExamGrades()`
- `public int getExamGrade(Student john)`
- `public void awardExamGrade(Student student, int grade)`
- `public void updateExamGrade(Student student, int grade)`
- `public void deleteExamGrade(Student student)`
- `public boolean hasExamGrade(Student student)`
- `public int getFinalGradeOutOfThirty(Student student)`
- `public Map<Student, Integer> getApproved()`
- `public Map<Student, Integer> getFailed()`

You should NOT change any method signature, but you are free to add new methods you see fit.

Your code should pass all tests defined in [`CourseConstructorTest`](../../../test/java/ex2/CourseConstructorTest.java), [`CourseCloneTest`](../../../test/java/ex2/CourseCloneTest.java), [`CourseEnrollmentTest`](../../../test/java/ex2/CourseEnrollmentTest.java), and [`CourseGradingTest`](../../../test/java/ex2/CourseGradingTest.java).
 