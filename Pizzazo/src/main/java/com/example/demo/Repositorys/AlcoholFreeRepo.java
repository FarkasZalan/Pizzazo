package com.example.demo.Repositorys;

import com.example.demo.entity.AlcoholFree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AlcoholFreeRepo extends JpaRepository<AlcoholFree, String> {
    // list all the alcohol-free drinks
    @Query(value = "select * from alcohol_free", nativeQuery = true)
    List<AlcoholFree> listOfAlcoholFree();

    // this method is modify the item image by the item id(name).
    // because all items have a default image in the beginning
    // just need to update the old image
    @Modifying
    @Query(value="update alcohol_free set image= :image where alcohol_free_name = :alcohol_free_name", nativeQuery = true)
    void itemImage(@Param("image") String image, @Param("alcohol_free_name") String alcohol_free_name);

    // basic updates for the item.
    // need all the variables to update everything and
    // don't modify to null
    @Modifying
    @Query(value="update alcohol_free set alcohol_free_price= :alcohol_free_price, sugar_content= :sugar_content where alcohol_free_name = :alcohol_free_name", nativeQuery = true)
    void update(@Param("alcohol_free_price") Integer alcohol_free_price, @Param("sugar_content") String sugar_content, @Param("alcohol_free_name") String alcohol_free_name);
}
