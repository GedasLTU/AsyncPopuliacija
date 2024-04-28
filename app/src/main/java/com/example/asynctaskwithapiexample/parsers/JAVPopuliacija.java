package com.example.asynctaskwithapiexample.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;

public class JAVPopuliacija {

    public static String getJAVpopu(InputStream stream) throws IOException {
        StringBuilder data = new StringBuilder();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        }

        String result;
        try {
            JSONObject jsonData = new JSONObject(data.toString());
            JSONArray dataJSONArray = jsonData.getJSONArray("data");

            StringBuilder populations = new StringBuilder();

            for (int i = 0; i < dataJSONArray.length(); i++) {
                JSONObject dataNode = dataJSONArray.getJSONObject(i);
                String year = dataNode.optString("Year");
                int population = dataNode.optInt("Population");

                populations.append("Metai: ").append(year).append(", Populiacija: ").append(population).append("\n");
            }

            result = populations.toString();
        } catch (JSONException e) {
            result = "Klaida apdorojant JSON duomenis: " + e.getMessage();
        }
        return result;
    }
}
