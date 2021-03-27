package ui;

import model.Grade;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class HistogramFrame extends JFrame {
    public static final int CANVAS_WIDTH = 800;
    public static final int CANVAS_HEIGHT = 800;
    public static final int TITLE_MARGIN = 40;
    public static final int PADDING = 60;
    private static final int LABEL_PADDING = 10;
    private static final String PATH = "./data/gradeHistogram.png";

    public static final String[] X_AXIS_LABELS = {"1-9", "10-19", "20-29", "30-39", "40-49", "50-59",
            "60-69", "70-79", "80-89", "90-100"};

    private int tickHeight;
    private int tickWidth;
    private int[] frequency = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private Graphics2D g2d;
    private List<Grade> gradesList;
    private double[] grades;
    private String className;

    // EFFECTS: Instantiates the JFrame and the JFrame's components
    public HistogramFrame(List<Grade> gradesList) {
        this.gradesList = gradesList;
        this.className = gradesList.get(0).getClassName();

        BufferedImage bufferedImage = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = bufferedImage.createGraphics();

        drawGraph();

        File file = new File(PATH);
        try {
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel();

        JLabel picLabel = new JLabel(new ImageIcon(bufferedImage));
        panel.add(picLabel);
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }

    // EFFECTS: Draw the histogram
    public void drawGraph() {
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        g2d.setColor(Color.black);

        g2d.drawString(className + "'s Grade Distribution", CANVAS_WIDTH / 2 - 50, TITLE_MARGIN);

        extractInfo();
        buildAxes();
        addLabels();
        fillGraph();

        g2d.dispose();
    }

    // MODIFIES: this
    // EFFECTS: Extracts the raw marks from gradesList and className
    public void extractInfo() {
        grades = new double[gradesList.size()];

        this.className = gradesList.get(0).getClassName();

        for (int i = 0; i < gradesList.size(); i++) {
            grades[i] = gradesList.get(i).getMark();
        }

    }

    // MODIFIES: this
    // EFFECTS: Builds the axes for the histogram
    public void buildAxes() {
        // drawing the lines for the axes
        // y-axis
        g2d.drawLine(PADDING, PADDING, PADDING, CANVAS_HEIGHT - PADDING);
        // x-axis
        g2d.drawLine(PADDING, CANVAS_HEIGHT - PADDING, CANVAS_WIDTH - PADDING, CANVAS_HEIGHT - PADDING);

        // drawing tick marks
        // y-axis
        buildYAxis();
        // x-axis
        buildXAxis();
    }

    // MODIFIES: this
    // EFFECTS: Fills the axis with tick marks and labels according to quantity of grades
    public void buildYAxis() {
        int length = CANVAS_HEIGHT - PADDING - PADDING;
        tickHeight = length / gradesList.size();
        int height;

        // adding tick marks and labels
        for (int i = 0; i <= gradesList.size(); i++) {
            height = PADDING + tickHeight * (gradesList.size() - i);
            g2d.drawLine(PADDING, height, PADDING - LABEL_PADDING, height);
            g2d.drawString(Integer.toString(i), PADDING - LABEL_PADDING - 10, height + 5);
        }
    }

    // MODIFIES: this
    // EFFECTS: Fills the axis with tick marks and labels
    public void buildXAxis() {
        int length = CANVAS_HEIGHT - PADDING - PADDING;
        tickWidth = length / X_AXIS_LABELS.length;
        int width;
        int tickLength = CANVAS_HEIGHT - PADDING + LABEL_PADDING;


        for (int i = 0; i <= X_AXIS_LABELS.length; i++) {
            width = PADDING + tickWidth * i;
            g2d.drawLine(width, CANVAS_HEIGHT - PADDING, width, tickLength);
            g2d.drawString(Integer.toString(i), width - 3, tickLength + 13);
        }
    }

    // EFFECTS: Add labels to the histogram
    public void addLabels() {
        // adding the x-axis label
        g2d.drawString("Grades (%)", CANVAS_WIDTH / 2 - 10, CANVAS_HEIGHT - PADDING + 40);
        // we need to rotate before we write the y-axis label
        Font font = g2d.getFont();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(-90), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2d.setFont(rotatedFont);
        g2d.drawString("Number of Grades in Range", PADDING - 30, CANVAS_HEIGHT / 2 + 60);
    }

    // EFFECTS: Fills the histogram with given data
    public void fillGraph() {
        sorting();
        fillRectangles();
    }

    // EFFECTS: Draws the rectangles that indicate the frequency
    public void fillRectangles() {
        int x;
        int y;
        int width;
        int height;

        // randomly generating new colours for each rectangle!
        Random rand = new Random();
        Color randomColour;
        float r;
        float g;
        float b;

        width = (CANVAS_HEIGHT - PADDING - PADDING) / frequency.length;

        for (int i = 0; i < frequency.length; i++) {
            r = rand.nextFloat();
            g = rand.nextFloat();
            b = rand.nextFloat();
            randomColour = new Color(r, g, b);
            g2d.setColor(randomColour);

            x = PADDING + tickWidth * i;
            y = CANVAS_HEIGHT - PADDING - tickHeight * frequency[i];
            height = tickHeight * frequency[i];

            g2d.fillRect(x, y, width, height);
        }
    }

    // EFFECTS: Counts the number of grades in each category (specified in X_AXIS_LABELS)
    public void sorting() {
        for (int i = 0; i < gradesList.size(); i++) {
            int current = (int) gradesList.get(i).getMark();
            checkRange(current);
        }
    }

    // MODIFIES: this
    // EFFECTS: Checks the range that the grade falls into and adds it to frequency
    private void checkRange(int current) {
        if (0 <= current && current <= 9) {
            frequency[0] += 1;
        } else if (10 <= current && current <= 19) {
            frequency[1] += 1;
        } else if (20 <= current && current <= 29) {
            frequency[2] += 1;
        } else if (30 <= current && current <= 39) {
            frequency[3] += 1;
        } else if (40 <= current && current <= 49) {
            frequency[4] += 1;
        } else if (50 <= current && current <= 59) {
            frequency[5] += 1;
        } else if (60 <= current && current <= 69) {
            frequency[6] += 1;
        } else if (70 <= current && current <= 79) {
            frequency[7] += 1;
        } else if (80 <= current && current <= 89) {
            frequency[8] += 1;
        } else if (90 <= current && current <= 100) {
            frequency[9] += 1;
        }
    }
}
