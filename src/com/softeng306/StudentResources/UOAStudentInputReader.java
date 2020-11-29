package com.softeng306.StudentResources;

import java.util.Scanner;

public class UOAStudentInputReader implements StudentInputReader{

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prints out the initial message when adding a new student
     */
    public void greetStudent() {
        System.out.println("addStudent is called");
        System.out.println("Choose the way you want to add a student:");
        System.out.println("1. Manually input the student ID.");
        System.out.println("2. Let the system self-generate the student ID.");
    }

    /**
     * Method called when user is manually inputting the ID of the
     * new student.
     * @return returns the user's input
     */
    public Integer chooseStudentIDInput() {
        Integer choice = null;
        System.out.println("Please input your choice:");
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    /**
     * Called when user enters an invalid id format
     * prompts the user to retry and prints the instructions on the
     * expected format
     * @return user input
     */
    public String enterStudentID() {
        String studentID = null;
        System.out.println("The student ID should follow:");
        System.out.println("Length is exactly 9");
        System.out.println("Start with U (Undergraduate)");
        System.out.println("End with a uppercase letter between A and L");
        System.out.println("Seven digits in the middle");
        System.out.println();
        System.out.println("Give this student an ID: ");
        if (scanner.hasNext())
            studentID = scanner.nextLine();
        return studentID;

    }

    /**
     * Asks the user for the name of the new student
     * @return user input
     */
    public String enterStudentName() {
        String studentName = null;
        System.out.println("Enter student Name: ");
        if (scanner.hasNext()) {
            studentName = scanner.nextLine();
        }
        return studentName;
    }

    /**
     * asks the user for the department the student is under
     * @return user input
     */
    public String enterDepartment(boolean printOut) {
        String studentSchool = null;
        if (printOut) {
            System.out.println("Enter student's school (uppercase): ");
            System.out.println("Enter -h to print all the schools.");
        }
        studentSchool = scanner.nextLine();
        return studentSchool;
    }

    /**
     * askes the user for the gender of the new student
     * @return user input
     */
    public String enterGender(boolean printOut) {
        String studentGender;
        if (printOut) {
            System.out.println("Enter student gender (uppercase): ");
            System.out.println("Enter -h to print all the genders.");
        }
        studentGender = scanner.nextLine();
        return studentGender;
    }

    /**
     * asks the user for the students school year
     * @return user input
     */
    public String enterStudentYear() {
        String studentYear = null;
        System.out.println("Enter student's school year (1-4) : ");
        if (scanner.hasNext()) {
            studentYear = scanner.nextLine();
        }
        return studentYear;
    }
}
