/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to create a model for the database object(s)
 **
 ***/

package com.uva.aesir.Model;

public class Weights {
    int setA, setB, setC, setD;
    String exercise;

    public Weights(int setA, int setB, int setC, int setD, String exercise) {
        this.setA = setA;
        this.setB = setB;
        this.setC = setC;
        this.setD = setD;
        this.exercise = exercise;
    }

    public int getSetA() {
        return setA;
    }

    public int getSetB() {
        return setB;
    }

    public int getSetC() {
        return setC;
    }

    public int getSetD() {
        return setD;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
