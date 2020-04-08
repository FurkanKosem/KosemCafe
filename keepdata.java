package com.example.kosemcafe3;

public class keepdata {
    String adi;
    String email;
    String sifre;
    String tip;
   public keepdata(){

   }
    public keepdata(String adi,String email,String sifre,String tip){
        this.adi=adi;
        this.email=email;
        this.sifre=sifre;
        this.tip=tip;
    }
    public String getadi() {
        return adi;
    }

    public void setadi(String adi) {
        this.adi = adi;
    }


    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }


    public String getsifre() {
        return sifre;
    }

    public void setsifre(String sifre) {
        this.sifre = sifre;
    }

    public String gettip() {
        return tip;
    }

    public void settip(String tip) {
        this.tip = tip;
    }

}
