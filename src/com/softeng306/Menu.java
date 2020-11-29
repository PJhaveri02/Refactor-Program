package com.softeng306;

import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    private University university;

    public Menu(University uni) {
        university = uni;
    }

    public void start() {
        printWelcome();
        int choice = 200;
        while (choice != 11) {
            printOptions();
            do {
                System.out.println("Enter your choice, let me help you:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Sorry. " + scanner.nextLine() + " is not an integer.");
                    System.out.println("Enter your choice, let me help you:");
                }
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 11) {
                    System.out.println("Please enter 0 ~ 11 for your choice:");
                    continue;
                }
                break;
            } while (true);

            switch (choice) {
                case 0:
                    break;
                case 1:
                    university.getStudentMgr().createStudent();
                    break;
                case 2:
                    university.getCourseMgr().createCourse();
                    break;
                case 3:
                    university.getCourseRegistrationMgr().registerCourse();
                    university.getMarkMgr().getMarks().add(university.getMarkMgr().initializeMark(university.getCourseRegistrationMgr().getCurrentStudent(), university.getCourseRegistrationMgr().getCurrentCourse()));
                    break;
                case 4:
                    university.getCourseMgr().checkAvailableSlots();
                    break;
                case 5:
                    university.getCourseRegistrationMgr().printStudents();
                    break;
                case 6:
                    university.getCourseMgr().enterCourseWorkComponentWeightage(null);
                    break;
                case 7:
                    university.getMarkMgr().setCourseWorkMark(false);
                    break;
                case 8:
                    university.getMarkMgr().setCourseWorkMark(true);
                    break;
                case 9:
                    university.getMarkMgr().printCourseStatistics();
                    break;
                case 10:
                    university.getMarkMgr().printStudentTranscript();
                    break;
                case 11:
                    exitApplication();
                    break;

            }
        }
    }


    /**
     * Displays the welcome message.
     */

    public static void printWelcome() {
        System.out.println();
        System.out.println("****************** Hello! Welcome to SOFTENG 306 Project 2! ******************");
        System.out.println("Please note this application is not developed in The University of Auckland. All rights reserved for the original developers.");
        System.out.println("Permission has been granted by the original developers to anonymize the code and use for education purposes.");
        System.out.println("******************************************************************************************************************************");
        System.out.println();
    }

    /**
     * Exits the application.
     */
    public void exitApplication() {

        System.out.println("Backing up data before exiting...");
        FILEMgr.backUpCourse(university.getCourseMgr().getCourses());
        FILEMgr.backUpMarks(university.getMarkMgr().getMarks());
        System.out.println("********* Bye! Thank you for using Main! *********");
        System.out.println();
        System.out.println("                 ######    #      #   #######                   ");
        System.out.println("                 #    ##    #    #    #                         ");
        System.out.println("                 #    ##     #  #     #                         ");
        System.out.println("                 ######       ##      #######                   ");
        System.out.println("                 #    ##      ##      #                         ");
        System.out.println("                 #    ##      ##      #                         ");
        System.out.println("                 ######       ##      #######                   ");
        System.out.println();

    }

    /**
     * Displays all the options of the system.
     */
    public static void printOptions() {
        System.out.println("************ I can help you with these functions: *************");
        System.out.println(" 0. Print Options");
        System.out.println(" 1. Add a student");
        System.out.println(" 2. Add a course");
        System.out.println(" 3. Register student for a course including tutorial/lab classes");
        System.out.println(" 4. Check available slots in a class (vacancy in a class)");
        System.out.println(" 5. Print student list by lecture, tutorial or laboratory session for a course");
        System.out.println(" 6. Enter course assessment components weightage");
        System.out.println(" 7. Enter coursework mark – inclusive of its components");
        System.out.println(" 8. Enter exam mark");
        System.out.println(" 9. Print course statistics");
        System.out.println("10. Print student transcript");
        System.out.println("11. Quit Main System");
        System.out.println();
    }
}
