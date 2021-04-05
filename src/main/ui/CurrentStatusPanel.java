package ui;

import javax.swing.*;
import java.awt.*;

// A panel that logs the current status of the grades.

public class CurrentStatusPanel extends JPanel {
    private static final Dimension PANE_SIZE = new Dimension(400, 100);
    private JTextArea currentStatusField;

    // EFFECTS: Instantiates the text field in the panel
    public CurrentStatusPanel() {
        currentStatusField = new JTextArea("Your Grades:");
        currentStatusField.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(currentStatusField);
        scrollPane.setPreferredSize(PANE_SIZE);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
    }

    // MODIFIES: this
    // EFFECTS: Sets the text in the text field to the inputted String
    public void setText(String text) {
        currentStatusField.setText(text);
    }

    // EFFECTS: Gets the text in the text field
    public String getText() {
        return currentStatusField.getText();
    }

}
