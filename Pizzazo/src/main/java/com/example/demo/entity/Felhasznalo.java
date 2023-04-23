package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "felhasznalo")
public class Felhasznalo {
    @Id
    @Column(name = "email", nullable = false, length = 128)
    private String id;

    @Column(name = "nev", nullable = false, length = 128)
    private String nev;

    @Column(name = "lakcim", nullable = false, length = 128)
    private String lakcim;

    @Column(name = "telefonszam", nullable = false, length = 128)
    private String telefonszam;

    @Column(name = "jelszo", nullable = false, length = 128)
    private String jelszo;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "felhasznaloEmailCime", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE})
    private Set<Rendeles> rendeles = new LinkedHashSet<>();

    public Felhasznalo() {
    }

    public Felhasznalo(String id, String nev, String lakcim, String telefonszam, String jelszo) {
        this.id = id;
        this.nev = nev;
        this.lakcim = lakcim;
        this.telefonszam = telefonszam;
        this.jelszo = jelszo;
    }

    public Set<Rendeles> getRendeles() {
        return rendeles;
    }

    public void setRendeles(Set<Rendeles> rendeles) {
        this.rendeles = rendeles;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getLakcim() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Felhasznalo{" +
                "id='" + id + '\'' +
                ", nev='" + nev + '\'' +
                ", lakcim='" + lakcim + '\'' +
                ", telefonszam='" + telefonszam + '\'' +
                ", jelszo='" + jelszo + '\'' +
                '}';
    }
}