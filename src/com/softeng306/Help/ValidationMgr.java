package com.softeng306.Help;

import com.softeng306.Domain.Course;
import com.softeng306.Domain.CourseRegistration;
import com.softeng306.Domain.Student;
import com.softeng306.UOA.UOACourseMgr;
import com.softeng306.UOA.UOACourseRegistrationMgr;
import com.softeng306.UOA.UOAStudentMgr;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Manages all the validation check in this system.
 */

public class ValidationMgr {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Checks whether the inputted department is valid.
     * @param department The inputted department.
     * @return boolean indicates whether the inputted department is valid.
     */
    public static boolean checkDepartmentValidation(String department){
        if(HelpInfoMgr.getAllDepartment().contains(department)){
            return true;
        }
        System.out.println("The department is invalid. Please re-enter.");
        return false;
    }


    /**
     * Prompts the user to input an existing student.
     * @return the inputted student.
     */
    public static Student checkStudentExists() {
        while(true) {
            System.out.println("Enter Student ID (-h to print all the student ID):");
            String input = scanner.nextLine();
            while (input.equals("-h")) {
                HelpInfoMgr.printAllStudents();
                input = scanner.nextLine();
            }

            for (Student student : UOAStudentMgr.getInstance().getStudents()) {
                if (student.getStudentID().equals(input)) {
                    return student;
                }
            }
            System.out.println("Invalid Student ID. Please re-enter.");
        }
    }

    /**
     * Prompts the user to input an existing course.
     * @return the inputted course.
     */
    public static Course checkCourseExists() {
        while(true) {
            System.out.println("Enter course ID (-h to print all the course ID):");
            String input = scanner.nextLine();
            while (input.equals("-h")) {
                HelpInfoMgr.printAllCourses();
                input = scanner.nextLine();
            }

            for (Course course : UOACourseMgr.getInstance().getCourses()) {
                if (course.getCourseID().equals(input)) {
                    return course;
                }
            }
            System.out.println("Invalid Course ID. Please re-enter.");
        }
    }

    /**
     * Prompts the user to input an existing department. Checks whether the department has courses.
     */
    public static void checkCourseDepartmentExistsWithCourses() {
        String courseDepartment;
        while(true){
            System.out.println("Which department's courses are you interested? (-h to print all the departments)");
            courseDepartment = scanner.nextLine();
            while("-h".equals(courseDepartment)){
                HelpInfoMgr.printAllDepartment();
                courseDepartment = scanner.nextLine();
            }
            if(ValidationMgr.checkDepartmentValidation(courseDepartment)) {
                String finalCourseDepartment = courseDepartment;
                List<Course> validCourses = UOACourseMgr.getInstance().getCourses().stream().filter(c ->
                        finalCourseDepartment.equals(c.getCourseDepartment())).collect(Collectors.toList());
                if(validCourses.size() == 0) {
                    System.out.println("Invalid choice of department.");
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Checks whether this course registration record exists.
     * @param studentID The inputted student ID.
     * @param courseID The inputted course ID.
     * @return the existing course registration record or else null.
     */
    public static CourseRegistration checkCourseRegistrationExists(String studentID, String courseID){

        for (CourseRegistration courseRegistration : UOACourseRegistrationMgr.getInstance().getCourseRegistrations()){
            if ((courseRegistration.getStudent().getStudentID().equals(studentID))&&courseRegistration.getCourse().getCourseID().equals(courseID)){
                System.out.println("Sorry. This student already registers this course.");
                return courseRegistration;
            }
        }
        return null;
    }
}
