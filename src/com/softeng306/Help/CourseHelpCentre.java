package com.softeng306.Help;

import com.softeng306.Domain.Course;
import com.softeng306.Domain.Professor;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class CourseHelpCentre {

    private GetEnum getEnum;
    private PrintEnum printEnum;
    private List<Course> courseList;
    private List<Professor> professorsList;

    public CourseHelpCentre(GetEnum getEnum, PrintEnum printEnum, List<Course> courseList, List<Professor> professorList) {
        this.getEnum = getEnum;
        this.printEnum = printEnum;
        this.courseList = courseList;
        this.professorsList = professorList;
    }

    /**
     * Checks whether the inputted course ID is in the correct format.
     * @param courseID The inputted course ID.
     * @return boolean indicates whether the inputted course ID is valid.
     */
    public boolean checkValidCourseIDInput(String courseID){
        String REGEX = "^[A-Z]{2}[0-9]{3,4}$";
        boolean valid = Pattern.compile(REGEX).matcher(courseID).matches();
        if(!valid){
            System.out.println("Wrong format of course ID.");
        }
        return valid;
    }

    /**
     * Checks whether this course ID is used by other courses.
     * @param courseID The inputted course ID.
     * @return the existing course or else null.
     */
    public Course checkCourseExists(String courseID, boolean printOut){
        for (Course course : courseList){
            if (course.getCourseID().equals(courseID)){
                if (printOut) {
                    System.out.println("Sorry. The course ID is used. This course already exists.");
                }
                return course;
            }
        }
        return null;
    }

    /**
     * Displays all the professors in the inputted department.
     *
     * @param department The inputted department.
     * @param printOut Represents whether print out the professor information or not
     * @return A list of all the names of professors in the inputted department or else null.
     */
    public List<String> printProfInDepartment(String department, boolean printOut) {
        if (checkDepartmentValidation(department)) {
            List<String> validProfString = professorsList.stream().filter(p -> String.valueOf(department).equals(p.getProfDepartment())).map(p -> p.getProfID()).collect(Collectors.toList());
            if (printOut) {
                validProfString.forEach(System.out::println);
            }
            return validProfString;
        }
        System.out.println("None.");
        return null;

    }

    /**
     * Checks whether the inputted department is valid.
     * @param department The inputted department.
     * @return boolean indicates whether the inputted department is valid.
     */
    public boolean checkDepartmentValidation(String department){
        if(getEnum.getAllDepartment().contains(department)){
            return true;
        }
        System.out.println("The department is invalid. Please re-enter.");
        return false;
    }



    public void printAllDepartment() {
        printEnum.printAllDepartment();
    }

    public void printAllGender() {
        printEnum.printAllGender();
    }

    public void printAllCourseType() {
        printEnum.printAllCourseType();
    }

    public List<String> getAllDepartment() {
        return getEnum.getAllDepartment();
    }

    public List<String> getAllGender() {
        return getEnum.getAllGender();
    }

    public List<String> getAllCourseType() {
        return getEnum.getAllCourseType();
    }

    public List<Professor> getAllProfessors() {
        return this.professorsList;
    }

    /**
     * Checks whether this professor ID is used by other professors.
     * @param profID The inputted professor ID.
     * @return the existing professor or else null.
     */
    public Professor checkProfExists(String profID){
        for (Professor professor : professorsList){
            if (professor.getProfID().equals(profID)){
                return professor;
            }
        }
        return null;
    }

    public void printAllCourses() {
        courseList.stream().map(c -> c.getCourseID()).forEach(System.out::println);
    }

}
