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
    // list all hamburgers
    @Query(value = "select * from hamburger", nativeQuery = true)
    List<Hamburger> listOfHamburgers();

    // this method is modify the item image by the item id(name).
    // because all items have a default image in the beginning
    // just need to update the old image
    @Modifying
    @Query(value="update hamburger set image= :image where hamburger_name = :hamburger_name", nativeQuery = true)
    void itemImage(@Param("image") String image, @Param("hamburger_name") String hamburger_name);

    // basic updates for the item.
    // need all the variables to update everything and
    // don't modify to null
    @Modifying
    @Query(value="update hamburger set hamburger_price= :hamburger_price, hamburger_content= :hamburger_content where hamburger_name = :hamburger_name", nativeQuery = true)
    void update(@Param("hamburger_price") Integer hamburger_price, @Param("hamburger_content") String hamburger_content, @Param("hamburger_name") String hamburger_name);
}
