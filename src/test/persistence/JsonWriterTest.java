package persistence;

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
        try {
            GradesCalculator gc = new GradesCalculator("Eric");
            gc.addGrade(new Grade(75.2, "Project Phase 2", "CPSC 210"));
            gc.addGrade(new Grade(27.35, "Project Phase 1", "CPSC 210"));
            gc.addGrade(new Grade(80.6, "Midterm 1", "MATH 101"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGradesCalculator.json");
            writer.open();
            writer.write(gc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGradesCalculator.json");
            gc = reader.read();
            assertEquals("Eric", gc.getUser());
            List<Grade> grades = gc.getGrades();
            assertEquals(3, grades.size());

            checkGrade(75.2, "Project Phase 2", "CPSC 210", grades.get(0));
            checkGrade(27.35, "Project Phase 1", "CPSC 210", grades.get(1));
            checkGrade(80.6, "Midterm 1", "MATH 101", grades.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}