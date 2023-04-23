package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pizza")
public class Pizza {
    @Id
    @Column(name = "pizza_neve", nullable = false, length = 50)
    private String nev;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pizza_neve", nullable = false)
    private RendelhetoTermek rendelhetotermek;

    @Column(name = "pizza_ara", nullable = false)
    private Integer pizzaAra;

    @Column(name = "feltet", nullable = false, length = 100)
    private String feltet;

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }

    @Lob
    @Column(name = "kep", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String kep;

    public Pizza() {
    }

    public Pizza(String nev, RendelhetoTermek rendelhetotermek, Integer pizzaAra, String feltet, String kep) {
        this.nev = nev;
        this.rendelhetotermek = rendelhetotermek;
        this.pizzaAra = pizzaAra;
        this.feltet = feltet;
        this.kep = kep;
    }

    public String getFeltet() {
        return feltet;
    }

    public void setFeltet(String feltet) {
        this.feltet = feltet;
    }

    public Integer getPizzaAra() {
        return pizzaAra;
    }

    public void setPizzaAra(Integer pizzaAra) {
        this.pizzaAra = pizzaAra;
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
        return "Pizza{" +
                "id='" + nev + '\'' +
                ", rendelhetotermek=" + rendelhetotermek +
                ", pizzaAra=" + pizzaAra +
                ", feltet='" + feltet + '\'' +
                '}';
    }
}