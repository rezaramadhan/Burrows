package id.wesudgitgud.burrows.services;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by rezaramadhan on 23/02/2017.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference tokenDatabaseRef;

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String username = firebaseUser.getDisplayName();

        tokenDatabaseRef = FirebaseDatabase.getInstance().getReference().child("user").child(username).child("token");
        tokenDatabaseRef.setValue(refreshedToken);
    }
}
