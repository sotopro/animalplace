package com.example.animalplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.animalplace.db.DbUsers;
import com.google.android.material.textfield.TextInputEditText;

public class AuthActivity extends AppCompatActivity {

    TextInputEditText inputEmail, inputPassword;
    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        inputEmail = findViewById(R.id.editText_email);
        inputPassword = findViewById(R.id.editText_password);
        btnSignIn = findViewById(R.id.button_sign_in);
        btnSignUp = findViewById(R.id.button_sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(AuthActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    DbUsers dbUsers = new DbUsers(AuthActivity.this);
                    boolean exists = dbUsers.isUserExists(email, password);
                    if (exists) {
                        Toast.makeText(AuthActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    } else {
                        long id = dbUsers.insertUser(email, password);

                        if (id > 0) {
                            Toast.makeText(AuthActivity.this, "User created", Toast.LENGTH_SHORT).show();
                            cleanFields();
                        } else {
                            Toast.makeText(AuthActivity.this, "User not created", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void cleanFields() {
        inputEmail.setText("");
        inputPassword.setText("");
    }
}