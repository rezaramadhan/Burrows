package id.wesudgitgud.burrows.models;

import android.app.Service;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

import id.wesudgitgud.burrows.Controller.DatabaseManager;

/**
 * Created by rezaramadhan on 23/02/2017.
 */

public class User extends UserData{
    private String username;
    private DatabaseManager databaseManager;
    private DatabaseReference databaseReference;

    public User(String username) {
        this.username = username;
        databaseManager = new DatabaseManager("user/" + username);
    }

    private void addAsFriend(String myUsername, String friendUsername) {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("friends").child(myUsername);
        databaseManager.setURL("friends/" + myUsername);

        try {
            JSONArray oldFriends;
            Log.d(TAG, databaseManager.getData());
            if (databaseManager.getData() == null || databaseManager.getData().equals("null"))
                oldFriends = new JSONArray();
            else
                oldFriends = databaseManager.getJSONArray();

            JSONObject newFriend = new JSONObject();
            newFriend.put("uname", friendUsername);
            oldFriends.put(newFriend);
            databaseReference.setValue(DatabaseManager.convertToFirebaseArray(oldFriends));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addFriend(String friendUsername) {
        addAsFriend(this.username, friendUsername);
        addAsFriend(friendUsername, this.username);
    }

    public String[] getAllFriend() {
        databaseManager.setURL("friends/" + username);

        try {
            JSONArray friends = databaseManager.getJSONArray();
            String listFriend[] = new String[friends.length()];

            for (int i = 0; i < friends.length(); i++) {
                listFriend[i] = friends.getJSONObject(i).getString("uname");
            }
            return  listFriend;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
