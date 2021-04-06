package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// Test class for Grade

class GradeTest {

    Grade grade;

    @BeforeEach
    void initialize() {
        grade = new Grade(50.7, "Lab 2", "CPSC 210");
    }

    @Test
    void testAllEqualBranches() {
        assertFalse(grade.equals(new Grade(96, "Lab 3", "CPSC 210")));
        assertFalse(grade.equals(""));
        assertFalse(grade.equals(new Grade(50.7, "Lab 3", "CPSC 210")));
        assertFalse(grade.equals(new Grade(50.7, "Lab 2", "MATH 101")));
    }

    @Test
    void changeMark() {
        grade.changeMark(97.6);
        assertEquals(97.6, grade.getMark());
    }

    @Test
    void changeAssignmentName() {
        grade.changeAssignmentName("Lab 5");
        assertEquals("Lab 5", grade.getAssignmentName());
    }

    @Test
    void changeClassName() {
        grade.changeClassName("CPSC 110");
        assertEquals("CPSC 110", grade.getClassName());
    }

    @Test
    void fromGradeToString() {
        assertEquals("Class: CPSC 210" + "\n Assignment: Lab 2" + "\n Mark: 50.7", grade.toString());
    }
}