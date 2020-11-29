package com.softeng306.GroupResources;

import com.softeng306.Domain.Group.Group;

import java.util.List;

public interface GroupValidate {

    boolean checkValidNumberOfGroup(String groupType, String numberOfGroups, int totalSeats);

    boolean checkValidWeeklyHr(String groupType, String weeklyHrStr, int AU);

    boolean checkValidGroupNameInput(String groupName);

    boolean checkValidGroupCapacity(String capacity, String groupType);

    boolean checkNotGroupNameExist(String groupType, List<? extends Group> groups, String groupName);

    boolean checkValidAssignedSeats(String groupType, String lectureGroupName, int i, int availableSeats, int numLectureGroup);
}
