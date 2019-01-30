/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to fill a listview with the correct data / style
 **
 ***/

package com.uva.aesir.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uva.aesir.Model.Exercise;
import com.uva.aesir.R;

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
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.exercise_entry, parent, false);
        }
        Exercise exercises = exercise.get(position);

        TextView name = convertView.findViewById(R.id.name);
        name.setText(exercises.getName());

        return convertView;
    }
}
