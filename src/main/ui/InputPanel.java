package ui;

import model.GradesCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton enterButton;

    // EFFECTS: Initializes the sub panels in the input panel
    public InputPanel(OutputPanel outputPanel) {
        this.outputPanel = outputPanel;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        initializeComponents();

        JPanel fieldPanel = new JPanel();
        fieldPanel.add(gradeLabel);
        fieldPanel.add(gradeField);
        fieldPanel.add(classLabel);
        fieldPanel.add(classField);
        fieldPanel.add(assignmentLabel);
        fieldPanel.add(assignmentField);
        fieldPanel.add(enterButton);

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

        gradeField.setEditable(false);
        classField.setEditable(false);
        assignmentField.setEditable(false);
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

        saveLoadWindowButton = new JButton("Open the save/load/quit menu");
        saveLoadWindowButton.setActionCommand("open menu");
        saveLoadWindowButton.addActionListener(this);

        enterButton = new JButton("Enter");
        enterButton.setActionCommand("enter");
        enterButton.addActionListener(this);
    }

    // EFFECTS: When a button is pressed, do what the button says
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("add")) {
            System.out.println("add");
        } else if (command.equals("remove")) {
            System.out.println("remove");
        } else if (command.equals("class grades")) {
            System.out.println("class grades");
        } else if (command.equals("class average")) {
            System.out.println("class average");
        } else if (command.equals("overall average")) {
            System.out.println("overall average");
        } else if (command.equals("enter")) {
            System.out.println("enter");
        } else {
            // TODO: test
            new SaveLoadFrame(new GradesCalculator(), outputPanel);
        }
    }
}
