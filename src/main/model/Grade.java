package model;

/*
 * Represents a Grade with the numeric mark value, an assignment name, and a class name.
 */

/* TODO: Citation - code from JsonSerializationDemo and modified for Grade (toJson())
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

public class Grade implements Writable {
    private double mark;
    private String assignmentName;
    private String className;

    /*
     * REQUIRES: mark is a non-negative value
     * EFFECTS: Constructs a Grade object where each input is assigned to
     *          their respective field (who has the same name)
     */

    // REQUIRES: mark >= 0
    // EFFECTS: instantiates the grade's fields
    public Grade(double mark, String assignmentName, String className) {
        this.mark = mark;
        this.assignmentName = assignmentName;
        this.className = className;
    }

    // EFFECTS: Returns the mark for this grade.
    public double getMark() {
        return this.mark;
    }

    // EFFECTS: Returns the assignment name corresponding to this grade.
    public String getAssignmentName() {
        return this.assignmentName;
    }

    // EFFECTS: Returns the class name corresponding to this grade.
    public String getClassName() {
        return this.className;
    }

    /*
     * REQUIRES: newMark is a non-negative value
     * EFFECTS: Changes the mark's value to a new value
     */
    public void changeMark(double newMark) {
        this.mark = newMark;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Changes the assignmentName to a new assignment name
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("className", className);
        json.put("assignmentName", assignmentName);
        json.put("mark", mark);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Grade grade = (Grade) o;
        return Double.compare(grade.mark, mark) == 0
                && Objects.equals(assignmentName, grade.assignmentName)
                && Objects.equals(className, grade.className);
    }
}
