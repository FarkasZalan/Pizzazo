package com.example.demo.model;

public class RendelesItem {

    private String rendelesAzonosito;
    private String rendelesIdopont;
    private String displayNev;
    private String displayMennyiseg;
    private String displayEgysegAr;
    private String rendelesVegosszeg;

    public RendelesItem(String rendelesAzonosito, String rendelesIdopont, String displayNev, String displayMennyiseg, String displayEgysegAr, String rendelesVegosszeg) {
        this.rendelesAzonosito = rendelesAzonosito;
        this.rendelesIdopont = rendelesIdopont;
        this.displayNev = displayNev;
        this.displayMennyiseg = displayMennyiseg;
        this.displayEgysegAr = displayEgysegAr;
        this.rendelesVegosszeg = rendelesVegosszeg;
    }

    public String getRendelesAzonosito() {
        return rendelesAzonosito;
    }

    public void setRendelesAzonosito(String rendelesAzonosito) {
        this.rendelesAzonosito = rendelesAzonosito;
    }

    public String getRendelesIdopont() {
        return rendelesIdopont;
    }

    public void setRendelesIdopont(String rendelesIdopont) {
        this.rendelesIdopont = rendelesIdopont;
    }

    public String getDisplayNev() {
        return displayNev;
    }

    public void setDisplayNev(String displayNev) {
        this.displayNev = displayNev;
    }

    public String getDisplayMennyiseg() {
        return displayMennyiseg;
    }

    public void setDisplayMennyiseg(String displayMennyiseg) {
        this.displayMennyiseg = displayMennyiseg;
    }

    public String getDisplayEgysegAr() {
        return displayEgysegAr;
    }

    public void setDisplayEgysegAr(String displayEgysegAr) {
        this.displayEgysegAr = displayEgysegAr;
    }

    public String getRendelesVegosszeg() {
        return rendelesVegosszeg;
    }

    public void setRendelesVegosszeg(String rendelesVegosszeg) {
        this.rendelesVegosszeg = rendelesVegosszeg;
    }
}
