package com.example.campus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView nav = findViewById(R.id.navigare);
        nav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            nav.setSelectedItemId(R.id.nav_acasa);
        }
    }

        private BottomNavigationView.OnNavigationItemSelectedListener navListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.nav_anunturi:
                                selectedFragment = new AnunturiFragment();
                                break;
                            case R.id.nav_inscrieri:
                                selectedFragment = new InscrieriFragment();
                                break;
                            case R.id.nav_acasa:
                                selectedFragment = new AcasaFragment();
                                break;
                            case R.id.nav_cantina:
                                selectedFragment = new CantinaFragment();
                                break;
                            case R.id.nav_plangeri:
                                selectedFragment = new PlangeriFragment();
                                break;
                        }
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectedFragment).commit();

                        return true;
                    }
                };

}