package com.example.demo.Repositorys;

import com.example.demo.entity.Hamburger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface HamburgerRepo extends JpaRepository<Hamburger, String> {
    @Query(value = "select * from hamburger", nativeQuery = true)
    List<Hamburger> hamburgerekListaja();

    @Modifying
    @Query(value="update hamburger set kep= :kep where hamburger_neve = :hamburger_neve", nativeQuery = true)
    void termekKepe(@Param("kep") String kep, @Param("hamburger_neve") String hamburger_neve);

    @Modifying
    @Query(value="update hamburger set hamburger_ara= :hamburger_ara, tartalom= :tartalom where hamburger_neve = :hamburger_neve", nativeQuery = true)
    void alapadatFrissites(@Param("hamburger_ara") Integer hamburger_ara,  @Param("tartalom") String tartalom, @Param("hamburger_neve") String hamburger_neve);
}
