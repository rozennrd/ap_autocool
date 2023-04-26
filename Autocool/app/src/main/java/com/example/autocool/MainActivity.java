package com.example.autocool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    String responseStr ;
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());

        setContentView(R.layout.activity_main);

        final Button buttonValiderAuthentification = (Button)findViewById(R.id.btnValider);
        buttonValiderAuthentification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Appel de la fonction authentification
                try {
                    authentification();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void authentification() throws IOException {

        final EditText textLogin = findViewById(R.id.editTextLogin);
        final EditText textMdp = findViewById(R.id.editTextPassword);

        RequestBody formBody = new FormBody.Builder()
                .add("login", textLogin.getText().toString())
                .add("mdp", textMdp.getText().toString())
                .build();

        Request request = new Request.Builder()
                .url(Constantes.getAPI("auth.php"))
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                responseStr = response.body().string();

                if (responseStr.compareTo("false") != 0) {
                    try {
                        Log.d("Test", "coucou");
                        Log.d("Test", responseStr);
                        JSONObject employe = new JSONObject(responseStr);
                        Intent intent = new Intent(MainActivity.this, choixPartie.class);
                        intent.putExtra("employe", employe.toString());
                        startActivity(intent);
                    } catch (JSONException e) {
                        Log.d("Test", e.getMessage());
                        Toast.makeText(MainActivity.this, "Erreur de connexion !!!! !", Toast.LENGTH_SHORT).show();
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