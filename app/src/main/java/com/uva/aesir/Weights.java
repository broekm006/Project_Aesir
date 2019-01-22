package com.uva.aesir;

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

    public void setSetA(int setA) {
        this.setA = setA;
    }

    public void setSetB(int setB) {
        this.setB = setB;
    }

    public void setSetC(int setC) {
        this.setC = setC;
    }

    public void setSetD(int setD) {
        this.setD = setD;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }
}
