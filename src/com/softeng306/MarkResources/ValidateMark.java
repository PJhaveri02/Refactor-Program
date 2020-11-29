package com.softeng306.MarkResources;

public interface ValidateMark {

    boolean checkStudent(String studentID);
    boolean checkCourse(String courseID);
    boolean checkAssignmentMarkChoice(int availableChoices, int choice);
    boolean checkMark(Double mark);
}
