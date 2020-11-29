package com.softeng306.CourseResources;

public interface CourseInputReader {

    String enterCourseID();

    String enterCourseName();

    String enterCourseVacancy();

    String enterCourseAU();

    String enterCourseDepartment(boolean printOut);

    String enterCourseType(boolean printOut);

    String enterProfessor(String courseDepartment, boolean printOut);

}
