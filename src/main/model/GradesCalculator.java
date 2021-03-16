package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents a List of Grade objects and a list of String to keep track of
 * the different classes each grade is from.
 */

/* TODO: Citation - code from JsonSerializationDemo (toJson & gradesToJson(), modified for GradesCalculator)
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

public class GradesCalculator implements Writable {
    private List<Grade> grades;
    private List<String> classes;
    private String user = "";

    /*
     * EFFECTS: instantiates grades and classes as Arraylists and sets a user name
     */
    public GradesCalculator(String user) {
        this.grades = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.user = user;
    }

    /*
     * EFFECTS: instantiates grades and classes as Arraylists with no user name
     */
    public GradesCalculator() {
        this.grades = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> gradeList) {
        this.grades = gradeList;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
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
     * EFFECTS: Removes Grade that has the same assignmentName, and className
     */
    public void removeGrade(String assignmentName, String className) {
        Grade toRemove = null;
        for (Grade grade : grades) {
            if (grade.getClassName().equals(className)) {
                if (grade.getAssignmentName().equals(assignmentName)) {
                    toRemove = grade;
                }
            }
        }
        grades.remove(toRemove);
    }


    /*
     * REQUIRES: className is in classes
     * EFFECTS: Calculates the average of all grades whose class is className
     */
    public double calculateClassAverage(String className) {
        List<Grade> gradesInClass = returnClassGrades(className);

        double sum = 0;

        for (Grade grade : gradesInClass) {
            sum += grade.getMark();
        }

        return sum / gradesInClass.size();
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

    /*
     * REQUIRES: className is a String that is contained in classes
     * EFFECTS: Returns a list of Grade objects whose className matches the inputted class name.
     */
    public List<Grade> returnClassGrades(String className) {
        List<Grade> gradesInClass = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.getClassName().equals(className)) {
                gradesInClass.add(grade);
            }
        }

        return gradesInClass;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", this.user);
        json.put("grades", gradesToJson());
        return json;
    }

    // EFFECTS: returns grades in this gradesCalculator as a JSON array
    private JSONArray gradesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Grade grade : grades) {
            jsonArray.put(grade.toJson());
        }

        return jsonArray;
    }

    @Override
    public String toString() {
        String output = "";

        for (Grade grade : grades) {
            output += grade.toString() + "\n";
        }

        return output;
    }

}
