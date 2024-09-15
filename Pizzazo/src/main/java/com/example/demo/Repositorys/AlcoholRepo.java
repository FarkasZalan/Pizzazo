package com.example.demo.Repositorys;

import com.example.demo.entity.Alcohol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AlcoholRepo extends JpaRepository<Alcohol, String> {
    // list all the alcohol drinks
    @Query(value = "select * from alcohol", nativeQuery = true)
    List<Alcohol> listOfAlcohol();

    // this method is modify the item image by the item id(name).
    // because all items have a default image in the beginning
    // just need to update the old image
    @Modifying
    @Query(value="update alcohol set image= :image where alcohol_name = :alcohol_name", nativeQuery = true)
    void itemImage(@Param("image") String image, @Param("alcohol_name") String hamburger_neve);

    // basic updates for the item.
    // need all the variables to update everything and
    // don't modify to null
    @Modifying
    @Query(value="update alcohol set alcohol_price= :alcohol_price, alcohol_percentage= :alcohol_percentage where alcohol_name = :alcohol_name", nativeQuery = true)
    void update(@Param("alcohol_price") Integer alcohol_price, @Param("alcohol_percentage") String alcohol_percentage, @Param("alcohol_name") String hamburger_neve);
}
