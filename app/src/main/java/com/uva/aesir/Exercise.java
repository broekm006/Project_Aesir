package com.uva.aesir;

public class Exercise {
    private String name, description, categorie, muscle;

    public Exercise(String name, String description, String categorie, String muscle){
        this.name = name;
        this.description = description;
        this.categorie = categorie;
        this.muscle = muscle;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }
}
