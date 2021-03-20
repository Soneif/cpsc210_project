package persistence;

import model.Grade;
import model.GradesCalculator;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/* TODO: Citation - code from JsonSerializationDemo and modified for GradesCalculator
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

// Represents a reader that reads GradesCalculator from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads gradesCalculator from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GradesCalculator read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGradesCalculator(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses gradesCalculator from JSON object and returns it
    private GradesCalculator parseGradesCalculator(JSONObject jsonObject) {
        String user = jsonObject.getString("user");
        GradesCalculator gc = new GradesCalculator(user);
        addGrades(gc, jsonObject);
        return gc;
    }

    // MODIFIES: gc
    // EFFECTS: parses grades from JSON object and adds them to gradesCalculator
    private void addGrades(GradesCalculator gc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("grades");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addGrade(gc, nextThingy);
        }
    }

    // MODIFIES: gc
    // EFFECTS: parses grades from JSON object and adds it to gradesCalculator
    private void addGrade(GradesCalculator gc, JSONObject jsonObject) {
        String className = jsonObject.getString("className");
        String assignmentName = jsonObject.getString("assignmentName");
        Double mark = jsonObject.getDouble("mark");
        Grade grade = new Grade(mark, assignmentName, className);
        gc.addGrade(grade);
    }


}
