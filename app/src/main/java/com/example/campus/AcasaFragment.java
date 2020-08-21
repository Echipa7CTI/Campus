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

    Button caminAbtn, caminBbtn, caminCbtn, caminDbtn, caminEbtn, caminGbtn, caminHbtn, caminLSGbtn;
    RelativeLayout caminArel, caminBrel, caminCrel, caminDrel, caminErel, caminGrel, caminHrel, caminLSGrel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_acasa, container, false);

        caminAbtn = view.findViewById(R.id.buton_A);
        caminBbtn = view.findViewById(R.id.buton_B);
        caminCbtn = view.findViewById(R.id.buton_C);
        caminDbtn = view.findViewById(R.id.buton_D);
        caminEbtn = view.findViewById(R.id.buton_E);
        caminGbtn = view.findViewById(R.id.buton_G);
        caminHbtn = view.findViewById(R.id.buton_H);
        caminLSGbtn = view.findViewById(R.id.buton_LSG);
        caminArel = view.findViewById(R.id.rel_A);
        caminBrel = view.findViewById(R.id.rel_B);
        caminCrel = view.findViewById(R.id.rel_C);
        caminDrel = view.findViewById(R.id.rel_D);
        caminErel = view.findViewById(R.id.rel_E);
        caminGrel = view.findViewById(R.id.rel_G);
        caminHrel = view.findViewById(R.id.rel_H);
        caminLSGrel = view.findViewById(R.id.rel_LSG);

        caminArel.setVisibility(View.GONE);
        caminBrel.setVisibility(View.GONE);
        caminCrel.setVisibility(View.GONE);
        caminDrel.setVisibility(View.GONE);
        caminErel.setVisibility(View.GONE);
        caminGrel.setVisibility(View.GONE);
        caminHrel.setVisibility(View.GONE);
        caminLSGrel.setVisibility(View.GONE);

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

        // Buton Camin D -->  info
        caminDbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminDrel.getVisibility() == View.GONE) {
                    caminDrel.setVisibility(View.VISIBLE);
                }
                else {
                    caminDrel.setVisibility(View.GONE);
                }
            }
        });

        // Buton Camin E -->  info
        caminEbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminErel.getVisibility() == View.GONE) {
                    caminErel.setVisibility(View.VISIBLE);
                }
                else {
                    caminErel.setVisibility(View.GONE);
                }
            }
        });

        // Buton Camin G -->  info
        caminGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminGrel.getVisibility() == View.GONE) {
                    caminGrel.setVisibility(View.VISIBLE);
                }
                else {
                    caminGrel.setVisibility(View.GONE);
                }
            }
        });

        // Buton Camin H -->  info
        caminHbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminHrel.getVisibility() == View.GONE) {
                    caminHrel.setVisibility(View.VISIBLE);
                }
                else {
                    caminHrel.setVisibility(View.GONE);
                }
            }
        });

        // Buton Camin LSG -->  info
        caminLSGbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (caminLSGrel.getVisibility() == View.GONE) {
                    caminLSGrel.setVisibility(View.VISIBLE);
                }
                else {
                    caminLSGrel.setVisibility(View.GONE);
                }
            }
        });



        return view;
    }
}
