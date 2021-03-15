package ui;

import javax.swing.*;
import java.awt.*;

/*
 * Represents the panel in which the information about grades
 */

// TODO:
//  - A text field that can't be edited, which outputs the information about grades after input or as requested

public class OutputPanel extends JPanel {
    private ActionLogPanel actionLog;
    private CurrentStatusPanel currentStatus;

    // EFFECTS: Instantiates the panels within OutputPanel
    public OutputPanel() {
        actionLog = new ActionLogPanel();
        currentStatus = new CurrentStatusPanel();

        this.add(actionLog);
        this.add(currentStatus);
    }

    // EFFECTS: Sets actionLog's text field text to the inputted string
    public void setActionLog(String text) {
        actionLog.setText(text);
    }

    // EFFECTS: Sets currentStatus' text field text to the inputted string
    public void setCurrentStatus(String text) {
        currentStatus.setText(text);
    }

    // EFFECTS: Gets text from actionLog's text field
    public String getActionLog() {
        return actionLog.getText();
    }

    // EFFECTS: Gets text from currentStatus' text field
    public String getCurrentStatus() {
        return currentStatus.getText();
    }

}
