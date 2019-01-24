package app.util;

import app.entities.Error;
import app.entities.Question;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonHelper {
    private static ObjectMapper mapper = new ObjectMapper();

    public static Error parseError(JSONObject obj) throws IOException {
        return mapper.readValue(obj.toString(), Error.class);
    }

    public static List<Question> parseQuestions(JSONObject obj) throws IOException {

        JSONArray arr = obj.getJSONArray("items");
        return Arrays.asList(mapper.readValue(arr.toString(), Question[].class));
    }
}
