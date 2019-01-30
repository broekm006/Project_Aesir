/***
 ** Marc van den Broek
 ** 10269602
 **
 ** This file is used to create a model for the database object(s)
 **
 ***/

package com.uva.aesir.Model;

public class ExerciseImg {
    private String idex, imgUrl;

    public ExerciseImg(String idex, String imgUrl) {
        this.idex = idex;
        this.imgUrl = imgUrl;
    }

    public String getIdex() {
        return idex;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
