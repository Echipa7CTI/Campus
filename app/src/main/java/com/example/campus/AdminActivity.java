package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminActivity extends AppCompatActivity {

    Button admAnunturiBtn, admInscrieriBtn, admCantinaBtn, admPlangeriBtn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        admAnunturiBtn = findViewById(R.id.admAnunturiBtn);
        admInscrieriBtn = findViewById(R.id.admInscrieriBtn);
        admCantinaBtn = findViewById(R.id.admCantinaBtn);
        admPlangeriBtn = findViewById(R.id.admPlangeriBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        admAnunturiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, AnunturiAdmActivity.class));
            }
        });

        admInscrieriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, InscrieriAdmActivity.class));
            }
        });

        admCantinaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, CantinaAdmActivity.class));
            }
        });

        admPlangeriBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this, PlangeriAdmActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
        } else {
            Intent i = new Intent(AdminActivity.this, MainActivity.class);
            // set the new task and clear flags
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        }
    }
}