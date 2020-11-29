package com.softeng306.StudentResources;

import com.softeng306.UOA.UOAStudentMgr;


/**
 * Implementation for abstract class StudentFlow
 */
public class UOAStudentFlow extends StudentFlow {

    public UOAStudentFlow(StudentInputReader inputReader, ValidateStudent validateStudent) {
        super(inputReader, validateStudent);
    }

    /**
     * Implementation for StudentFlow#greetStudent()
     */
    @Override
    public void greetUser() {
        inputReader.greetStudent();
    }


    /**
     * Implementation for StudentFlow#doStudentIDSelection()
     */
    @Override
    public Integer doStudentIDSelection() {
        Integer choiceID = Integer.MIN_VALUE;
        boolean validInput = false;
        while (!validInput) {
            choiceID = inputReader.chooseStudentIDInput();
            validInput = validateStudent.studentGeneratorIsValid(choiceID);
        }
        return choiceID;
    }

    /**
     * Implementation for StudentFlow#doStudentID()
     */
    @Override
    public String doStudentID(Integer choice) {
        String studentID = null;
        boolean validStudentID = false;

        while (!validStudentID) {
            if (choice == 1) {
                studentID = inputReader.enterStudentID();

                if (validateStudent.checkValidStudentIDInput(studentID)) {
                    if (validateStudent.checkStudentExists(studentID, true) == null) {
                        validStudentID = true;
                    }
                }
            } else if (choice == 2) {
                studentID = UOAStudentMgr.getInstance().generateStudentID();
                validStudentID = true;
            }
        }

        return studentID;
    }

    /**
     * Implementation for StudentFlow#doStudentName()
     */
    @Override
    public String doStudentName() {
        String studentName = null;
        boolean validStudentName = false;

        while (!validStudentName) {
            studentName = inputReader.enterStudentName();
            validStudentName = validateStudent.checkValidPersonNameInput(studentName);
        }
        return studentName;
    }

    /**
     * Implementation for StudentFlow#doDepartment()
     */
    @Override
    public String doDepartment() {
        String department = null;
        boolean validDepartment = false;
        boolean printMessage = true;

        while (!validDepartment) {
            department = inputReader.enterDepartment(printMessage);
            printMessage = false;
            validDepartment = validateStudent.validDepartment(department);
        }

        return department;
    }

    /**
     * Implementation for StudentFlow#doGender()
     */
    @Override
    public String doGender() {
        String studentGender = null;
        boolean validSG = false;
        boolean printMessage = true;

        while (!validSG) {
            studentGender = inputReader.enterGender(printMessage);
            validSG = validateStudent.validGender(studentGender);
            printMessage = false;
        }
        return studentGender;
    }

    /**
     * Implementation for StudentFlow#doStudentYear()
     */
    @Override
    public String doStudentYear() {
        String studentYear = null;
        boolean validSY = false;
        while (!validSY) {
            studentYear = inputReader.enterStudentYear();
            validSY = validateStudent.studentYearIsValid(studentYear);
        }
        return studentYear;
    }

}
