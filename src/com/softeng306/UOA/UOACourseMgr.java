package com.softeng306.UOA;


import com.softeng306.*;
import com.softeng306.CourseResources.CourseFlow;
import com.softeng306.Domain.Components.MainComponent;
import com.softeng306.Domain.Components.SubComponent;
import com.softeng306.Domain.Course;
import com.softeng306.Domain.Group.LabGroup;
import com.softeng306.Domain.Group.LectureGroup;
import com.softeng306.Domain.Group.TutorialGroup;
import com.softeng306.Help.ValidationMgr;
import com.softeng306.MgrInterfaces.CourseMgr;

import java.util.*;
import java.io.PrintStream;
import java.io.OutputStream;


public class UOACourseMgr implements CourseMgr {

    private CourseFlow courseFlow;
    private static Scanner scanner = new Scanner(System.in);
    private static UOACourseMgr instance = null;
    private List<Course> courseList;


    private UOACourseMgr(List<Course> courses, CourseFlow courseFlow){
        this.courseList = courses;
        this.courseFlow = courseFlow;
    }

    /**
     * Creates a new course and stores it in the file.
     */
    public void createCourse() {
        Course course = courseFlow.addCourseTemplate();
        addCourseworkComponent(course);
    }

    /**
     * Initialises an instance of a UOACourseMgr by calling the constructor
     * with the courselist Input.
     * @param courses: List of courses in the University
     * @return an instance of a UOACourseMgr.
     */
    public static UOACourseMgr getInstance(List<Course> courses, CourseFlow courseFlow){
        if(instance == null){
            instance = new UOACourseMgr(courses, courseFlow);
        }
        return instance;
    }

    /**
     * @return an instance of an existing UOACourseMgr
     */
    public static UOACourseMgr getInstance(){
        return instance;
    }


    public void addCourseworkComponent(Course course) {

        System.out.println("Create course components and set component weightage now?");
        System.out.println("1. Yes");
        System.out.println("2. Not yet");
        int addCourseComponentChoice;
        addCourseComponentChoice = scanner.nextInt();
        scanner.nextLine();

        while (addCourseComponentChoice > 2 || addCourseComponentChoice < 0) {
            System.out.println("Invalid choice, please choose again.");
            System.out.println("1. Yes");
            System.out.println("2. Not yet");
            addCourseComponentChoice = scanner.nextInt();
            scanner.nextLine();
        }
        if (addCourseComponentChoice == 2) {
            //add course into file
            FILEMgr.writeCourseIntoFile(course);
            courseList.add(course);
            System.out.println("Course " + course.getCourseID() + " is added, but assessment components are not initialized.");
            printCourses();
            return;
        }

        enterCourseWorkComponentWeightage(course);

        FILEMgr.writeCourseIntoFile(course);
        courseList.add(course);
        System.out.println("Course " + course.getCourseID() + " is added");
        printCourses();
    }

    /**
     * Checks whether a course (with all of its groups) have available slots and displays the result.
     */
    public void checkAvailableSlots() {
        //printout the result directly
        System.out.println("checkAvailableSlots is called");
        Course currentCourse;

        do {
            currentCourse = ValidationMgr.checkCourseExists();
            if (currentCourse != null) {
                System.out.println(currentCourse.getCourseID() + " " + currentCourse.getCourseName() + " (Available/Total): " + currentCourse.getVacancies() + "/" + currentCourse.getTotalSeats());
                System.out.println("--------------------------------------------");
                for (LectureGroup lectureGroup : currentCourse.getLectureGroups()) {
                    System.out.println("Lecture group " + lectureGroup.getGroupName() + " (Available/Total): " + lectureGroup.getAvailableVacancies() + "/" + lectureGroup.getTotalSeats());
                }
                if (currentCourse.getTutorialGroups() != null) {
                    System.out.println();
                    for (TutorialGroup tutorialGroup : currentCourse.getTutorialGroups()) {
                        System.out.println("Tutorial group " + tutorialGroup.getGroupName() + " (Available/Total):  " + tutorialGroup.getAvailableVacancies() + "/" + tutorialGroup.getTotalSeats());
                    }
                }
                if (currentCourse.getLabGroups() != null) {
                    System.out.println();
                    for (LabGroup labGroup : currentCourse.getLabGroups()) {
                        System.out.println("Lab group " + labGroup.getGroupName() + " (Available/Total): " + labGroup.getAvailableVacancies() + "/" + labGroup.getTotalSeats());
                    }
                }
                System.out.println();
                break;
            } else {
                System.out.println("This course does not exist. Please check again.");
            }
        } while (true);

    }

