package model;

import exceptions.EmptyClassListException;
import exceptions.InvalidClassNameException;
import exceptions.NegativeMarkException;
import exceptions.PreExistingGradeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test class for GradesCalculator

class GradesCalculatorTest {

    GradesCalculator calc;

    @BeforeEach
    void initialize() {
        calc = new GradesCalculator();
        calc.setUser("test");
    }

    @Test
    void addOneGrade() {
        Grade grade = new Grade(76.32, "Test 1", "MATH 123");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());
    }

    @Test
    void tryRemoveGradeNotMatchAssignmentName() {
        Grade grade = new Grade(24.6, "Homework", "MATH 102");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());

        calc.removeGrade("Homework 1", "MATH 102");

        output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());
    }

    @Test
    void tryRemoveGradeNotMatchClassName() {
        Grade grade = new Grade(24.6, "Homework", "MATH 435");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());

        calc.removeGrade("Homework", "MATH 205");

        output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());
    }

    @Test
    void addOneGradeRemoveOneGrade() {
        Grade grade = new Grade(87, "Test 5", "CPSC 123");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade));
        assertEquals(1, output.size());

        calc.removeGrade("Test 5", "CPSC 123");

        output = calc.getGrades();

        assertFalse(output.contains(grade));
        assertEquals(0, output.size());
    }

    @Test
    void addTwoGradesRemoveOneGrade() {
        Grade grade1 = new Grade(90.23, "Test 5", "CPSC 123");
        try {
            calc.addGrade(grade1);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        List<Grade> output = calc.getGrades();

        assertTrue(output.contains(grade1));
        assertEquals(1, output.size());

        Grade grade2 = new Grade(110.3, "Test 3", "CPSC 789");
        try {
            calc.addGrade(grade2);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        output = calc.getGrades();

        assertTrue(output.contains(grade2));
        assertEquals(2, output.size());

        calc.removeGrade("Test 5", "CPSC 123");

        assertFalse(output.contains(grade1));
        assertEquals(1, output.size());
        assertEquals(output.get(0), grade2);
    }


    @Test
    void calculateClassAverageAllMarksFromOneClass() {
        Grade grade = new Grade(11.4, "Worksheet 2", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(90.2, "Worksheet 3", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(92, "Test 4", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }

        try {
            assertEquals((11.4 + 90.2 + 92) / 3, calc.calculateClassAverage("DSCI 500"));
        } catch (InvalidClassNameException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void overallAverageMultipleGradesForEachClass() {
        Grade grade = new Grade(64, "Concept Sketch", "EOSC 100");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(80, "Quiz 1", "EOSC 100");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(97.5, "Test 2", "EOSC 100");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        double eoscAverage = (64 + 80 + 97.5) / 3;

        try {
            assertEquals(eoscAverage, calc.calculateClassAverage("EOSC 100"));
        } catch (InvalidClassNameException e) {
            fail("Exception should not have been thrown");
        }

        grade = new Grade(79.5, "Problem Set", "CPSC 325");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(97.2, "Group Assignment", "CPSC 325");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        double cpscAverage = (79.5 + 97.2) / 2;

        try {
            assertEquals(cpscAverage, calc.calculateClassAverage("CPSC 325"));
        } catch (InvalidClassNameException e) {
            fail("Exception should not have been thrown");
        }

        grade = new Grade(22, "Homework 1", "MATH 283");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(70.5, "Take Home Test", "MATH 283");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        double mathAverage = (22 + 70.5) / 2;

        try {
            assertEquals(mathAverage, calc.calculateClassAverage("MATH 283"));
        } catch (InvalidClassNameException e) {
            fail("Exception should not have been thrown");
        }

        try {
            assertEquals(Double.toString((eoscAverage + cpscAverage + mathAverage) / 3), calc.calculateOverallAverage());
        } catch (EmptyClassListException e) {
            fail("Exception should not have been thrown");
        } catch (InvalidClassNameException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void returnClassGradeMultipleClasses() {
        List<Grade> match = new ArrayList<>();
        Grade grade = new Grade(97.4, "Worksheet 2", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        match.add(grade);
        grade = new Grade(4.5, "Quiz 3", "EOSC 245");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(45.7, "Test", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        match.add(grade);

        List<Grade> grades = null;
        try {
            grades = calc.returnClassGrades("DSCI 500");
        } catch (InvalidClassNameException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals(match, grades);
    }

    @Test
    void setGradesTest() {
        List<Grade> test = new ArrayList<>();
        Grade grade = new Grade(24.4, "Homework 1", "MATH 100");
        test.add(grade);
        grade = new Grade(2.5, "Quiz 1", "MATH 100");
        test.add(grade);
        calc.setGrades(test);
        assertEquals(test, calc.getGrades());
    }

    @Test
    void printGradesTest() {
        Grade grade = new Grade(20.8, "Worksheet 6", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(88.5, "Worksheet 1", "DSCI 200");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        grade = new Grade(96, "Test 7", "DSCI 500");
        try {
            calc.addGrade(grade);
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Class: DSCI 500\n" +
                " Assignment: Worksheet 6\n" +
                " Mark: 20.8\n" +
                "Class: DSCI 200\n" +
                " Assignment: Worksheet 1\n" +
                " Mark: 88.5\n" +
                "Class: DSCI 500\n" +
                " Assignment: Test 7\n" +
                " Mark: 96.0\n", calc.toString());
    }

    @Test
    void setClassesTest() {
        List<String> test = new ArrayList<>();
        test.add("Test 1");
        test.add("Test 2");
        test.add("Test 3");
        calc.setClasses(test);
        assertEquals(test, calc.getClasses());
    }

    @Test
    void addNegativeMark() {
        try {
            calc.addGrade(new Grade(-20, "Homework 1", "MATH 101"));
            fail("Should have thrown exception.");
        } catch (NegativeMarkException e) {
            // This is expected
        } catch (PreExistingGradeException e) {
            fail("Thrown wrong exception.");
        }
    }

    @Test
    void addPreExistingMark() {
        try {
            calc.addGrade(new Grade(5, "Homework 2", "CPSC 210"));
        } catch (NegativeMarkException e) {
            fail("No exception expected.");
        } catch (PreExistingGradeException e) {
            fail("No exception expected.");
        }

        try {
            calc.addGrade(new Grade(5, "Homework 2", "CPSC 210"));
            fail("Should have thrown exception.");
        } catch (NegativeMarkException e) {
            fail("No exception expected.");
        } catch (PreExistingGradeException e) {
            // This is expected
        }

    }

    @Test
    void calculateClassAverageForClassNotInCalculator() {
        try {
            calc.addGrade(new Grade(100, "Homework 5", "DSCI 100"));
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("No exception expected.");
        }

        try {
            calc.calculateClassAverage("MATH 101");
            fail("Should have thrown exception.");
        } catch (InvalidClassNameException e) {
            // This is expected
        }
    }

    @Test
    void calculateOverallAverageForEmptyCalculator() {
        try {
            calc.calculateOverallAverage();
            fail("Should have thrown exception.");
        } catch (EmptyClassListException e) {
            // This is expected
        } catch (InvalidClassNameException e) {
            fail("This exception should not have been thrown");
        }
    }

    @Test
    void returnClassAverageForClassNotInCalculator() {
        try {
            calc.addGrade(new Grade(100, "Homework 5", "DSCI 100"));
        } catch (NegativeMarkException | PreExistingGradeException e) {
            fail("No exception expected.");
        }

        try {
            calc.returnClassGrades("MATH 101");
            fail("Should have thrown exception.");
        } catch (InvalidClassNameException e) {
            // This is expected
        }
    }

}