package com.softeng306.UOA;


import com.softeng306.FILEMgr;
import com.softeng306.Domain.Student;
import com.softeng306.StudentResources.StudentFlow;
import com.softeng306.MgrInterfaces.StudentMgr;
import java.util.List;

/**
 * Manages the student related operations.
 * Contains addStudent.
 * StudentMgr is a singleton class (class that can only have only one object instance at a time).
 */
public class UOAStudentMgr implements StudentMgr {

    private StudentFlow studentFlow;
    private static UOAStudentMgr instance = null;
    private List<Student> students;

    /**
     * Uses idNumber to generate student ID.
     */
    private static int idNumber = 1800000;

    // Private constructor
    private UOAStudentMgr(List<Student> students, StudentFlow studentFlow) {
        this.students = students;
        this.studentFlow = studentFlow;
    }

    /**
     * This method ensures the singleton nature of the class.
     * It creates a new object if none exists, and returns the singleton instance.
     * @param students
     * @param studentFlow
     * @return The singleton UOAStudentMgr instance.
     */
    public static UOAStudentMgr getInstance(List<Student> students, StudentFlow studentFlow) {
        if (instance ==  null) {
            instance = new UOAStudentMgr(students, studentFlow);
        }
        return instance;
    }

    /**
     * Gets the UOAStudentMgr instance
     * @return UOAStudentMgr instance
     */
    public static UOAStudentMgr getInstance() {
        return instance;
    }

    public void createStudent() {
        Student student = studentFlow.addStudentTemplate();
        if (student != null) {
            this.students.add(student);
            FILEMgr.writeStudentsIntoFile(student);
            System.out.println("Student named: " + student.getStudentName() + " is added, with ID: " + student.getStudentID());
            displayAllStudents();
        }
    }

    /**
     * Returns the list of students
     * @return A list of students in the University
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Sets the idNumber variable of this student class.
     * @param idNumber static variable idNumber of this class.
     */
    public static void setIdNumber(int idNumber) {
        UOAStudentMgr.idNumber = idNumber;
    }

    /**
     * Generates the ID of a new student.
     * @return the generated student ID.
     */
    public String generateStudentID() {
        String generateStudentID;
        boolean studentIDUsed;
        do{
            int rand = (int)(Math.random() * ((76 - 65) + 1)) + 65;
            String lastPlace = Character.toString ((char) rand);
            idNumber += 1;
            generateStudentID = "U" + idNumber + lastPlace;
            studentIDUsed = false;
            for(Student student: students){
                if(generateStudentID.equals(student.getStudentID())){
                    studentIDUsed = true;
                    break;
                }
            }
            if(!studentIDUsed){
                break;
            }
        }while(true);
        return generateStudentID;
    }

    /**
     * The method prints out all the students in the University
     */
    @Override
    public void displayAllStudents() {
        String GPA = "not available";
        System.out.println("Student List: ");
        System.out.println("| Student ID | Student Name | Student School | Gender | Year | GPA |");
        for (Student s : students) {
            if (Double.compare(s.getStudentGPA(), 0.0) != 0) {
                GPA = String.valueOf(s.getStudentGPA());
            }
            System.out.println(" " + s.getStudentID() + " | " + s.getStudentName() + " | " + s.getStudentSchool() + " | " + s.getStudentGender() + " | " + s.getStudentYear() + " | " + GPA);
        }
    }
}
