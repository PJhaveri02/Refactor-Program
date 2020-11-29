package com.softeng306.GroupResources;

import com.softeng306.Domain.Group.Group;

import java.util.List;

/**
 * Implementation for GroupFlow abstract class
 */
public class UOAGroupFlow extends GroupFlow {

    public UOAGroupFlow(GroupInputReader groupInputReader, GroupValidate groupValidate) {
        super(groupInputReader, groupValidate);
    }

    /**
     * Implementation for GroupFlow#numOfGroupSelection()
     */
    @Override
    public String numOfGroupSelection(String groupType, int vacancy) {
        String numLectureGroup = null;
        boolean validLectureGroup = false;

        while (!validLectureGroup) {
            numLectureGroup = groupInputReader.enterNumOfGroup(groupType);
            validLectureGroup = groupValidate.checkValidNumberOfGroup(groupType, numLectureGroup, vacancy);
        }

        return numLectureGroup;
    }

    /**
     * Implementation for GroupFlow#weeklyHrSelection()
     */
    @Override
    public String weeklyHrSelection(String groupType, int AU) {
        String tutWeeklyHr = null;
        boolean validWeeklyHr = false;

        while (!validWeeklyHr) {
            tutWeeklyHr = groupInputReader.enterWeeklyHour(groupType);
            validWeeklyHr = groupValidate.checkValidWeeklyHr(groupType, tutWeeklyHr, AU);
        }
        return tutWeeklyHr;
    }

    /**
     * Implementation for GroupFlow#groupNameSelection()
     */
    @Override
    public String groupNameSelection(String groupType, List<? extends Group> groups) {
        String groupName = null;
        boolean validGroupName = false;
        while (!validGroupName) {
            groupName = groupInputReader.enterGroupName(groupType);
            if (groupValidate.checkValidGroupNameInput(groupName)) {
                validGroupName = groupValidate.checkNotGroupNameExist(groupType, groups, groupName);
            }
        }
        return groupName;
    }

    /**
     * Implementation for GroupFlow#groupCapacity()
     */
    @Override
    public String groupCapacity(String groupType) {
        String groupCapacity = null;
        boolean validGroupCapacity = false;
        boolean printMessage = true;

        while (!validGroupCapacity) {
            groupCapacity = groupInputReader.enterGroupCapacity(groupType, printMessage);
            validGroupCapacity = groupValidate.checkValidGroupCapacity(groupCapacity, groupType);
            printMessage= false;
        }
        return groupCapacity;
    }
}
