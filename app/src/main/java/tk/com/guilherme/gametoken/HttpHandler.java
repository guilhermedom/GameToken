package tk.com.guilherme.gametoken;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHandler {

    public HttpHandler() {

    }

    public String makeServiceCall(String baseUrl) {
        String urlReturn = null;

        try {
            URL url = new URL(baseUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            urlReturn = convertInputToString(inputStream);
        } catch (Exception e) {
            Log.e("HTTP Handler", "Exception: " + e.getMessage());
        }

        return urlReturn;
    }

    private String convertInputToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();

        String singleLine;
        try {
            while ((singleLine = reader.readLine()) != null) {
                stringBuilder.append(singleLine).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
