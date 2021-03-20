package ui;

import model.Grade;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GraphPanel extends JPanel {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 800;
    public static final int CIRCLE_RADIUS = 10;
    private static final String PATH = "./data/gradeLineGraph.png";

    private Graphics2D g2d;
    private List<Grade> gradesList;
    private int[] grades;

    // EFFECTS: Instantiates the components for this panel
    public GraphPanel(List<Grade> gradesList) {
        this.gradesList = gradesList;
        extractGrades();

        BufferedImage bufferedImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = bufferedImage.createGraphics();

        drawGraph();

        g2d.dispose();

        File file = new File(PATH);
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
        add(picLabel);

    }

    // EFFECTS: Extracts grades from Grade objects in gradesCalculator
    public void extractGrades() {
        grades = new int[gradesList.size()];

        for (int i = 0; i < gradesList.size(); i++) {
            grades[i] = (int) gradesList.get(i).getMark();
        }

    }

    // EFFECTS: Draws a line graph of all the grades
    public void drawGraph() {
        int x = CANVAS_WIDTH / grades.length;
        int y = 8;
        int preX = 0;
        int preY = 0;

        // filling canvas with white first
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        g2d.setColor(Color.black);

        for (int i = 0; i < grades.length; i++) {
            if (i == 0) {
                g2d.fillOval(x * i - CIRCLE_RADIUS, y * grades[i] - CIRCLE_RADIUS, CIRCLE_RADIUS, CIRCLE_RADIUS);
            } else {
                g2d.fillOval(x * i - CIRCLE_RADIUS, y * grades[i] - CIRCLE_RADIUS, CIRCLE_RADIUS, CIRCLE_RADIUS);
                g2d.drawLine(preX, preY, x * i - CIRCLE_RADIUS / 2, y * grades[i] - CIRCLE_RADIUS / 2);
            }
            preX = x * i - CIRCLE_RADIUS / 2;
            preY = y * grades[i] - CIRCLE_RADIUS / 2;
        }

    }
}
