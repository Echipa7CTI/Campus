package com.example.campus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AnunturiFragment extends Fragment {

    RecyclerView listaAnunturi;
    List<Anunt> listAnunt;
    AnuntUserAdapter adapterAnunt;
    Context ctx = getActivity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anunturi, container, false) ;

        listaAnunturi = view.findViewById(R.id.listaAnunturi);

        listaAnunturi.setHasFixedSize(true);
        listaAnunturi.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        listAnunt = new ArrayList<>();
        adapterAnunt = new AnuntUserAdapter(getActivity(), listAnunt, this);
        listaAnunturi.setAdapter(adapterAnunt);

        bdAnunturi();

        return view;
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

    public void openMancareActivity(List<Anunt> arrayListAnunt, int adapterPosition) {
        Anunt anunt = arrayListAnunt.get(adapterPosition);
        Intent i = new Intent(getActivity(), AnuntUserActivity.class);
        i.putExtra("anunt", anunt);
        startActivity(i);

    }
}
