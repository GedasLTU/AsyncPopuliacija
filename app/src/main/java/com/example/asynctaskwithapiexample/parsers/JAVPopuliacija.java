package com.example.asynctaskwithapiexample.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JAVPopuliacija {
    public static String getJAVpopu(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        String data = "";
        while (line != null) {
            line = bufferedReader.readLine();
            data = data + line;
        }

        String result = "";
        try {
            JSONObject jData = new JSONObject(data);
            JSONObject dataNode = jData.getJSONObject("data");
            String metai =dataNode.getString("Year");
            String popul = dataNode.getString("Population");
            result = String.format("Metai: %s,\n populiacija: %s", metai, popul);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}