package com.softeng306.StudentResources;

import com.softeng306.Help.GetEnum;
import com.softeng306.Help.PrintEnum;
import com.softeng306.Help.StudentHelpCentre;
import com.softeng306.Domain.Student;

import java.util.List;

public class UOAValidateStudent extends StudentHelpCentre implements ValidateStudent{

    public UOAValidateStudent(GetEnum getEnum, PrintEnum printEnum, List<Student> students) {
        super(getEnum, printEnum, students);
    }

    /**
     * Checks whether the user input for the option of generating a student ID is valid
     * @param choice the inputted choice
     * @return true if choice is valid
     */
    public boolean studentGeneratorIsValid(Integer choice) {
        if (choice == null) {
            System.out.println("Your input " + choice + " is not an integer.");
            return false;
        } else if (choice < 1 || choice > 2) {
            System.out.println("Invalid input. Please re-enter.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks whether the inputted year for a student is valid
     * @param year the inputted year
     * @return true if the inputted year is valid
     */
    public boolean studentYearIsValid(String year) {
        int intYear;
        try {
            intYear = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            System.out.println("Your input " + year + " is not an integer");
            System.out.println("Please re-enter.");
            return false;
        }

        if (intYear < 1 || intYear > 4) {
            System.out.println("Your input is out of bound.");
            System.out.println("Please re-enter an integer between 1 and 4");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks whether the inputted department for a student is valid
     * @param studentSchool the inputted department
     * @return true if the inputted department is valid
     */
    public boolean validDepartment(String studentSchool) {
        if (studentSchool.equals("-h")) {
            super.printAllDepartment();
            return false;
        } else if (super.getAllDepartment().contains(studentSchool)) {
            return true;
        }
        System.out.println("The department is invalid. Please re-enter.");
        System.out.println("Enter student's school (uppercase): ");
        System.out.println("Enter -h to print all the schools.");
        return false;
    }

    /**
     * Checks whether the inputted gender for a student is valid
     * @param gender the inputted gender
     * @return true if the inputted gender is valid
     */
    public boolean validGender(String gender) {
        if (gender.equals("-h")) {
            super.printAllGender();
            return false;
        } else if (super.getAllGender().contains(gender)) {
            return true;
        }
        System.out.println("The gender is invalid. Please re-enter.");
        System.out.println("Enter student gender (uppercase): ");
        System.out.println("Enter -h to print all the genders.");
        return false;
    }

}
