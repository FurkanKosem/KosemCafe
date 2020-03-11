package com.example.kosemcafe;

public class Kullanici {
    private int Id;
    private String Ad;
    private String Soyad;
    private String Mail;
    private String Sifre;
    public Kullanici(){

    }

    public Kullanici(String ad,String soyad,String mail,String sifre) {
        Ad = ad;
        Soyad=soyad;
        Mail=mail;
        Sifre=sifre;

    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public void setSoyad(String soyad) {
        Soyad = soyad;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
