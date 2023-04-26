package com.example.autocool;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONAdapter extends BaseAdapter implements ListAdapter {

    private final Activity activity;
    private final JSONArray jsonArray;
    public JSONAdapter(Activity activity, JSONArray jsonArray) {

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
            convertView = activity.getLayoutInflater().inflate(R.layout.text_row_item, null);

            Button button = convertView.findViewById(R.id.button);
            JSONObject jsonObject = getItem(position);

        try {
            String buttonText = jsonObject.getString("CODECATEG");
            button.setText(buttonText);
        } catch (JSONException e) {
            Log.d("Test", e.getMessage());
            e.printStackTrace();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an Intent to start the target activity
                String buttonText = button.getText().toString();
                Intent intent = new Intent(v.getContext(), listeVoitures.class);
                Log.d("Test", buttonText);
                intent.putExtra("CODECATEG", buttonText); // Add any necessary data to the Intent

                // Start the activity
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}

