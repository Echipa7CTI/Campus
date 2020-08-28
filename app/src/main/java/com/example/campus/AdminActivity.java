package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {

    Button admAnunturiBtn, admInscrieriBtn, admCantinaBtn, admPlangeriBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        admAnunturiBtn = findViewById(R.id.admAnunturiBtn);
        admInscrieriBtn = findViewById(R.id.admInscrieriBtn);
        admCantinaBtn = findViewById(R.id.admCantinaBtn);
        admPlangeriBtn = findViewById(R.id.admPlangeriBtn);

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
}