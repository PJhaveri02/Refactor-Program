package com.softeng306.UOA;

import com.softeng306.*;
import com.softeng306.Domain.Course;
import com.softeng306.Domain.CourseRegistration;
import com.softeng306.Domain.Group.Group;
import com.softeng306.Domain.Student;
import com.softeng306.Help.HelpInfoMgr;
import com.softeng306.Help.ValidationMgr;
import com.softeng306.MgrInterfaces.CourseRegistrationMgr;

import java.util.*;

import static com.softeng306.Domain.CourseRegistration.LabComparator;
import static com.softeng306.Domain.CourseRegistration.LecComparator;
import static com.softeng306.Domain.CourseRegistration.TutComparator;


public class UOACourseRegistrationMgr implements CourseRegistrationMgr {
    private static Scanner scanner = new Scanner(System.in);
    private List<CourseRegistration> courseRegistrations;
    private static UOACourseRegistrationMgr instance = null;
    private Student student;
    private Course course;

    private UOACourseRegistrationMgr(List<CourseRegistration> courseRegs){
        this.courseRegistrations = courseRegs;
    }

    /**
     * Returns the current instance of the class, creates a new one if one
     * does not currently exist. Used to make class singleton
     * @param courseRegs list of course registrations
     * @return the instance of the class
     */
    public static UOACourseRegistrationMgr getInstance(List<CourseRegistration> courseRegs) {
        if (instance==null){
            instance = new UOACourseRegistrationMgr(courseRegs);
        }
        return instance;
    }

    /**
     * returns current instance without list of course registrations being needed
     * Does not create a new instance if one does not exist, so only called when an instance
     * of courseRegistrationMgr does exist
     * @return instance of courseRegistrationMgr
     */
    public static UOACourseRegistrationMgr getInstance() {
        return instance;
    }

    /**
     * Registers a course for a student
     */
    public void registerCourse() {
        System.out.println("registerCourse is called");
        String selectedLectureGroupName = null;
        String selectedTutorialGroupName = null;
        String selectedLabGroupName = null;

        Student currentStudent = ValidationMgr.checkStudentExists();
        String studentID = currentStudent.getStudentID();

        ValidationMgr.checkCourseDepartmentExistsWithCourses();

        Course currentCourse = ValidationMgr.checkCourseExists();
        String courseID = currentCourse.getCourseID();


        if (ValidationMgr.checkCourseRegistrationExists(studentID, courseID) != null) {
            return;
        }

        if (currentCourse.getMainComponents().size() == 0) {
            System.out.println("Professor " + currentCourse.getProfInCharge().getProfName() + " is preparing the assessment. Please try to register other courses.");
            return;
        }

        if (currentCourse.getVacancies() == 0) {
            System.out.println("Sorry, the course has no vacancies any more.");
            return;
        }

        System.out.println("Student " + currentStudent.getStudentName() + " with ID: " + currentStudent.getStudentID() +
                " wants to register " + currentCourse.getCourseID() + " " + currentCourse.getCourseName());

        ArrayList<Group> lecGroups = new ArrayList<>(0);
        lecGroups.addAll(currentCourse.getLectureGroups());

        selectedLectureGroupName = HelpInfoMgr.printGroupWithVacancyInfo("lecture", lecGroups);

        ArrayList<Group> tutGroups = new ArrayList<>(0);
        tutGroups.addAll(currentCourse.getTutorialGroups());

        selectedTutorialGroupName = HelpInfoMgr.printGroupWithVacancyInfo("tutorial", tutGroups);

        ArrayList<Group> labGroups = new ArrayList<>(0);
        labGroups.addAll(currentCourse.getLabGroups());

        selectedLabGroupName = HelpInfoMgr.printGroupWithVacancyInfo("lab", labGroups);

        currentCourse.enrolledIn();
        CourseRegistration courseRegistration = new CourseRegistration(currentStudent, currentCourse, selectedLectureGroupName, selectedTutorialGroupName, selectedLabGroupName);
        FILEMgr.writeCourseRegistrationIntoFile(courseRegistration);

        this.courseRegistrations.add(courseRegistration);

        this.course = currentCourse;
        this.student = currentStudent;

        System.out.println("Course registration successful!");
        System.out.print("Student: " + currentStudent.getStudentName());
        System.out.print("\tLecture Group: " + selectedLectureGroupName);
        if (currentCourse.getTutorialGroups().size() != 0) {
            System.out.print("\tTutorial Group: " + selectedTutorialGroupName);
        }
        if (currentCourse.getLabGroups().size() != 0) {
            System.out.print("\tLab Group: " + selectedLabGroupName);
        }
        System.out.println();
    }

