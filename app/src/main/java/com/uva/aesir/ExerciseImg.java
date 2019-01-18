package com.uva.aesir;

public class ExerciseImg{
    private String idex, imgUrl;

    public ExerciseImg(String idex, String imgUrl){
        this.idex = idex;
        this.imgUrl = imgUrl;
    }

        public String getIdex() {
            return idex;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setIdex(String idex) {
            this.idex = idex;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
}
