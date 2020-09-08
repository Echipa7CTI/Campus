package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PlangereStudentActivity extends AppCompatActivity {

    Plangere plangere;
    TextView subiectPlangere, detalierePlangere, raspunsAdministrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plangere_student);

        subiectPlangere = findViewById(R.id.subiectPlangere);
        detalierePlangere = findViewById(R.id.detalierePlangere);
        raspunsAdministrator = findViewById(R.id.text3);

        Intent i = getIntent();
        plangere = i.getParcelableExtra("plangere");

        String subiect = plangere.getTitlu();
        String detaliere = plangere.getDescriere();
        String raspuns = plangere.getRaspuns();

        subiectPlangere.setText(subiect);
        detalierePlangere.setText(detaliere);
        raspunsAdministrator.setText(raspuns);
    }
}