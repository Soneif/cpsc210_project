package persistence;

import exceptions.NegativeMarkException;
import exceptions.PreExistingGradeException;
import model.Grade;
import model.GradesCalculator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* TODO: Citation - code from JsonSerializationDemo modified for Grade and GradesCalculator objects
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            GradesCalculator gc = new GradesCalculator("My grades calculator");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGradesCalculator() {
        try {
            GradesCalculator gc = new GradesCalculator("Amy");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGradesCalculator.json");
            writer.open();
            writer.write(gc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGradesCalculator.json");
            gc = reader.read();
            assertEquals("Amy", gc.getUser());
            assertTrue(gc.getGrades().isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGradesCalculator() {
        GradesCalculator gc = new GradesCalculator("Eric");
        try {
            gc.addGrade(new Grade(75.2, "Project Phase 2", "CPSC 210"));
        } catch (NegativeMarkException e) {
            fail("Exception should not have been thrown");
        } catch (PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        try {
            gc.addGrade(new Grade(27.35, "Project Phase 1", "CPSC 210"));
        } catch (NegativeMarkException e) {
            fail("Exception should not have been thrown");
        } catch (PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }
        try {
            gc.addGrade(new Grade(80.6, "Midterm 1", "MATH 101"));
        } catch (NegativeMarkException e) {
            fail("Exception should not have been thrown");
        } catch (PreExistingGradeException e) {
            fail("Exception should not have been thrown");
        }

        JsonWriter writer = new JsonWriter("./data/testWriterGeneralGradesCalculator.json");
        try {
            writer.open();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        writer.write(gc);
        writer.close();

        JsonReader reader = new JsonReader("./data/testWriterGeneralGradesCalculator.json");
        try {
            gc = reader.read();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
        assertEquals("Eric", gc.getUser());
        List<Grade> grades = gc.getGrades();
        assertEquals(3, grades.size());

        checkGrade(75.2, "Project Phase 2", "CPSC 210", grades.get(0));
        checkGrade(27.35, "Project Phase 1", "CPSC 210", grades.get(1));
        checkGrade(80.6, "Midterm 1", "MATH 101", grades.get(2));
    }

}