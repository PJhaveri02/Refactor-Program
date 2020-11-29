package com.softeng306.Help;

import com.softeng306.Domain.Student;
import java.util.List;
import java.util.regex.Pattern;

public abstract class StudentHelpCentre {

    protected GetEnum getEnum;
    protected PrintEnum printEnum;
    protected List<Student> students;

    public StudentHelpCentre(GetEnum getEnum, PrintEnum printEnum, List<Student> students) {
        this.getEnum = getEnum;
        this.printEnum = printEnum;
        this.students = students;
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

    /**
     * checks if a student currently exists with the specified studentID.
     * @param studentID
     * @return instance of student with the corresponding studentID, or null
     */
    public Student checkStudentExists(String studentID, boolean printOut) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                if (printOut) {
                    System.out.println("Sorry. The student ID is used. This student already exists.");
                }
                return student;
            }
        }
        return null;
    }

    /**
     * checks if an inputted student ID has valid formatting
     * @param studentID
     * @return true if the ID is valid and false if it in not valid.
     */
    public boolean checkValidStudentIDInput(String studentID){
        String REGEX = "^U[0-9]{7}[A-Z]$";
        boolean valid = Pattern.compile(REGEX).matcher(studentID).matches();
        if(!valid){
            System.out.println("Wrong format of student ID.");
        }
        return valid;
    }

    /**
     * checks if an inputted student name has valid formatting
     * @param personName
     * @return true if the name is valid and false if it is not valid
     */
    public boolean checkValidPersonNameInput(String personName) {
        String REGEX = "^[ a-zA-Z]+$";
        boolean valid = Pattern.compile(REGEX).matcher(personName).matches();
        if (!valid) {
            System.out.println("Wrong format of name.");
        }
        return valid;
    }

    public void printAllStudents() {
        students.stream().map(s -> s.getStudentID()).forEach(System.out::println);
    }
}
