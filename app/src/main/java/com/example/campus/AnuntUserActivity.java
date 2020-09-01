package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AnuntUserActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView textAnunt, titluAnunt;
    Anunt anunt;
    EditText introduMesaj;
    Button trimiteMesaj;

    RecyclerView comentariiRv;
    List<Comentariu> listComentariu;
    ComentariuAdapter adapterComentariu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anunt_user);

        textAnunt = findViewById(R.id.textAnunt);
        titluAnunt = findViewById(R.id.titluAnunt);
        comentariiRv = findViewById(R.id.comentariiRv);
        introduMesaj = findViewById(R.id.introduMesaj);
        trimiteMesaj = findViewById(R.id.trimiteMesaj);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        final String mail = user.getEmail();

        Intent i = getIntent();
        anunt = i.getParcelableExtra("anunt");

        final String titluOrig = anunt.getTitluAnunt();
        final String textOrig = anunt.getTextAnunt();
        final String idAnunt = anunt.getUploadId();

        titluAnunt.setText(titluOrig);
        textAnunt.setText(textOrig);

        trimiteMesaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mesaj = String.valueOf(introduMesaj.getText());
                if (Objects.equals(mesaj, "")){
                    Toast.makeText(AnuntUserActivity.this, "Introdu un mesaj!", Toast.LENGTH_SHORT).show();
                }
                else{
                    addComment(idAnunt, mail, mesaj);
                    introduMesaj.setText("");
                    hideKeyboard(AnuntUserActivity.this);
                }
            }
        });

        comentariiRv.setHasFixedSize(true);
        comentariiRv.setLayoutManager(new GridLayoutManager(this, 1));
        listComentariu = new ArrayList<>();
        adapterComentariu = new ComentariuAdapter(this, listComentariu);
        comentariiRv.setAdapter(adapterComentariu);

        bdComentarii(idAnunt);
    }

    private void bdComentarii(String idAnunt) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Anunturi").child(idAnunt).child("comentarii");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listComentariu.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Comentariu ComentariuObj = ds.getValue(Comentariu.class);
                    listComentariu.add(0, ComentariuObj);
                }
                adapterComentariu.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addComment(String idAnunt, String mail, String mesaj) {

        DatabaseReference mDatabase= FirebaseDatabase.getInstance().getReference().child("Anunturi").child(idAnunt).child("comentarii");
        HashMap<String,Object> comentariu = new HashMap<>();
        String key = mDatabase.push().getKey();
        comentariu.put("mailComentariu", mail);
        comentariu.put("textComentariu", mesaj);
        comentariu.put("idComentariu", key);

        mDatabase.child(key).setValue(comentariu);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}