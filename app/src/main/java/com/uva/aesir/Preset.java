package com.uva.aesir;

public class Preset {
    //private int idExercise;
    private int id;
    private String title, exercise_name;

    public Preset(String title, String exercise_name){
        this.title = title;
        //this.numberOfTimes = numberOfTimes;
        //this.idExercise = idExercise;
        this.exercise_name = exercise_name;
    }

    //public void setIdExercise(int idExercise) {
    //    this.idExercise = idExercise;
    //}

    public int getId(){
        return id;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //    public int getIdExercise() {
//        return idExercise;
//    }

    public String getExercise_name() {
        return exercise_name;
    }

    public String getTitle() {
        return title;
    }

}
