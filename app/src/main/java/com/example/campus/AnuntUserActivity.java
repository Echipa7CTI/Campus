package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AnuntUserActivity extends AppCompatActivity {

    TextView textAnunt, titluAnunt;
    Anunt anunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunt_user);

        textAnunt = findViewById(R.id.textAnunt);
        titluAnunt = findViewById(R.id.titluAnunt);

        Intent i = getIntent();
        anunt = i.getParcelableExtra("anunt");

        final String titluOrig = anunt.getTitluAnunt();
        final String textOrig = anunt.getTextAnunt();

        titluAnunt.setText(titluOrig);
        textAnunt.setText(textOrig);
    }
}