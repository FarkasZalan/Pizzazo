package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
// I create a table where I will store every item name and price
// In this table I will store all the items, so I named this table as 'item'
@Table(name = "item")
public class Item {
    // I set the name as the ID, the key in ths table
    @Id
    @Column(name = "item_name", nullable = false, length = 200)
    private String itemName;
    // this column will store the items price
    @Column(name = "price", nullable = false)
    private Integer price;

    // Create connection between this table and the 'pizza' table
    // and if I delete this item, then will be deleted from the pizza table
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Pizza pizza;

    // Create connection between this table and the 'alcoholFree' table
    // and if I delete this item, then will be deleted from the alcoholFree table
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private AlcoholFree alcoholFree;

    // Create connection between this table and the 'alcohol' table
    // and if I delete this item, then will be deleted from the alcohol table
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Alcohol alcohol;

    // Create connection between this table and the 'hamburger' table
    // and if I delete this item, then will be deleted from the hamburger table
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Hamburger hamburger;

    public Item() {
    }

    public Item(String itemName, Integer price) {
        this.itemName = itemName;
        this.price = price;
    }

    public Hamburger getHamburger() {
        return hamburger;
    }

    public void setHamburger(Hamburger hamburger) {
        this.hamburger = hamburger;
    }

    public Alcohol getAlkoholosital() {
        return alcohol;
    }

    public void setAlkoholosital(Alcohol alcohol) {
        this.alcohol = alcohol;
    }

    public AlcoholFree getAlkoholmentesital() {
        return alcoholFree;
    }

    public void setAlkoholmentesital(AlcoholFree alcoholFree) {
        this.alcoholFree = alcoholFree;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }
}