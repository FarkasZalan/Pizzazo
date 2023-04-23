package com.example.demo.Repositorys;

import com.example.demo.entity.Rendeles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RendelesRepo extends JpaRepository<Rendeles, Integer> {
    @Query(value = "select * from rendeles", nativeQuery = true)
    List<Rendeles> rendelesekListaja();

    @Query(value = "SELECT rendeles_reszeltes_listaja.termek_neve_key AS termek_neve , SUM(rendeles_reszeltes_listaja.mennyiseg) AS mennyiseg, MAX(rendeles.rendeles_idopontja) AS idopont FROM rendeles_reszeltes_listaja INNER JOIN rendeles ON rendeles_reszeltes_listaja.rendelesid=rendeles.rendeles_id GROUP BY termek_neve ORDER BY mennyiseg DESC LIMIT 1;", nativeQuery = true)
    String legtobbetRendeltTermekek();

    @Query(value = "SELECT rendeles_reszeltes_listaja.termek_neve_key AS termek_neve , SUM(rendeles_reszeltes_listaja.mennyiseg) AS mennyiseg, MAX(rendeles.rendeles_idopontja) AS idopont FROM rendeles_reszeltes_listaja INNER JOIN rendeles ON rendeles_reszeltes_listaja.rendelesid=rendeles.rendeles_id GROUP BY termek_neve ORDER BY mennyiseg LIMIT 1;", nativeQuery = true)
    String legkevesebbetRendeltTermekek();

    @Query(value = "SELECT felhasznalo.email AS felhasznalo_email_cime,MAX(rendeles.fizetendo_osszeg) AS osszeg, rendeles.rendeles_id AS azonosito, rendeles.rendeles_idopontja AS idopont FROM felhasznalo INNER JOIN rendeles ON felhasznalo.email=rendeles.felhasznalo_email_cime and rendeles.fizetendo_osszeg=(SELECT MAX(rendeles.fizetendo_osszeg) FROM rendeles);", nativeQuery = true)
    String legtobbetFizetett();
}
