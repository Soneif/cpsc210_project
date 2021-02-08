package ui;

import model.Grade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradesCalculatorTest {

    GradesCalculator calc;

    @BeforeEach
    void initialize() {
        calc = new GradesCalculator();
    }

    @Test
    void addOneGrade() {
        Grade grade = new Grade(76.32, "Test 1", "MATH 123");
        calc.addGrade(grade);
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());
    }

    @Test
    void addOneGradeRemoveOneGrade() {
        Grade grade = new Grade(87, "Test 5", "CPSC 123");
        calc.addGrade(grade);
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());

        calc.removeGrade(grade);

        assertFalse(output.contains(grade));
        assertEquals(0, output.size());
    }

    @Test
    void addTwoGradesRemoveOneGrade() {
        Grade grade1 = new Grade(90.23, "Test 5", "CPSC 123");
        calc.addGrade(grade1);
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade1));
        assertEquals(1, output.size());

        Grade grade2 = new Grade(110.3, "Test 3", "CPSC 789");
        calc.addGrade(grade2);
        output = calc.getGrades();

        assertTrue(output.contains(grade2));
        assertEquals(2, output.size());

        calc.removeGrade(grade1);

        assertFalse(output.contains(grade1));
        assertEquals(1, output.size());
    }


    @Test
    void calculateClassAverageAllMarksFromOneClass() {
        Grade grade = new Grade(11.4, "Worksheet 2", "DSCI 500");
        calc.addGrade(grade);
        grade = new Grade(90.2, "Worksheet 3", "DSCI 500");
        calc.addGrade(grade);
        grade = new Grade(92, "Test 4", "DSCI 500");
        calc.addGrade(grade);

        assertEquals((11.4 + 90.2 + 92) / 3, calc.calculateClassAverage("DSCI 500"));
    }

    @Test
    void overallAverageMultipleGradesForEachClass() {
        Grade grade = new Grade(64, "Concept Sketch", "EOSC 100");
        calc.addGrade(grade);
        grade = new Grade(80, "Quiz 1", "EOSC 100");
        calc.addGrade(grade);
        grade = new Grade(97.5, "Test 2", "EOSC 100");
        calc.addGrade(grade);
        double eoscAverage = (64 + 80 + 97.5) / 3;

        assertEquals(eoscAverage, calc.calculateClassAverage("EOSC 100"));

        grade = new Grade(79.5, "Problem Set", "CPSC 325");
        calc.addGrade(grade);
        grade = new Grade(97.2, "Group Assignment", "CPSC 325");
        calc.addGrade(grade);
        double cpscAverage = (79.5 + 97.2) / 2;

        assertEquals(cpscAverage, calc.calculateClassAverage("CPSC 325"));

        grade = new Grade(22, "Homework 1", "MATH 283");
        calc.addGrade(grade);
        grade = new Grade(70.5, "Take Home Test", "MATH 283");
        calc.addGrade(grade);
        double mathAverage = (22 + 70.5) / 2;

        assertEquals(mathAverage, calc.calculateClassAverage("MATH 283"));

        assertEquals((eoscAverage + cpscAverage + mathAverage) / 3, calc.calculateOverallAverage());
    }
}