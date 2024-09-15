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
    // list all pizzas
    @Query(value = "select * from pizza", nativeQuery = true)
    List<Pizza> listOfPizzas();

    // this method is modify the item image by the item id(name).
    // because all items have a default image in the beginning
    // just need to update the old image
    @Modifying
    @Query(value="update pizza set image= :image where pizza_name = :pizza_name", nativeQuery = true)
    void itemImage(@Param("image") String image, @Param("pizza_name") String pizza_name);

    // basic updates for the item.
    // need all the variables to update everything and
    // don't modify to null
    @Modifying
    @Query(value="update pizza set pizza_price= :pizza_price, toppings= :toppings where pizza_name = :pizza_name", nativeQuery = true)
    void update(@Param("pizza_price") Integer pizza_price, @Param("toppings") String toppings, @Param("pizza_name") String pizza_name);
}
