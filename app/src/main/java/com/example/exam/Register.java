package com.example.exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.register_email);
        etPassword = findViewById(R.id.register_pw);
        btnLogin = findViewById(R.id.register_loginBtn);
        btnRegister = findViewById(R.id.register_Btn);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(v -> registerUser());
        btnLogin.setOnClickListener(v -> loginUser());

    }

    private void loginUser() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void registerUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Gagal" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}