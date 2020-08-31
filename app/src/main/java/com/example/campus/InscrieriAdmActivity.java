package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InscrieriAdmActivity extends AppCompatActivity {

    RecyclerView cerereRv;
    List<Cerere> listCerere;
    InscrieriAdmAdapter adapterCerere;

    Spinner selectContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrieri_adm);

        selectContract = findViewById(R.id.tip1);
        cerereRv = findViewById(R.id.listaContracte);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.tip_contract, android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectContract.setAdapter(staticAdapter);

        cerereRv.setHasFixedSize(true);
        cerereRv.setLayoutManager(new GridLayoutManager(this, 1));
        listCerere = new ArrayList<>();
        adapterCerere = new InscrieriAdmAdapter(this, listCerere);
        cerereRv.setAdapter(adapterCerere);


        selectContract.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = selectContract.getSelectedItem().toString();

                ArrayList<Cerere> filteredList = new ArrayList<>();

                for (Cerere cerere : listCerere) {
                    if (cerere.getStatus().contains(text)) {
                        filteredList.add(cerere);
                    }
                }
                adapterCerere.filterList(filteredList);
                adapterCerere.notifyDataSetChanged();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bdCerere();

    }

    private void bdCerere() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Contracte");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listCerere.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Cerere cerereObj = ds.getValue(Cerere.class);
                    listCerere.add(cerereObj);
                }
                adapterCerere.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openMancareActivity(List<Cerere> arrayListCerere, int adapterPosition) {
        Cerere cerere = arrayListCerere.get(adapterPosition);
        Intent i = new Intent(InscrieriAdmActivity.this, ContractIndividualActivity.class);
        i.putExtra("cerere", cerere);
        startActivity(i);
    }
}