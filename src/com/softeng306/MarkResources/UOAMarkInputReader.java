package com.softeng306.MarkResources;

import java.util.Scanner;

public class UOAMarkInputReader implements MarkInputReader{

    private Scanner scanner = new Scanner(System.in);

    /**
     * Receives input from the user for a student ID, optionally informs user of help option.
     * @param printOut the flag for displaying help option information
     * @return the inputted student ID
     */
    public String readStudentID(boolean printOut) {
        String input = null;
        if (printOut) {
            System.out.println("Enter Student ID (-h to print all the student ID):");
        }
        if (scanner.hasNext()) {
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * Receives input from the user for a course ID, optionally informs user of help option.
     * @param printOut the flag for displaying help option information
     * @return the inputted course ID
     */
    public String readCourseID(boolean printOut) {
        String input = null;
        if (printOut) {
            System.out.println("Enter course ID (-h to print all the course ID):");
        }
        if (scanner.hasNext()) {
            input = scanner.nextLine();
        }
        return input;
    }

    /**
     * Prompts the user to enter a choice while updating assignment mark.
     * @return the inputted choice as Integer
     */
    public Integer readAssignmentMarkChoice() {
        Integer choice = null;
        System.out.println("Enter your choice");
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        }
        return choice;
    }

    /**
     * Prompts the user to enter a mark for the specified assignment.
     * @return the inputted mark as Double
     */
    public Double readAssignmentMark() {
        Double assessmentMark = null;
        System.out.println("Enter the mark for this assessment:");
        if (scanner.hasNextDouble()) {
            assessmentMark = scanner.nextDouble();
            scanner.nextLine();
        }
        return assessmentMark;
    }

    /**
     * Prompts the user to enter a mark for the specified exam.
     * @return the inputted mark as Double
     */
    public Double readExamMark() {
        Double examMark = null;
        System.out.println("Enter exam mark:");
        if (scanner.hasNextDouble()) {
            examMark = scanner.nextDouble();
            scanner.nextLine();
        }
        return examMark;
    }
}
