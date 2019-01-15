package com.uva.aesir;

import android.content.Context;
import android.text.Html;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExerciseRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    private Context context;
    private ArrayList<Exercise> exercises = new ArrayList<Exercise>();
    private Callback callback;


    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotExerciseError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{

            JSONArray array = response.getJSONArray("results");

            for(int i = 0; i < array.length(); i++){
                JSONObject specific = array.getJSONObject(i);

                String idex = specific.getString("id");
                String name = Html.fromHtml(specific.getString("name")).toString();
                String description = Html.fromHtml(specific.getString("description")).toString();
                String categorie = Html.fromHtml(specific.getString("category")).toString();
                exercises.add(new Exercise(idex, name, description, categorie));
            }

            String nextPage = response.getString("next");

            if(nextPage != "null"){
                newPage(nextPage);
            }
        }


        catch (JSONException e){
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

    void newPage(String url){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequests = new JsonObjectRequest(url, null, this,this);
        queue.add(jsonObjectRequests);
    }
    void getExercise(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://wger.de/api/v2/exercise/?format=json&?language=2";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this,this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }

    public void requestWithSomeHttpHeaders() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://wger.de/api/v2/exercise/?format=json&?language=2";
        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("ERROR", "error => " + error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept","application/json");
                params.put("Authorization", "Token afa5da175337fec5e9b4405e7bdb910f63a1a7aa");

                return params;
            }
        };
        queue.add(getRequest);
    }
}
