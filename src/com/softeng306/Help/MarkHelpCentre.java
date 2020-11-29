package com.softeng306.Help;

import com.softeng306.Domain.Course;
import com.softeng306.Domain.Student;


public abstract class MarkHelpCentre {

    private StudentHelpCentre studentHelpCentre;
    private CourseHelpCentre courseHelpCentre;

    public MarkHelpCentre(StudentHelpCentre studentHelpCentre, CourseHelpCentre courseHelpCentre) {
        this.studentHelpCentre = studentHelpCentre;
        this.courseHelpCentre = courseHelpCentre;
    }

   public boolean checkStudentExists(String studentID) {
      Student student = studentHelpCentre.checkStudentExists(studentID, false);
      if (student == null) {
          return false;
      }
       return true;
   }

    public boolean checkCourseExists(String courseID){
        Course course = courseHelpCentre.checkCourseExists(courseID, false);
        if (course == null) {
            return false;
        }
        return true;
    }

    public void printAllStudents() {
        studentHelpCentre.printAllStudents();
    }

    public void printAllCourses() {
        courseHelpCentre.printAllCourses();
    }

}
