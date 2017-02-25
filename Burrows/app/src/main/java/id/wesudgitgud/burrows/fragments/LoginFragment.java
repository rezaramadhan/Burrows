package id.wesudgitgud.burrows.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import id.wesudgitgud.burrows.R;

/**
 * Created by rezaramadhan on 25/02/2017.
 */

public class LoginFragment extends Fragment {
    public boolean isPortrait;

    private String TAG = "LoginActivity";

    private FirebaseAuth firebaseAuth;

    private EditText fieldPassword;
    private EditText fieldEmail;
    private Button btnLogin;
    private TextView textRegister;

    public LoginFragment() {
        isPortrait = true;
    }

    public LoginFragment(boolean b) {
        isPortrait = b;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (isPortrait)
            return inflater.inflate(R.layout.fragment_login_portrait, container, false);
        else
            return inflater.inflate(R.layout.fragment_login_landscape, container, false);
    }
}
