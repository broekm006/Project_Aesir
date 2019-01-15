package com.uva.aesir;

public class Exercise {
    private String idex, name, description, categorie;

    public Exercise(String idex, String name, String description, String categorie){
        this.idex = idex;
        this.name = name;
        this.description = description;
        this.categorie = categorie;
    }

    public String getIdex() {
        return idex;
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

    public void setIdex(String idex) {
        this.idex = idex;
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
