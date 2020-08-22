package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

public class CantinaAdmActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Spinner tipMancare;
    Button adaugaMancare;
    EditText numeMancare, pretMancare;

    RecyclerView listaMancare;
    List<Mancare> listMancare;
    MancareAdapter adapterMancare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cantina_adm);

        tipMancare = findViewById(R.id.tipMancare);
        adaugaMancare = findViewById(R.id.adaugaMancareBtn);
        numeMancare = findViewById(R.id.numeMancare);
        pretMancare = findViewById(R.id.pretMancare);
        listaMancare = findViewById(R.id.listaMancare);

        //setare lista de mancaruri
        listaMancare.setHasFixedSize(true);
        listaMancare.setLayoutManager(new GridLayoutManager(this,2));
        listMancare = new ArrayList<>();
        adapterMancare = new MancareAdapter(this,listMancare);
        listaMancare.setAdapter(adapterMancare);

        //aducerea mancarii din baza de date
        bdMancare();

        //lista de tipuri de mancare
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.tip_mancare, android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipMancare.setAdapter(staticAdapter);

        //butonul de adaugare de mancare
        adaugaMancare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tip = tipMancare.getSelectedItem().toString();
                String nume = numeMancare.getText().toString();
                String pret = pretMancare.getText().toString();

                if(Objects.equals(nume, "")){
                    Toast.makeText(CantinaAdmActivity.this, "Introdu un nume de mancare!", Toast.LENGTH_SHORT).show();
                } else if(Objects.equals(pret, "")){
                    Toast.makeText(CantinaAdmActivity.this, "Introdu un pret!", Toast.LENGTH_SHORT).show();
                } else if(Objects.equals(tip, "Alege tipul de mancare")) {
                    Toast.makeText(CantinaAdmActivity.this, "Alege tipul mancarii!", Toast.LENGTH_SHORT).show();
                } else {
                    adaugaMancare(nume, pret, tip);
                    Toast.makeText(CantinaAdmActivity.this, "Mancare introdusa cu succes!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //functia de aducere a mancarii din baza de date
    private void bdMancare() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Mancare");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listMancare.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Mancare mancareObj = ds.getValue(Mancare.class);
                    listMancare.add(mancareObj);
                }
                adapterMancare.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    //functia de adaugare mancare in baza de date
    private void adaugaMancare(String nume, String pret, String tip) {

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Mancare");

        String uploadId = databaseReference.push().getKey();

        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("numeMancare", nume);
        hashMap.put("pretMancare", pret);
        hashMap.put("tipMancare", tip);
        hashMap.put("idMancare", uploadId);

        databaseReference.child(uploadId).setValue(hashMap);

        }

    public void openMancareActivity(List<Mancare> arrayListMancare, int adapterPosition) {

        Mancare mancare = arrayListMancare.get(adapterPosition);
        Intent i = new Intent(CantinaAdmActivity.this, MancareActivity.class);
        i.putExtra("mancare", mancare);
        startActivity(i);

    }
}