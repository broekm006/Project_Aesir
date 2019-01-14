package com.uva.aesir;

public class Preset {
    //private int idExercise;
    private int id;
    private String title, numberOfTimes, exercise_name;

    public Preset(String title, String numberOfTimes, String exercise_name){
        this.title = title;
        this.numberOfTimes = numberOfTimes;
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

    public void setNumberOfTimes(String numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
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

    public String getNumberOfTimes() {
        return numberOfTimes;
    }
}
