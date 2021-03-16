package ui;

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

// TODO:
//  - Text fields for grade, class, assignment
//  - Buttons for the different operations:
//      - Add grade (all text fields)
//      - Remove grade (class and assignment text fields)
//      - View all grades (no text fields)
//      - View a class' grades (no text fields)
//      - View the class average (no text fields)
//      - View the overall average (no text fields)
//      - Save from file (no text field, prompts SaveLoadWindow)
//      - Load from file (no text field, prompts SaveLoadWindow)
//      - Quit program (no text fields)
//   When the text fields are not in use, make them non-editable by the user.
//   (This also means, they have to press a button first and then enter the corresponding inputs if required)

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

        gradesCalculator.addGrade(grade);

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
        List<Grade> grades = gradesCalculator.returnClassGrades(className);

        return className + " grades: \n" + grades.toString();
    }

    /*
     * EFFECTS: Returns the average of the class the user inputted.
     */
    private String oneAverage() {
        String className = classField.getText();
        double output = gradesCalculator.calculateClassAverage(className);

        return className + " average: " + Double.toString(output);
    }

    /*
     * EFFECTS: Returns the overall average (average of every class' average).
     */
    private String overallAverage() {
        return "Overall average: " + Double.toString(gradesCalculator.calculateOverallAverage());
    }

}
