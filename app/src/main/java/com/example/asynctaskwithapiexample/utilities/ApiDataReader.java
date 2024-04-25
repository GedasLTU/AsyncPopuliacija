package com.example.asynctaskwithapiexample.utilities;

//import com.example.asynctaskwithapiexample.parsers.FloatRatesXmlParser;
//import com.example.asynctaskwithapiexample.parsers.GunfireHtmlParser;
import com.example.asynctaskwithapiexample.parsers.JAVPopuliacija;
//import com.example.asynctaskwithapiexample.parsers.MeteoLtJsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.asynctaskwithapiexample.utilities.Constants.JAV_POP_URL;
//import static com.example.asynctaskwithapiexample.utilities.Constants.GUNFIRE_URL;
//import static com.example.asynctaskwithapiexample.utilities.Constants.METEOLT_API_URL;

public class ApiDataReader {
    public static String getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        String result = "";
        try {
            switch (apiCode) {
                case JAV_POP_URL:
                    apiContentStream = downloadUrlContent();
                    result = JAVPopuliacija.getJAVpopu(apiContentStream);
                    break;
                default:
            }
        }
        finally {
            if (apiContentStream != null) {
                apiContentStream.close();
            }
        }
        return result;
    }

    //Routine that creates and calls GET request to web page
    private static InputStream downloadUrlContent() throws IOException {
        URL url = new URL(Constants.JAV_POP_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }
}
