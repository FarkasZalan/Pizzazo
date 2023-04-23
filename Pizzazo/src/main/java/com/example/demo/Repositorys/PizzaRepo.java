package com.example.demo.Repositorys;

import com.example.demo.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PizzaRepo extends JpaRepository<Pizza, String> {
    @Query(value = "select * from pizza", nativeQuery = true)
    List<Pizza> pizzakListaja();

    @Modifying
    @Query(value="update pizza set kep= :kep where pizza_neve = :pizza_neve", nativeQuery = true)
    void termekKepe(@Param("kep") String kep, @Param("pizza_neve") String pizza_neve);

    @Modifying
    @Query(value="update pizza set pizza_ara= :pizza_ara, feltet= :feltet where pizza_neve = :pizza_neve", nativeQuery = true)
    void alapadatFrissites(@Param("pizza_ara") Integer pizza_ara, @Param("feltet") String feltet, @Param("pizza_neve") String pizza_neve);
}
