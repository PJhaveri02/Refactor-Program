package com.softeng306;

import com.softeng306.MgrInterfaces.*;

public class University {
    //manager classes of the fields originally in Main
    private StudentMgr studentMgr;
    private CourseMgr courseMgr;
    private CourseRegistrationMgr courseRegistrationMgr;
    private MarkMgr markMgr;

    public University(StudentMgr studentMgr, CourseMgr courseMgr, CourseRegistrationMgr courseRegistrationMgr, MarkMgr markMgr) {
        this.studentMgr = studentMgr;
        this.courseMgr = courseMgr;
        this.courseRegistrationMgr = courseRegistrationMgr;
        this.markMgr = markMgr;
    }

    public StudentMgr getStudentMgr() {
        return studentMgr;
    }

    public CourseMgr getCourseMgr() {
        return courseMgr;
    }

    public CourseRegistrationMgr getCourseRegistrationMgr() {
        return courseRegistrationMgr;
    }

    public MarkMgr getMarkMgr() {
        return markMgr;
    }

}


