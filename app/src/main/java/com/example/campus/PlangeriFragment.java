package com.example.campus;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlangeriFragment extends Fragment {

    EditText subiectEt, detaliereEt;
    Button sendBtn;
    Spinner statusSp;
    RecyclerView plangeriRv;
    List<Plangere> listPlangere;
    PlangereUserAdapter adapterPlangere;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plangeri, container, false) ;

        subiectEt = view.findViewById(R.id.subiectPlangere);
        detaliereEt = view.findViewById(R.id.detalierePlangere);
        sendBtn = view.findViewById(R.id.trimiteBtn);
        statusSp = view.findViewById(R.id.tip1);
        plangeriRv = view.findViewById(R.id.plangeri);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.tip_plangere, android.R.layout.simple_spinner_item);
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSp.setAdapter(staticAdapter);

        plangeriRv.setHasFixedSize(true);
        plangeriRv.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        listPlangere = new ArrayList<>();
        adapterPlangere = new PlangereUserAdapter(getActivity(), listPlangere, this);
        plangeriRv.setAdapter(adapterPlangere);


        statusSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String text = statusSp.getSelectedItem().toString();

                ArrayList<Plangere> filteredList = new ArrayList<>();

                for (Plangere plangere : listPlangere) {
                    if (plangere.getStatus().contains(text)) {
                        filteredList.add(plangere);
                    }
                }
                adapterPlangere.filterList(filteredList);
                adapterPlangere.notifyDataSetChanged();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bdPlangeri();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final String email = user.getEmail();
        final String userId = user.getUid();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subiect = String.valueOf(subiectEt.getText());
                String detaliere = String.valueOf(detaliereEt.getText());
                if(Objects.equals(subiect, "") || Objects.equals(detaliere, "")){
                    Toast.makeText(getActivity(), "Introdu date corecte!", Toast.LENGTH_SHORT).show();
                } else{
                    sendToFirebase(subiect, detaliere, email, userId);
                }
            }
        });



        return view;
    }

    private void bdPlangeri() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Plangeri");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listPlangere.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Plangere plangereObj = ds.getValue(Plangere.class);
                    listPlangere.add(plangereObj);
                }
                adapterPlangere.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendToFirebase(String subiect, String detaliere, String email, String userId) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Plangeri");
        String uploadId = ref.push().getKey();

        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("titlu", subiect);
        hashMap.put("descriere", detaliere);
        hashMap.put("uploadId", uploadId);
        hashMap.put("email", email);
        hashMap.put("userId", userId);
        hashMap.put("status", "Nerevizuit");
        hashMap.put("raspuns", "");

        ref.child(uploadId).setValue(hashMap);

        Toast.makeText(getActivity(), "Plangere trimisa cu succes!", Toast.LENGTH_SHORT).show();
    }

    public void openPlangereActivity(List<Plangere> arrayListPlangere, int adapterPosition) {
        Plangere plangere = arrayListPlangere.get(adapterPosition);
        Intent i = new Intent(getActivity(), PlangereStudentActivity.class);
        i.putExtra("plangere", plangere);
        startActivity(i);
    }
}
