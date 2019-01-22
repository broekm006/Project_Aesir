package com.uva.aesir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;


public class SpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        Intent intent = getIntent();

        TextView name = findViewById(R.id.specific_name);
        TextView description = findViewById(R.id.specific_description);
        ImageView image = findViewById(R.id.specific_image);

        name.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        Picasso.get().load(intent.getStringExtra("image"))
                .resize(400, 600)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);
    }

    public void OnButtonClick(View view) {
        startActivity(new Intent(this, TimerActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SpecificActivity.this, ExerciseActivity.class));
    }
}