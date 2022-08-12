package com.example.animalplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.animalplace.db.DbUsers;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextInputEditText inputEmail, inputPassword;
    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        FirebaseApp.initializeApp(this);
        inputEmail = findViewById(R.id.editText_email);
        inputPassword = findViewById(R.id.editText_password);
        btnSignIn = findViewById(R.id.button_sign_in);
        btnSignUp = findViewById(R.id.button_sign_up);
        mAuth = FirebaseAuth.getInstance();

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
                            Log.d("AuthActivity", "User inserted successfully");
                            mAuth.createUserWithEmailAndPassword(email, password)
                                            .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                                                @Override
                                                public void onComplete(@NonNull Task<AuthResult> task) {
                                                    if(task.isSuccessful()){
                                                        Toast.makeText(AuthActivity.this, "User created", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(AuthActivity.this, "User not created", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                            cleanFields();
                        } else {
                            Toast.makeText(AuthActivity.this, "User not created", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(AuthActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(AuthActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if(user != null){
                                            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    } else {
                                        Toast.makeText(AuthActivity.this, "User not logged in", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    private void cleanFields() {
        inputEmail.setText("");
        inputPassword.setText("");
        inputPassword.clearFocus();
    }
}