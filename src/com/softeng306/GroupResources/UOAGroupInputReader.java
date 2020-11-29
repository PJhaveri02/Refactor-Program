package com.softeng306.GroupResources;

import java.util.Scanner;

public class UOAGroupInputReader implements GroupInputReader{

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Prompts the user to input the number of groups for a specified type and returns their input
     * @param groupType the type of group
     * @return the number of groups
     */
    public String enterNumOfGroup(String groupType) {
        String noOfGroups = null;
        System.out.println("Enter the number of " + groupType + " groups: ");

        if (scanner.hasNext()) {
            noOfGroups = scanner.nextLine();
        }

        return noOfGroups;
    }

    /**
     *  Prompts the user to input the number of hours per week for a group type
     * @param groupType the type of group
     * @return the number of weekly hours
     */
    public String enterWeeklyHour(String groupType) {
        String lecWeeklyHour = null;
        System.out.println("Enter the weekly " + groupType + " hour for this course: ");
        if (scanner.hasNext()) {
            lecWeeklyHour = scanner.nextLine();
        }
        return lecWeeklyHour;
    }

    /**
     * Prompts the user to enter a name for a group
     * @param groupType the type of group
     * @return the inputted name of the group
     */
    public String enterGroupName(String groupType) {
        String groupName = null;
        System.out.println("Give a name to the " + groupType + " group");
        System.out.println("Enter a group Name: ");
        if (scanner.hasNext()) {
            groupName = scanner.nextLine();
        }
        return groupName;
    }

    /**
     * Prompts the user to enter the capacity of a specified group
     * @param groupType the type of group
     * @return the inputted capacity of the group
     */
    public String enterGroupCapacity(String groupType, boolean printOut) {
        String capacity = null;
        if (printOut) {
            System.out.println("Enter this " + groupType + " group's capacity: ");
        }
        if (scanner.hasNext()) {
            capacity = scanner.nextLine();
        }
        return capacity;
    }
}
