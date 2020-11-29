package com.softeng306.Domain;

import java.util.Comparator;


public class CourseRegistration {
    private Student student;
    private Course course;
    private String lectureGroup;
    private String tutorialGroup;
    private String labGroup;

    public CourseRegistration(Student student, Course course, String lectureGroup, String tutorialGroup, String labGroup) {
        this.student = student;
        this.course = course;
        this.lectureGroup = lectureGroup;
        this.tutorialGroup = tutorialGroup;
        this.labGroup = labGroup;
    }

    public Student getStudent() {
        return student;
    }

    public String getStudentID() { return student.getStudentID();}

    public String getCourseID() { return course.getCourseID();}

    public Course getCourse() {
        return course;
    }

    public String getLectureGroup() {
        return lectureGroup;
    }

    public String getTutorialGroup() {
        return tutorialGroup;
    }

    public String getLabGroup() {
        return labGroup;
    }

    //replaced with lambda to make it look cleaner
    public static Comparator<CourseRegistration> LecComparator = (o1, o2) -> {
        String group1 = o1.getLectureGroup().toUpperCase();
        String group2 = o2.getLectureGroup().toUpperCase();

        //ascending order
        return group1.compareTo(group2);

    };

    //replaced with lambda to make it look cleaner
    public static Comparator<CourseRegistration> TutComparator = (s1, s2) -> {
        String group1 = s1.getTutorialGroup().toUpperCase();
        String group2 = s2.getTutorialGroup().toUpperCase();

        //ascending order
        return group1.compareTo(group2);

    };

    //replaced with lambda to make it look cleaner
    public static Comparator<CourseRegistration> LabComparator = (o1, o2) -> {
        String group1 = o1.getLabGroup().toUpperCase();
        String group2 = o2.getLabGroup().toUpperCase();

        //ascending order
        return group1.compareTo(group2);
    };
}
