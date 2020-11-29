package com.softeng306.StudentResources;

public interface StudentInputReader {

    void greetStudent();

    Integer chooseStudentIDInput();

    String enterStudentID();

    String enterStudentName();

    String enterDepartment(boolean printOut);

    String enterGender(boolean printOut);

    String enterStudentYear();

}
