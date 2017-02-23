package id.wesudgitgud.burrows;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import id.wesudgitgud.burrows.models.User;

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = "RegisterActivity";
    private FirebaseAuth firebaseAuth;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference userDatabase;

    private EditText fieldEmail;
    private EditText fieldPassword;
    private EditText fieldFullName;
    private EditText fieldUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference();

        userDatabase = firebaseDatabase.child("user");

        fieldEmail = ((EditText) findViewById(R.id.formEmail));
        fieldPassword = ((EditText) findViewById(R.id.formPassword));
        fieldUsername = ((EditText) findViewById(R.id.formUsername));
        fieldFullName = ((EditText) findViewById(R.id.formFullname));

    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void signUp (View view) {
        String email = ((EditText) findViewById(R.id.formEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.formPassword)).getText().toString();
        String username = ((EditText) findViewById(R.id.formUsername)).getText().toString();
        String fullname = ((EditText) findViewById(R.id.formFullname)).getText().toString();

        Log.d(TAG, "Signing up\n" + email + "\n" + password + "\n" + username + "\n" + fullname);
        createNewUser(email, password, username, fullname);
//        addUsertoDB("bagus@gmail.com", "bagus", "Bagus Sekali");
    }

    private boolean validateForm() {
        boolean valid = true;

        if (TextUtils.isEmpty(fieldEmail.getText().toString())) {
            valid = false;
            fieldEmail.setError("Required.");
        }
        if (TextUtils.isEmpty(fieldPassword.getText().toString())) {
            valid = false;
            fieldPassword.setError("Required.");
        }
        if (TextUtils.isEmpty(fieldUsername.getText().toString())) {
            valid = false;
            fieldUsername.setError("Required.");
        }
        if (TextUtils.isEmpty(fieldFullName.getText().toString())) {
            valid = false;
            fieldFullName.setError("Required.");
        }

        return valid;
    }

    private void createNewUser(final String email, String password, final String username, final String fullname) {
        if (!validateForm())
            return;

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "SIGNIN:------------------------" + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d(TAG, "-----failed" + task.getException());
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Signin success",
                                    Toast.LENGTH_SHORT).show();

                            addUsertoDB(email, username, fullname);

                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                    }
                });
    }

    private void addUsertoDB(String email, String username, String fullname) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(username)
                    .build();

            firebaseUser.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User profile updated.");
                            }
                        }
                    });
        }

        User newuser = new User(email, fullname);

        Log.d(TAG, "createuser");

        DatabaseReference newUserRef = userDatabase.child(username);
        newUserRef.setValue(newuser);
    }

}
