package id.wesudgitgud.burrows.Controller;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by rezaramadhan on 25/02/2017.
 */

public class DatabaseManager {
    private String baseLoc = "https://burrows-a36e9.firebaseio.com/";
    private String TAG = "DB_Manager";
    private String location;
    private String data;

    public DatabaseManager(String loc){
        this.location = baseLoc + loc + ".json";
        fetchData();
    }

    private void fetchData() {
        Log.d(TAG, "fetchData from: " + location);
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(location);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            data = getStringFromInputStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getStringFromInputStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        return out.toString();
    }

    public JSONObject getJSONDObject() throws JSONException {
        return new JSONObject(data);
    }

    public JSONArray getJSONArray() throws JSONException {
        return new JSONArray(data);
    }

    public String getData() {
        return this.data;
    }
}
