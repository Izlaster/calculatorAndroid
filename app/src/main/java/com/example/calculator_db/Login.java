package com.example.calculator_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPasswd;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.emailLog);
        editPasswd = findViewById(R.id.passwordLog);
        final TextView register = findViewById(R.id.hereLog);
        register.setOnClickListener(this);

        Button btnLog = findViewById(R.id.buttonLog);
        btnLog.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hereLog:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.buttonLog:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        if (editEmail.getText().toString().isEmpty()) {
            editEmail.setError("Email отсутсвует!");
            editEmail.requestFocus();
            return;
        }

        if (editPasswd.getText().toString().isEmpty()) {
            editPasswd.setError("Пароль отсутсвует!");
            editPasswd.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(editEmail.getText().toString(), editPasswd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, Calculator.class));
                }
                else {
                    Toast.makeText(Login.this, "Ошибка! Проверьте свой почтовый адрес или пароль", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}