package com.uva.aesir;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Picasso;


public class SpecificActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String getExerciseName, txt;
    PresetDatabase getListNames;
    ListNameDatabase db;
    Spinner spin;
    Button btnListName;
    TextView explain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
        Intent intent = getIntent();
        getListNames = PresetDatabase.getInstance(getApplicationContext());
        db = ListNameDatabase.getInstance(getApplicationContext());

        TextView name = findViewById(R.id.specific_name);
        TextView description = findViewById(R.id.specific_description);
        ImageView image = findViewById(R.id.specific_image);

        getExerciseName = intent.getStringExtra("title");
        name.setText(getExerciseName);
        description.setText(intent.getStringExtra("description"));
        Picasso.get().load(intent.getStringExtra("image"))
                .resize(400, 600)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        txt = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do nothing
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SpecificActivity.this, ExerciseActivity.class));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_list:
                spin = findViewById(R.id.spinner_get_list_name);
                spin.setVisibility(View.VISIBLE);

                btnListName = findViewById(R.id.button_get_list_name);
                btnListName.setVisibility(View.VISIBLE);

                explain = findViewById(R.id.textView12);
                explain.setVisibility(View.VISIBLE);

                ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getNameList());
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapter);
                spin.setOnItemSelectedListener(this);

                // force screen to new spinner
                ScrollView scroller = (ScrollView) this.findViewById(R.id.scroller);
                scroller.fullScroll(ScrollView.FOCUS_DOWN);
        }
        return (super.onOptionsItemSelected(item));
    }

    public void onOkClick(View view) {
        Preset connectToList = new Preset(txt, getExerciseName);
        getListNames.insert(connectToList);

        String string = "Exercise: " + getExerciseName + " is added to: " + txt;
        Toast toast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
        toast.show();

        spin.setVisibility(View.GONE);
        btnListName.setVisibility(View.GONE);
        explain.setVisibility(View.GONE);
    }

    public List<String> getNameList() {
        List<String> nameList = new ArrayList<String>();

        db.getReadableDatabase();
        Cursor cursor = db.selectDistinctNames();

        if (cursor.moveToFirst()) {
            do {
                nameList.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return nameList;
    }
}