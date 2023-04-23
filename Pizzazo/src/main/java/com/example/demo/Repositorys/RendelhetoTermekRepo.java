package com.example.demo.Repositorys;

import com.example.demo.entity.RendelhetoTermek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendelhetoTermekRepo extends JpaRepository<RendelhetoTermek, String> {
    @Query(value = "select * from rendelhetotermek", nativeQuery = true)
    List<RendelhetoTermek> rendelhetotermekekListaja();
}
