package com.rythm_zone.loginPage1;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class chatbot {
     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth = screenBounds.getWidth();
        double screenHeight = screenBounds.getHeight();

    // ✅ Replace with your actual Google API key
    static final String API_KEY = "AIzaSyDvrHlFJrXX9gtklq2VRFJpg5C07wttFUY";

    public static String getAIResponse(String userInput) {
        try {
            System.setProperty("https.protocols", "TLSv1.2");

            URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            // ✅ Gemini API request body
            String jsonInput = "{\n" +
                    "  \"contents\": [\n" +
                    "    {\n" +
                    "      \"parts\": [\n" +
                    "        {\n" +
                    "          \"text\": \"" + userInput.replace("\"", "\\\"") + "\"\n" +
                    "        }\n" +
                    "      ]\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes("utf-8"));
            }

            InputStream is = (conn.getResponseCode() == 200)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }

            JSONObject obj = new JSONObject(response.toString());

            if (obj.has("error")) {
                JSONObject err = obj.getJSONObject("error");
                return "Error: " + err.getString("message");
            }

            // ✅ Parse the first response from Gemini
            JSONArray candidates = obj.getJSONArray("candidates");
            JSONObject firstCandidate = candidates.getJSONObject(0);
            JSONObject content = firstCandidate.getJSONObject("content");
            JSONArray parts = content.getJSONArray("parts");
            JSONObject firstPart = parts.getJSONObject(0);

            return firstPart.getString("text").trim();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}