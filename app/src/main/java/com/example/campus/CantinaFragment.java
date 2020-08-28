package com.example.campus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CantinaFragment extends Fragment {

    TextView dataTv;
    TextView supaMeniu, ciorbaMeniu, fel2Meniu, carneMeniu, desertMeniu;
    TextView pretSupa, pretCiorba, pretFel2, pretCarne, pretDesert;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cantina, container, false) ;

        dataTv = view.findViewById(R.id.dataTv);
        supaMeniu = view.findViewById(R.id.supaMeniu);
        ciorbaMeniu = view.findViewById(R.id.ciorbaMeniu);
        fel2Meniu = view.findViewById(R.id.fel2Meniu);
        carneMeniu = view.findViewById(R.id.carneMeniu);
        desertMeniu = view.findViewById(R.id.desertMeniu);
        pretSupa = view.findViewById(R.id.pretSupa);
        pretCiorba = view.findViewById(R.id.pretCiorba);
        pretFel2 = view.findViewById(R.id.pretFel2);
        pretCarne = view.findViewById(R.id.pretCarne);
        pretDesert = view.findViewById(R.id.pretDesert);

        //seteaza ziua curenta
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String zi = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
        if(zi.equals("Sunday")){
            zi = "Duminica";
        }
        if(zi.equals("Saturday")){
            zi = "Sambata";
        }
        if(zi.equals("Monday")){
            zi = "Luni";
        }
        if(zi.equals("Tuesday")){
            zi = "Marti";
        }
        if(zi.equals("Wednesday")){
            zi = "Miercuri";
        }
        if(zi.equals("Thursday ")){
            zi = "Joi";
        }
        if(zi.equals("Friday")){
            zi = "Vineri";
        }
        dataTv.setText(currentDate+" ("+zi+")");

        //apelarea functiei de aducere din baza de date a meniului
        if(zi.equals("Duminica") || zi.equals("Sunday") || zi.equals("Sambata") || zi.equals("Saturday")){
            Toast.makeText(getActivity(),"Meniul zilei nu este disponibil in weekend!", Toast.LENGTH_LONG).show();
        } else{
            getMeniu();
        }


        return view;
    }

    private void getMeniu() {
        final String[] tipuri= {"Supa", "Ciorba", "Fel 2", "Carne", "Desert"};
        for (int i = 0; i < tipuri.length; i++) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Meniu").child(tipuri[i]);
            final int finalI = i;
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String nume = snapshot.child("numeMancare").getValue(String.class);
                    String pret = snapshot.child("pretMancare").getValue(String.class);
                    if(tipuri[finalI].equals("Supa")){
                        supaMeniu.setText(nume);
                        pretSupa.setText(pret + " RON");
                    }
                    if(tipuri[finalI].equals("Ciorba")){
                        ciorbaMeniu.setText(nume);
                        pretCiorba.setText(pret + " RON");
                    }
                    if(tipuri[finalI].equals("Fel 2")){
                        fel2Meniu.setText(nume);
                        pretFel2.setText(pret + " RON");
                    }
                    if(tipuri[finalI].equals("Carne")){
                        carneMeniu.setText(nume);
                        pretCarne.setText(pret + " RON");
                    }
                    if(tipuri[finalI].equals("Desert")){
                        desertMeniu.setText(nume);
                        pretDesert.setText(pret + " RON");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}
