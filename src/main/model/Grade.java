package model;

/*
 * Represents a Grade with the numeric mark value, an assignment name, and a class name.
 */

public class Grade {
    private double mark;
    private String assignmentName;
    private String className;

    /*
     * REQUIRES: mark is a non-negative value
     * EFFECTS: Constructs a Grade object where each input is assigned to
     *          their respective field (who has the same name)
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

    /*
     * REQUIRES: newMark is a non-negative value
     * MODIFIES: this
     * EFFECTS: Changes the mark's value to a new value
     */
    public void changeMark(double newMark) {
        this.mark = newMark;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Changes the assignmentName to a new name
     */
    public void changeAssignmentName(String newAssignmentName) {
        this.assignmentName = newAssignmentName;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Changes the className to a new class name
     */
    public void changeClassName(String newClassName) {
        this.className = newClassName;
    }

    /*
     * EFFECTS: Changes the object's String return value
     */
    @Override
    public String toString() {
        return "Class: " + this.className + "\n Assignment: " + this.assignmentName + "\n Mark: " + this.mark;
    }

}
