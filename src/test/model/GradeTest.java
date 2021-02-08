package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    Grade grade;

    @BeforeEach
    void initialize() {
        grade = new Grade(50.7, "Lab 2", "CPSC 210");
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

}