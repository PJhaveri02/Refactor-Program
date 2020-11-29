package com.softeng306;

import com.softeng306.CourseResources.*;
import com.softeng306.Domain.*;
import com.softeng306.GroupResources.GroupFlow;
import com.softeng306.GroupResources.UOAGroupFlow;
import com.softeng306.GroupResources.UOAGroupInputReader;
import com.softeng306.GroupResources.UOAGroupValidate;
import com.softeng306.Help.CourseHelpCentre;
import com.softeng306.Help.StudentHelpCentre;
import com.softeng306.Help.UOAGetEnum;
import com.softeng306.Help.UOAPrintEnum;
import com.softeng306.MarkResources.*;
import com.softeng306.StudentResources.*;
import com.softeng306.MgrInterfaces.*;
import com.softeng306.UOA.*;

import java.util.List;

public class Main {

    /**
     * The main function of the system.
     * Command line interface.
     * @param args The command line parameters.
     */
    public static void main(String[] args) {

        List<Course> courses = FILEMgr.loadCourses();
        List<Student> students = FILEMgr.loadStudents();
        List<CourseRegistration> courseRegistrations = FILEMgr.loadCourseRegistration();
        List<Mark> marks = FILEMgr.loadStudentMarks();
        List<Professor> professors = FILEMgr.loadProfessors();

        // Course Manager Initialize
        GroupFlow UOAGroupFlow = new UOAGroupFlow(new UOAGroupInputReader(), new UOAGroupValidate());
        CourseInputReader UOACIR = new UOACourseInputReader();
        ValidateCourse UOAValidateCourse = new UOAValidateCourse(new UOAGetEnum(), new UOAPrintEnum(), courses, professors);
        CourseFlow UOACourseFlow = new UOACourseFlow(UOAGroupFlow, UOACIR, UOAValidateCourse);
        CourseMgr courseMgr = UOACourseMgr.getInstance(courses, UOACourseFlow);

        // Student Manager Initialize
        StudentInputReader UOAStudentInputReader = new UOAStudentInputReader();
        ValidateStudent validateStudent = new UOAValidateStudent(new UOAGetEnum(), new UOAPrintEnum(), students);
        StudentFlow studentFlow = new UOAStudentFlow(UOAStudentInputReader, validateStudent);
        StudentMgr studentMgr = UOAStudentMgr.getInstance(students, studentFlow);

        // Mark Manager Initialize
        MarkInputReader markInputReader = new UOAMarkInputReader();
        ValidateMark validateMark = new UOAValidateMark((StudentHelpCentre) validateStudent, (CourseHelpCentre) UOAValidateCourse);
        MarkFlow markFlow = new UOAMarkFlow(markInputReader, validateMark);
        MarkMgr markMgr = UOAMarkMgr.getInstance(marks, markFlow);

        CourseRegistrationMgr courseRegistrationMgr = UOACourseRegistrationMgr.getInstance(courseRegistrations);

        University UOA = new University(studentMgr, courseMgr, courseRegistrationMgr, markMgr);
        Menu menu = new Menu(UOA);
        menu.start();
    }
}