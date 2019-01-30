/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to create a model for the database object(s)
 **
 ***/

package com.uva.aesir.Model;

public class Preset {
    private String listName, exerciseName;

    public Preset(String listName, String exerciseName) {
        this.listName = listName;
        this.exerciseName = exerciseName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getListName() {

        return listName;
    }
}
