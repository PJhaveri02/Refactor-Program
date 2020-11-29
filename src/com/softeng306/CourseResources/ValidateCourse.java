package com.softeng306.CourseResources;

import com.softeng306.Domain.Course;
import com.softeng306.Domain.Professor;

public interface ValidateCourse {

    boolean checkValidCourseIDInput(String courseID);

    Course checkCourseExists(String courseID, boolean printOut);

    boolean checkValidCourseName(String courseName);

    boolean checkValidCourseVacancy(String vacancy);

    boolean checkValidCourseAU(String AU);

    boolean checkValidCourseDepartment(String department);

    boolean checkValidCourseType(String courseType);

    Professor checkValidProfessor(String professorID, String department);
}
