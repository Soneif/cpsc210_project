package model;

// !!! class level comment

public class Grade {
    private double mark;
    private String assignmentName;
    private String className;

    /*
     * REQUIRES: mark is a non-negative value
     * EFFECTS: each input is assigned to their respective field (who has the same name)
     */

    public Grade(double mark, String assignmentName, String className) {
        this.mark = mark;
        this.assignmentName = assignmentName;
        this.className = className;
    }

    public double getMark() {
        return this.mark;
    }

    public String getAssignmentName() {
        return this.assignmentName;
    }

    public String getClassName() {
        return this.className;
    }

    public void changeMark(double newMark) {
        this.mark = newMark;
    }

    public void changeAssignmentName(String newAssignmentName) {
        this.assignmentName = newAssignmentName;
    }

    public void changeClassName(String newClassName) {
        this.className = newClassName;
    }

    @Override
    public String toString() {
        return "Class: " + this.className + "\n Assignment: " + this.assignmentName + "\n Mark: " + this.mark;
    }

}
