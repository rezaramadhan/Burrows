package id.wesudgitgud.burrows.Controller;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import id.wesudgitgud.burrows.models.User;

/**
 * Created by rezaramadhan on 24/02/2017.
 */

public class ChatManager {
    public static void getFirebaseToken() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

        DatabaseReference tokenDatabaseRef;

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String username = firebaseUser.getDisplayName();

        tokenDatabaseRef = FirebaseDatabase.getInstance().getReference().child("user").child(username).child("token");
        tokenDatabaseRef.setValue(refreshedToken);
    }

    public static void sendChat(String sendUsername, String recvUsername, String message){
        String recvToken = User.getTokenFromUsername(recvUsername);

        String data =   "{\"notification\": {\n" +
                        "    \"title\": \"" + sendUsername + "\",\n" +
                        "    \"body\": \"" + message + "\"\n" +
                        "  },\n" +
                        "  \"to\" : " + recvToken + "}";
        String loc = "https://fcm.googleapis.com/fcm/send";
        Log.d("Chat_Manager", "data\n" + data);
        URL url = null; //Enter URL here
        try {
            url = new URL(loc);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST"); // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            httpURLConnection.setRequestProperty("Content-Type", "application/json"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.setRequestProperty("Authorization", "key=AAAAVc_Z_dU:APA91bFhDf-fK4XpIQysd3nJ4ostsrxrG4bRh38MuBkgqiOPteNw3CH5JYFNlUEQXYR1XpLRZP6ZFqbTJS3JH4AYepLj3gfX3UIlPafXyaIvyZ7ozFUhXvqUbXFJouuMS1gCwPOmj1oW"); // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.connect();

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
