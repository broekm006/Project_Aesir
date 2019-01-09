package com.uva.aesir;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class ExerciseRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }
}
