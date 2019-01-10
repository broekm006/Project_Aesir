package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        Intent intent = getIntent();

        TextView name = findViewById(R.id.specific_name);
        TextView description = findViewById(R.id.specific_description);

        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
    }

    public void OnButtonClick(View view){
        startActivity(new Intent(this, TimerActivity.class));
    }


    // voor spinner inhoud zie:
    // https://developer.android.com/guide/topics/ui/controls/spinner#java
    // heeft eigen adapter nodig
}
