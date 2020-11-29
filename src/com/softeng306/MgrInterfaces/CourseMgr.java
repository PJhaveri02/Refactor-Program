package com.softeng306.MgrInterfaces;


import com.softeng306.Domain.Course;

import java.util.List;

/**
 * Manages all the course related operations.

 */

public interface CourseMgr {


    void addCourseworkComponent(Course course);

    /**
     * Checks whether a course (with all of its groups) have available slots and displays the result.
     */
    void checkAvailableSlots();

    /**
     * Sets the course work component weightage of a course.
     */
    void enterCourseWorkComponentWeightage(Course currentCourse);

    /**
     * Prints the list of courses
     */
    void printCourses();

    /**
     * Prints the list of courses
     */
    List<Course> getCourses();

    void createCourse();
}