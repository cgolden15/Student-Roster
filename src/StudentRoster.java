import java.util.Scanner;
import java.util.ArrayList;


public class StudentRoster {

    public static void main(String[] args) {

        ArrayList<String> studentList = new ArrayList<>();
        ArrayList<Double> gradeList = new ArrayList<>();
        initializeRoster(studentList, gradeList); // default students in roster


        System.out.println("Welcome to your student roster!");

        boolean run = true;
        while (run) {
            String choice = menu();

            switch (choice) {
                case "1":
                    printRoster(studentList, gradeList);
                    continueDelay();
                    break;
                case "2":
                    addStudent(studentList, gradeList);
                    continueDelay();
                    break;
                case "3":
                    updateStudent(studentList, gradeList);
                    continueDelay();
                    break;
                case "4":
                    removeStudent(studentList, gradeList);
                    continueDelay();
                    break;
                case "5":
                    calculateGrade(studentList, gradeList);
                    continueDelay();
                    break;
                case "6":
                    classAverage(gradeList);
                    continueDelay();
                    break;
                case "7":
                    searchStudent(studentList, gradeList);
                    continueDelay();
                    break;
                default:
                    System.out.println("Thats not an option, try again!");
                case "-1":
                    run = false;
                    break;
            }
        }

    }

    /**
     *
     * Fills the studentList and gradeList array's with the default 5 students
     *
     * @param studentList
     * @param gradeList
     */
    public static void initializeRoster(ArrayList<String> studentList, ArrayList<Double> gradeList) {
        studentList.add("Joe");
        gradeList.add(98.5);
        studentList.add("John");
        gradeList.add(99.2);
        studentList.add("Steve");
        gradeList.add(78.9);
        studentList.add("Mike");
        gradeList.add(87.5);
        studentList.add("Luke");
        gradeList.add(88.7);
    }

    /**
     * Prompts the user to select from the available functions.
     * @return - returns the selected menu option for case handling
     */
    public static String menu() {
        System.out.println("\nPlease select an option from the following options by responding with its line number:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                1. Display all student’s name and grade
                2. Add a new student and grade
                3. Update an existing student’s grade
                4. Remove a student and the student’s grade
                5. Calculate the student’s letter grade
                6. Calculate the class average
                7. Search for a student
                -1. Exit.
                """);

        return scanner.next();
    }

    public static void continueDelay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPress enter to continue");
        scanner.nextLine();
    }

    /**
     *
     * Loops through all names and grades in the parallel arrays "studentList" and "gradeList".
     * Prints a formatted roster of all students and their respective grades
     *
     * @param studentList array holding the list of student names
     * @param gradeList array holding the list of student grades
     */
    public static void printRoster(ArrayList<String> studentList, ArrayList<Double> gradeList) {
        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i) + "  - Grade: " + gradeList.get(i));
        }
    }

    /**
     *
     * Creates an array list with a new students name and grade to add to the studentList and gradeList array's.
     * Does not allow duplicate names,
     *
     * @param gradeList
     * @param studentList
     */
    public static void addStudent(ArrayList<String> studentList, ArrayList<Double> gradeList) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the student: ");
        String studentName = scanner.next();
        if (studentList.contains(studentName)) {
            System.out.println("Student already exists!");
            return;
        }
        System.out.println("Please enter the grade of the student: ");
        Double grade = scanner.nextDouble();
        studentList.add(studentName);
        gradeList.add(grade);
        System.out.println("Student added!");
    }

    /**
     *
     * Indexes the studentList array, and checks to see if provided student name exists. If it does, prompt to update
     * the grade. If student doesn't exist, return.
     *
     * @param studentList
     * @param gradeList
     */
    public static void updateStudent(ArrayList<String> studentList, ArrayList<Double> gradeList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the student you would like to update: ");
        String studentName = scanner.next();

        int index = studentList.indexOf(studentName);
        if(index != -1) {
            System.out.println("Please enter " + studentName + "'s new grade: ");
            Double grade = scanner.nextDouble();
            gradeList.set(index, grade);
            System.out.println("Student updated!");
        } else {
            System.out.println("Student not found! Try again.");
        }

    }

    /**
     *
     * Removes a student and their grade from the studentList and gradeList array.
     *
     * @param studentList
     * @param gradeList
     */
    public static void removeStudent(ArrayList<String> studentList, ArrayList<Double> gradeList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the student you would like to remove: ");
        String studentName = scanner.next();
        int index = studentList.indexOf(studentName);
        if(index != -1) {
            studentList.remove(index);
            gradeList.remove(index);
            System.out.println("Student successfully removed!");
        } else {
            System.out.println("Student not found! Try again.");
        }
    }

    /**
     *
     * Searches for the provided student in the studentList and gets their index number. Uses their index number to fetch
     * the corresponding grade, and calculate their letter grade.
     *
     * @param studentList
     * @param gradeList
     */
    private static void calculateGrade(ArrayList<String> studentList, ArrayList<Double> gradeList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a students name: ");
        String studentName = scanner.next();
        int index = studentList.indexOf(studentName);
        if(index != -1) {
            Double percent = gradeList.get(index);
            if (percent >= 90) { System.out.println("Student has an A, or a " + percent + "%"); }
            if (percent >= 80 && percent < 90) { System.out.println("Student has a B, or a " + percent + "%"); }
            if (percent >= 70 && percent < 80) { System.out.println("Student has a C, or a " + percent + "%"); }
            if (percent >= 60 && percent < 70) { System.out.println("Student has a D, or a " + percent + "%"); }
            if (percent < 60) { System.out.println("Student has a F, or a " + percent + "%"); }
        } else {
            System.out.println("Student not found! Try again.");
        }
    }

    /**
     *
     * Pulls all grades from gradeList and calculates the class average with them.
     *
     * @param gradeList
     */
    private static void classAverage(ArrayList<Double> gradeList) {
        Double totalgrade = 0.0;
        for (int i = 0; i < gradeList.size(); i++) {
            totalgrade += gradeList.get(i);
        }

        double average = totalgrade / gradeList.size();
        System.out.println("Average grade: " + average + "%");
    }

    /**
     *
     * Searches for a student by name, and returns their name and grade if valid.
     *
     * @param studentList
     * @param gradeList
     */
    private static void searchStudent(ArrayList<String> studentList, ArrayList<Double> gradeList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of the student: ");
        String studentName = scanner.next();
        int index = studentList.indexOf(studentName);
        if(index != -1) {
            System.out.println(studentName + "has a grade of " + gradeList.get(index) + "%");
        } else {
            System.out.println("Student not found! Try again.");
        }
    }
}