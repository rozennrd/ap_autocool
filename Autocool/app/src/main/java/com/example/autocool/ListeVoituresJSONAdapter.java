package com.example.autocool;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListeVoituresJSONAdapter extends BaseAdapter implements ListAdapter {

    private final Activity activity;
    private final JSONArray jsonArray;
    public ListeVoituresJSONAdapter(Activity activity, JSONArray jsonArray) {
        this.jsonArray = jsonArray;
        this.activity = activity;
    }


    @Override public int getCount() {
        return jsonArray.length();
    }

    @Override public JSONObject getItem(int position) {
        // Ca fonctionne bien.
        return jsonArray.optJSONObject(position);

    }

    @Override public long getItemId(int position) {
        JSONObject jsonObject = getItem(position);
        return jsonObject.optLong("CODECATEG");

    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null)
            convertView = activity.getLayoutInflater().inflate(R.layout.liste_voiture_item, null);

        TextView tvNomVoiture = convertView.findViewById(R.id.nomvoiture);
        JSONObject jsonObject = getItem(position);

        try {
            String buttonText = jsonObject.getString("NUMVEHICULE");
            tvNomVoiture.setText(buttonText);
        } catch (JSONException e) {
            Log.d("Test", e.getMessage());
            e.printStackTrace();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an Intent to start the target activity

                String buttonText = tvNomVoiture.getText().toString();
                Intent intent = new Intent(v.getContext(), detailVoiture.class);
                intent.putExtra("NUMVEHICULE", buttonText); // Add any necessary data to the Intent

                // Start the activity
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
