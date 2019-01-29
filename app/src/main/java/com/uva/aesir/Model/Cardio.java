package com.uva.aesir.Model;

public class Cardio {
    int km, speed, time;
    String activity;

    public Cardio(int km, int speed, int time, String activity) {
        this.km = km;
        this.speed = speed;
        this.time = time;
        this.activity = activity;
    }

    public int getKm() {
        return km;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTime() {
        return time;
    }

    public String getActivity() {
        return activity;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
