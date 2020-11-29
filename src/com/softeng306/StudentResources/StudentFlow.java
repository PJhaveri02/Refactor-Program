package com.softeng306.StudentResources;

import com.softeng306.Domain.Student;

/**
 * Abstract class to represent the flow of adding students.
 * <p>
 * createStudentTemplate() is a template method defining the
 * steps needed for adding students. Subclasses need to
 * implement these methods by synchronising inputReader and validateStudent.
 */
public abstract class StudentFlow {

    protected StudentInputReader inputReader;
    protected ValidateStudent validateStudent;

    public StudentFlow(StudentInputReader inputReader, ValidateStudent validateStudent) {
        this.inputReader = inputReader;
        this.validateStudent = validateStudent;
    }

    /**
     * Template method to create a student
     *
     * @return a student object
     */
    public Student addStudentTemplate() {
        greetUser();
        Integer choice = doStudentIDSelection();
        String studentID = doStudentID(choice);
        String studentName = doStudentName();
        String department = doDepartment();
        String gender = doGender();
        String year = doStudentYear();
        return new Student(studentID, studentName, department, gender, Integer.parseInt(year));
    }

    /**
     * First method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method greets the user.
     */
    public abstract void greetUser();

    /**
     * Second method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise generating studentID option with validating input option.
     *
     * @return an Integer representing generating studentID option.
     */
    public abstract Integer doStudentIDSelection();

    /**
     * Third method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering studentID with validating studentID.
     *
     * @param choice an integer representing generating studentID option.
     * @return a string representing the studentID
     */
    public abstract String doStudentID(Integer choice);

    /**
     * Fourth method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering student name with validating student name.
     *
     * @return a string representing the student name.
     */
    public abstract String doStudentName();

    /**
     * Fifth method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering department with validating department input.
     *
     * @return a string representing the department.
     */
    public abstract String doDepartment();

    /**
     * Sixth method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering gender with validating gender input.
     *
     * @return a string representing the gender.
     */
    public abstract String doGender();

    /**
     * Seventh method call from template method.
     * Subclasses need to implement this method.
     * <p>
     * Method should synchronise entering student year with validating student year input.
     *
     * @return a string representing the student year.
     */
    public abstract String doStudentYear();
}
