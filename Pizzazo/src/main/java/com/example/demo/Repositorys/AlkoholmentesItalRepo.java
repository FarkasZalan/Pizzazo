package com.example.demo.Repositorys;

import com.example.demo.entity.AlkoholmentesItal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AlkoholmentesItalRepo extends JpaRepository<AlkoholmentesItal, String> {
    @Query(value = "select * from alkoholmentesital", nativeQuery = true)
    List<AlkoholmentesItal> alkoholmentesItalokListaja();

    @Modifying
    @Query(value="update alkoholmentesital set kep= :kep where alkoholmentes_ital_neve = :alkoholmentes_ital_neve", nativeQuery = true)
    void termekKepe(@Param("kep") String kep, @Param("alkoholmentes_ital_neve") String alkoholmentes_ital_neve);

    @Modifying
    @Query(value="update alkoholmentesital set alkoholmentes_ital_ara= :alkoholmentes_ital_ara, cukortartalom= :cukortartalom where alkoholmentes_ital_neve = :alkoholmentes_ital_neve", nativeQuery = true)
    void alpadatFrissites(@Param("alkoholmentes_ital_ara") Integer alkoholmentes_ital_ara, @Param("cukortartalom") String cukortartalom, @Param("alkoholmentes_ital_neve") String alkoholmentes_ital_neve);
}
