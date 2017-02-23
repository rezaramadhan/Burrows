package id.wesudgitgud.burrows;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnLogin)
            login(fieldEmail.getText().toString(), fieldPassword.getText().toString());
        else if (id == R.id.textRegister)
            changeToRegisterActivity();

    }

    private void changeToRegisterActivity() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void login(String email, String password){
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
                    Toast.makeText(LoginActivity.this, "Failed to login",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login success",
                            Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        });
    }
}

