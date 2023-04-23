package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "alkoholmentesital")
public class AlkoholmentesItal {
    @Id
    @Column(name = "alkoholmentes_ital_neve", nullable = false, length = 50)
    private String nev;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alkoholmentes_ital_neve", nullable = false)
    private RendelhetoTermek rendelhetotermek;

    @Column(name = "alkoholmentes_ital_ara", nullable = false)
    private Integer alkoholmentesItalAra;

    @Column(name = "cukortartalom", nullable = false, length = 4)
    private String cukortartalom;

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }

    @Lob
    @Column(name = "kep", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String kep;

    public AlkoholmentesItal() {
    }

    public AlkoholmentesItal(String nev, RendelhetoTermek rendelhetotermek, Integer alkoholmentesItalAra, String cukortartalom, String kep) {
        this.nev = nev;
        this.rendelhetotermek = rendelhetotermek;
        this.alkoholmentesItalAra = alkoholmentesItalAra;
        this.cukortartalom = cukortartalom;
        this.kep = kep;
    }

    public String getCukortartalom() {
        return cukortartalom;
    }

    public void setCukortartalom(String cukortartalom) {
        this.cukortartalom = cukortartalom;
    }

    public Integer getAlkoholmentesItalAra() {
        return alkoholmentesItalAra;
    }

    public void setAlkoholmentesItalAra(Integer alkoholmentesItalAra) {
        this.alkoholmentesItalAra = alkoholmentesItalAra;
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
        return "AlkoholmentesItal{" +
                "id='" + nev + '\'' +
                ", rendelhetotermek=" + rendelhetotermek +
                ", alkoholmentesItalAra=" + alkoholmentesItalAra +
                ", cukortartalom='" + cukortartalom + '\'' +
                '}';
    }
}