package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "rendelhetotermek")
public class RendelhetoTermek {
    @Id
    @Column(name = "nev", nullable = false, length = 200)
    private String nev;
    @Column(name = "ar", nullable = false)
    private Integer ar;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rendelhetotermek", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pizza pizza;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rendelhetotermek", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AlkoholmentesItal alkoholmentesital;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rendelhetotermek", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AlkoholosItal alkoholosital;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "rendelhetotermek", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hamburger hamburger;

    public RendelhetoTermek() {
    }

    public RendelhetoTermek(String nev, Integer ar) {
        this.nev = nev;
        this.ar = ar;
    }

    public Hamburger getHamburger() {
        return hamburger;
    }

    public void setHamburger(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    public AlkoholosItal getAlkoholosital() {
        return alkoholosital;
    }

    public void setAlkoholosital(AlkoholosItal alkoholosital) {
        this.alkoholosital = alkoholosital;
    }

    public AlkoholmentesItal getAlkoholmentesital() {
        return alkoholmentesital;
    }

    public void setAlkoholmentesital(AlkoholmentesItal alkoholmentesital) {
        this.alkoholmentesital = alkoholmentesital;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }
}