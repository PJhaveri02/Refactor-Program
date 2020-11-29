package com.softeng306;


import com.softeng306.Domain.*;
import com.softeng306.Domain.Components.CourseworkComponent;
import com.softeng306.Domain.Components.MainComponent;
import com.softeng306.Domain.Components.SubComponent;
import com.softeng306.Domain.Group.Group;
import com.softeng306.Domain.Group.LabGroup;
import com.softeng306.Domain.Group.LectureGroup;
import com.softeng306.Domain.Group.TutorialGroup;
import com.softeng306.UOA.UOAStudentMgr;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;



public class FILEMgr {

    /**
     * The string of {@code COMMA_DELIMITER}.
     */
    private static final String COMMA_DELIMITER = ",";

    /**
     * The string of {@code NEW_LINE_SEPARATOR}.
     */
    private static final String NEW_LINE_SEPARATOR = "\n";

    /**
     * The string of {@code LINE_DELIMITER}.
     */
    private static final String LINE_DELIMITER = "|";

    /**
     * The string of {@code EQUAL_SIGN}.
     */
    private static final String EQUAL_SIGN = "=";

    /**
     * The string of {@code HYPHEN}.
     */
    private static final String HYPHEN = "-";

    /**
     * The string of {@code SLASH}.
     */
    private static final String SLASH = "/";

    /**
     * The file name of studentFile.csv.
     */
    private static final String studentFileName = "data/studentFile.csv";

    /**
     * The file name of courseFile.csv.
     */
    private static final String courseFileName = "data/courseFile.csv";

    /**
     * The file name of professorFile.csv.
     */
    private static final String professorFileName = "data/professorFile.csv";

    /**
     * The file name of courseRegistrationFile.csv.
     */
    private static final String courseRegistrationFileName = "data/courseRegistrationFile.csv";

    /**
     * The file name of markFile.csv.
     */
    private static final String markFileName = "data/markFile.csv";

    /**
     * The header of studentFile.csv.
     */
    private static final String student_HEADER = "studentID,studentName,studentSchool,studentGender,studentGPA,studentYear";

    /**
     * The header of courseFile.csv.
     */
    private static final String course_HEADER = "courseID,courseName,profInCharge,vacancies,totalSeats,lectureGroups,TutorialGroups,LabGroups,MainComponents,AU,courseDepartment,courseType,lecHr,tutHr,labHr";

    /**
     * The header of professorFile.csv.
     */
    private static final String professor_HEADER = "professorID,professorName,profDepartment";

    /**
     * The header of courseRegistrationFile.csv.
     */
    private static final String courseRegistration_HEADER = "studentID,courseID,lectureGroup,tutorialGroup,labGroup";

    /**
     * The header of markFile.csv.
     */
    private static final String mark_HEADER = "studentID,courseID,courseWorkMarks,totalMark";

    /**
     * The index of the student ID in studentFile.csv.
     */
    private static final int studentIdIndex = getHeaderIndex(student_HEADER, "studentID");

    /**
     * The index of the student name in studentFile.csv.
     */
    private static final int studentNameIndex = getHeaderIndex(student_HEADER, "studentName");

    /**
     * The index of the student school in studentFile.csv.
     */
    private static final int studentSchoolIndex = getHeaderIndex(student_HEADER, "studentSchool");

    /**
     * The index of the student gender in studentFile.csv.
     */
    private static final int studentGenderIndex = getHeaderIndex(student_HEADER, "studentGender");

    /**
     * The index of the student GPA in studentFile.csv.
     */
    private static final int studentGPAIndex = getHeaderIndex(student_HEADER, "studentGPA");

    /**
     * The index of the student year in studentFile.csv.
     */
    private static final int studentYearIndex = getHeaderIndex(student_HEADER, "studentYear");

    /**
     * The index of the course ID in courseFile.csv.
     */
    private static final int courseIdIndex = getHeaderIndex(course_HEADER, "courseID");

