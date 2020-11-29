package com.softeng306.MgrInterfaces;

import com.softeng306.Domain.Student;
import java.util.List;

/**
 * Manages the student related operations.
 */
public interface StudentMgr {

    List<Student> getStudents();
    String generateStudentID();

    /**
     * creates a new student and adds it to the list of students
     * Updates the csv file and prints out all students when a new student is
     * successfully added
     */
    void createStudent();

    /**
     * Prints out all enrolled students in the university
     */
    void displayAllStudents();

}
