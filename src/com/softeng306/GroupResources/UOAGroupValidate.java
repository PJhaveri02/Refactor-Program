package com.softeng306.GroupResources;

import com.softeng306.Domain.Group.Group;
import com.softeng306.Help.GroupHelpCentre;

import java.util.List;

public class UOAGroupValidate extends GroupHelpCentre implements GroupValidate {

    /**
     * Checks the validity of the given number of groups and total seats for the group type
     * @param groupType the type of group being validated
     * @param numberOfGroups the user input for number of groups
     * @param totalSeats the total number of seats for the groups
     * @return a boolean holding the validity of the input
     */
    public boolean checkValidNumberOfGroup(String groupType, String numberOfGroups, int totalSeats) {
        Integer noOfGroups = null;
        try {
            noOfGroups = Integer.parseInt(numberOfGroups);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + numberOfGroups + " is not an integer.");
            return false;
        }
        if (groupType.equalsIgnoreCase("LECTURE") && noOfGroups > 0 && noOfGroups <= totalSeats) {
            return true;
        } else if (groupType.equalsIgnoreCase("LAB") || groupType.equalsIgnoreCase("TUTORIAL")) {
            if (noOfGroups >= 0 && noOfGroups <= totalSeats) {
                return true;
            }
        }

        System.out.println("Invalid input.");
        System.out.println("Number of " + groupType + " group must be positive but less than total seats in this course.");
        System.out.println("Please re-enter");
        return false;
    }

    /**
     * Checks the validity of the hours per week for the given group type and number of academic units
     * @param groupType the type of group being validated
     * @param weeklyHrStr the weekly number of hours for the given group type
     * @param AU the number of academic units
     * @return a boolean holding the validity of the number of hours
     */
    public boolean checkValidWeeklyHr(String groupType, String weeklyHrStr, int AU) {
        Integer weeklyHr = null;
        try {
            weeklyHr = Integer.parseInt(weeklyHrStr);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + weeklyHrStr + " is not an integer.");
            return false;
        }
        if (weeklyHr < 0 || weeklyHr > AU) {
            System.out.println("Weekly " + groupType + " hour out of bound. Please re-enter.");
            return false;
        }
        return true;
    }

    /**
     * Checks the validity of an inputted capacity for a group
     * @param capacity user input for the capacity of a group
     * @return a boolean holding the validity of the capacity
     */
    public boolean checkValidGroupCapacity(String capacity, String groupType) {
        Integer lectureGroupCapacity = null;

        try {
            lectureGroupCapacity = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + capacity + " is not an integer.");
            return false;
        }
        if ((lectureGroupCapacity > 0) && (groupType.equalsIgnoreCase("LECTURE") )) {
            return true;
        } else if (!(groupType.equalsIgnoreCase("LECTURE"))){
            return true;
        }
        System.out.println("Capacity must be positive. Please re-enter.");
        return false;
    }

    /**
     * Checks that the user inputted group name is not already used by another group
     * @param groupType the type of group the group name is for
     * @param groups a list of all groups currently in the system
     * @param groupName the inputted group name
     * @return a boolean holding the validity of the group name
     */
    public boolean checkNotGroupNameExist(String groupType, List<? extends Group> groups, String groupName) {
        for (Group group : groups) {
            if (group.getGroupName().equals(groupName)) {
                System.out.println("This " + groupType + " group already exist for this course.");
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that a group has a valid number of seats
     * @param groupType the type of group
     * @param lectureGroupName the name of the group
     * @param i loop counter from calling method
     * @param availableSeats number of seats for the group
     * @param numLectureGroup number of groups
     * @return a boolean holding the validity of the number of seats for a group
     */
    public boolean checkValidAssignedSeats(String groupType, String lectureGroupName, int i, int availableSeats, int numLectureGroup) {
        if ((groupType.equalsIgnoreCase("LECTURE") && (availableSeats > 0 && i != (numLectureGroup - 1))) || (availableSeats == 0 && i == (numLectureGroup - 1))) {
            return true;
        } else if ((groupType.equalsIgnoreCase("LAB"))|| (groupType.equalsIgnoreCase("TUTORIAL"))) {
            if ((i != numLectureGroup - 1) || (availableSeats <= 0)) {
                return true;
            }
        }

        if ((groupType.equalsIgnoreCase("LECTURE"))) {
            System.out.println("Sorry, the total capacity you allocated for all the " + groupType + " groups exceeds or does not add up to the total seats for this course.");
            System.out.println("Please re-enter the capacity for the last " + groupType + " group " + lectureGroupName + " you have entered.");
        } else {
            System.out.println("Sorry, the total capacity you allocated for all the " + groupType + " groups is not enough for this course.");
            System.out.println("Please re-enter the capacity for the last " + groupType + " group " + lectureGroupName + " you have entered.");
        }
        return false;
    }
}
