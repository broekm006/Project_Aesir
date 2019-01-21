package com.uva.aesir;

public class Preset {
    private String listName, exercise_name;

    public Preset(String listName, String exercise_name) {
        this.listName = listName;
        this.exercise_name = exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListName() {

        return listName;
    }
}
