package ui;

import model.Grade;
import model.GradesCalculator;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String PATH = "./data/savedData.json";

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
        jsonReader = new JsonReader(PATH);
        jsonWriter = new JsonWriter(PATH);
        gradesCalculator = new GradesCalculator();
    }

    /*
     * EFFECTS: Prints out console menu for the user
     */
    private void printMenu() {
        System.out.println("Please choose one of the following: \n"
                + "a  - Add a new grade \n"
                + "r  - Remove grade \n"
                + "va - (view all) View all grades \n"
                + "vc - (view class) View grades for a specific class \n"
                + "c  - View the average for a specific class \n"
                + "co - (calculate overall) Calculate overall average \n"
                + "s  - Save grades to file \n"
                + "l  - Load grades from file \n"
                + "q  - Quit program");
    }

    /*
     * EFFECTS: Calls a method according to the user's input and prints out the output
     */
    private void processOperation(String operation) {
        String output = "";

        if (operation.equals("a")) {
            output = addGrade();
        } else if (operation.equals("r")) {
            output = removeGrade();
        } else if (operation.equals("va")) {
            output = viewAll();
        } else if (operation.equals("vc")) {
            output = viewClass();
        } else if (operation.equals("c")) {
            output = oneAverage();
        } else if (operation.equals("co")) {
            output = overallAverage();
        } else if (operation.equals("s")) {
            output = saveGrades();
        } else if (operation.equals("l")) {
            output = loadGrades();
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

    // EFFECTS: saves the grades(calculator) to file
    private String saveGrades() {
        if (gradesCalculator.getUser().equals("")) {
            System.out.println("What's your name?");
            gradesCalculator.setUser(input.nextLine());
        }

        try {
            jsonWriter.open();
            jsonWriter.write(gradesCalculator);
            jsonWriter.close();
            return "Saved " + gradesCalculator.getUser() + " to " + PATH;
        } catch (FileNotFoundException e) {
            return "Unable to write to file: " + PATH;
        }
    }

    // MODIFIES: this
    // EFFECTS: loads grades(calculator) from file
    private String loadGrades() {
        try {
            gradesCalculator = jsonReader.read();
            return "Loaded " + gradesCalculator.getUser() + " from " + PATH;
        } catch (IOException e) {
            return "Unable to read from file: " + PATH;
        }
    }

}
