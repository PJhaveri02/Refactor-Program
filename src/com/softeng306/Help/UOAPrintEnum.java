package com.softeng306.Help;

import com.softeng306.Enum.CourseType;
import com.softeng306.Enum.Department;
import com.softeng306.Enum.Gender;

public class UOAPrintEnum implements PrintEnum{

    /**
     * prints all course types
     */
    public void printAllCourseType() {
        int index = 1;
        for (CourseType courseType : CourseType.values()) {
            System.out.println(index + ": " + courseType);
            index++;
        }
    }

    /**
     * prints all departments
     */
    public void printAllDepartment() {
        int index = 1;
        for (Department department : Department.values()) {
            System.out.println(index + ": " + department);
            index++;
        }
    }

    /**
     * prints all Genders
     */
    public void printAllGender() {
        int index = 1;
        for (Gender gender : Gender.values()) {
            System.out.println(index + ": " + gender);
            index++;
        }
    }
}
