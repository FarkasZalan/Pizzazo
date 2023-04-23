package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Felhasznalo;
import com.example.demo.entity.Rendeles;
import com.example.demo.entity.RendelhetoTermek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@RestController
public class Letrehozas {
    @Autowired
    private RendelhetoTermekRepo termekekRepo;
    private RendelesRepo rendelesRepo;
    private FelhasznaloRepo felhasznaloRepo;
    private int eddigFizetendo = 0;
    private String idopont = "";

    public Letrehozas(RendelhetoTermekRepo termekekRepo, PizzaRepo pizzakRepo, HamburgerRepo hamburgerRepo, AlkoholosItalRepo alkoholosItalRepo, AlkoholmentesItalRepo alkoholmentesItalRepo, RendelesRepo rendelesRepo, FelhasznaloRepo felhasznaloRepo) {
        this.termekekRepo = termekekRepo;
        this.rendelesRepo = rendelesRepo;
        this.felhasznaloRepo = felhasznaloRepo;
    }

    public Letrehozas(int eddigFizetendo) {
        this.eddigFizetendo = eddigFizetendo;
    }

    Letrehozas() {
    }

    public int getEddigFizetendo() {
        return eddigFizetendo;
    }

    public void setEddigFizetendo(int eddigFizetendo) {
        this.eddigFizetendo = eddigFizetendo;
    }

    public String getIdopont() {
        return idopont;
    }

    public String felhasznaloLetrehoz(Felhasznalo felhasznalo) {
        boolean van = false;
        if (!(felhasznalo.getId().contains("@") && (felhasznalo.getId().contains(".")))) {
            return "Érvénytelen email cím, az email címben kell lennie '@'-nak és egy érvényes domainnek";
        }
        for (Felhasznalo felhasznalo1 : felhasznaloRepo.felhasznalokListaja()) {

            if (Objects.equals(felhasznalo.getId(), felhasznalo1.getId())) {
                van = true;
                break;
            }
        }
        if (!van) {
            felhasznaloRepo.save(felhasznalo);
            felhasznaloRepo.felhasznalokListaja().add(felhasznalo);
            return "Sikeres mentés!";
        }
        return "Ezzel az email címmel már regisztráltak!";
    }

    public int rendelesLetrehozasa(Map<RendelhetoTermek, Integer> lista, Felhasznalo felhasznalo, int fizetendo) {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        idopont = dateFormat.format(date);

        Rendeles rendeles = new Rendeles(fizetendo, idopont, felhasznalo, lista);
        rendelesRepo.save(rendeles);
        return rendeles.getId();
    }

    public Map<RendelhetoTermek, Integer> pizzaHozzaadasa(Map<RendelhetoTermek, Integer> lista, String pizzaNeve) {
        boolean benneVan = false;
        for (RendelhetoTermek termekek : termekekRepo.rendelhetotermekekListaja()) {
            if (pizzaNeve.equals(termekek.getNev())) {
                if (lista.size() == 0) {
                    lista.put(termekek, 1);
                } else {
                    for (Map.Entry<RendelhetoTermek, Integer> listabanLevo : lista.entrySet()) {
                        if (listabanLevo.getKey().getNev().contains(pizzaNeve)) {
                            Integer mennyiseg = listabanLevo.getValue();
                            mennyiseg++;
                            listabanLevo.setValue(mennyiseg);
                            benneVan = true;
                        }
                    }
                    if (!benneVan) {
                        lista.put(termekek, 1);
                    }
                }
                eddigFizetendo += termekek.getAr();
            }
        }
        return lista;
    }

    public Map<RendelhetoTermek, Integer> hamburgerHozzaadasa(Map<RendelhetoTermek, Integer> lista, String hamburgerNeve) {
        boolean benneVan = false;
        for (RendelhetoTermek termekek : termekekRepo.rendelhetotermekekListaja()) {
            if (hamburgerNeve.equals(termekek.getNev())) {
                if (lista.size() == 0) {
                    lista.put(termekek, 1);
                } else {
                    for (Map.Entry<RendelhetoTermek, Integer> listabanLevo : lista.entrySet()) {
                        if (listabanLevo.getKey().getNev().contains(hamburgerNeve)) {
                            Integer mennyiseg = listabanLevo.getValue();
                            mennyiseg++;
                            listabanLevo.setValue(mennyiseg);
                            benneVan = true;
                        }
                    }
                    if (!benneVan) {
                        lista.put(termekek, 1);
                    }
                }
                eddigFizetendo += termekek.getAr();
            }
        }
        return lista;
    }

    public Map<RendelhetoTermek, Integer> alkoholosItalHozzaadasa(Map<RendelhetoTermek, Integer> lista, String alkeszpiaNeve) {
        boolean benneVan = false;
        for (RendelhetoTermek termekek : termekekRepo.rendelhetotermekekListaja()) {
            if (alkeszpiaNeve.equals(termekek.getNev())) {
                if (lista.size() == 0) {
                    lista.put(termekek, 1);
                } else {
                    for (Map.Entry<RendelhetoTermek, Integer> listabanLevo : lista.entrySet()) {
                        if (listabanLevo.getKey().getNev().contains(alkeszpiaNeve)) {
                            Integer mennyiseg = listabanLevo.getValue();
                            mennyiseg++;
                            listabanLevo.setValue(mennyiseg);
                            benneVan = true;
                        }
                    }
                    if (!benneVan) {
                        lista.put(termekek, 1);
                    }
                }
                eddigFizetendo += termekek.getAr();
            }
        }
        return lista;
    }

    public Map<RendelhetoTermek, Integer> termekHozzaadasa(Map<RendelhetoTermek, Integer> lista, String termekNeve) {
        boolean benneVan = false;
        for (RendelhetoTermek termekek : termekekRepo.rendelhetotermekekListaja()) {
            if (termekNeve.equals(termekek.getNev())) {
                if (lista.size() == 0) {
                    lista.put(termekek, 1);
                } else {
                    for (Map.Entry<RendelhetoTermek, Integer> listabanLevo : lista.entrySet()) {
                        if (listabanLevo.getKey().getNev().contains(termekNeve)) {
                            Integer mennyiseg = listabanLevo.getValue();
                            mennyiseg++;
                            listabanLevo.setValue(mennyiseg);
                            benneVan = true;
                        }
                    }
                    if (!benneVan) {
                        lista.put(termekek, 1);
                    }
                }
                eddigFizetendo += termekek.getAr();
            }
        }
        return lista;
    }

    public Map<RendelhetoTermek, Integer> alkoholmentesItalHozzaadasa(Map<RendelhetoTermek, Integer> lista, String alkoholmentesitalNeve) {
        boolean benneVan = false;
        for (RendelhetoTermek termekek : termekekRepo.rendelhetotermekekListaja()) {
            if (alkoholmentesitalNeve.equals(termekek.getNev())) {
                if (lista.size() == 0) {
                    lista.put(termekek, 1);
                } else {
                    for (Map.Entry<RendelhetoTermek, Integer> listabanLevo : lista.entrySet()) {
                        if (listabanLevo.getKey().getNev().contains(alkoholmentesitalNeve)) {
                            Integer mennyiseg = listabanLevo.getValue();
                            mennyiseg++;
                            listabanLevo.setValue(mennyiseg);
                            benneVan = true;
                        }
                    }
                    if (!benneVan) {
                        lista.put(termekek, 1);
                    }
                }
                eddigFizetendo += termekek.getAr();
            }
        }
        return lista;
    }
}
