package com.softeng306.GroupResources;

import com.softeng306.Domain.Group.Group;
import com.softeng306.Domain.Group.LabGroup;
import com.softeng306.Domain.Group.LectureGroup;
import com.softeng306.Domain.Group.TutorialGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class to represent the flow of adding Group.
 * <p>
 * createGroupTemplate() is a template method defining the
 * steps needed for adding Group. Subclasses need to
 * implement these methods by synchronising inputReader and validateGroup.
 */
public abstract class GroupFlow {

    protected GroupInputReader groupInputReader;
    protected GroupValidate groupValidate;

    public GroupFlow(GroupInputReader groupInputReader, GroupValidate groupValidate) {
        this.groupInputReader = groupInputReader;
        this.groupValidate = groupValidate;
    }

    /**
     * Template method for creating a group
     *
     * @param groupType the type of group to create
     * @param vacancy number of available seats
     * @param AU number of academic unit
     * @return a Group Data Access Object (DAO) to return a group block of info.
     */
    public GroupDAO addGroupTemplate(String groupType, int vacancy, int AU) {
        String numOfGroup = numOfGroupSelection(groupType, vacancy);
        Integer numOfGroupInt = Integer.parseInt(numOfGroup);
        if (numOfGroupInt == 0) {
            return new GroupDAO(new ArrayList<Group>(), 0, 0);
        }
        String weeklyHr = weeklyHrSelection(groupType, AU);
        int availableSeats = vacancy;
        List<Group> groupList = new ArrayList<>();
        boolean createdGroup, validGroupCapacity;
        for (int i = 0; i < numOfGroupInt; i++) {
            createdGroup = false;
            while (!createdGroup) {
                String groupName = groupNameSelection(groupType,groupList);
                validGroupCapacity = false;
                while (!validGroupCapacity) {
                    String groupCapacity = groupCapacity(groupType);

                    int lectureGroupCapacity = Integer.parseInt(groupCapacity);
                    availableSeats -= lectureGroupCapacity;
                    validGroupCapacity = groupValidate.checkValidAssignedSeats(groupType, groupName, i, availableSeats, numOfGroupInt);
                    if (validGroupCapacity) {
                        Group lectureGroup = createGroup(groupType, groupName, lectureGroupCapacity, lectureGroupCapacity);
                        groupList.add(lectureGroup);
                        createdGroup = true;
                    } else {
                        availableSeats += lectureGroupCapacity;
                    }
                }
            }
        }

        return new GroupDAO(groupList, numOfGroupInt, Integer.parseInt(weeklyHr));
    }


    /**
     * First method call from template method.
     * Subclasses need to implement this method.
     *
     * Method should synchronise number of group input with validating the input.
     * @param groupType the type of group
     * @param vacancy number of available seats
     * @return a string for number of group
     */
    public abstract String numOfGroupSelection (String groupType, int vacancy);

    /**
     * Second method call from template method.
     * Subclasses need to implement this method.
     *
     * Method should synchronise entering weekly hour input with validating weekly hour.
     * @param groupType the type of group
     * @param AU number of academic unit
     * @return a string representing weekly hour
     */
    public abstract String weeklyHrSelection (String groupType, int AU);

    /**
     * Third method call from template method.
     * Subclasses need to implement this method.
     *
     * Method should synchronise entering group name with validating group name.
     * @param groupType the type of group
     * @param groups a list of groups containing already created groups
     * @return a string representing the group name
     */
    public abstract String groupNameSelection (String groupType, List < ? extends Group > groups);

    /**
     * Forth method call from template method.
     * Subclasses need to implement this method.
     *
     * Method should synchronise entering group capacity with validating group capacity.
     * @param groupType the type of group
     * @return a string representing the group capacity
     */
    public abstract String groupCapacity(String groupType);

    /**
     * Helper method to create a specific group class.
     * @param groupType the type of group
     * @param groupName the name of the group
     * @param availableVacancies number of available seats
     * @param totalSeats total number of seats
     * @return a group object
     */
    protected Group createGroup(String groupType, String groupName, int availableVacancies, int totalSeats) {
        if (groupType.equalsIgnoreCase("LECTURE")) {
            return new LectureGroup(groupName,availableVacancies,totalSeats);
        } else if (groupType.equalsIgnoreCase("LAB")) {
            return new LabGroup(groupName, availableVacancies, totalSeats);
        } else if (groupType.equalsIgnoreCase("TUTORIAL")) {
            return new TutorialGroup(groupName, availableVacancies, totalSeats);
        }
        return null;
    }
}
