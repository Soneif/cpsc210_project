package persistence;

import model.Grade;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* TODO: Citation - code from JsonSerializationDemo modified for Grade objects
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

public class JsonTest {
    protected void checkGrade(double mark, String assignmentName, String className, Grade grade) {
        assertEquals(className, grade.getClassName());
        assertEquals(assignmentName, grade.getAssignmentName());
        assertEquals(mark, grade.getMark());
    }
}
