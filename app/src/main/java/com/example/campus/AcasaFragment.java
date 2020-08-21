package com.example.campus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AcasaFragment extends Fragment {

    Button caminAbtn, caminBbtn, caminCbtn;
    RelativeLayout caminArel, caminBrel, caminCrel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_acasa, container, false);

        caminAbtn = view.findViewById(R.id.buton_A);
        caminBbtn = view.findViewById(R.id.buton_B);
        caminCbtn = view.findViewById(R.id.buton_C);
        caminArel = view.findViewById(R.id.rel_A);
        caminBrel = view.findViewById(R.id.rel_B);
        caminCrel = view.findViewById(R.id.rel_C);

        caminArel.setVisibility(View.GONE);
        caminBrel.setVisibility(View.GONE);
        caminCrel.setVisibility(View.GONE);

        // Buton Camin A -->  info
        caminAbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminArel.getVisibility() == View.GONE) {
                    caminArel.setVisibility(View.VISIBLE);
                }
                else {
                    caminArel.setVisibility(View.GONE);
                }
            }
        });

        // Buton Camin B -->  info
        caminBbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminBrel.getVisibility() == View.GONE) {
                    caminBrel.setVisibility(View.VISIBLE);
                }
                else {
                    caminBrel.setVisibility(View.GONE);
                }
            }
        });

        // Buton Camin C -->  info
        caminCbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminCrel.getVisibility() == View.GONE) {
                    caminCrel.setVisibility(View.VISIBLE);
                }
                else {
                    caminCrel.setVisibility(View.GONE);
                }
            }
        });



        return view;
    }
}
