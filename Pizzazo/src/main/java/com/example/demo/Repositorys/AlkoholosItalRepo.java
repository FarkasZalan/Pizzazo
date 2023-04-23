package com.example.demo.Repositorys;

import com.example.demo.entity.AlkoholosItal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AlkoholosItalRepo extends JpaRepository<AlkoholosItal, String> {
    @Query(value = "select * from alkoholosital", nativeQuery = true)
    List<AlkoholosItal> alkoholosItalokListaja();

    @Modifying
    @Query(value="update alkoholosital set kep= :kep where alkoholos_ital_neve = :alkoholos_ital_neve", nativeQuery = true)
    void termekKepe(@Param("kep") String kep, @Param("alkoholos_ital_neve") String hamburger_neve);

    @Modifying
    @Query(value="update alkoholosital set alkoholos_ital_ara= :alkoholos_ital_ara, alkohol_szazalek= :alkohol_szazalek where alkoholos_ital_neve = :alkoholos_ital_neve", nativeQuery = true)
    void alapadatFrissites(@Param("alkoholos_ital_ara") Integer alkoholos_ital_ara, @Param("alkohol_szazalek") String alkohol_szazalek, @Param("alkoholos_ital_neve") String hamburger_neve);
}
