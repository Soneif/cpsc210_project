package ui;

import exceptions.EmptyClassListException;
import exceptions.InvalidClassNameException;
import exceptions.NegativeMarkException;
import exceptions.PreExistingGradeException;
import model.Grade;
import model.GradesCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/*
 * Represents the panel in which the user inputs information (through buttons and text fields).
 */

public class InputPanel extends JPanel implements ActionListener {
    private static final Dimension FIELD_SIZE = new Dimension(200, 24);

    private GradesCalculator gradesCalculator;
    private OutputPanel outputPanel;

    private JLabel gradeLabel;
    private JLabel classLabel;
    private JLabel assignmentLabel;

    private JTextField gradeField;
    private JTextField classField;
    private JTextField assignmentField;

    private JButton addButton;
    private JButton removeButton;
    private JButton viewClassButton;
    private JButton viewClassAverageButton;
    private JButton viewOverallAverageButton;
    private JButton saveLoadWindowButton;
    private JButton generateGraphButton;

    // EFFECTS: Initializes the sub panels in the input panel
    public InputPanel(OutputPanel outputPanel) {
        this.outputPanel = outputPanel;
        gradesCalculator = new GradesCalculator();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initializeComponents();

        JPanel fieldPanel = new JPanel();
        fieldPanel.add(gradeLabel);
        fieldPanel.add(gradeField);
        fieldPanel.add(assignmentLabel);
        fieldPanel.add(assignmentField);
        fieldPanel.add(classLabel);
        fieldPanel.add(classField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewClassButton);
        buttonPanel.add(viewClassAverageButton);
        buttonPanel.add(viewOverallAverageButton);
        buttonPanel.add(saveLoadWindowButton);
        buttonPanel.add(generateGraphButton);

        this.add(fieldPanel);
        this.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: Initializes components for the sub panels
    private void initializeComponents() {
        gradeLabel = new JLabel("Grade: ");
        classLabel = new JLabel("Class: ");
        assignmentLabel = new JLabel("Assignment: ");

        gradeField = new JTextField();
        classField = new JTextField();
        assignmentField = new JTextField();

        gradeField.setPreferredSize(FIELD_SIZE);
        classField.setPreferredSize(FIELD_SIZE);
        assignmentField.setPreferredSize(FIELD_SIZE);

        initializeButtons();
    }

    // MODIFIES: this
    // EFFECTS: Initializes buttons for the sub panels
    private void initializeButtons() {
        addButton = new JButton("Add grade");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);

        removeButton = new JButton("Remove grade");
        removeButton.setActionCommand("remove");
        removeButton.addActionListener(this);

        viewClassButton = new JButton("View all grades from a class");
        viewClassButton.setActionCommand("class grades");
        viewClassButton.addActionListener(this);

        viewClassAverageButton = new JButton("View a class average");
        viewClassAverageButton.setActionCommand("class average");
        viewClassAverageButton.addActionListener(this);

        viewOverallAverageButton = new JButton("View the overall average");
        viewOverallAverageButton.setActionCommand("overall average");
        viewOverallAverageButton.addActionListener(this);

        saveLoadWindowButton = new JButton("Open the save/load menu");
        saveLoadWindowButton.setActionCommand("open menu");
        saveLoadWindowButton.addActionListener(this);

        generateGraphButton = new JButton("Generate graph from class marks");
        generateGraphButton.setActionCommand("generate graph");
        generateGraphButton.addActionListener(this);
    }

    // EFFECTS: When a button is pressed, do what the button says
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String output = "";
        if (command.equals("add")) {
            output = addGrade();
        } else if (command.equals("remove")) {
            output = removeGrade();
        } else if (command.equals("class grades")) {
            output = viewClass();
        } else if (command.equals("class average")) {
            output = oneAverage();
        } else if (command.equals("overall average")) {
            output = overallAverage();
        } else if (command.equals("generate graph")) {
            output = generateGraph();
        } else {
            new SaveLoadFrame(gradesCalculator, outputPanel);
        }
        printOperation(output);
    }

    // EFFECTS: Prints out status message for operations
    private void printOperation(String output) {
        String original = outputPanel.getActionLog();
        outputPanel.setActionLog(original + "\n" + output);
        outputPanel.setCurrentStatus("Your Grades: " + "\n" + gradesCalculator.toString());
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

        mark = Double.parseDouble(gradeField.getText());
        assignment = assignmentField.getText();
        className = classField.getText();

        grade = new Grade(mark, assignment, className);

        try {
            gradesCalculator.addGrade(grade);
        } catch (NegativeMarkException e) {
            return "Please enter a non-negative mark!";
        } catch (PreExistingGradeException e) {
            return "Please enter a grade that is not already in the system!";
        }

        return "Added " + grade.getAssignmentName();
    }

    /*
     * EFFECTS: Asks user for an assignment name and class name.
     *          Calls gradesCalculator.removeGrade() with the
     *          information given and returns a String for the user's feedback.
     */
    private String removeGrade() {
        String assignment;
        String className;

        assignment = assignmentField.getText();
        className = classField.getText();

        gradesCalculator.removeGrade(assignment, className);

        return "Removed " + assignment;
    }

    /*
     * EFFECTS: Returns the String value of all the Grade objects in grades whose className is the user inputted class.
     */
    private String viewClass() {
        String className = classField.getText();
        List<Grade> grades = null;
        try {
            grades = gradesCalculator.returnClassGrades(className);
        } catch (InvalidClassNameException e) {
            return "Please enter a class that exists in the system.";
        }

        return className + " grades: \n" + grades.toString();
    }

    /*
     * EFFECTS: Returns the average of the class the user inputted.
     */
    private String oneAverage() {
        String className = classField.getText();
        double output = 0;
        try {
            output = gradesCalculator.calculateClassAverage(className);
        } catch (InvalidClassNameException e) {
            return "Please enter a class that is already in the system.";
        }

        return className + " average: " + output;
    }

    /*
     * EFFECTS: Returns the overall average (average of every class' average).
     */
    private String overallAverage() {
        String average = null;
        try {
            average = "Overall average: " + gradesCalculator.calculateOverallAverage();
        } catch (EmptyClassListException e) {
            return "Please enter some grades first before trying to calculate an overall average!";
        }
        return average;
    }

    // EFFECTS: generates a graph and makes a new JFrame to place the graph in, returns output message
    private String generateGraph() {
        String className = classField.getText();
        List<Grade> grades = null;
        try {
            grades = gradesCalculator.returnClassGrades(className);
        } catch (InvalidClassNameException e) {
            return "Please enter a class that exists in the system.";
        }

        new HistogramFrame(grades);

        return "Generated a graph for " + className + "'s marks.";
    }

}
