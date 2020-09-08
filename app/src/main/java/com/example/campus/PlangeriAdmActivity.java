package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlangeriAdmActivity extends AppCompatActivity {

    RecyclerView plangeriRv;
    PlangereAdmAdapter adapter;
    List<Plangere> listPlangere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plangeri_adm);

        plangeriRv = findViewById(R.id.plangeri);

        plangeriRv.setHasFixedSize(true);
        plangeriRv.setLayoutManager(new GridLayoutManager(this, 1));
        listPlangere = new ArrayList<>();
        adapter = new PlangereAdmAdapter(this, listPlangere);
        plangeriRv.setAdapter(adapter);

        bdPlangeri();
    }

    private void bdPlangeri() {
        Query query = FirebaseDatabase.getInstance().getReference("Plangeri");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listPlangere.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Plangere plangereObj = ds.getValue(Plangere.class);
                    String status = plangereObj.getStatus();
                    if(Objects.equals(status, "Nerevizuit")){
                        listPlangere.add(plangereObj);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openPlangereActivity(List<Plangere> arrayListPlangere, int adapterPosition) {
        Plangere plangere = arrayListPlangere.get(adapterPosition);
        Intent i = new Intent(this, PlangereAdminActivity.class);
        i.putExtra("plangere", plangere);
        startActivity(i);
    }
}