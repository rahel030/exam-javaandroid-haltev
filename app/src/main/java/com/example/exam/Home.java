package com.example.exam;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {
EditText editTextMakan;
Button btnKirim;
DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextMakan = findViewById(R.id.edit_makan);
        btnKirim = findViewById(R.id.tombolBtn);

        // 🔗 Hubungkan ke Realtime Database
        databaseRef = FirebaseDatabase.getInstance().getReference("makanan");

        btnKirim.setOnClickListener(v -> {
            String makanan = editTextMakan.getText().toString().trim();

            if (makanan.isEmpty()) {
                Toast.makeText(Home.this, "Isi dulu makananmu!", Toast.LENGTH_SHORT).show();
            } else {
                // Simpan ke Firebase
                String id = databaseRef.push().getKey(); // generate ID unik
                databaseRef.child(id).setValue(makanan)
                        .addOnSuccessListener(unused -> {
                            Toast.makeText(Home.this, "Data terkirim ke Firebase!", Toast.LENGTH_SHORT).show();
                            editTextMakan.setText("");
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(Home.this, "Gagal: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}