package com.softeng306.MarkResources;

public interface MarkInputReader {

    String readStudentID(boolean printOut);
    String readCourseID(boolean printOut);
    Integer readAssignmentMarkChoice();
    Double readAssignmentMark();
    Double readExamMark();

}
