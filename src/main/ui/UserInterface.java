package ui;

import javax.swing.*;

/*
 * Represents the window that the user interacts with
 */

public class UserInterface extends JFrame {
    private InputPanel input;
    private OutputPanel output;
    private JPanel window;

    // Constructs main window
    // EFFECTS: sets up the window
    public UserInterface() {
        super("Grades Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        output = new OutputPanel();
        input = new InputPanel(output);

        window = new JPanel();
        window.setLayout(new BoxLayout(window, BoxLayout.PAGE_AXIS));
        window.add(output);
        window.add(input);

        this.add(window);

        pack();
        setVisible(true);
    }
}
