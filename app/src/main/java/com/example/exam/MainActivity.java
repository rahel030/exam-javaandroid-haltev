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

public class MainActivity extends AppCompatActivity {
EditText etEmail, etPassword;
Button btnLogin, btnRegister;
FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmail = findViewById(R.id.edit_email);
        etPassword = findViewById(R.id.editPw);
        btnLogin = findViewById(R.id.loginBtn);
        btnRegister = findViewById(R.id.bikin_akunBtn);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(v -> registerUser());
        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void registerUser() {
        startActivity(new Intent(this, Register.class));
    }

    private void loginUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, Home.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Login Gagal" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}