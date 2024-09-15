package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
// Hamburger table is 'hamburger'
@Table(name = "hamburger")
public class Hamburger {
    // I used the hamburger ID as the hamburger name and I identify this variable as 'name'
    @Id
    @Column(name = "hamburger_name", nullable = false, length = 50)
    private String name;

    // I create a connection between the 'item' table and the 'hamburger' table
    // and if the item is deleted from the table then it will be deleted from the other one too
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "hamburger_name", nullable = false)
    private Item item;

    // this column will store the hamburger price
    @Column(name = "hamburger_price", nullable = false)
    private Integer hamburger_price;

    // this column will store the content of the hamburger
    @Column(name = "hamburger_content", nullable = false, length = 100)
    private String hamburgerContent;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // this column will store the hamburger image as a MEDIUMBLOB
    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String image;

    public Hamburger() {
    }

    public Hamburger(String name, Item item, Integer hamburger_price, String hamburgerContent, String image) {
        this.name = name;
        this.item = item;
        this.hamburger_price = hamburger_price;
        this.hamburgerContent = hamburgerContent;
        this.image = image;
    }

    public String getHamburgerContent() {
        return hamburgerContent;
    }

    public void setHamburgerContent(String hamburgerContent) {
        this.hamburgerContent = hamburgerContent;
    }

    public Integer getHamburger_price() {
        return hamburger_price;
    }

    public void setHamburger_price(Integer priceOfTheHamburger) {
        this.hamburger_price = priceOfTheHamburger;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item hamburgerItem) {
        this.item = hamburgerItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}