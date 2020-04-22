package com.example.kosemcafe3;

public class urundata {
    String urunadi;
    String urunkategori;
    String urunfiyat;

    public urundata(){


    }
    public urundata(String urunadi,String urunkategori,String urunfiyat){
        this.urunadi=urunadi;
        this.urunkategori=urunkategori;
        this.urunfiyat=urunfiyat;

    }

    public String getUrunadi() {
        return urunadi;
    }

    public void setUrunadi (String urunadi) { this.urunadi = urunadi;
    }


    public String getUrunkategori() {
        return urunkategori;
    }

    public void setUrunkategori(String urunkategori) {
        this.urunkategori = urunkategori;
    }


    public String getUrunfiyat() {
        return urunfiyat;
    }

    public void setUrunfiyat(String urunfiyat) {
        this.urunfiyat = urunfiyat;
    }

}