    /**
     * The index of the course name in courseFile.csv.
     */
    private static final int courseNameIndex = getHeaderIndex(course_HEADER, "courseName");

    /**
     * The index of the professor in charge of this course in courseFile.csv.
     */
    private static final int profInChargeIndex = getHeaderIndex(course_HEADER, "profInCharge");
    /**
     * The index of course vacancies in courseFile.csv.
     */
    private static final int vacanciesIndex = getHeaderIndex(course_HEADER, "vacancies");

    /**
     * The index of course total seats in courseFile.csv.
     */
    private static final int totalSeatsIndex = getHeaderIndex(course_HEADER, "totalSeats");

    /**
     * The index of course lecture groups in courseFile.csv.
     */
    private static final int lectureGroupsIndex = getHeaderIndex(course_HEADER, "lectureGroups");

    /**
     * The index of course tutorial groups in courseFile.csv.
     */
    private static final int tutorialGroupIndex = getHeaderIndex(course_HEADER, "TutorialGroups");

    /**
     * The index of course lab group in courseFile.csv.
     */
    private static final int labGroupIndex = getHeaderIndex(course_HEADER, "labGroups");

    /**
     * The index of course main components in courseFile.csv.
     */
    private static final int mainComponentsIndex = getHeaderIndex(course_HEADER, "MainComponents");

    /**
     * The index of course AU in courseFile.csv.
     */
    private static final int AUIndex = getHeaderIndex(course_HEADER, "AU");

    /**
     * The index of course department in courseFile.csv.
     */
    private static final int courseDepartmentIndex = getHeaderIndex(course_HEADER, "courseDepartment");

    /**
     * The index of course type in courseFile.csv.
     */
    private static final int courseTypeIndex = getHeaderIndex(course_HEADER, "courseType");

    /**
     * The index of course weekly lecture hour in courseFile.csv.
     */
    private static final int lecHrIndex = getHeaderIndex(course_HEADER, "lecHr");

    /**
     * The index of course weekly tutorial hour in courseFile.csv.
     */
    private static final int tutHrIndex = getHeaderIndex(course_HEADER, "tutHr");

    /**
     * The index of course weekly lab hour in courseFile.csv.
     */
    private static final int labHrIndex = getHeaderIndex(course_HEADER, "labHr");

    /**
     * The index of professor ID in professorFile.csv.
     */
    private static final int professorIdIndex = getHeaderIndex(professor_HEADER, "professorID");

    /**
     * The index of professor name in professorFile.csv.
     */
    private static final int professorNameIndex = getHeaderIndex(professor_HEADER, "professorName");

    /**
     * The index of professor department in professorFile.csv.
     */
    private static final int professorDepartmentIndex = getHeaderIndex(professor_HEADER, "profDepartment");

    /**
     * The index of studentID in courseRegistrationFile.csv.
     */
    private static final int studentIdInRegistrationIndex = getHeaderIndex(courseRegistration_HEADER, "studentID");

    /**
     * The index of courseID in courseRegistrationFile.csv.
     */
    private static final int courseIdInRegistrationIndex = getHeaderIndex(courseRegistration_HEADER, "courseID");

    /**
     * The index of lectureGroup in courseRegistrationFile.csv.
     */
    private static final int lectureGroupInRegistrationIndex = getHeaderIndex(courseRegistration_HEADER, "lectureGroup");

    /**
     * The index of tutorialGroup in courseRegistrationFile.csv.
     */
    private static final int tutorialGroupInRegistrationIndex = getHeaderIndex(courseRegistration_HEADER, "tutorialGroup");

    /**
     * The index of labGroup in courseRegistrationFile.csv.
     */
    private static final int labGroupInRegistrationIndex = getHeaderIndex(courseRegistration_HEADER, "labGroup");


    /**
     * The index of studentID in markFile.csv.
     */
    private static final int studentIdIndexInMarks = getHeaderIndex(mark_HEADER, "studentID");

