package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "rendeles")
public class Rendeles {
    @ElementCollection
    @MapKeyColumn(name = "termek_neve")
    @Column(name = "mennyiseg")
    @CollectionTable(name = "rendelesReszeltesListaja", joinColumns = {@JoinColumn(name = "rendelesID")},
            foreignKey = @ForeignKey(
                    name = "rendelesAzonosito", foreignKeyDefinition = "foreign key (rendelesid) references rendeles (rendeles_id) on delete cascade"
            ))
    Map<RendelhetoTermek, Integer> termekNeve = new HashMap<>();
    @Id
    @SequenceGenerator(
            name = "rendelesId",
            sequenceName = "rendelesId",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rendelesId")
    @Column(name = "rendelesId", nullable = false)
    private Integer id;
    @Column(name = "fizetendo_osszeg", nullable = false)
    private Integer fizetendoOsszeg;
    @Column(name = "rendeles_idopontja", nullable = false, length = 128)
    private String rendelesIdopontja;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "felhasznalo_email_cime", nullable = false)
    private Felhasznalo felhasznaloEmailCime;

    public Rendeles() {
    }

    public Rendeles(Integer fizetendoOsszeg, String rendelesIdopontja, Felhasznalo felhasznaloEmailCime, Map<RendelhetoTermek, Integer> termekek) {
        this.fizetendoOsszeg = fizetendoOsszeg;
        this.rendelesIdopontja = rendelesIdopontja;
        this.felhasznaloEmailCime = felhasznaloEmailCime;
        this.termekNeve = termekek;
    }

    public Map<RendelhetoTermek, Integer> getTermekNeve() {
        return termekNeve;
    }

    public void setTermekNeve(Map<RendelhetoTermek, Integer> termekNeve) {
        this.termekNeve = termekNeve;
    }

    public Felhasznalo getFelhasznaloEmailCime() {
        return felhasznaloEmailCime;
    }

    public void setFelhasznaloEmailCime(Felhasznalo felhasznaloEmailCime) {
        this.felhasznaloEmailCime = felhasznaloEmailCime;
    }

    public String getRendelesIdopontja() {
        return rendelesIdopontja;
    }

    public void setRendelesIdopontja(String rendelesIdopontja) {
        this.rendelesIdopontja = rendelesIdopontja;
    }

    public Integer getFizetendoOsszeg() {
        return fizetendoOsszeg;
    }

    public void setFizetendoOsszeg(Integer fizetendoOsszeg) {
        this.fizetendoOsszeg = fizetendoOsszeg;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Rendeles{" +
                "id=" + id +
                ", fizetendoOsszeg=" + fizetendoOsszeg +
                ", rendelesIdopontja='" + rendelesIdopontja + '\'' +
                ", felhasznaloEmailCime=" + felhasznaloEmailCime +
                '}';
    }
}