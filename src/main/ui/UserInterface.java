package ui;

import javax.swing.*;

/*
 * Represents the window that the user interacts with
 */

// TODO: Make JPanels for each of the interactions?
//  One panel for interactions with GradesCalculator
//  One panel for saving/loading previous saves?
//  The save panel can pop up as you open and close the program as well as a button.
//  A sound can be played and a gif of saying goodbye when closing the system.
//  Similarly, a sound and a gif can be played when opening the system.

public class UserInterface extends JFrame {
    private InputPanel input;
    private OutputPanel output;
    private JPanel window;

    // Constructs main window
    // EFFECTS: sets up the window
    public UserInterface() {
        super("Grades Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: Testing purposes; remove once implemented and debugged
        input = new InputPanel();
        output = new OutputPanel();

        window = new JPanel();
        window.add(input);
        window.add(output);

        this.add(window);

        pack();
        setVisible(true);
    }
}
