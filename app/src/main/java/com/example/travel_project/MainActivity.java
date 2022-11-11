package com.example.travel_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText emailTextView, passwordTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.userName);
        passwordTextView = findViewById(R.id.username);
        Button btn = findViewById(R.id.btnToCountry);
        btn.setOnClickListener(view -> loginUserAccount());

        Button toSignUp = findViewById(R.id.toSignUp);
        toSignUp.setOnClickListener(view -> {
            Intent intent2 = new Intent(this, SignupActivity.class);
            startActivity(intent2);
        });
    }

    private void loginUserAccount() {
        String email, password;
        email = emailTextView.getText().toString();
        password = passwordTextView.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
