/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used as a navigation pane. It just shows 2 options to click on.
 **
 ***/

package com.uva.aesir.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uva.aesir.R;

public class ResultsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ResultsListActivity.this, MainActivity.class));
    }


    // on button click go to the corresponding activity
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Results_weights:
                startActivity(new Intent(this, ResultsWeightsActivity.class));
                break;
            case R.id.Results_cardio:
                startActivity(new Intent(this, ResultsCardioActivity.class));
                break;
        }
    }


}
