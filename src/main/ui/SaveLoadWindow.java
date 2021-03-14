package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.exit;

/*
 * Represents the panel in which an option to save or load information is provided.
 */

// TODO:
//  A panel with three buttons, save, load, and quit.
//  The panel should give a message detailing whether it had successfully saved/loaded and then close itself.
//  If quit is clicked, close.

public class SaveLoadWindow extends JPanel implements ActionListener {
    private JLabel options;
    private JButton save;
    private JButton load;
    private JButton quit;

    // EFFECTS: Initializes the buttons and label in the panel
    public SaveLoadWindow() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        options = new JLabel("Please select one of the three options.");
        options.setHorizontalAlignment(JLabel.CENTER);

        save = new JButton("Save from file");
        load = new JButton("Load from file");
        quit = new JButton("Quit program");

        save.setActionCommand("save");
        load.setActionCommand("load");
        quit.setActionCommand("quit");

        save.addActionListener(this);
        load.addActionListener(this);
        quit.addActionListener(this);

        JPanel labelPanel = new JPanel();
        labelPanel.add(options);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(save);
        buttonPanel.add(load);
        buttonPanel.add(quit);

        this.add(labelPanel);
        this.add(buttonPanel);
    }

    // EFFECTS: When a button is pressed, do what the button says
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("save")) {
            // TODO: save
        } else if (command.equals("load")) {
            // TODO: load
        } else {
            exit(0);
        }

    }
}
