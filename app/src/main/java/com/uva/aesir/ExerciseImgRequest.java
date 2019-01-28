package com.uva.aesir;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExerciseImgRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    Context context;
    private ArrayList<ExerciseImg> imgUrls = new ArrayList<>();
    private Callback callback;

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotExerciseImgError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        // get JSON data from API "exercise name and image url"
        try {
            JSONArray array = response.getJSONArray("results");

            for (int i = 0; i < array.length(); i++) {
                JSONObject specific = array.getJSONObject(i);
                String exercise = specific.getString("exercise");
                String imgUrl = specific.getString("image");

                imgUrls.add(new ExerciseImg(exercise, imgUrl));
            }

            // see if there is more information to be gathered or everything is collected
            String nextPage = response.getString("next");

            // if more information is available rerun JSON call with new url
            if (nextPage != "null") {
                newPage(nextPage);
            }
        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }

        callback.gotExerciseImg(imgUrls);
    }

    public interface Callback {
        void gotExerciseImg(ArrayList<ExerciseImg> exerciseImgs);

        void gotExerciseImgError(String message);
    }

    public ExerciseImgRequest(Context c) {
        this.context = c;
    }

    // insert new url when more information is available and add request to the queue
    void newPage(String url) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequests = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequests);
    }

    // initial url with JSON volley request
    void getExerciseImg(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://wger.de/api/v2/exerciseimage/";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }

}
