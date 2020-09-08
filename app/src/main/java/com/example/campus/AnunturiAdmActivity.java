package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class AnunturiAdmActivity extends AppCompatActivity {

    EditText titluAnunt, textAnunt;
    Button posteazaAnuntBtn;

    RecyclerView anuntRv;
    List<Anunt> listAnunt;
    AnuntAdmAdapter adapterAnunt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunturi_adm);

        anuntRv = findViewById(R.id.listaAnunturi);
        titluAnunt = findViewById(R.id.titluAnunt);
        textAnunt = findViewById(R.id.textAnunt);
        posteazaAnuntBtn = findViewById(R.id.posteazaAnuntBtn);

        posteazaAnuntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String titlu = String.valueOf(titluAnunt.getText());
                String text = String.valueOf(textAnunt.getText());
                if (Objects.equals(titlu, "")) {
                    Toast.makeText(AnunturiAdmActivity.this, "Introdu un titlu!", Toast.LENGTH_SHORT).show();
                } else if (Objects.equals(text, "")) {
                    Toast.makeText(AnunturiAdmActivity.this, "Introdu textul anuntului!", Toast.LENGTH_SHORT).show();
                } else {
                    posteazaAnunt(titlu, text, currentDate);
                    titluAnunt.setText("");
                    textAnunt.setText("");
                }
            }
        });

        anuntRv.setHasFixedSize(true);
        anuntRv.setLayoutManager(new GridLayoutManager(this, 1));
        listAnunt = new ArrayList<>();
        adapterAnunt = new AnuntAdmAdapter(this, listAnunt);
        anuntRv.setAdapter(adapterAnunt);

        bdAnunturi();

    }

    private void bdAnunturi() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Anunturi");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listAnunt.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Anunt anuntObj = ds.getValue(Anunt.class);
                    listAnunt.add(0, anuntObj);
                }
                adapterAnunt.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void posteazaAnunt(String titlu, String text, String data) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Anunturi");
        String uploadId = ref.push().getKey();

        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("titluAnunt", titlu);
        hashMap.put("textAnunt", text);
        hashMap.put("uploadId", uploadId);
        hashMap.put("data", data);

        ref.child(uploadId).setValue(hashMap);

        Toast.makeText(this, "Anunt adaugat cu succes!", Toast.LENGTH_SHORT).show();

    }

    public void openMancareActivity(List<Anunt> arrayListAnunt, int adapterPosition) {
        Anunt anunt = arrayListAnunt.get(adapterPosition);
        Intent i = new Intent(AnunturiAdmActivity.this, AnuntAdmIndividualActivity.class);
        i.putExtra("anunt", anunt);
        startActivity(i);
    }
}