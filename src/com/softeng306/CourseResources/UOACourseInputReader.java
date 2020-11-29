package com.softeng306.CourseResources;

import java.util.Scanner;

public class UOACourseInputReader implements CourseInputReader {

    private static Scanner scanner = new Scanner(System.in);

    @Override
    public String enterCourseID() {
        String courseID = null;
        System.out.println("Give this course an ID: ");
        if (scanner.hasNext()) {
            courseID = scanner.nextLine();
        }
        return courseID;
    }

    @Override
    public String enterCourseName() {
        String courseName = null;
        System.out.println("Enter course Name: ");
        if (scanner.hasNext()) {
            courseName = scanner.nextLine();
        }
        return courseName;
    }

    @Override
    public String enterCourseVacancy() {
        String totalSeats = null;
        System.out.println("Enter the total vacancy of this course: ");
        if (scanner.hasNext()) {
            totalSeats = scanner.nextLine();
        }

        return totalSeats;
    }

    @Override
    public String enterCourseAU() {
        String AU = null;
        System.out.println("Enter number of academic unit(s): ");
        if (scanner.hasNext()) {
            AU = scanner.nextLine();
        }
        return AU;
    }

    @Override
    public String enterCourseDepartment(boolean printOut) {
        String courseDepartment = null;
        if (printOut) {
            System.out.println("Enter course's department (uppercase): ");
            System.out.println("Enter -h to print all the departments.");
        }
        if (scanner.hasNext()) {
            courseDepartment = scanner.nextLine();
        }
        return courseDepartment;
    }

    @Override
    public String enterCourseType(boolean printOut) {
        String courseType = null;
        if (printOut) {
            System.out.println("Enter course type (uppercase): ");
            System.out.println("Enter -h to print all the course types.");
        }
        if (scanner.hasNext()) {
            courseType = scanner.nextLine();
        }
        return courseType;
    }

    @Override
    public String enterProfessor(String courseDepartment, boolean printOut) {
        String professor = null;
        if (printOut) {
            System.out.println("Enter the ID for the professor in charge please:");
            System.out.println("Enter -h to print all the professors in " + courseDepartment + ".");
        }
        if (scanner.hasNext()) {
            professor = scanner.nextLine();
        }
        return professor;
    }

}
