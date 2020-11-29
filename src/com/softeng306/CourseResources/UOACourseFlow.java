package com.softeng306.CourseResources;

import com.softeng306.GroupResources.GroupFlow;
import com.softeng306.Domain.Professor;

public class UOACourseFlow extends CourseFlow {

    private CourseInputReader courseReader;
    private ValidateCourse validateCourse;

    public UOACourseFlow(GroupFlow groupFlow, CourseInputReader courseReader, ValidateCourse validateCourse) {
        super(groupFlow);
        this.courseReader = courseReader;
        this.validateCourse = validateCourse;
    }

    @Override
    public String courseIDSelection() {
        String courseID = null;
        boolean validCourseID = false;

        while (!validCourseID) {
            courseID = courseReader.enterCourseID();
            if (validateCourse.checkValidCourseIDInput(courseID)) {
                if (validateCourse.checkCourseExists(courseID, true) == null) {
                    validCourseID = true;
                }
            }

        }
        return courseID;
    }

    @Override
    public String courseNameSelection() {
        String courseName = null;
        boolean validCourseName = false;

        while (!validCourseName) {
            courseName = courseReader.enterCourseName();
            validCourseName = validateCourse.checkValidCourseName(courseName);
        }
        return courseName;
    }

    @Override
    public String courseVacancySelection() {
        String courseVacancy = null;
        boolean validCourseVacancy = false;

        while (!validCourseVacancy) {
            courseVacancy = courseReader.enterCourseVacancy();
            validCourseVacancy = validateCourse.checkValidCourseVacancy(courseVacancy);
        }

        return courseVacancy;
    }

    @Override
    public String courseAUSelection() {
        String courseAU = null;
        boolean validCourseAU = false;

        while (!validCourseAU) {
            courseAU = courseReader.enterCourseAU();
            validCourseAU = validateCourse.checkValidCourseAU(courseAU);
        }

        return courseAU;
    }


    @Override
    public String courseDepartmentSelection() {
        String courseDepartment = null;
        boolean validCourseDpt = false;
        boolean printMessage = true;

        while (!validCourseDpt) {
            courseDepartment = courseReader.enterCourseDepartment(printMessage);
            printMessage = false;
            validCourseDpt = validateCourse.checkValidCourseDepartment(courseDepartment);
        }
        return courseDepartment;
    }

    @Override
    public String courseTypeSelection() {
        String courseType = null;
        boolean validCourseType = false;
        boolean printMessage = true;

        while (!validCourseType) {
            courseType = courseReader.enterCourseType(printMessage);
            validCourseType = validateCourse.checkValidCourseType(courseType);
            printMessage = false;
        }

        return courseType;
    }

    @Override
    public Professor professorSelection(String department) {
        String profID = null;
        boolean printOut = true;
        Professor profInCharge = null;

        while (profInCharge == null) {
            profID = courseReader.enterProfessor(department, printOut);
            profInCharge = validateCourse.checkValidProfessor(profID, department);
            printOut = false;
        }

        return profInCharge;
    }
}
