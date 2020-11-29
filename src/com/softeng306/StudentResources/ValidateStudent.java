package com.softeng306.StudentResources;

import com.softeng306.Domain.Student;

public interface ValidateStudent {

    boolean studentGeneratorIsValid(Integer choice);

    boolean studentYearIsValid(String year);

    boolean validDepartment(String studentSchool);

    boolean validGender(String gender);

    boolean checkValidStudentIDInput(String studentID);

    Student checkStudentExists(String studentID, boolean printOut);

    boolean checkValidPersonNameInput(String personName);

}
