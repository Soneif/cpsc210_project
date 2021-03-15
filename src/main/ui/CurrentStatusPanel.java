package ui;

import javax.swing.*;
import java.awt.*;

public class CurrentStatusPanel extends JPanel {
    private JTextField currentStatusField;

    // EFFECTS: Instantiates the text field in the panel
    public CurrentStatusPanel() {
        currentStatusField = new JTextField("Your Grades:");
        currentStatusField.setEditable(false);
        currentStatusField.setPreferredSize(new Dimension(450, 100));

        this.add(currentStatusField);
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
