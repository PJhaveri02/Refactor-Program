package com.softeng306.MgrInterfaces;


import com.softeng306.Domain.Course;
import com.softeng306.Domain.Student;
/**
 * Manages the Course Registration related operations.
 */
public interface CourseRegistrationMgr {

    /**
     * method signature for registering a course
     */
    void registerCourse();

    /**
     * method signature for printing ot all the students in a course
     */
    void printStudents();

    Course getCurrentCourse();

    Student getCurrentStudent();

}
