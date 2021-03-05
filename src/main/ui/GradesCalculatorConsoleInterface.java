package ui;

import model.Grade;
import model.GradesCalculator;

import java.util.List;
import java.util.Scanner;

/*
 * Console interface/application for GradesCalculator
 */

/* TODO: Citation - code from TellerApp modified for GradesCalculatorConsoleInterface
         (https://github.students.cs.ubc.ca/CPSC210/TellerApp) */

public class GradesCalculatorConsoleInterface {

    private GradesCalculator gradesCalculator;
    private Scanner input;

    /*
     * EFFECTS: Calls runConsoleInterface() to run the program
     */
    public GradesCalculatorConsoleInterface() {
        runConsoleInterface();
    }

    /*
     * EFFECTS: Loops and waits for user input until user quits program.
     */
    private void runConsoleInterface() {
        boolean continueInterface = true;
        String operation;

        initialize();

        while (continueInterface) {
            printMenu();
            operation = input.nextLine().toLowerCase();
            if (operation.equals("q")) {
                continueInterface = false;
            } else {
                processOperation(operation);
            }
        }
        System.out.println("Thank you, see you next time!");
    }

    /*
     * EFFECTS: Instantiates the private fields in the class
     */
    private void initialize() {
        input = new Scanner(System.in);
        System.out.println("What's your name?");
        gradesCalculator = new GradesCalculator(input.nextLine());
    }

    /*
     * EFFECTS: Prints out console menu for the user
     */
    private void printMenu() {
        System.out.println("Please choose one of the following: \n"
                + "a  - Add a new grade \n"
                + "r  - remove grade \n"
                + "va - (view all) View all grades \n"
                + "vc - (view class) View grades for a specific class \n"
                + "c  - View the average for a specific class \n"
                + "co - (calculate overall) Calculate overall average \n"
                + "q  - Quit program");
    }

    /*
     * EFFECTS: Calls a method according to the user's input and prints out the output
     */
    private void processOperation(String operation) {
        String output = "";

        switch (operation) {
            case ("a"):
                output = addGrade();
                break;
            case ("r"):
                output = removeGrade();
                break;
            case ("va"):
                output = viewAll();
                break;
            case ("vc"):
                output = viewClass();
                break;
            case ("c"):
                output = oneAverage();
                break;
            case ("co"):
                output = overallAverage();
                break;
        }
        System.out.println(output);
        pause();
    }

    /*
     * EFFECTS: Lets the console and program wait for 1 second before continuing
     */
    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /*
     * EFFECTS: Asks user for a grade, assignment name, and class name.
     *          Creates a Grade object given this information and adds it to gradesCalculator.
     *          Returns the Grade object's string value to output back to the user.
     */
    private String addGrade() {
        Grade grade;
        double mark;
        String assignment;
        String className;

        System.out.println("Please enter a grade.");
        mark = Double.parseDouble(input.nextLine());
        System.out.println("Please enter the assignment name.");
        assignment = input.nextLine();
        System.out.println("Please enter the class name.");
        className = input.nextLine();

        grade = new Grade(mark, assignment, className);

        gradesCalculator.addGrade(grade);

        return grade.toString();
    }

    /*
     * EFFECTS: Asks user for an assignment name and class name.
     *          Calls gradesCalculator.removeGrade() with the
     *          information given and returns a String for the user's feedback.
     */
    private String removeGrade() {
        String assignment;
        String className;

        System.out.println("Please enter the assignment name.");
        assignment = input.nextLine();
        System.out.println("Please enter the class name.");
        className = input.nextLine();

        gradesCalculator.removeGrade(assignment, className);

        return "Removed " + assignment;
    }

    /*
     * EFFECTS: Returns the String value of all the Grade objects in grades.
     */
    private String viewAll() {
        List<Grade> grades = gradesCalculator.getGrades();
        String output = "";

        for (Grade grade : grades) {
            output += grade.toString() + "\n";
        }
        return output;
    }

    /*
     * EFFECTS: Returns the String value of all the Grade objects in grades whose className is the user inputted class.
     */
    private String viewClass() {
        String command;
        String output = "";
        System.out.println("Please insert a class.");
        command = input.nextLine();
        List<Grade> grades = gradesCalculator.returnClassGrades(command);

        for (Grade grade : grades) {
            output += grade.toString() + "\n";
        }

        return output;
    }

    /*
     * EFFECTS: Returns the average of the class the user inputted.
     */
    private String oneAverage() {
        String command;
        System.out.println("Please insert a class.");
        command = input.nextLine();
        double output = gradesCalculator.calculateClassAverage(command);

        return Double.toString(output);
    }

    /*
     * EFFECTS: Returns the overall average (average of every class' average).
     */
    private String overallAverage() {
        return Double.toString(gradesCalculator.calculateOverallAverage());
    }

}
