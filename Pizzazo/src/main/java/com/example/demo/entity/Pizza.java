package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
// Pizza table is 'pizza'
@Table(name = "pizza")
public class Pizza {
    // I used the pizza ID as the pizza name and I identify this variable as 'name'
    @Id
    @Column(name = "pizza_name", nullable = false, length = 50)
    private String name;

    // I create a connection between the 'item' table and the 'pizza' table
    // and if the item is deleted from the table then it will be deleted from the other one too
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pizza_name", nullable = false)
    private Item item;

    // this column will store the pizza price
    @Column(name = "pizza_price", nullable = false)
    private Integer pizzaPrice;

    // this column will store the content of the pizza
    @Column(name = "toppings", nullable = false, length = 100)
    private String toppings;

    public String getImage() {
        return image;
    }

    public void setImage(String kep) {
        this.image = kep;
    }

    // this column will store the pizza image as a MEDIUMBLOB
    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String image;

    public Pizza() {
    }

    public Pizza(String name, Item item, Integer pizzaPrice, String toppings, String image) {
        this.name = name;
        this.item = item;
        this.pizzaPrice = pizzaPrice;
        this.toppings = toppings;
        this.image = image;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public Integer getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(Integer pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}