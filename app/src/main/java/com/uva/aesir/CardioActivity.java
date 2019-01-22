package com.uva.aesir;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CardioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio);
    }

    public void onSubmit(View view) {
        // insert data to database (new table cardio)
    }

    public void onButtonSelect(View view){
        switch (view.getId()){ // check statement getID is correct
            case R.id.Cardio_walking:
                // do something
            case R.id.Cardio_running:
                // do something
            case R.id.Cardio_cycling:
                // do something
            default:
                // null > please enter activity
        }
    }


}
