package com.softeng306.MarkResources;

import com.softeng306.Domain.Components.CourseworkComponent;
import com.softeng306.Domain.Components.MainComponent;
import com.softeng306.Domain.Mark;
import com.softeng306.Domain.Components.SubComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UOAMarkFlow extends MarkFlow {


    public UOAMarkFlow(MarkInputReader markInputReader, ValidateMark validateMark) {
        super(markInputReader, validateMark);
    }

    /**
     * Implementation for MarkFlow#getStudentID()
     */
    @Override
    public String getStudentID() {
        String studentID = null;
        boolean validStudent = false;
        boolean printMessage = true;

        while (!validStudent) {
            studentID = markInputReader.readStudentID(printMessage);
            validStudent = validateMark.checkStudent(studentID);
            printMessage = false;
        }

        return studentID;
    }

    /**
     * Implementation for MarkFlow#getCourseID()
     */
    @Override
    public String getCourseID() {
        String courseID = null;
        boolean validCourse = false;
        boolean printMessage = true;

        while (!validCourse) {
            courseID = markInputReader.readCourseID(printMessage);
            validCourse = validateMark.checkCourse(courseID);
            printMessage = false;
        }
        return courseID;
    }

    /**
     * Implementation for MarkFlow#getMark()
     */
    @Override
    public Mark getMark(List<Mark> markList, String studentID, String courseID) {
        for (Mark mark : markList) {
            if (mark.getCourse().getCourseID().equals(courseID) && mark.getStudent().getStudentID().equals(studentID)) {
                return mark;
            }
        }

        return null;
    }

    /**
     * Implementation for MarkFlow#updateExamMark()
     */
    @Override
    void updateExamMark(Mark mark) {
        Double examMark = null;
        boolean validMark = false;

        while(!validMark) {
            examMark = markInputReader.readExamMark();
            validMark = validateMark.checkMark(examMark);
        }

        mark.setMainCourseWorkMarks("Exam", examMark);
    }

    /**
     * Implementation for MarkFlow#updateAssignmentMark()
     */
    @Override
    void updateAssignmentMark(Mark mark) {
        System.out.println("Here are the choices you can have: ");
        ArrayList<String> availableChoices = new ArrayList<String>(0);
        ArrayList<Double> weights = new ArrayList<Double>(0);
        ArrayList<Boolean> isMainAss = new ArrayList<Boolean>(0);

        for (HashMap.Entry<CourseworkComponent, Double> assessmentResult : mark.getCourseWorkMarks().entrySet()){
            CourseworkComponent key = assessmentResult.getKey();
            if (key instanceof MainComponent) {
                if ((!key.getComponentName().equals("Exam")) && ((MainComponent) key).getSubComponents().size() == 0) {
                    availableChoices.add(key.getComponentName());
                    weights.add((double)key.getComponentWeight());
                    isMainAss.add(true);
                } else {
                    for (SubComponent subComponent : ((MainComponent) key).getSubComponents()) {
                        availableChoices.add(key.getComponentName() + "-" + subComponent.getComponentName());
                        weights.add((double)key.getComponentWeight() * (double)subComponent.getComponentWeight() / 100d);
                        isMainAss.add(false);
                    }
                }
            }
        }

        for (int i = 0; i < availableChoices.size(); i++) {
            System.out.println((i + 1) + ". " + availableChoices.get(i) + " Weight in Total: " + weights.get(i) + "%");
        }
        System.out.println((availableChoices.size() + 1) + ". Quit");

        int choice = Integer.MIN_VALUE;
        boolean validChoice = false;

        while (!validChoice) {
            choice = markInputReader.readAssignmentMarkChoice();
            validChoice = validateMark.checkAssignmentMarkChoice(availableChoices.size(), choice);
        }


        if (choice == (availableChoices.size() + 1)) {
            return;
        }

        double assessmentMark = Double.MIN_VALUE;
        boolean validAssMark = false;

        while (!validAssMark) {
            assessmentMark = markInputReader.readAssignmentMark();
            validAssMark = validateMark.checkMark(assessmentMark);
        }

        if (isMainAss.get(choice - 1)) {
            // This is a stand alone main assessment
            mark.setMainCourseWorkMarks(availableChoices.get(choice - 1), assessmentMark);
        }
        else {
            mark.setSubCourseWorkMarks(availableChoices.get(choice - 1).split("-")[1], assessmentMark);
        }
    }
}
