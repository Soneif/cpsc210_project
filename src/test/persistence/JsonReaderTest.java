package persistence;

import model.Grade;
import model.GradesCalculator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/* TODO: Citation - code from JsonSerializationDemo modified for Grade and GradesCalculator objects
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            GradesCalculator gc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGradesCalculator() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGradesCalculator.json");
        try {
            GradesCalculator gc = reader.read();
            assertEquals("Felix", gc.getUser());
            assertTrue(gc.getGrades().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGradesCalculator() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGradesCalculator.json");
        try {
            GradesCalculator gc = reader.read();
            assertEquals("Emma", gc.getUser());
            List<Grade> grades = gc.getGrades();
            assertEquals(3, grades.size());

            checkGrade(100, "Concept Sketch", "EOSC 112", grades.get(0));
            checkGrade(94.6, "Project Proposal", "DSCI 100", grades.get(1));
            checkGrade(80.5, "Project Pledge", "DSCI 100", grades.get(2));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}