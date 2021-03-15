package ui;

import javax.swing.*;
import java.awt.*;

public class ActionLogPanel extends JPanel {
    private static final Dimension PANE_SIZE = new Dimension(450, 100);
    private JTextArea actionLogField;

    // EFFECTS: Instantiates the text field in the panel
    public ActionLogPanel() {
        actionLogField = new JTextArea("Action Log:");
        actionLogField.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(actionLogField);
        scrollPane.setPreferredSize(PANE_SIZE);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);
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
