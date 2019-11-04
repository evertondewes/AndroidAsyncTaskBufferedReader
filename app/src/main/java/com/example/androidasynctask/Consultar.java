package com.example.androidasynctask;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Consultar extends AsyncTask<String, String, String> {
    static final String REQUEST_METHOD = "GET";
    static final int READ_TIMEOUT = 15000;
    static final int CONNECTION_TIMEOUT = 15000;
    private String URL;

    public Consultar(String URL) {
        this.URL = URL;
    }

    @Override
    protected String doInBackground(String... params) {
        String result;
        String inputLine;

        try {
            // connect to the server
            URL myUrl = new URL("http://" + this.URL + "/teste/index.php");
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            connection.connect();

            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
            result = "error";
        }
        Log.d("Retorno:", result);
        return result;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }
}
