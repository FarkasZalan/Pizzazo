package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Felhasznalo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class Belepes {
    @Autowired
    private final RendelhetoTermekRepo termekekRepo;
    private final FelhasznaloRepo felhasznaloRepo;

    public Belepes(RendelhetoTermekRepo termekekRepo, PizzaRepo pizzakRepo, HamburgerRepo hamburgerRepo, AlkoholosItalRepo alkoholosItalRepo, AlkoholmentesItalRepo alkoholmentesItalRepo, RendelesRepo rendelesRepo, FelhasznaloRepo felhasznaloRepo) {
        this.termekekRepo = termekekRepo;
        this.felhasznaloRepo = felhasznaloRepo;
    }

    public String belepes(String email, String jelszo) {
        String uzenet = "";
        for (Felhasznalo felhasznalo : felhasznaloRepo.felhasznalokListaja()) {
            if (Objects.equals(felhasznalo.getId(), email)) {
                uzenet = "Van email";
            }
            if (Objects.equals(felhasznalo.getJelszo(), jelszo) && Objects.equals(felhasznalo.getId(), email)) {
                uzenet = "Sikeres belépés";
                break;
            }
        }
        return uzenet;
    }

    public Felhasznalo felhasznaloMegtalal(String email, String jelszo) {
        Felhasznalo felhasznalo = new Felhasznalo("", "", "", "", "");
        for (Felhasznalo felhasznalo1 : felhasznaloRepo.felhasznalokListaja()) {
            if (felhasznalo1.getId().equals(email)) {
                felhasznalo.setId(felhasznalo1.getId());
                felhasznalo.setNev(felhasznalo1.getNev());
                felhasznalo.setLakcim(felhasznalo1.getLakcim());
                felhasznalo.setTelefonszam(felhasznalo1.getTelefonszam());
                felhasznalo.setJelszo(felhasznalo1.getJelszo());
            }
        }
        return felhasznalo;
    }
}
