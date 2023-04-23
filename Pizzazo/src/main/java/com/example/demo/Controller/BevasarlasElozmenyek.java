package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Rendeles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class BevasarlasElozmenyek {
    @Autowired
    private final RendelhetoTermekRepo termekekRepo;
    private final RendelesRepo rendelesRepo;

    public BevasarlasElozmenyek(RendelhetoTermekRepo termekekRepo, PizzaRepo pizzakRepo, HamburgerRepo hamburgerRepo, AlkoholosItalRepo alkoholosItalRepo, AlkoholmentesItalRepo alkoholmentesItalRepo, RendelesRepo rendelesRepo, FelhasznaloRepo felhasznaloRepo) {
        this.termekekRepo = termekekRepo;
        this.rendelesRepo = rendelesRepo;
    }

    public List<Rendeles> elozmenyek(List<Rendeles> elozmenyek, String email) {
        boolean marBenneVan = false;
        for (Rendeles rendeles : rendelesRepo.rendelesekListaja()) {
            if (rendeles.getFelhasznaloEmailCime().getId().equals(email)) {
                for (Rendeles rendeles1 : elozmenyek) {
                    if (Objects.equals(rendeles1.getId(), rendeles.getId())) {
                        marBenneVan = true;
                        break;
                    }
                    marBenneVan = false;
                }
                if (!marBenneVan) {
                    elozmenyek.add(rendeles);
                }
            }
        }
        return elozmenyek;
    }
}
