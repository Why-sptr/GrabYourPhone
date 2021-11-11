package com.example.grabyourphone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpActivity extends AppCompatActivity {

    // Initialize

    EditText name, number, email, pass;
    Button btnSignup;
    TextView signUpTV;



    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Typecasting

        name = findViewById(R.id.name_signup);
        number = findViewById(R.id.number_signup);
        email = findViewById(R.id.email_signup);
        pass = findViewById(R.id.pass_signup);
        btnSignup = findViewById(R.id.btn_signup);
        signUpTV = findViewById(R.id.login_tv);

        // firebase
        auth = FirebaseAuth.getInstance();


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID, password, userName, userNumber;

                userName = name.getText().toString();
                userNumber = number.getText().toString();
                emailID = email.getText().toString();
                password = pass.getText().toString();

                if (userName.isEmpty()){

                    name.setError("Please enter your Name");
                    name.requestFocus();

                }else if (userNumber.isEmpty()){

                    number.setError("Please enter your Name");
                    number.requestFocus();

                }else if (emailID.isEmpty()){

                    email.setError("Please enter your Name");
                    email.requestFocus();

                }else if (password.isEmpty()){

                    pass.setError("Please enter your Name");
                    pass.requestFocus();

                }else {

                    auth.createUserWithEmailAndPassword(emailID, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });

        signUpTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null){
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }
}

