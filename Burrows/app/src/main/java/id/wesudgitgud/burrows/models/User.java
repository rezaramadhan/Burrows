package id.wesudgitgud.burrows.models;

import android.util.Log;

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

public class User {
    private String TAG = "User_Model";

    public String fullname;
    public String email;
    public int exp;
    public int money;
    public int highscore;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String fullname, String email) {
        this.fullname = fullname;
        this.email = email;
        this.exp = 0;
        this.money = 1000;
        this.highscore = 0;
    }

    public void retrieveUserData(String username) {
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
            parseJSON(resultJSON);
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

    private void parseJSON(String json) throws JSONException {
        JSONObject reader = new JSONObject(json);
        this.email = reader.getString("email");
        this.exp = reader.getInt("exp");
        this.fullname = reader.getString("fullname");
        this.highscore = reader.getInt("highscore");
        this.money = reader.getInt("money");
    }

    @Override
    public String toString() {
        String str = "";
        str += "fullname " + fullname;
        str += "\nemail " + email;
        str += "\nexp " + exp;
        str += "\nhighscore " + highscore;
        str += "\nmoney " + money;
        return str;
    }

    public static String getTokenFromUsername (String username) {
        String location = "https://burrows-a36e9.firebaseio.com/user/" + username + "/token.json";

        URL url;
        HttpURLConnection urlConnection;
        try {
            url = new URL(location);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }

            String token = out.toString();

            urlConnection.disconnect();
            return token;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
