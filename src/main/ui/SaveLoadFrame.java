package ui;

import model.Grade;
import model.GradesCalculator;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/*
 * Represents the panel in which an option to save or load information is provided.
 */

public class SaveLoadFrame extends JFrame implements ActionListener {
    private OutputPanel outputPanel;

    private static final String PATH = "./data/savedData.json";
    private GradesCalculator gradesCalculator;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JLabel options;
    private JButton save;
    private JButton load;
    private JPanel overlay;

    private JLabel nameLabel;
    private JTextField name;
    private JButton enter;
    private JFrame frame;

    // EFFECTS: Initializes the sub panels and places them in the JFrame
    public SaveLoadFrame(GradesCalculator gradesCalculator, OutputPanel outputPanel) {
        this.outputPanel = outputPanel;
        this.gradesCalculator = gradesCalculator;
        jsonReader = new JsonReader(PATH);
        jsonWriter = new JsonWriter(PATH);

        overlay = new JPanel();
        overlay.setLayout(new BoxLayout(overlay, BoxLayout.PAGE_AXIS));

        initializeComponents();

        JPanel labelPanel = new JPanel();
        labelPanel.add(options);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(save);
        buttonPanel.add(load);

        overlay.add(labelPanel);
        overlay.add(buttonPanel);
        this.add(overlay);

        this.pack();
        this.setVisible(true);
    }

    // EFFECTS: Initializes the components for the sub panels
    public void initializeComponents() {
        options = new JLabel("Please select either option.");
        options.setHorizontalAlignment(JLabel.CENTER);

        save = new JButton("Save to file");
        load = new JButton("Load from file");

        save.setActionCommand("save");
        load.setActionCommand("load");

        save.addActionListener(this);
        load.addActionListener(this);
    }

    // EFFECTS: When a button is pressed, do what the button says
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String output = "";

        switch (command) {
            case "save":
                if (checkUser()) {
                    output = saveGrades();
                }
                break;
            case "enter":
                gradesCalculator.setUser(name.getText());
                output = saveGrades();
                frame.dispose();
                break;
            default:
                output = loadGrades();
                break;
        }

        String original = outputPanel.getActionLog();
        outputPanel.setActionLog(original + output);
        outputPanel.setCurrentStatus("Your Grades: \n" + gradesCalculator.toString());
        this.dispose();
    }

    // EFFECTS: checks if there is already a user name, if not, prompt the user to input it
    private boolean checkUser() {
        if (gradesCalculator.getUser().equals("")) {
            frame = new JFrame();
            JPanel panel = new JPanel();

            nameLabel = new JLabel("Please enter a name: ");

            name = new JTextField();
            name.setPreferredSize(new Dimension(200, 24));

            enter = new JButton("Enter");
            enter.setActionCommand("enter");
            enter.addActionListener(this);

            panel.add(nameLabel);
            panel.add(name);
            panel.add(enter);

            frame.add(panel);
            frame.pack();
            frame.setVisible(true);

            return false;
        }
        return true;
    }

    // EFFECTS: saves the grades(calculator) to file
    private String saveGrades() {

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
            GradesCalculator gc = jsonReader.read();
            changeGrades(gc);
            return "Loaded " + gradesCalculator.getUser() + " from " + PATH;
        } catch (IOException e) {
            return "Unable to read from file: " + PATH;
        }
    }

    // MODIFIES: this
    // EFFECTS: changes the values in gradesCalculator to the saved gradesCalculator values
    private void changeGrades(GradesCalculator gc) {
        List<Grade> gradesList = gc.getGrades();
        String user = gc.getUser();
        gradesCalculator.setGrades(gradesList);
        gradesCalculator.setUser(user);
        List<String> classList = gc.getClasses();
        gradesCalculator.setClasses(classList);
    }

}
