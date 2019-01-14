package com.uva.aesir;

public class Exercise {
    private String name, description, categorie;

    public Exercise(String name, String description, String categorie){
        this.name = name;
        this.description = description;
        this.categorie = categorie;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