    /**
     * The index of courseID in markFile.csv.
     */
    private static final int courseIdIndexInMarks = getHeaderIndex(mark_HEADER, "courseID");

    /**
     * The index of courseWorkMark in markFile.csv..
     */
    private static final int courseWorkMarksIndex = getHeaderIndex(mark_HEADER, "courseWorkMarks");

    /**
     * The index of totalMark in markFile.csv.
     */
    private static final int totalMarkIndex = getHeaderIndex(mark_HEADER, "totalMark");


    /**
     * Write a new student information into the file.
     *
     * @param student a student to be added into the file
     */
    public static void writeStudentsIntoFile(Student student) {
        File file;
        FileWriter fileWriter = null;
        try {
            file = new File(studentFileName);
            //initialize file header if have not done so
            fileWriter = new FileWriter(studentFileName, true);
            if (file.length() == 0) {
                fileWriter.append(student_HEADER);
            }
            String tempVal = generalWriter(student, student_HEADER);
            fileWriter.append(tempVal);
        } catch (Exception e) {
            System.out.println("Error in adding a student to the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error in flushing or closing the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Load all the students' information from file into the system.
     *
     * @return an array list of all the students.
     */
    public static ArrayList<Student> loadStudents() {
        BufferedReader fileReader = null;
        ArrayList<Student> students = new ArrayList<Student>(0);
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(studentFileName));
            fileReader.readLine();//read the header to skip it
            int recentStudentID = 0;
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    recentStudentID = Math.max(recentStudentID, Integer.parseInt(tokens[studentIdIndex].substring(1, 8)));
                    Student student = new Student(tokens[studentIdIndex], tokens[studentNameIndex]);
                    student.setStudentSchool(tokens[studentSchoolIndex]);
                    student.setGender(tokens[studentGenderIndex]);
                    student.setGPA(Double.parseDouble(tokens[studentGPAIndex]));
                    student.setStudentYear(Integer.parseInt(tokens[studentYearIndex]));
                    students.add(student);
                }
            }
            // Set the recent student ID, let the newly added student have the ID onwards.
            // If there is no student in DB, set recentStudentID to 1800000 (2018 into Uni)

            UOAStudentMgr.setIdNumber(recentStudentID > 0 ? recentStudentID : 1800000);
        } catch (Exception e) {
            System.out.println("Error occurs when loading students.");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error occurs when closing the fileReader.");
                e.printStackTrace();
            }
        }
        return students;
    }

    /**
     * Write a new course information into the file.
     *
     * @param course a course to be added into file
     */
    public static void writeCourseIntoFile(Course course) {
        File file;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(courseFileName, true);
            //initialize file header if have not done so
            file = new File(courseFileName);
            if (file.length() == 0) {
                fileWriter.append(course_HEADER);
            }
            String tempVal = generalWriter(course, course_HEADER);
            fileWriter.append(tempVal);

        } catch (Exception e) {
            System.out.println("Error in adding a course to the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error occurs occurs when flushing or closing the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Load all the courses' information from file into the system.
     *
     * @return an array list of all the courses.
     */
    public static ArrayList<Course> loadCourses() {
        ArrayList<Course> courses = new ArrayList<Course>(0);
        BufferedReader fileReader = null;
        try {
            String line;
            int thisProfessor = 0;
            Professor currentProfessor = null;
            ArrayList<Professor> professors = loadProfessors();
            fileReader = new BufferedReader(new FileReader(courseFileName));
            fileReader.readLine();//read the header to skip it
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    String courseID = tokens[courseIdIndex];
                    String courseName = tokens[courseNameIndex];
                    String profInCharge = tokens[profInChargeIndex];
                    for (Professor professor : professors) {
                        if (professor.getProfID().equals(profInCharge)) {
                            currentProfessor = professor;
                            break;
                        }
                    }
                    int vacancies = Integer.parseInt(tokens[vacanciesIndex]);
                    int totalSeats = Integer.parseInt(tokens[totalSeatsIndex]);
                    int AU = Integer.parseInt(tokens[AUIndex]);
                    String courseDepartment = tokens[courseDepartmentIndex];
                    String courseType = tokens[courseTypeIndex];
                    int lecWeeklyHr = Integer.parseInt(tokens[lecHrIndex]);
                    int tutWeeklyHr = Integer.parseInt(tokens[tutHrIndex]);
                    int labWeeklyHr = Integer.parseInt(tokens[labHrIndex]);

                    String lectureGroupsString = tokens[lectureGroupsIndex];
                    ArrayList<LectureGroup> lectureGroups = new ArrayList<LectureGroup>(0);
                    String[] eachLectureGroupsString = lectureGroupsString.split(Pattern.quote(LINE_DELIMITER));

                    for (int i = 0; i < eachLectureGroupsString.length; i++) {
                        String[] thisLectureGroup = eachLectureGroupsString[i].split(EQUAL_SIGN);
                        lectureGroups.add(new LectureGroup(thisLectureGroup[0], Integer.parseInt(thisLectureGroup[1]), Integer.parseInt(thisLectureGroup[2])));
                    }

                    Course course = new Course(courseID, courseName, currentProfessor, vacancies, totalSeats, lectureGroups, AU, courseDepartment, courseType, lecWeeklyHr);

                    String tutorialGroupsString = tokens[tutorialGroupIndex];
                    ArrayList<TutorialGroup> tutorialGroups = new ArrayList<TutorialGroup>(0);

                    if (!tutorialGroupsString.equals("NULL")) {
                        String[] eachTutorialGroupsString = tutorialGroupsString.split(Pattern.quote(LINE_DELIMITER));
                        for (int i = 0; i < eachTutorialGroupsString.length; i++) {
                            String[] thisTutorialGroup = eachTutorialGroupsString[i].split(EQUAL_SIGN);
                            tutorialGroups.add(new TutorialGroup(thisTutorialGroup[0], Integer.parseInt(thisTutorialGroup[1]), Integer.parseInt(thisTutorialGroup[2])));
                        }
                    }
                    course.setTutorialGroups(tutorialGroups);
                    course.setTutWeeklyHour(tutWeeklyHr);

                    String labGroupsString = tokens[labGroupIndex];
                    ArrayList<LabGroup> labGroups = new ArrayList<LabGroup>(0);
                    if (!labGroupsString.equals("NULL")) {
                        String[] eachLabGroupString = labGroupsString.split(Pattern.quote(LINE_DELIMITER));
                        for (int i = 0; i < eachLabGroupString.length; i++) {
                            String[] thisLabGroup = eachLabGroupString[i].split(EQUAL_SIGN);
                            labGroups.add(new LabGroup(thisLabGroup[0], Integer.parseInt(thisLabGroup[1]), Integer.parseInt(thisLabGroup[2])));
                        }
                    }
                    course.setLabGroups(labGroups);
                    course.setLabWeeklyHour(labWeeklyHr);

                    String mainComponentsString = tokens[mainComponentsIndex];
                    ArrayList<MainComponent> mainComponents = new ArrayList<MainComponent>(0);
                    if (!mainComponentsString.equals("NULL")) {
                        String[] eachMainComponentsString = mainComponentsString.split(Pattern.quote(LINE_DELIMITER));
                        for (int i = 0; i < eachMainComponentsString.length; i++) {
                            String[] thisMainComponent = eachMainComponentsString[i].split(EQUAL_SIGN);
                            ArrayList<SubComponent> subComponents = new ArrayList<SubComponent>(0);
                            if (thisMainComponent.length > 2) {
                                String[] subComponentsString = thisMainComponent[2].split(SLASH);
                                for (int j = 0; j < subComponentsString.length; j++) {
                                    String[] thisSubComponent = subComponentsString[j].split(HYPHEN);
                                    subComponents.add(new SubComponent(thisSubComponent[0], Integer.parseInt(thisSubComponent[1])));
                                }
                            }

                            mainComponents.add(new MainComponent(thisMainComponent[0], Integer.parseInt(thisMainComponent[1]), subComponents));
                        }
                    }
                    course.setMainComponents(mainComponents);
                    course.setVacancies(vacancies);
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            System.out.println("Error happens when loading courses.");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error happens when closing the fileReader.");
                e.printStackTrace();
            }
        }
        return courses;
    }

    /**
     * Backs up all the changes of courses made into the file.
     *
     * @param courses courses to be backed up
     */
    public static void backUpCourse(List<Course> courses) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(courseFileName);


            fileWriter.append(course_HEADER);
            //fileWriter.append(NEW_LINE_SEPARATOR);

            for (Course course : courses) {
                String tempVal = generalWriter(course, course_HEADER);
                fileWriter.append(tempVal);
            }

        } catch (Exception e) {
            System.out.println("Error in backing up courses.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error occurs when flushing or closing the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Load all the professors' information from file into the system.
     *
     * @return an array list of all the professors.
     */
    public static ArrayList<Professor> loadProfessors() {
        BufferedReader fileReader = null;
        ArrayList<Professor> professors = new ArrayList<Professor>(0);
        try {
            String line;
            fileReader = new BufferedReader(new FileReader(professorFileName));
            //read the header to skip it
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    Professor professor = new Professor(tokens[professorIdIndex], tokens[professorNameIndex]);
                    professor.setProfDepartment(tokens[professorDepartmentIndex]);
                    professors.add(professor);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurs when loading professors.");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error occurs when closing the fileReader.");
                e.printStackTrace();
            }
        }
        return professors;
    }

    /**
     * Writes a new course registration record into the file.
     *
     * @param courseRegistration courseRegistration to be added into file
     */
    public static void writeCourseRegistrationIntoFile(CourseRegistration courseRegistration) {
        File file;
        FileWriter fileWriter = null;
        try {
            file = new File(courseRegistrationFileName);
            //initialize file header if have not done so
            fileWriter = new FileWriter(courseRegistrationFileName, true);
            if (file.length() == 0) {
                fileWriter.append(courseRegistration_HEADER);
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            String tempVal = generalWriter(courseRegistration, courseRegistration_HEADER);
            fileWriter.append(tempVal);
        } catch (Exception e) {
            System.out.println("Error in adding a course registration to the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error occurs when flushing or closing the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Load all the course registration records from file into the system.
     *
     * @return an array list of all the course registration records.
     */
    public static ArrayList<CourseRegistration> loadCourseRegistration() {
        BufferedReader fileReader = null;
        ArrayList<CourseRegistration> courseRegistrations = new ArrayList<CourseRegistration>(0);
        try {
            String line;
            Student currentStudent = null;
            Course currentCourse = null;
            ArrayList<Student> students = loadStudents();

            fileReader = new BufferedReader(new FileReader(courseRegistrationFileName));
            fileReader.readLine();//read the header to skip it

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    String studentID = tokens[studentIdInRegistrationIndex];

                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            currentStudent = student;
                            break;
                        }
                    }
                    String courseID = tokens[courseIdInRegistrationIndex];
                    ArrayList<Course> courses = loadCourses();
                    for (Course course : courses) {
                        if (course.getCourseID().equals(courseID)) {
                            currentCourse = course;
                            break;
                        }
                    }
                    courseRegistrations.add(new CourseRegistration(currentStudent, currentCourse, tokens[lectureGroupInRegistrationIndex], tokens[tutorialGroupInRegistrationIndex], tokens[labGroupInRegistrationIndex]));
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurs when loading course registrations.");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error occurs when closing the fileReader.");
                e.printStackTrace();
            }
        }
        return courseRegistrations;
    }

    /**
     * Writes a new student mark record into the file.
     *
     * @param mark mark to be updated into the file
     */
    public static void updateStudentMarks(Mark mark) {
        File file;
        FileWriter fileWriter = null;
        try {
            file = new File(markFileName);
            //initialize file header if have not done so
            fileWriter = new FileWriter(markFileName, true);
            if (file.length() == 0) {
                fileWriter.append(mark_HEADER);
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            String tempVal = generalWriter(mark, mark_HEADER);
            fileWriter.append(tempVal);

        } catch (Exception e) {
            System.out.println("Error in adding a mark to the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error occurs in flushing or closing the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Load all the student mark records from file into the system.
     *
     * @return an array list of all the student mark records.
     */
    public static ArrayList<Mark> loadStudentMarks() {
        BufferedReader fileReader = null;
        ArrayList<Mark> marks = new ArrayList<Mark>(0);
        try {
            String line;

            ArrayList<Student> students = loadStudents();
            ArrayList<Course> courses = loadCourses();

            fileReader = new BufferedReader(new FileReader(markFileName));
            //read the header to skip it
            fileReader.readLine();
            while ((line = fileReader.readLine()) != null) {
                Student currentStudent = null;
                Course currentCourse = null;

                HashMap<CourseworkComponent, Double> courseWorkMarks = new HashMap<CourseworkComponent, Double>(0);
                String[] thisCourseWorkMark;

                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    String studentID = tokens[studentIdIndexInMarks];

                    for (Student student : students) {
                        if (student.getStudentID().equals(studentID)) {
                            currentStudent = student;
                            break;
                        }
                    }

                    String courseID = tokens[courseIdIndexInMarks];

                    for (Course course : courses) {
                        if (course.getCourseID().equals(courseID)) {
                            currentCourse = course;
                            break;
                        }
                    }

                    String courseWorkMarksString = tokens[courseWorkMarksIndex];
//                    System.out.println("From File, This course work components is: " + courseWorkMarksString);

                    String[] eachCourseWorkMark = courseWorkMarksString.split(Pattern.quote(LINE_DELIMITER));
                    // Get all the main components
//                    System.out.println("From the file: " + eachCourseWorkMark.length + " main components.");

                    for (int i = 0; i < eachCourseWorkMark.length; i++) {
                        thisCourseWorkMark = eachCourseWorkMark[i].split(EQUAL_SIGN);

                        ArrayList<SubComponent> subComponents = new ArrayList<SubComponent>(0);
                        HashMap<SubComponent, Double> subComponentMarks = new HashMap<SubComponent, Double>();
                        for (int j = 3; j < thisCourseWorkMark.length; j++) {
                            if (thisCourseWorkMark[3].equals("")) {
                                break;
                            }
                            String[] thisSubComponent = thisCourseWorkMark[j].split(SLASH);
                            subComponents.add(new SubComponent(thisSubComponent[0], Integer.parseInt(thisSubComponent[1])));
                            subComponentMarks.put(new SubComponent(thisSubComponent[0], Integer.parseInt(thisSubComponent[1])), Double.parseDouble(thisSubComponent[2]));
                        }

                        courseWorkMarks.put(new MainComponent(thisCourseWorkMark[0], Integer.parseInt(thisCourseWorkMark[1]), subComponents), Double.parseDouble(thisCourseWorkMark[2]));
                        // Put sub component
                        for (HashMap.Entry<SubComponent, Double> entry : subComponentMarks.entrySet()) {
                            SubComponent subComponent = entry.getKey();
                            Double subComponentResult = entry.getValue();
                            courseWorkMarks.put(subComponent, subComponentResult);
                        }
                    }
                    Double totalMark = Double.parseDouble(tokens[totalMarkIndex]);
                    Mark mark = new Mark(currentStudent, currentCourse, courseWorkMarks, totalMark);
                    marks.add(mark);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurs when loading student marks.");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error occurs when closing the fileReader.");
                e.printStackTrace();
            }
        }
        return marks;
    }

    /**
     * Backs up all the changes of student mark records made into the file.
     *
     * @param marks marks to be backed up into file
     */
    public static void backUpMarks(List<Mark> marks) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(markFileName);

            fileWriter.append(mark_HEADER);

            for (Mark mark : marks) {
                String tempVal = generalWriter(mark, mark_HEADER);
                fileWriter.append(tempVal);
            }
        } catch (Exception e) {
            System.out.println("Error in adding a mark to the file.");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error occurs in flushing or closing the file.");
                e.printStackTrace();
            }
        }
    }

    /**
     * returns the index of a specified column header within a specified file header
     * @param fileHeader
     * @param columnHeader
     * @return the index of column header within fileHeader
     */
    private static int getHeaderIndex(String fileHeader, String columnHeader){

        fileHeader = fileHeader.toUpperCase();
        columnHeader = columnHeader.toUpperCase();
        List<String> headerSplit = Arrays.asList(fileHeader.split(COMMA_DELIMITER));
        int index = headerSplit.indexOf(columnHeader);
        return index;
    }

    /**
     * String concatenation of all group information.
     * @param methodName getter for the group object stored within the obj.
     * @param obj course object which will be invoked with the group
     *            getter method.
     * @return concatenated string - information of the group belonging
     *         to the object.
     */
    private static String groupWriter(String methodName, Object obj) {
        String tempVal = "";
        try {
            Method method = obj.getClass().getMethod(methodName);
            ArrayList<Group> groups = (ArrayList<Group>) method.invoke(obj);
            if (groups.size() != 0) {
                int index = 0;
                for (Group group : groups) {
                    tempVal += group.getGroupName();
                    tempVal += EQUAL_SIGN;
                    tempVal += String.valueOf(group.getAvailableVacancies());
                    tempVal += EQUAL_SIGN;
                    tempVal += String.valueOf(group.getTotalSeats());
                    tempVal += COMMA_DELIMITER;
                    index++;
                    if (index != groups.size()) {
                        if (tempVal.endsWith(COMMA_DELIMITER)){
                            tempVal = tempVal.substring(0, tempVal.length()-1);
                        }
                        tempVal += LINE_DELIMITER;
                    }
                }
            } else {
                tempVal = "NULL" + COMMA_DELIMITER;
            }
        } catch (Exception e){
            System.out.println("Error with invoking method");
        }
        return tempVal;
    }

    /**
     * String concatenation of all Coursework component marks.
     * @param methodName getter for the coursework component stored
     *                   within the student object.
     * @param obj course object which will be invoked with the group
     *            getter method.
     * @return concatenated string - information of all the marks for
     *          the student.
     */
    private static String markWriter(String methodName, Mark obj){
        String tempVal = "";
        try {
            Method method = obj.getClass().getMethod(methodName);
            HashMap<CourseworkComponent, Double> courseworkMarks = (HashMap<CourseworkComponent, Double>) method.invoke(obj);
            if (!courseworkMarks.isEmpty()) {
                int index = 0;
                for (HashMap.Entry<CourseworkComponent, Double> entry : courseworkMarks.entrySet()) {
                    CourseworkComponent key = entry.getKey();
                    Double value = entry.getValue();
                    if (key instanceof MainComponent) {
                        tempVal += key.getComponentName();
                        tempVal += EQUAL_SIGN;
                        tempVal += String.valueOf(key.getComponentWeight());
                        tempVal += EQUAL_SIGN;
                        tempVal += String.valueOf(value);
                        tempVal += EQUAL_SIGN;
                        ArrayList<SubComponent> subComponents = ((MainComponent) key).getSubComponents();
                        int subComponent_index = 0;
                        for (SubComponent subComponent : subComponents) {
                            tempVal += subComponent.getComponentName();
                            tempVal += SLASH;
                            tempVal += String.valueOf(subComponent.getComponentWeight());
                            tempVal += SLASH;
                            String subComponentName = subComponent.getComponentName();
                            double subComponentMark = 0d;
                            for (HashMap.Entry<CourseworkComponent, Double> subEntry : obj.getCourseWorkMarks().entrySet()) {
                                CourseworkComponent subKey = subEntry.getKey();
                                Double subValue = subEntry.getValue();
                                if (subKey instanceof SubComponent && subKey.getComponentName().equals(subComponentName)) {
                                    subComponentMark = subValue;
                                    break;
                                }
                            }
                            tempVal += String.valueOf(subComponentMark);
                            subComponent_index++;
                            if (subComponent_index != subComponents.size()) {
                                tempVal += EQUAL_SIGN;
                            }
                        }
                    }
                    index++;
                    if (index != courseworkMarks.size() && (key instanceof MainComponent)) {
                        tempVal += LINE_DELIMITER;
                    }
                }
            } else {
                tempVal += "NULL";
            }
        } catch (Exception e){
            System.out.println("Error with invoking method");
        }
        tempVal += COMMA_DELIMITER;
        return tempVal;
    }


    /**
     * String concatenation of all component object information.
     * @param methodName getter for the Component (Sub & Main)
     * @param obj course object - which will contain the component information.
     * @return a contatening string - information of the component information.
     */
    private static String componentWriter(String methodName, Object obj) {
        String tempVal = "";
        try {
            Method method = obj.getClass().getMethod(methodName);
            ArrayList<MainComponent> mainComponents = (ArrayList<MainComponent>) method.invoke(obj);
            if (mainComponents.size() != 0) {
                int index = 0;
                for (MainComponent component : mainComponents) {
                    tempVal += component.getComponentName();
                    tempVal += EQUAL_SIGN;
                    tempVal += String.valueOf(component.getComponentWeight());
                    tempVal += EQUAL_SIGN;
                    ArrayList<SubComponent> subComponents = component.getSubComponents();
                    int inner_index = 0;
                    for (SubComponent subComponent : subComponents) {
                        tempVal += subComponent.getComponentName();
                        tempVal += HYPHEN;
                        tempVal += String.valueOf(subComponent.getComponentWeight());
                        inner_index++;
                        if (inner_index != subComponents.size()) {
                            tempVal += SLASH;
                        }
                    }
                    index++;
                    if (index != mainComponents.size()) {
                        if (tempVal.endsWith(COMMA_DELIMITER)){
                            tempVal = tempVal.substring(0, tempVal.length()-1);
                        }
                        tempVal += LINE_DELIMITER;
                    }
                }
            } else {
                tempVal += "NULL";
            }
        }catch (Exception e){}
        tempVal += COMMA_DELIMITER;
        return tempVal;
    }

    /**
     * Helper method to help write all obj information in to the csv file.
     * @param obj Object which is being written into the csv file
     * @param objectHeader The fields of the Object which will be written into
     *                     csv file.
     * @return a concatenated string which contains all the information of the object
     *         written in the csv file format.
     *
     */
    private static String generalWriter(Object obj, String objectHeader) {
        String resultString= "";
        resultString += NEW_LINE_SEPARATOR;
        for (String string1: objectHeader.split(COMMA_DELIMITER)){
            try {
                String fieldName = string1.substring(0, 1).toUpperCase() + string1.substring(1);
                Method method = obj.getClass().getMethod("get" + fieldName);
                String methodName = method.getName();

                if ((methodName.equals("getLabGroups")) || (methodName.equals("getLectureGroups")) || (methodName.equals("getTutorialGroups"))){
                    resultString += groupWriter(methodName, obj);
                } else if (methodName.equals("getMainComponents")){
                    resultString += componentWriter(methodName, obj);
                } else if (methodName.equals("getProfInCharge")){
                    Professor obj2 = (Professor) method.invoke(obj);
                    resultString += obj2.getProfID();
                    resultString += COMMA_DELIMITER;
                } else if (methodName.equals("getCourseWorkMarks")){
                    resultString += markWriter(methodName, (Mark) obj);
                } else {
                    resultString += String.valueOf(method.invoke(obj));
                    resultString += COMMA_DELIMITER;
                }
            } catch (Exception e){}
        }
        resultString = resultString.substring(0, resultString.length()-1);
        return resultString;
    }
}
