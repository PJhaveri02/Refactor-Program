package com.softeng306.Help;

import com.softeng306.Enum.*;
import com.softeng306.Domain.Group.Group;
import com.softeng306.UOA.UOACourseMgr;
import com.softeng306.UOA.UOAStudentMgr;

import java.util.*;

/**
 * Manages all the help information display in the system.

 */

public class HelpInfoMgr {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Displays a list of IDs of all the students.
     */
    public static void printAllStudents() {
        UOAStudentMgr.getInstance().getStudents().stream().map(s -> s.getStudentID()).forEach(System.out::println);
    }

    /**
     * Displays a list of IDs of all the courses.
     */
    public static void printAllCourses() {
        UOACourseMgr.getInstance().getCourses().stream().map(c -> c.getCourseID()).forEach(System.out::println);

    }

    /**
     * Displays a list of all the departments.
     */
    public static void printAllDepartment() {
        int index = 1;
        for (Department department : Department.values()) {
            System.out.println(index + ": " + department);
            index++;
        }

    }

    /**
     * Gets all the departments as an array list.
     *
     * @return an array list of all the departments.
     */
    public static ArrayList<String> getAllDepartment() {
        Set<Department> departmentEnumSet = EnumSet.allOf(Department.class);
        ArrayList<String> departmentStringList = new ArrayList<String>(0);
        Iterator iter = departmentEnumSet.iterator();
        while (iter.hasNext()) {
            departmentStringList.add(iter.next().toString());
        }
        return departmentStringList;

    }

    /**
     * Checks whether the inputted department is valid.
     *
     * @param groupType The type of this group.
     * @param groups    An array list of a certain type of groups in a course.
     * @return the name of the group chosen by the user.
     */
    public static String printGroupWithVacancyInfo(String groupType, ArrayList<Group> groups) {
        int index;
        HashMap<String, Integer> groupAssign = new HashMap<String, Integer>(0);
        int selectedGroupNum;
        String selectedGroupName = null;

        if (groups.size() != 0) {
            System.out.println("Here is a list of all the " + groupType + " groups with available slots:");
            do {
                index = 0;
                for (Group group : groups) {
                    if (group.getAvailableVacancies() == 0) {
                        continue;
                    }
                    index++;
                    System.out.println(index + ": " + group.getGroupName() + " (" + group.getAvailableVacancies() + " vacancies)");
                    groupAssign.put(group.getGroupName(), index);
                }
                System.out.println("Please enter an integer for your choice:");
                selectedGroupNum = scanner.nextInt();
                scanner.nextLine();
                if (selectedGroupNum < 1 || selectedGroupNum > index) {
                    System.out.println("Invalid choice. Please re-enter.");
                } else {
                    break;
                }
            } while (true);

            for (HashMap.Entry<String, Integer> entry : groupAssign.entrySet()) {
                String groupName = entry.getKey();
                int num = entry.getValue();
                if (num == selectedGroupNum) {
                    selectedGroupName = groupName;
                    break;
                }
            }

            for (Group group : groups) {
                if (group.getGroupName().equals(selectedGroupName)) {
                    group.enrolledIn();
                    break;
                }
            }
        }
        return selectedGroupName;
    }

}
