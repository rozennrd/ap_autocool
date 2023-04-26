package com.example.autocool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class detailVoiture extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();
    private Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_detail_voiture);
        ctx = this;

        RequestBody formBody = null;
        Bundle data = this.getIntent().getExtras();
        if (data != null) {
            String idVoiture = data.getString("NUMVEHICULE");
            formBody = new FormBody.Builder()
                    .add("idVoiture", idVoiture)
                    .build();
        }



        Request request = new Request.Builder()
                .url(Constantes.getAPI("selectVoiture.php"))
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                responseStr = response.body().string();

                if (responseStr.compareTo("false") != 0) {
                    try {
                        JSONObject voiture = new JSONObject(responseStr);
                        TextView tvTypeVoiture = findViewById(R.id.typeVoiture);
                        TextView tvStation = findViewById(R.id.station);
                        TextView tvEssence = findViewById(R.id.essence);
                        TextView tvPlaces = findViewById(R.id.nbPlaces);
                        TextView tvCategVoiture = findViewById(R.id.categVoiture);
                        TextView tvKilometrage = findViewById(R.id.kilometrage);
                        TextView tvNomVoiture = findViewById(R.id.nomVoiture);

                        tvTypeVoiture.setText(voiture.getString("LIBELLETYPE"));
                        tvStation.setText(voiture.getString("NOMLIEU"));
                        tvEssence.setText(voiture.getString("NIVEAUESSENCE"));
                        tvPlaces.setText(voiture.getString("NBPLACES"));
                        tvCategVoiture.setText(voiture.getString("LIBELLECATEG"));
                        tvKilometrage.setText(voiture.getString("KILOMETRAGE"));
                        tvNomVoiture.setText(voiture.getString("NUMVEHICULE"));
                    } catch (JSONException e) {
                        Log.d("Test", e.getMessage());
                        // Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
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


    }
}