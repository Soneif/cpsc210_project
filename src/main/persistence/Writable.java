package persistence;

import org.json.JSONObject;

/* TODO: Citation - code from JsonSerializationDemo
         (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo) */

/*
 * Writable is an interface that gives classes the ability of being returned as a JSON object
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
