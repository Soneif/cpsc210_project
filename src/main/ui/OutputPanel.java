package ui;

import javax.swing.*;
import java.awt.*;

/*
 * Represents the panel in which the information about grades
 */

// TODO:
//  - A text field that can't be edited, which outputs the information about grades after input or as requested

public class OutputPanel extends JPanel {
    private JTextField outputField;

    public OutputPanel() {
        outputField = new JTextField();
        outputField.setEditable(false);
        outputField.setPreferredSize(new Dimension(200, 500));

        this.add(outputField);
    }
}
