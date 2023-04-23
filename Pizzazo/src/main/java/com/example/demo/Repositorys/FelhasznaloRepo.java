package com.example.demo.Repositorys;

import com.example.demo.entity.Felhasznalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FelhasznaloRepo extends JpaRepository<Felhasznalo, String> {
    @Query(value = "select * from felhasznalo", nativeQuery = true)
    List<Felhasznalo> felhasznalokListaja();
}
