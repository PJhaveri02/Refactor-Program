package com.softeng306.MgrInterfaces;


import com.softeng306.Domain.Course;
import com.softeng306.Domain.Mark;
import com.softeng306.Domain.Student;
import java.util.*;

/**
 * Manages all the mark related operations.
 */

public interface MarkMgr {

    /**
     * used to initialize the mark of a course for a student
     * @param student takes in a students who's mark needs to be initialised
     * @param course the course of which the mark needs to be initialised for
     * @return the newly created mark
     */
    Mark initializeMark(Student student, Course course);

    /**
     * used to set the mark of a component
     * @param isExam used to check of the component is an exam
     */
    void setCourseWorkMark(boolean isExam);

    /**
     * Used to calulate the overall mark for a component
     * @param thisCourseMark list of marks
     * @param thisComponentName the name of the component
     * @return the overall mark
     */
    double computeMark(List<Mark> thisCourseMark, String thisComponentName);

    /**
     * Prints the statics of the course which include the enrollment rate,
     * average result for every assessment component and the
     * average overall performance of this course.
     */
    void printCourseStatistics();

    /**
     * Prints transcript
     */
    void printStudentTranscript();

    /**
     * Calculates the gpa from the given grade
     * @param result grade in percentage
     * @return gpa corresponding to the percentage
     */
    double gpaCalculator(double result);

    List<Mark> getMarks();

}
