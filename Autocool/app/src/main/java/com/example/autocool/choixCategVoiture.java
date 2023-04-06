package com.example.autocool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_categ_voiture);


        Request request = new Request.Builder()
                .url("http://192.168.56.1/Autocool-php/controleurAndroid/listecategories.php")
                .get()
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            public void onResponse(Call call, Response response) throws IOException {

                responseStr = response.body().string();

                if (responseStr.compareTo("false") != 0) {
                    try {
                        // récupérer le

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

    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

        private String[] localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder)
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                textView = (TextView) view.findViewById(R.id.textView);
            }

            public TextView getTextView() {
                return textView;
            }
        }

        /**
         * Initialize the dataset of the Adapter
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView
         */
        public CustomAdapter(String[] dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout., viewGroup, false);

            return new ViewHolder(view);
        }


        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView().setText(localDataSet[position]);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.length;
        }
    }

}