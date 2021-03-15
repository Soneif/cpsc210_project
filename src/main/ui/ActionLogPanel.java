package ui;

import javax.swing.*;
import java.awt.*;

public class ActionLogPanel extends JPanel {
    private JTextField actionLogField;

    // EFFECTS: Instantiates the text field in the panel
    public ActionLogPanel() {
        actionLogField = new JTextField("Action Log:");
        actionLogField.setEditable(false);
        actionLogField.setPreferredSize(new Dimension(450, 100));

        this.add(actionLogField);
    }

    // MODIFIES: this
    // EFFECTS: Sets the text in the text field to the inputted String
    public void setText(String text) {
        actionLogField.setText(text);
    }

    // EFFECTS: Gets the text in the text field
    public String getText() {
        return actionLogField.getText();
    }

}
