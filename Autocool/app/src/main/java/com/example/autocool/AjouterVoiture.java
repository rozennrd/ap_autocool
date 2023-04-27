package com.example.autocool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AjouterVoiture extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();
    Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_ajouter_voiture);
        Spinner spStations = findViewById(R.id.spinner_stations);
        Spinner lTypesVehicules = findViewById(R.id.spinner_type_vehicule);
        HashMap<String, Pair<String, String>> stationDictionaire = new HashMap<>();
        HashMap<String, String> typeVehDictionnaire = new HashMap<>();
        ctx = this;
        Request request = new Request.Builder()
                .url(Constantes.getAPI("listetypesvehicules.php"))
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                responseStr = response.body().string();
                Log.d("test", responseStr);
                if (responseStr.compareTo("false") != 0) {
                    try {
                        JSONArray jsonObject = new JSONArray(responseStr);


                        ArrayList<String> listeTypesVehicules = new ArrayList<>();
                        for(int i=0;i<jsonObject.length();i++){
                            JSONObject jsonObject1=jsonObject.getJSONObject(i);
                            String libelletype=jsonObject1.getString("LIBELLETYPE");
                            listeTypesVehicules.add(libelletype);
                            typeVehDictionnaire.put("LIBELLETYPE", "CODETYPE");
                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>
                                (ctx, android.R.layout.simple_spinner_item,
                                        listeTypesVehicules); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                                .simple_spinner_dropdown_item);
                        lTypesVehicules.setAdapter(new ArrayAdapter<String>(AjouterVoiture.this, android.R.layout.simple_spinner_dropdown_item, listeTypesVehicules));
                    } catch (JSONException e) {
                        Log.d("Test", e.getMessage());
                        e.printStackTrace();
                    }


                } else {
                    Log.d("Test", "Login ou mot de  passe non valide !");
                }
            }

            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Test", "erreur!!! connexion impossible");
                Log.d("Test", e.getMessage());
                e.printStackTrace();
            }
        });

        Request requestStation = new Request.Builder()
                .url(Constantes.getAPI("stations.php"))
                .get()
                .build();
        Call callStations = client.newCall(requestStation);
        callStations.enqueue(new Callback() {

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                responseStr = response.body().string();
                Log.d("test", responseStr);
                if (responseStr.compareTo("false") != 0) {
                    try {

                        JSONArray jsonObject = new JSONArray(responseStr);
                        Log.d("Test", responseStr);
                        ArrayList<String> listeStations = new ArrayList<>();
                        for(int i=0;i<jsonObject.length();i++){
                            JSONObject jsonObject1=jsonObject.getJSONObject(i);
                            String libelletype=jsonObject1.getString("NOMVILLE") + " - " + jsonObject1.getString("NOMLIEU");
                            listeStations.add(libelletype);
                            Pair<String, String> tupleId = new Pair<String, String>(jsonObject1.getString("CODEVILLE"), jsonObject1.getString("CODELIEU"));
                            stationDictionaire.put(jsonObject1.getString("NOMLIEU"), tupleId);

                        }
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>
                                (ctx, android.R.layout.simple_spinner_item,
                                        listeStations); //selected item will look like a spinner set from XML
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                                .simple_spinner_dropdown_item);
                        spStations.setAdapter(new ArrayAdapter<String>(AjouterVoiture.this, android.R.layout.simple_spinner_dropdown_item, listeStations));
                    } catch (JSONException e) {
                        Log.d("Test", e.getMessage());
                        e.printStackTrace();
                    }


                } else {
                    Log.d("Test", "Login ou mot de  passe non valide !");
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
        });


        Button btnAjouter = findViewById(R.id.btnAjouterVoiture);
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Informations du spinner station
                Log.d("Test", "button clicked");
                String station = (String) spStations.getSelectedItem();
                String[] stationInfo = station.split(" - ");
                Pair<String, String> pairStationInfo = stationDictionaire.get(stationInfo[1]); // first = CodeVille + second = CodeLieu
                if (pairStationInfo == null) {
                    Log.d("Test", "pas d'infos pour pairInfoStation");
                }

                // Informations du spinner type_info
                String typeVehRaw = (String) lTypesVehicules.getSelectedItem();
                String typeVeh = typeVehDictionnaire.get(typeVehRaw);

                // Nom / id du véhicule
                TextView tvNomVeh = findViewById(R.id.nomVoiture);
                String nomVeh = (String) tvNomVeh.getText();

                // Kilométrage
                TextView tvKm = findViewById(R.id.kilometrage);
                String km = (String) tvKm.getText();

                // niveau essence
                TextView tvEssence = findViewById(R.id.essence);
                String essence = (String) tvEssence.getText();

                // Requête
                FormBody fm = new FormBody.Builder()
                        .add("idStation", pairStationInfo.second) // CodeLieu, le lieu de la station
                        .add("idType", typeVeh)
                        .add("libelle", nomVeh)
                        .add("km", km)
                        .add("niveauEssence", essence)
                        .build();
                Request requestPost = new Request.Builder()
                        .url(Constantes.getAPI("ajouterVoiture.php"))
                        .post(fm)
                        .build();

                Call callpost = client.newCall(requestPost);
                callpost.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        int dureeToast = Toast.LENGTH_SHORT;
                        CharSequence txt = "Erreur lors de l'insertion des données";
                        Toast toast = Toast.makeText(getApplicationContext(),txt , dureeToast);
                        toast.show();
                        Log.d("Test", e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Log.d("Test", response.toString());
                        Intent intent = new Intent(AjouterVoiture.this, choixPartie.class);
                        int dureeToast = Toast.LENGTH_SHORT;
                        CharSequence txt = "Succès !";
                        Toast toast = Toast.makeText(getApplicationContext(),txt , dureeToast);
                        toast.show();
                        startActivity(intent);
                    }
                });

            }
        });

    }

    // https://developer.android.com/develop/ui/views/components/spinner#java
}