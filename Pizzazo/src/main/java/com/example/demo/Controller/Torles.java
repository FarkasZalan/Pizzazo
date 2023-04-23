package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.RendelhetoTermek;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;

public class Torles {
    @Autowired
    private final RendelhetoTermekRepo termekekRepo;
    private final PizzaRepo pizzakRepo;
    private final HamburgerRepo hamburgerRepo;
    private final AlkoholosItalRepo alkoholosItalRepo;
    private final AlkoholmentesItalRepo alkoholmentesItalRepo;
    private final RendelesRepo rendelesRepo;
    private final FelhasznaloRepo felhasznaloRepo;

    public Torles(RendelhetoTermekRepo termekekRepo, PizzaRepo pizzakRepo, HamburgerRepo hamburgerRepo, AlkoholosItalRepo alkoholosItalRepo, AlkoholmentesItalRepo alkoholmentesItalRepo, RendelesRepo rendelesRepo, FelhasznaloRepo felhasznaloRepo) {
        this.termekekRepo = termekekRepo;
        this.pizzakRepo = pizzakRepo;
        this.hamburgerRepo = hamburgerRepo;
        this.alkoholosItalRepo = alkoholosItalRepo;
        this.alkoholmentesItalRepo = alkoholmentesItalRepo;
        this.rendelesRepo = rendelesRepo;
        this.felhasznaloRepo = felhasznaloRepo;
    }

    public int termekTorlese(String termekNeve, Map<RendelhetoTermek, Integer> lista, int eddigFizetendo) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        Iterator<Map.Entry<RendelhetoTermek, Integer>> elemek = lista.entrySet().iterator();
        while (elemek.hasNext()) {
            Map.Entry<RendelhetoTermek, Integer> elem = elemek.next();
            int szam = elem.getValue();

            if (elem.getKey().getNev().equals(termekNeve) && szam > 1) {
                szam--;
                elem.setValue(szam);
                eddigFizetendo -= elem.getKey().getAr();
                break;
            }
            if (elem.getKey().getNev().equals(termekNeve) && szam <= 1) {
                elemek.remove();
                eddigFizetendo -= elem.getKey().getAr();
                break;
            }
        }
        return eddigFizetendo;
    }
}
