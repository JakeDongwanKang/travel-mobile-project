package com.example.travel_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText userTextView, passwordTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        userTextView = findViewById(R.id.username);
        passwordTextView = findViewById(R.id.password);
        Button btn = findViewById(R.id.signUp);

        btn.setOnClickListener(v -> registerNewUser());
    }

    private void registerNewUser() {
        String userName, password;
        userName = userTextView.getText().toString();
        password = passwordTextView.getText().toString();

        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(getApplicationContext(), "Please enter a username!", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter a password!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(userName, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Registration successful!",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupActivity.this, TitleActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Registration failed, please try again.", Toast.LENGTH_LONG).show();
                    }
                });
    }
}