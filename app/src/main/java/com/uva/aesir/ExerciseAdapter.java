package com.uva.aesir;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExerciseAdapter extends ArrayAdapter<Exercise> {
    private ArrayList<Exercise> exercise;

    public ExerciseAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Exercise> objects) {
        super(context, resource, objects);
        this.exercise = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_entry, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        //TextView description = convertView.findViewById(R.id.description);
        //TextView categorie = convertView.findViewById(R.id.categorie);

        Exercise exercises = exercise.get(position);

        name.setText(exercises.getName());
        //description.setText(exercises.getDescription());
        //categorie.setText(exercises.getCategorie());

        return convertView;
    }
}
