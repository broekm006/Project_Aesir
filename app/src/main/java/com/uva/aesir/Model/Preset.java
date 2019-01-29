package com.uva.aesir.Model;

public class Preset {
    private String listName, exercise_name;

    public Preset(String listName, String exercise_name) {
        this.listName = listName;
        this.exercise_name = exercise_name;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public String getListName() {

        return listName;
    }
}
