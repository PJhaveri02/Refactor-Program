package com.softeng306.GroupResources;

import com.softeng306.Domain.Group.Group;

import java.util.List;

/**
 * This class is used to represent a number of groups with its count and weekly hours. Is used to return group blocks
 * of info from methods.
 */
public class GroupDAO {

    private List<Group> groupList;
    private int numOfGroup;
    private int weeklyHr;

    public GroupDAO(List<Group> groupList, int numOfGroup, int weeklyHr) {
        this.groupList = groupList;
        this.numOfGroup = numOfGroup;
        this.weeklyHr = weeklyHr;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public int getNumOfGroup() {
        return numOfGroup;
    }

    public int getWeeklyHr() {
        return weeklyHr;
    }
}
