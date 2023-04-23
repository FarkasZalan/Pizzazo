package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "alkoholosital")
public class AlkoholosItal {
    @Id
    @Column(name = "alkoholos_ital_neve", nullable = false, length = 50)
    private String nev;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alkoholos_ital_neve", nullable = false)
    private RendelhetoTermek rendelhetotermek;

    @Column(name = "alkoholos_ital_ara", nullable = false)
    private Integer alhoholosItalAra;

    @Column(name = "alkohol_szazalek", nullable = false, length = 4)
    private String alhoholSzazalek;

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }

    @Lob
    @Column(name = "kep", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String kep;

    public AlkoholosItal() {
    }

    public AlkoholosItal(String nev, RendelhetoTermek rendelhetotermek, Integer alhoholosItalAra, String alhoholSzazalek, String kep) {
        this.nev = nev;
        this.rendelhetotermek = rendelhetotermek;
        this.alhoholosItalAra = alhoholosItalAra;
        this.alhoholSzazalek = alhoholSzazalek;
        this.kep = kep;
    }

    public String getAlhoholSzazalek() {
        return alhoholSzazalek;
    }

    public void setAlhoholSzazalek(String alhoholSzazalek) {
        this.alhoholSzazalek = alhoholSzazalek;
    }

    public Integer getAlhoholosItalAra() {
        return alhoholosItalAra;
    }

    public void setAlhoholosItalAra(Integer alhoholosItalAra) {
        this.alhoholosItalAra = alhoholosItalAra;
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
        return "AlkoholosItal{" +
                "id='" + nev + '\'' +
                ", rendelhetotermek=" + rendelhetotermek +
                ", alhoholosItalAra=" + alhoholosItalAra +
                ", alhoholSzazalek='" + alhoholSzazalek + '\'' +
                '}';
    }
}