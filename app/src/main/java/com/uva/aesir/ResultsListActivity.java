package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    public void onClick(View view){
          switch(view.getId()){
            case R.id.Results_weights:
                startActivity(new Intent(this, ResultsActivity.class));
                break;
            case R.id.Results_cardio:
                startActivity(new Intent(this, ResultsCardioActivity.class));
                break;
            default:
                startActivity(new Intent(this, ResultsActivity.class));
                break;
        }
    }


}
