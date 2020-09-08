package com.example.campus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class PlangereAdminActivity extends AppCompatActivity {

    TextView subiectPlangere, detalierePlangere;
    EditText raspunsPlangere;
    RadioGroup statusRG;
    Button send;
    String status;

    Plangere plangere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plangere_admin);

        subiectPlangere = findViewById(R.id.subiectPlangere);
        detalierePlangere = findViewById(R.id.detalierePlangere);
        raspunsPlangere = findViewById(R.id.text3);
        statusRG = findViewById(R.id.radioGrup);
        send = findViewById(R.id.trimiteBtn);

        Intent i = getIntent();
        plangere = i.getParcelableExtra("plangere");

        String titlu = plangere.getTitlu();
        String detaliere = plangere.getDescriere();

        subiectPlangere.setText(titlu);
        detalierePlangere.setText(detaliere);

        final String idPlangere = plangere.getUploadId();

        statusRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radio_rezolvat) {
                    status = "Rezolvat";
                } else {
                    status = "Nerezolvat";
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String raspuns = String.valueOf(raspunsPlangere.getText());

                if(Objects.equals(raspuns, "") || statusRG.getCheckedRadioButtonId() == -1){
                    Toast.makeText(PlangereAdminActivity.this, "Introduceti date corecte.", Toast.LENGTH_SHORT).show();
                } else {
                    trimiteRaspuns(idPlangere, status, raspuns);
                }
            }
        });
    }

    private void trimiteRaspuns(String idPlangere, String status, String raspuns) {
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference("Plangeri").child(idPlangere);
        HashMap<String,Object> raspunde = new HashMap<>();
        raspunde.put("status", status);
        raspunde.put("raspuns", raspuns);
        mDatabase.updateChildren(raspunde);
    }
}