package com.example.autocool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class choixCategVoiture extends AppCompatActivity {
    /*
    * Vue permettant de choisir la catégorie de véhicules
    * Pour en savoir plus sur les recyclerView : https://developer.android.com/develop/ui/views/layout/recyclerview?gclid=Cj0KCQjwiZqhBhCJARIsACHHEH9_jr2XHwqIwrfsxWT-V77vfp9pLvuxCB_MkFQ6NRgONQS6GbvsMiUaAkU3EALw_wcB&gclsrc=aw.ds#java
    * https://guides.codepath.com/android/using-the-recyclerview
    * */

    String responseStr ;
    OkHttpClient client = new OkHttpClient();
    private String[] localDataSet;
    private Context ctx;
    private ListView lvBtnCateg;
    private JSONArray lesCategVehicule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_choix_categ_voiture);
        lvBtnCateg = findViewById(R.id.lvBtnCateg);
        ctx = this;

        // Ajout voiture
        Button btnAjoutVoiture  = findViewById(R.id.btnAjoutVoiture);
        btnAjoutVoiture.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(choixCategVoiture.this, AjouterVoiture.class);
                startActivity(intent);
            }
        });

        Request request = new Request.Builder()
                .url(Constantes.getAPI("listecategories.php"))
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            responseStr = Objects.requireNonNull(response.body()).string();
            Log.d("Test", responseStr);
            if (responseStr.compareTo("false") != 0) {
                try {
                    JSONArray lesCategVehicule = new JSONArray(responseStr);
                    Log.d("Test", lesCategVehicule.toString());
                    JSONAdapter adapter= new JSONAdapter(choixCategVoiture.this, lesCategVehicule);

                    lvBtnCateg.setAdapter(adapter);
                    lvBtnCateg.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            try {
                                JSONObject object = adapter.getItem(position);
                                Log.d("Test", object.toString());
                                Intent intent = new Intent(choixCategVoiture.this, listeVoitures.class);
                                Log.d("Test", "code catégorie : " + object.getString("CODECATEG"));
                                intent.putExtra("CODECATEG", object.getString("CODECATEG"));
                                startActivity(intent);
                            } catch (JSONException e) {
                                Log.d("Test", e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    });

                } catch (JSONException e) {
                    Log.d("Test", e.getMessage());
                    Toast.makeText(choixCategVoiture.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
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