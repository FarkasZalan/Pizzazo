package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Modositas {
    @Autowired
    private final FelhasznaloRepo felhasznaloRepo;
    private final PizzaRepo pizzaRepo;
    private final RendelhetoTermekRepo rendelhetoTermekRepo;
    private final HamburgerRepo hamburgerRepo;
    private final AlkoholosItalRepo alkoholosItalRepo;
    private final AlkoholmentesItalRepo alkoholmentesItalRepo;

    public Modositas(FelhasznaloRepo felhasznaloRepo, PizzaRepo pizzaRepo, RendelhetoTermekRepo rendelhetoTermekRepo, HamburgerRepo hamburgerRepo, AlkoholosItalRepo alkoholosItalRepo, AlkoholmentesItalRepo alkoholmentesItalRepo) {
        this.felhasznaloRepo = felhasznaloRepo;
        this.pizzaRepo = pizzaRepo;
        this.rendelhetoTermekRepo = rendelhetoTermekRepo;
        this.hamburgerRepo = hamburgerRepo;
        this.alkoholosItalRepo = alkoholosItalRepo;
        this.alkoholmentesItalRepo = alkoholmentesItalRepo;
    }

    public Felhasznalo felhasznaloModositas(Felhasznalo felhasznalo, String email, String nev, String lakcim, String telefonszam, String jelszo) {
        String ujnev = "";
        String ujLakcim = "";
        String ujTelefonszam = "";
        String ujJelszo = "";
        if (nev == null || nev.isEmpty()) {
            ujnev = felhasznalo.getNev();
        }
        assert nev != null;
        if (!nev.isEmpty()) {
            ujnev = nev;
        }
        if (lakcim == null || lakcim.isEmpty()) {
            ujLakcim = felhasznalo.getLakcim();
        }
        assert lakcim != null;
        if (!lakcim.isEmpty()) {
            ujLakcim = lakcim;
        }
        if (telefonszam == null || telefonszam.isEmpty()) {
            ujTelefonszam = felhasznalo.getTelefonszam();
        }
        assert telefonszam != null;
        if (!telefonszam.isEmpty()) {
            ujTelefonszam = telefonszam;
        }
        if (jelszo == null || jelszo.isEmpty()) {
            ujJelszo = felhasznalo.getJelszo();
        }
        assert jelszo != null;
        if (!jelszo.isEmpty()) {
            ujJelszo = jelszo;
        }
        felhasznalo = new Felhasznalo(email, ujnev, ujLakcim, ujTelefonszam, ujJelszo);
        felhasznaloRepo.save(felhasznalo);
        return felhasznalo;
    }

    public void pizzaModositas(Pizza pizza, String nev, int ar, String feltet) {
        int ujar = 0;
        String ujfeltet = "";
        if (ar == 0) {
            ujar = pizza.getPizzaAra();
        }
        if (ar != 0) {
            ujar = ar;
        }
        if (feltet == null || feltet.isEmpty()) {
            ujfeltet = pizza.getFeltet();
        }
        assert feltet != null;
        if (!feltet.isEmpty()) {
            ujfeltet = feltet;
        }
        RendelhetoTermek ujPizza = new RendelhetoTermek(nev, ujar);
        pizzaRepo.alapadatFrissites(ujar,ujfeltet,nev);
        rendelhetoTermekRepo.save(ujPizza);
        pizzaRepo.save(pizza);
    }

    public void hamburgerModositas(Hamburger hamburger, String nev, int ar, String tartalom) {
        int ujar = 0;
        String ujTartalom = "";
        if (ar == 0) {
            ujar = hamburger.getHamburgerAra();
        }
        if (ar != 0) {
            ujar = ar;
        }
        if (tartalom == null || tartalom.isEmpty()) {
            ujTartalom = hamburger.getTartalom();
        }
        assert tartalom != null;
        if (!tartalom.isEmpty()) {
            ujTartalom = tartalom;
        }
        RendelhetoTermek ujHamburger = new RendelhetoTermek(nev, ujar);
        hamburgerRepo.alapadatFrissites(ujar, ujTartalom, nev);
        rendelhetoTermekRepo.save(ujHamburger);
        hamburgerRepo.save(hamburger);
    }

    public void alkoholosModositas(AlkoholosItal alkoholosItal, String nev, int ar, String alkoholSzazalek) {
        int ujar = 0;
        String ujalkoholszazalek = "";
        if (ar == 0) {
            ujar = alkoholosItal.getAlhoholosItalAra();
        }
        if (ar != 0) {
            ujar = ar;
        }
        if (alkoholSzazalek == null || alkoholSzazalek.isEmpty()) {
            ujalkoholszazalek = alkoholosItal.getAlhoholSzazalek();
        }
        assert alkoholSzazalek != null;
        if (!alkoholSzazalek.isEmpty()) {
            ujalkoholszazalek = alkoholSzazalek;
        }
        RendelhetoTermek ujAlkoholos = new RendelhetoTermek(nev, ujar);
        alkoholosItalRepo.alapadatFrissites(ujar, ujalkoholszazalek, nev);
        rendelhetoTermekRepo.save(ujAlkoholos);
        alkoholosItalRepo.save(alkoholosItal);
    }

    public void alkoholmentesModositas(AlkoholmentesItal alkoholmentesItal, String nev, int ar, String cukortartalom) {
        int ujar = 0;
        String ujCukortartalom = "";
        if (ar == 0) {
            ujar = alkoholmentesItal.getAlkoholmentesItalAra();
        }
        if (ar != 0) {
            ujar = ar;
        }
        if (cukortartalom == null || cukortartalom.isEmpty()) {
            ujCukortartalom = alkoholmentesItal.getCukortartalom();
        }
        assert cukortartalom != null;
        if (!cukortartalom.isEmpty()) {
            ujCukortartalom = cukortartalom;
        }
        RendelhetoTermek ujMentes = new RendelhetoTermek(nev, ujar);
        alkoholmentesItalRepo.alpadatFrissites(ujar, ujCukortartalom, nev);
        rendelhetoTermekRepo.save(ujMentes);
        alkoholmentesItalRepo.save(alkoholmentesItal);
    }
}
