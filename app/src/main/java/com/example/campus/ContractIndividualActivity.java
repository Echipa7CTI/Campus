package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static com.google.firebase.storage.FirebaseStorage.getInstance;

public class ContractIndividualActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    Cerere cerere;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    ImageView img1, img2;
    Button descarca, accepta, respinge;

    private static final String FILE_NAME = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_individual);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Contracte");
        storageReference = getInstance().getReference();

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        descarca = findViewById(R.id.descarcaBtn);
        accepta = findViewById(R.id.acceptaBtn);
        respinge = findViewById(R.id.respingeBtn);

        descarca.setVisibility(View.GONE);
        accepta.setVisibility(View.GONE);
        respinge.setVisibility(View.GONE);

        Intent i = getIntent();
        cerere = i.getParcelableExtra("cerere");

        assert cerere != null;
        tv1.setText(cerere.getNumeStudent());
        tv2.setText(cerere.getPrenumeStudent());
        tv3.setText(cerere.getNumeFacultate());
        tv4.setText(cerere.getCicluStudii());
        tv5.setText(cerere.getAn());
        tv6.setText(cerere.getAdresa());
        tv7.setText(cerere.getTelefon());
        tv8.setText(cerere.getCaminDorit());

        Query query = databaseReference.orderByChild("idUser").equalTo(cerere.getIdUser());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    String image1 = ""+ds.child("pozaBuletin").getValue();
                    String image2 = ""+ds.child("pozaSemnatura").getValue();

                    try{
                        Picasso.get().load(image1).into(img1);
                        Picasso.get().load(image2).into(img2);
                    }
                    catch(Exception e){
                        Picasso.get().load(R.drawable.ic_baseline_emoji_people_24).into(img1);
                        Picasso.get().load(R.drawable.ic_baseline_emoji_people_24).into(img2);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String status = cerere.getStatus();
        if(Objects.equals(status, "Trimis")){
            accepta.setVisibility(View.VISIBLE);
            respinge.setVisibility(View.VISIBLE);
        } else if (Objects.equals(status, "Acceptat")){
            descarca.setVisibility((View.VISIBLE));
        }

        accepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptaCerere();
                onBackPressed();
            }
        });

        respinge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                respingeCerere();
                onBackPressed();
            }
        });



    }

    private void respingeCerere() {
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Contracte").child(cerere.getIdUser());
        HashMap<String,Object> cerere = new HashMap<>();
        cerere.put("status", "Refuzat");
        mDatabase.updateChildren(cerere);
    }

    private void acceptaCerere() {
        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Contracte").child(cerere.getIdUser());
        HashMap<String,Object> cerere = new HashMap<>();
        cerere.put("status", "Acceptat");
        mDatabase.updateChildren(cerere);
    }
}