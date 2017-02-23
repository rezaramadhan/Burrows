package id.wesudgitgud.burrows.models;

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
 * Created by rezaramadhan on 23/02/2017.
 */

public class Pet {
    private final String TAG = "Pet_Model";
    public String name;
    public int exp;
    public int lv;

    public Pet() {

    }

    public Pet(String name, int exp, int lv) {
        this.name = name;
        this.exp = exp;
        this.lv = lv;
    }

    public Pet(String name) {
        this.name = name;
        this.exp = 0;
        this.lv = 1;
    }

    public void retrievePetData(int username, int petid) {
        String location = "https://burrows-a36e9.firebaseio.com/user/" + username + ".json";

        Log.d(TAG, "loc:\n" + location);
        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(location);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String resultJSON = getStringFromInputStream(in);
            Log.d(TAG, "result \n" + resultJSON);
            parseJSON(resultJSON, petid);
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException je) {
            je.printStackTrace();
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

    public void parseJSON(String json, int id) throws JSONException {
        JSONArray reader = new JSONArray(json);
        name = reader.getJSONObject(id).getString("name");
        exp = reader.getJSONObject(id).getInt("exp");
        lv = reader.getJSONObject(id).getInt("lv");
    }

    @Override
    public String toString() {
        String str = "";
        str += "name " + this.name;
        str += "\nexp " + this.exp;
        str += "\nlv " + lv;
        return str;
    }
}