    public Student getCurrentStudent() {
        return student;
    }

    public Course getCurrentCourse() {
        return course;
    }

    /**
     * Prints the students in a course according to their lecture group, tutorial group or lab group.
     */
    public void printStudents() {
        System.out.println("printStudent is called");
        Course currentCourse = ValidationMgr.checkCourseExists();

        System.out.println("Print student by: ");
        System.out.println("(1) Lecture group");
        System.out.println("(2) Tutorial group");
        System.out.println("(3) Lab group");
        // READ courseRegistrationFILE
        // return ArrayList of Object(student,course,lecture,tut,lab)
        ArrayList<CourseRegistration> allStuArray = FILEMgr.loadCourseRegistration();


        ArrayList<CourseRegistration> stuArray = new ArrayList<CourseRegistration>(0);
        for (CourseRegistration courseRegistration : allStuArray) {
            if (courseRegistration.getCourse().getCourseID().equals(currentCourse.getCourseID())) {
                stuArray.add(courseRegistration);
            }
        }


        int opt;
        do {
            opt = scanner.nextInt();
            scanner.nextLine();

            System.out.println("------------------------------------------------------");

            if (stuArray.size() == 0) {
                System.out.println("No one has registered this course yet.");
            }

            this.printBy(opt, stuArray);
            System.out.println("------------------------------------------------------");
        } while (opt < 1 || opt > 3);


    }

    public List<CourseRegistration> getCourseRegistrations() {
        return courseRegistrations;
    }

    /**
     * helper method used to print out the students by lecture, tutorial or
     * laboratory session for a course
     * @param opt this represents what to sort by
     * @param stuArray the array of students
     */
    private void printBy(int opt, ArrayList<CourseRegistration> stuArray){
        boolean checkExist = false;
        Comparator<CourseRegistration> comparator;
        switch(opt){
            case 1:
                comparator = LecComparator;
                break;
            case 2:
                checkExist = stuArray.get(0).getCourse().getTutorialGroups().size() == 0;
                comparator = TutComparator;
                break;
            case 3:
                checkExist = stuArray.get(0).getCourse().getLabGroups().size() == 0;
                comparator = LabComparator;
                break;
            default:
                System.out.println("Invalid input. Please re-enter.");
                return;
        }
        String newTemp = "";
        Collections.sort(stuArray, comparator);
        if (stuArray.size() > 0 && checkExist) {
            System.out.println("This course does not contain any lab group.");
        } else if (stuArray.size() > 0) {
            for (int i = 0; i < stuArray.size(); i++) {
                if(opt == 1){
                    if (!newTemp.equals(stuArray.get(i).getLectureGroup())) {  // if new lecture group print out group name
                        newTemp = stuArray.get(i).getLectureGroup();
                        System.out.println("Lecture group : " + newTemp);
                    }
                }else if(opt == 2){
                    if (!newTemp.equals(stuArray.get(i).getTutorialGroup())) {
                        newTemp = stuArray.get(i).getTutorialGroup();
                        System.out.println("Tutorial group : " + newTemp);
                    }
                }else{
                    if (!newTemp.equals(stuArray.get(i).getLabGroup())) {
                        newTemp = stuArray.get(i).getLabGroup();
                        System.out.println("Lab group : " + newTemp);
                    }
                }
                System.out.print("Student Name: " + stuArray.get(i).getStudent().getStudentName());
                System.out.println(" Student ID: " + stuArray.get(i).getStudent().getStudentID());
            }
            System.out.println();
        }
    }

}