    /**
     * Sets the course work component weightage of a course.
     */
    public void enterCourseWorkComponentWeightage(Course currentCourse) {
        // Assume when course is created, no components are added yet
        // Assume once components are created and set, cannot be changed.
        int numberOfMain;
        int weight;
        int noOfSub;
        int sub_weight;

        System.out.println("enterCourseWorkComponentWeightage is called");
        if (currentCourse ==null) {
            currentCourse = ValidationMgr.checkCourseExists();
        }
        ArrayList<MainComponent> mainComponents = new ArrayList<MainComponent>(0);
        // Check if mainComponent is empty
        if (currentCourse.getMainComponents().size() == 0) {
            // empty course
            System.out.println("Currently course " + currentCourse.getCourseID() + " " + currentCourse.getCourseName() + " does not have any assessment component.");

            int hasFinalExamChoice;
            int examWeight = 0;
            while (true) {
                System.out.println("Does this course have a final exam? Enter your choice:");
                System.out.println("1. Yes! ");
                System.out.println("2. No, all CAs.");
                hasFinalExamChoice = scanner.nextInt();
                scanner.nextLine();
                if (hasFinalExamChoice == 1) {
                    System.out.println("Please enter weight of the exam: ");
                    examWeight = scanner.nextInt();
                    scanner.nextLine();
                    while (examWeight > 80 || examWeight <= 0) {
                        if (examWeight > 80 && examWeight <= 100) {
                            System.out.println("According to the course assessment policy, final exam cannot take up more than 80%...");
                        }
                        System.out.println("Weight entered is invalid, please enter again: ");
                        examWeight = scanner.nextInt();
                        scanner.nextLine();
                    }
                    MainComponent exam = new MainComponent("Exam", examWeight, new ArrayList<SubComponent>(0));
                    mainComponents.add(exam);
                    break;
                } else if (hasFinalExamChoice == 2) {
                    System.out.println("Okay, please enter some continuous assessments");
                    break;
                }
            }

            do {
                System.out.println("Enter number of main component(s) to add:");
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.println("Sorry. " + input + " is not an integer.");
                    System.out.println("Enter number of main component(s) to add:");
                }
                numberOfMain = scanner.nextInt();
                if (numberOfMain < 0) {
                    System.out.println("Please enter a valid positive integer:");
                    continue;
                }
                break;
            } while (true);
            scanner.nextLine();

            boolean componentExist;
            String mainComponentName;
            String subComponentName;
            do {
                int totalWeightage = 100 - examWeight;
                for (int i = 0; i < numberOfMain; i++) {
                    ArrayList<SubComponent> subComponents = new ArrayList<SubComponent>(0);
                    do {
                        componentExist = false;
                        System.out.println("Total weightage left to assign: " + totalWeightage);
                        System.out.println("Enter main component " + (i + 1) + " name: ");
                        mainComponentName = scanner.nextLine();

                        if (mainComponents.size() == 0) {
                            break;
                        }
                        if (mainComponentName.equals("Exam")) {
                            System.out.println("Exam is a reserved assessment.");
                            componentExist = true;
                            continue;
                        }
                        for (MainComponent mainComponent : mainComponents) {
                            if (mainComponent.getComponentName().equals(mainComponentName)) {
                                componentExist = true;
                                System.out.println("This sub component already exist. Please enter.");
                                break;
                            }
                        }
                    } while (componentExist);

                    do {
                        System.out.println("Enter main component " + (i + 1) + " weightage: ");
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Sorry. " + input + " is not an integer.");
                            System.out.println("Enter main component " + (i + 1) + " weightage:");
                        }
                        weight = scanner.nextInt();
                        if (weight < 0 || weight > totalWeightage) {
                            System.out.println("Please enter a weight between 0 ~ " + totalWeightage + ":");
                            continue;
                        }
                        break;
                    } while (true);
                    scanner.nextLine();
                    totalWeightage -= weight;
                    do {
                        System.out.println("Enter number of sub component under main component " + (i + 1) + ":");
                        while (!scanner.hasNextInt()) {
                            String input = scanner.next();
                            System.out.println("Sorry. " + input + " is not an integer.");
                            System.out.println("Enter number of sub component under main component " + (i + 1) + ":");
                        }
                        noOfSub = scanner.nextInt();
                        if (noOfSub < 0) {
                            System.out.println("Please enter a valid integer:");
                            continue;
                        }
                        break;
                    } while (true);
                    scanner.nextLine();
                    boolean flagSub = true;
                    while (flagSub) {

                        int sub_totWeight = 100;
                        for (int j = 0; j < noOfSub; j++) {


                            do {
                                componentExist = false;
                                System.out.println("Total weightage left to assign to sub component: " + sub_totWeight);
                                System.out.println("Enter sub component " + (j + 1) + " name: ");
                                subComponentName = scanner.nextLine();

                                if (subComponents.size() == 0) {
                                    break;
                                }
                                if (subComponentName.equals("Exam")) {
                                    System.out.println("Exam is a reserved assessment.");
                                    componentExist = true;
                                    continue;
                                }
                                for (SubComponent subComponent : subComponents) {
                                    if (subComponent.getComponentName().equals(subComponentName)) {
                                        componentExist = true;
                                        System.out.println("This sub component already exist. Please enter.");
                                        break;
                                    }
                                }
                            } while (componentExist);


                            do {
                                System.out.println("Enter sub component " + (j + 1) + " weightage: ");
                                while (!scanner.hasNextInt()) {
                                    String input = scanner.next();
                                    System.out.println("Sorry. " + input + " is not an integer.");
                                    System.out.println("Enter sub component " + (j + 1) + " weightage (out of the main component): ");
                                }
                                sub_weight = scanner.nextInt();
                                if (sub_weight < 0 || sub_weight > sub_totWeight) {
                                    System.out.println("Please enter a weight between 0 ~ " + sub_totWeight + ":");
                                    continue;
                                }
                                break;
                            } while (true);
                            scanner.nextLine();

                            //Create Subcomponent

                            SubComponent sub = new SubComponent(subComponentName, sub_weight);
                            subComponents.add(sub);
                            sub_totWeight -= sub_weight;
                        }
                        if (sub_totWeight != 0 && noOfSub != 0) {
                            System.out.println("ERROR! sub component weightage does not tally to 100");
                            System.out.println("You have to reassign!");
                            subComponents.clear();
                            flagSub = true;
                        } else {
                            flagSub = false;
                        }
                        //exit if weight is fully allocated
                    }
                    //Create main component
                    MainComponent main = new MainComponent(mainComponentName, weight, subComponents);
                    mainComponents.add(main);
                }

                if (totalWeightage != 0) {
                    // weightage assign is not tallied
                    System.out.println("Weightage assigned does not tally to 100!");
                    System.out.println("You have to reassign!");
                    mainComponents.clear();
                } else {
                    break;
                }
            } while (true);

            //set maincomponent to course
            currentCourse.setMainComponents(mainComponents);

        } else {
            System.out.println("Course Assessment has been settled already!");
        }
        System.out.println(currentCourse.getCourseID() + " " + currentCourse.getCourseName() + " components: ");
        for (MainComponent each_comp : currentCourse.getMainComponents()) {
            System.out.println("    " + each_comp.getComponentName() + " : " + each_comp.getComponentWeight() + "%");
            for (SubComponent each_sub : each_comp.getSubComponents()) {
                System.out.println("        " + each_sub.getComponentName() + " : " + each_sub.getComponentWeight() + "%");
            }
        }
        // Update course into course.csv
    }

    /**
     * Prints the list of courses
     */
    public void printCourses() {
        System.out.println("Course List: ");
        System.out.println("| Course ID | Course Name | Professor in Charge |");
        for (Course course : courseList) {
            System.out.println("| " + course.getCourseID() + " | " + course.getCourseName() + " | " + course.getProfInCharge().getProfName() + " |");
        }
        System.out.println();
    }

    public List<Course> getCourses(){
        return courseList;
    }

}