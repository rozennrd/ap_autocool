package com.example.autocool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.io.IOException;

public class choixPartie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_choix_partie);

        final Button btnLesVehicules = (Button)findViewById(R.id.btnVehicules);
        btnLesVehicules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si l'utilisateur clique sur le bouton "Les véhicules", démarrer le choix de la catégorie
                //Appel de la fonction authentification
                Bundle employe = getIntent().getExtras();
                Intent intent = new Intent(choixPartie.this, choixCategVoiture.class);
                intent.putExtra("employe", employe);
                startActivity(intent);
            }
        });
    }
}