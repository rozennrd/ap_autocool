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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class listeVoitures extends AppCompatActivity {
    String responseStr ;
    OkHttpClient client = new OkHttpClient();
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_liste_voitures);
        ctx = this;

        RequestBody formBody = null;
        Bundle data = this.getIntent().getExtras();
        if (data != null) {
             String categVoiture = data.getString("CODECATEG");
            formBody = new FormBody.Builder()
                    .add("categorie", categVoiture)
                    .build();
        } else {
            Log.d("Test", "data is null !!");
        }

        Request request = new Request.Builder()
                .url(Constantes.getAPI("listevehiculesbycategorie.php"))
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                responseStr = response.body().string();
                Log.d("Test", "responseStr listevoitures : " + responseStr);
                if (responseStr.compareTo("false") != 0) {
                    try {
                        ListView lvListeVoitures = findViewById(R.id.lvListeVoitures);
                        JSONArray lesVoitures = new JSONArray(responseStr);
                        ListeVoituresJSONAdapter adapter= new ListeVoituresJSONAdapter(listeVoitures.this, lesVoitures);
                        lvListeVoitures.setAdapter(adapter);
                        lvListeVoitures.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                try {
                                    JSONObject object = adapter.getItem(position);
                                    Intent intent = new Intent(listeVoitures.this, detailVoiture.class);
                                    intent.putExtra("NUMVEHICULE", object.getString("NUMVEHICULE"));
                                } catch (JSONException e) {
                                    Log.d("Test", e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                        });



                    } catch (JSONException e) {
                        Log.d("Test", e.getMessage());
                        // Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Log.d("Test", "Login ou mot de  passe non valide !");
                }
            }

            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                CharSequence msg = "Erreur de connexion";
                Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
                toast.show();
                Log.d("Test", "erreur!!! connexion impossible");
                Log.d("Test", e.getMessage());
                e.printStackTrace();
            }

        });
    }

}