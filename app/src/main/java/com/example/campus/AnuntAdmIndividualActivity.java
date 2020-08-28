package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class AnuntAdmIndividualActivity extends AppCompatActivity {

    EditText titluAnunt, textAnunt;
    Button salveazaAnunt, stergeAnunt;

    Anunt anunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunt_adm_individual);

        titluAnunt = findViewById(R.id.titluAnunt);
        textAnunt = findViewById(R.id.textAnunt);
        salveazaAnunt = findViewById(R.id.salveazaAnunt);
        stergeAnunt = findViewById(R.id.stergeAnunt);

        Intent i = getIntent();
        anunt = i.getParcelableExtra("anunt");

        final String uid = anunt.getUploadId();
        final String titluOrig = anunt.getTitluAnunt();
        final String textOrig = anunt.getTextAnunt();

        titluAnunt.setText(titluOrig, TextView.BufferType.EDITABLE);
        textAnunt.setText(textOrig,  TextView.BufferType.EDITABLE);

        salveazaAnunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String titlu = String.valueOf(titluAnunt.getText());
                String text = String.valueOf(textAnunt.getText());
                if(Objects.equals(titlu, "")){
                    Toast.makeText(AnuntAdmIndividualActivity.this, "Introdu un titlu!", Toast.LENGTH_SHORT).show();
                } else if(Objects.equals(text, "")){
                    Toast.makeText(AnuntAdmIndividualActivity.this, "Scrie anuntul!", Toast.LENGTH_SHORT).show();
                } else {
                    updateAnunt(titlu, text, uid);
                    onBackPressed();
                    Toast.makeText(AnuntAdmIndividualActivity.this, "Anunt updatat cu succes!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stergeAnunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAnunt(uid);
                onBackPressed();
                Toast.makeText(AnuntAdmIndividualActivity.this, "Anunt sters cu succes!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void deleteAnunt(String uid) {
        DatabaseReference delAnunt = FirebaseDatabase.getInstance().getReference("Anunturi").child(uid);
        delAnunt.removeValue();
    }

    private void updateAnunt(String titlu, String text, String uid) {
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Anunturi").child(uid);
        HashMap<String,Object> anunt = new HashMap<>();
        anunt.put("textAnunt", text);
        anunt.put("titluAnunt", titlu);
        mDatabase.updateChildren(anunt);
    }
}