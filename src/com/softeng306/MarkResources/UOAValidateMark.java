package com.softeng306.MarkResources;

import com.softeng306.Help.CourseHelpCentre;
import com.softeng306.Help.MarkHelpCentre;
import com.softeng306.Help.StudentHelpCentre;

public class UOAValidateMark extends MarkHelpCentre implements ValidateMark{

    public UOAValidateMark(StudentHelpCentre studentHelpCentre, CourseHelpCentre courseHelpCentre) {
        super(studentHelpCentre, courseHelpCentre);
    }

    public boolean checkStudent(String studentID) {
        if (studentID.equalsIgnoreCase("-h")) {
            super.printAllStudents();
            return false;
        } else if (!super.checkStudentExists(studentID)) {
            System.out.println("Invalid Student ID. Please re-enter.");
            return false;
        }
        return true;
    }

    public boolean checkCourse(String courseID) {
        if (courseID.equalsIgnoreCase("-h")) {
            super.printAllCourses();
            return false;
        } else if (!super.checkCourseExists(courseID)) {
            System.out.println("Invalid Course ID. Please re-enter.");
            return false;
        }
        return true;
    }

    public boolean checkAssignmentMarkChoice(int availableChoices, int choice) {
        if (choice > (availableChoices + 1) || choice < 0) {
            System.out.println("Please enter choice between " + 0 + "~" + (availableChoices + 1));
            return false;
        }
        return true;
    }

    public boolean checkMark(Double mark) {
        if (mark > 100 || mark < 0) {
            System.out.println("Please enter mark in range 0 ~ 100.");
            return false;
        }
        return true;
    }
}
