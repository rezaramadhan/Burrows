package id.wesudgitgud.burrows;

import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;

import id.wesudgitgud.burrows.Controller.ChatManager;
import id.wesudgitgud.burrows.models.Item;
import id.wesudgitgud.burrows.models.Pet;
import id.wesudgitgud.burrows.models.User;
import id.wesudgitgud.burrows.services.CounterTimeService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "LoginActivity";

    private FirebaseAuth firebaseAuth;

    private EditText fieldPassword;
    private EditText fieldEmail;
    private Button btnLogin;
    private TextView textRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fieldPassword = (EditText) findViewById(R.id.formPassword);
        fieldEmail = (EditText) findViewById(R.id.formEmail);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        textRegister = (TextView) findViewById(R.id.textRegister);

        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.btnLogin).setOnClickListener(this);
        findViewById(R.id.textRegister).setOnClickListener(this);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnLogin)
            check();
//            login(fieldEmail.getText().toString(), fieldPassword.getText().toString());
        else if (id == R.id.textRegister)
            changeToRegisterActivity();

    }

    private void changeToRegisterActivity() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void check() {
        User u = new User("resakemal");

        u.addFriend("umay");
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

        return valid;
    }

    private void login(String email, String password){
        if (!validateForm())
            return;

        Log.d(TAG, "try_login " + email + " " + password);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInWithEmail:failed", task.getException());
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login success",
                            Toast.LENGTH_SHORT).show();

                    ChatManager.getFirebaseToken();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }
}

