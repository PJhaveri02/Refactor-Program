package com.softeng306.Help;

import com.softeng306.Enum.CourseType;
import com.softeng306.Enum.Department;
import com.softeng306.Enum.Gender;
import java.util.*;

public class UOAGetEnum implements GetEnum {

    /**
     *
     * @return a list of all course types as strings
     */
    public List<String> getAllCourseType() {
        Set<CourseType> courseTypeEnumSet = EnumSet.allOf(CourseType.class);
        ArrayList<String> courseTypeStringSet = new ArrayList<String>(0);
        for (CourseType courseType : courseTypeEnumSet) {
            courseTypeStringSet.add(courseType.toString());
        }
        return courseTypeStringSet;
    }

    /**
     *
     * @return a list of all departments as strings
     */
    public List<String> getAllDepartment() {
        Set<Department> departmentEnumSet = EnumSet.allOf(Department.class);
        ArrayList<String> departmentStringList = new ArrayList<String>(0);
        for (Department department : departmentEnumSet) {
            departmentStringList.add(department.toString());
        }
        return departmentStringList;

    }

    /**
     *
     * @return a list of all genders as strings
     */
    public List<String> getAllGender() {
        Set<Gender> genderEnumSet = EnumSet.allOf(Gender.class);
        ArrayList<String> genderStringList = new ArrayList<String>(0);
        for (Gender gender : genderEnumSet) {
            genderStringList.add(gender.toString());
        }
        return genderStringList;
    }
}
