package com.uva.aesir.Request;

import android.content.Context;
import android.text.Html;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.uva.aesir.Model.Exercise;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExerciseRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();
    private Callback callback;


    // check for errors
    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotExerciseError(error.getMessage());
    }


    // if json url is valid get data
    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray array = response.getJSONArray("results");

            // go through array and get JSONObjects based on string
            for (int i = 0; i < array.length(); i++) {
                JSONObject specific = array.getJSONObject(i);

                String idex = specific.getString("id");
                String name = Html.fromHtml(specific.getString("name")).toString();
                String description = Html.fromHtml(specific.getString("description")).toString();
                String categorie = Html.fromHtml(specific.getString("category")).toString();

                // add objects to arraylist
                exercises.add(new Exercise(idex, name, description, categorie));
            }

            String nextPage = response.getString("next");

            // check if nextPage exists (json is separated over multiple pages)
            if (nextPage != "null") {
                newPage(nextPage);
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        callback.gotExercise(exercises);
    }


    public interface Callback {
        void gotExercise(ArrayList<Exercise> exercise);

        void gotExerciseError(String message);
    }

    public ExerciseRequest(Context c) {
        this.context = c;
    }


    // if another page exists add new url to the Volley queue
    public void newPage(String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequests = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequests);
    }

    public void getExercise(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://wger.de/api/v2/exercise/?format=json&?language=2";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }
}
