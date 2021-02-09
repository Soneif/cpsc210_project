package ui;

import model.Grade;
import model.GradesCalculator;

import java.util.List;
import java.util.Scanner;

// !!! no comments yet

public class GradesCalculatorConsoleInterface {

    private GradesCalculator gradesCalculator;
    private Scanner input;

    public GradesCalculatorConsoleInterface() {
        runConsoleInterface();
    }

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

    private void initialize() {
        gradesCalculator = new GradesCalculator();
        input = new Scanner(System.in);
    }

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

    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

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

    private String viewAll() {
        List<Grade> grades = gradesCalculator.getGrades();
        String output = "";

        for (Grade grade : grades) {
            output += grade.toString() + "\n";
        }
        return output;
    }

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

    private String oneAverage() {
        String command;
        System.out.println("Please insert a class.");
        command = input.nextLine();
        double output = gradesCalculator.calculateClassAverage(command);

        return Double.toString(output);
    }

    private String overallAverage() {
        return Double.toString(gradesCalculator.calculateOverallAverage());
    }

}
