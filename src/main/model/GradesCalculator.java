package model;

import model.Grade;

import java.util.ArrayList;
import java.util.List;

public class GradesCalculator {
    private List<Grade> grades;
    private List<String> classes;

    /*
     * EFFECTS: instantiates grades and classes as Arraylists
     */
    public GradesCalculator() {
        grades = new ArrayList<Grade>();
        classes = new ArrayList<String>();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    /*
     *  REQUIRES: mark >= 0 and no Grade in grades that has the same assignmentName and className
     *  MODIFIES: this
     *  EFFECTS: Creates a Grade object using the given inputs and
     *          adds className to classes if it's not already in classes.
     */
    public void addGrade(Grade grade) {
        grades.add(grade);

        if (!classes.contains(grade.getClassName())) {
            classes.add(grade.getClassName());
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: Removes Grade that has the same mark, assignmentName, and className
     */
    public void removeGrade(Grade match) {
        grades.remove(match);
    }


    /*
     * REQUIRES: className is in classes
     * EFFECTS: Calculates the average of all grades whose class is className
     */
    public double calculateClassAverage(String className) {
        double sum = 0;
        int n = 0;

        for (Grade grade : grades) {
            if (grade.getClassName().equals(className)) {
                sum += grade.getMark();
                n++;
            }
        }
        return sum / n;
    }

    /*
     * REQUIRES: classes is not an empty list
     * EFFECTS: Calculates the overall average by taking the mean of every class' average.
     */
    public double calculateOverallAverage() {
        double sum = 0;
        int n = 0;

        for (String className : classes) {
            sum += calculateClassAverage(className);
            n++;
        }

        return sum / n;
    }

}
