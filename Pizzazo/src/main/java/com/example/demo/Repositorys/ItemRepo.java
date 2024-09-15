package com.example.demo.Repositorys;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, String> {
    // list all items
    @Query(value = "select * from item", nativeQuery = true)
    List<Item> listOfItems();
}
