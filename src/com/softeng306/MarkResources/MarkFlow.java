package com.softeng306.MarkResources;

import com.softeng306.Domain.Mark;

import java.util.List;

public abstract class MarkFlow {

    protected MarkInputReader markInputReader;
    protected ValidateMark validateMark;

    public MarkFlow(MarkInputReader markInputReader, ValidateMark validateMark) {
        this.markInputReader = markInputReader;
        this.validateMark = validateMark;
    }

    public void updateMarkTemplate(List<Mark> markList, boolean isExam){
        String studentID = getStudentID(); //read and validate
        String courseID = getCourseID(); //read and validate

        //get the mark for the course the student is enrolled in
        //if end of loop is reached and no mark is found, print "this student isn't enrolled..."
        Mark mark = getMark(markList, studentID, courseID);
        if (mark == null){
            System.out.println("This student haven't registered " + courseID);
            return;
        }

        if (isExam){
            updateExamMark(mark);
        } else{
            updateAssignmentMark(mark);
        }
    }

    abstract String getStudentID();
    abstract String getCourseID();
    abstract Mark getMark(List<Mark> markList, String studentID, String courseID);
    abstract void updateExamMark(Mark mark);
    abstract void updateAssignmentMark(Mark mark);

}
