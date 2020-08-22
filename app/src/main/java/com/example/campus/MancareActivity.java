package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class MancareActivity extends AppCompatActivity {

    EditText editNumeMancare, editPretMancare;
    Spinner editTipMancare;
    Button updateMancare, stergeMancare;

    Mancare mancare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mancare);

        editNumeMancare = findViewById(R.id.editNumeMancare);
        editPretMancare = findViewById(R.id.editPretMancare);
        editTipMancare = findViewById(R.id.editTipMancare);
        updateMancare = findViewById(R.id.updateMancare);
        stergeMancare = findViewById(R.id.stergeMancare);

        Intent i = getIntent();
        mancare = i.getParcelableExtra("mancare");

        final String uid = mancare.getIdMancare();

        editNumeMancare.setText(mancare.getNumeMancare(), TextView.BufferType.EDITABLE);
        editPretMancare.setText(mancare.getPretMancare(), TextView.BufferType.EDITABLE);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.tip_mancare, android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTipMancare.setAdapter(staticAdapter);

        updateMancare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tip = editTipMancare.getSelectedItem().toString();
                String nume = editNumeMancare.getText().toString();
                String pret = editPretMancare.getText().toString();

                if(Objects.equals(nume, "")){
                    Toast.makeText(MancareActivity.this, "Introdu un nume de mancare!", Toast.LENGTH_SHORT).show();
                } else if(Objects.equals(pret, "")){
                    Toast.makeText(MancareActivity.this, "Introdu un pret!", Toast.LENGTH_SHORT).show();
                } else if(Objects.equals(tip, "Alege tipul de mancare")) {
                    Toast.makeText(MancareActivity.this, "Alege tipul mancarii!", Toast.LENGTH_SHORT).show();
                } else {
                    updateMancare(nume, pret, tip, uid);
                    Toast.makeText(MancareActivity.this, "Mancare updatata cu succes!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        stergeMancare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMancare(uid);
                onBackPressed();
                Toast.makeText(MancareActivity.this, "Mancare stearsa cu succes!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteMancare(String uid) {
        DatabaseReference delMancare = FirebaseDatabase.getInstance().getReference("Mancare").child(uid);
        delMancare.removeValue();
    }

    private void updateMancare(String nume, String pret, String tip, String uid) {
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Mancare").child(uid);
        HashMap<String,Object> mancare = new HashMap<>();
        mancare.put("numeMancare", nume);
        mancare.put("pretMancare", pret);
        mancare.put("tipMancare", tip);
        mDatabase.updateChildren(mancare);
    }
}