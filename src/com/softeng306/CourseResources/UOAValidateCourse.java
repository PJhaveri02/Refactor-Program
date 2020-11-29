package com.softeng306.CourseResources;


import com.softeng306.Domain.Course;
import com.softeng306.Help.CourseHelpCentre;
import com.softeng306.Help.GetEnum;
import com.softeng306.Help.PrintEnum;
import com.softeng306.Domain.Professor;
import java.util.List;

public class UOAValidateCourse extends CourseHelpCentre implements ValidateCourse {
    
    public UOAValidateCourse(GetEnum getEnum, PrintEnum printEnum, List<Course> courseList, List<Professor> professorList) {
        super(getEnum, printEnum, courseList, professorList);
    }

    /**
     * checks if a specified course name is valid
     * @param courseName
     * @return true if course name is valid
     */
    @Override
    public boolean checkValidCourseName(String courseName) {
        return true;
    }

    /**
     *  checks if a specified course vacancy is valid
     * @param vacancy
     * @return true if course vacancy is valid and false if it is not valid
     */
    @Override
    public boolean checkValidCourseVacancy(String vacancy) {
        Integer vacant = null;
        try {
            vacant = Integer.parseInt(vacancy);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + vacant + " is not an integer");
            System.out.println("Please re-enter.");
            return false;
        }

        if (vacant <= 0) {
            System.out.println("Please enter a valid vacancy (greater than 0)");
            return false;
        }
        return true;

    }

    /**
     * checks if a specified academic unit is valid
     * @param AU
     * @return true if the AU is valid and false if it is not valid
     */
    @Override
    public boolean checkValidCourseAU(String AU) {
        Integer unit = null;
        try {
            unit = Integer.parseInt(AU);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + AU + " is not an integer.");
            return false;
        }
        if (unit < 0 || unit > 10) {
            System.out.println("AU out of bound. Please re-enter.");
            return false;
        }
        return true;

    }

    /**
     * checks if a specified department is valid
     * @param department
     * @return true if the department is valid and false if it is not valid
     */
    @Override
    public boolean checkValidCourseDepartment(String department) {
        if (department.equals("-h")) {
            super.printAllDepartment();
            return false;
        } else if (super.getAllDepartment().contains(department)) {
            return true;
        }
        System.out.println("The department is invalid. Please re-enter.");
        System.out.println("Enter course's department (uppercase): ");
        System.out.println("Enter -h to print all the departments.");
        return false;
    }

    /**
     * checks if a specified courseType is valid
     * @param courseType
     * @return true if the courseType is valid, false if it is not valid
     */
    @Override
    public boolean checkValidCourseType(String courseType) {
        if (courseType.equals("-h")) {
            super.printAllCourseType();
            return false;
        } else if (super.getAllCourseType().contains(courseType)) {
            return true;
        }
        System.out.println("The course type is invalid. Please re-enter.");
        System.out.println("Enter course type (uppercase): ");
        System.out.println("Enter -h to print all the course types.");
        return false;
    }

    /**
     * checks if a specified professor ID and department correspond to a current professor
     * @param professorID
     * @param department
     * @return an instance of professor that corresponds to the ID or null
     */
    @Override
    public Professor checkValidProfessor(String professorID, String department) {
        List<String> profInDepartment = super.printProfInDepartment(department, false);
        Professor profInCharge = super.checkProfExists(professorID);
        if (professorID.equals("-h")) {
            super.printProfInDepartment(department, true);
            return null;
        } else if (super.checkProfExists(professorID) != null && !profInDepartment.contains(professorID)) {
            System.out.println("This prof is not in " + department + ".");
            System.out.println("Thus he/she cannot teach this course.");
            return null;
        } else if (!profInDepartment.contains(professorID)) {
            System.out.println("Invalid input. Please re-enter.");
            return null;
        }
        return profInCharge;
    }
}
