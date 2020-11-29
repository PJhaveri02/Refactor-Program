package com.softeng306.CourseResources;

import com.softeng306.Domain.Course;
import com.softeng306.Domain.Group.Group;
import com.softeng306.Domain.Group.LabGroup;
import com.softeng306.Domain.Group.LectureGroup;
import com.softeng306.Domain.Group.TutorialGroup;
import com.softeng306.Domain.Professor;
import com.softeng306.GroupResources.GroupDAO;
import com.softeng306.GroupResources.GroupFlow;

import java.util.ArrayList;
import java.util.List;
/**
 * Abstract class to represent the flow of adding courses.
 * <p>
 * addCourseTemplate() is a template method defining the
 * steps needed for adding Course. Subclasses need to
 * implement these methods by synchronising inputReader and validateCourse.
 */
public abstract class CourseFlow {

    private GroupFlow groupFlow;

    public CourseFlow(GroupFlow groupFlow) {
        this.groupFlow = groupFlow;
    }

    /**
     * templete method to create new student
     * @return a course object
     */
    public Course addCourseTemplate(){
        String courseID = courseIDSelection();
        String courseName = courseNameSelection();
        String courseVacancy = courseVacancySelection();
        String courseAU = courseAUSelection();
        String courseDepartment = courseDepartmentSelection();
        String courseType = courseTypeSelection();
        GroupDAO lectureDAO = groupFlow.addGroupTemplate("lecture", Integer.parseInt(courseVacancy),
                Integer.parseInt(courseAU));
        GroupDAO tutorialDAO = groupFlow.addGroupTemplate("tutorial", Integer.parseInt(courseVacancy),
                Integer.parseInt(courseAU));
        GroupDAO labDAO = groupFlow.addGroupTemplate("lab", Integer.parseInt(courseVacancy),
                Integer.parseInt(courseAU));
        Professor profInCharge = professorSelection(courseDepartment);

        return new Course(courseID, courseName, profInCharge,
                Integer.parseInt(courseVacancy), Integer.parseInt(courseVacancy),
                convertToLectureGroup(lectureDAO.getGroupList()),
                convertToTutorialGroup(tutorialDAO.getGroupList()),
                convertToLabGroup(labDAO.getGroupList()),
                Integer.parseInt(courseAU), courseDepartment, courseType,
                lectureDAO.getWeeklyHr(), tutorialDAO.getWeeklyHr(), labDAO.getWeeklyHr());

    }

    /**
     * first method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering courseID option with validating course option.
     *
     * @return an String representing courseID.
     */
    public abstract String courseIDSelection();

    /**
     * second method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering course Name option with validating course name.
     *
     * @return an String representing course name.
     */
    public abstract String courseNameSelection();

    /**
     * Third method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering course vacancy option with validating course vacancy.
     *
     * @return an String representing vacancy for the course.
     */
    public abstract String courseVacancySelection();

    /**
     * fourth method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering Academic unit with validating Academic unit.
     *
     * @return an String representing the Academic unit.
     */
    public abstract String courseAUSelection();

    /**
     * fifth method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering department with validating course department.
     *
     * @return an String representing the department.
     */
    public abstract String courseDepartmentSelection();

    /**
     * sixth method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering course type with validating course type.
     *
     * @return an String representing the course type.
     */
    public abstract String courseTypeSelection();

    /**
     * seventh method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering professor ID with validating professor ID.
     *
     * @param department String representing a department
     * @return professor object
     */
    public abstract Professor professorSelection(String department);

    /**
     * converts a list of type group to a list of type LectureGroup
     * @param groups list of Groups
     * @return list of LectureGroups
     */
    protected ArrayList<LectureGroup> convertToLectureGroup(List<Group> groups) {
        ArrayList<LectureGroup> lectureGroups = new ArrayList<LectureGroup>();

        for (Group group : groups) {
            if (group instanceof LectureGroup) {
                lectureGroups.add((LectureGroup) group);
            }
        }
        return lectureGroups;
    }

    /**
     * converts a list of type group to a list of type TutorialGroup
     * @param groups list of Groups
     * @return list of TutorialGroups
     */
    protected ArrayList<TutorialGroup> convertToTutorialGroup(List<Group> groups) {
        ArrayList<TutorialGroup> labGroups = new ArrayList<TutorialGroup>();

        for (Group group : groups) {
            if (group instanceof TutorialGroup) {
                labGroups.add((TutorialGroup) group);
            }
        }
        return labGroups;
    }

    /**
     * converts a list of type group to a list of type LabGroup
     * @param groups list of Groups
     * @return list of LabGroups
     */
    protected ArrayList<LabGroup> convertToLabGroup(List<Group> groups) {
        ArrayList<LabGroup> labGroups = new ArrayList<LabGroup>();

        for (Group group : groups) {
            if (group instanceof LabGroup) {
                labGroups.add((LabGroup) group);
            }
        }
        return labGroups;
    }

}
