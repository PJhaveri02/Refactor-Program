package com.softeng306.GroupResources;

public interface GroupInputReader {

    String enterNumOfGroup(String groupType);

    String enterWeeklyHour(String groupType);

    String enterGroupName(String groupType);

    String enterGroupCapacity(String groupType, boolean printOut);

}
