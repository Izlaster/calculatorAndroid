package com.example.calculator_db;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editEmail = findViewById(R.id.emailReg);
        final EditText editPasswd = findViewById(R.id.passwordReg);
        Button btnReg = findViewById(R.id.buttonReg);
        DAOUser dao = new DAOUser();
        mAuth = FirebaseAuth.getInstance();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPasswd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User usr = new User(editEmail.getText().toString());
                            dao.add(usr, mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Register.this, "Успешно!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(Register.this, Login.class));
                                    } else {
                                        Toast.makeText(Register.this, "Ошибка!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Register.this, "Ошибка! (Возможно почта уже используется)", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}