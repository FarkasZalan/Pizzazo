package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "hamburger")
public class Hamburger {
    @Id
    @Column(name = "hamburger_neve", nullable = false, length = 50)
    private String nev;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hamburger_neve", nullable = false)
    private RendelhetoTermek rendelhetotermek;

    @Column(name = "hamburger_ara", nullable = false)
    private Integer hamburgerAra;

    @Column(name = "tartalom", nullable = false, length = 100)
    private String tartalom;

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }

    @Lob
    @Column(name = "kep", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String kep;

    public Hamburger() {
    }

    public Hamburger(String nev, RendelhetoTermek rendelhetotermek, Integer hamburgerAra, String tartalom, String kep) {
        this.nev = nev;
        this.rendelhetotermek = rendelhetotermek;
        this.hamburgerAra = hamburgerAra;
        this.tartalom = tartalom;
        this.kep = kep;
    }

    public String getTartalom() {
        return tartalom;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
    }

    public Integer getHamburgerAra() {
        return hamburgerAra;
    }

    public void setHamburgerAra(Integer hamburgerAra) {
        this.hamburgerAra = hamburgerAra;
    }

    public RendelhetoTermek getRendelhetotermek() {
        return rendelhetotermek;
    }

    public void setRendelhetotermek(RendelhetoTermek rendelhetotermek) {
        this.rendelhetotermek = rendelhetotermek;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @Override
    public String toString() {
        return "Hamburger{" +
                "id='" + nev + '\'' +
                ", rendelhetotermek=" + rendelhetotermek +
                ", hamburgerAra=" + hamburgerAra +
                ", tartalom='" + tartalom + '\'' +
                '}';
    }
}