package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
// Alcohol drinks table is 'alcohol'
@Table(name = "alcohol")
public class Alcohol {
    // I used the alcohol drinks ID as the alcohol drink name and I identify this variable as 'name'
    @Id
    @Column(name = "alcohol_name", nullable = false, length = 50)
    private String name;

    // I create a connection between the 'item' table and the 'alcohol' table
    // and if the item is deleted from the table then it will be deleted from the other one too
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alcohol_name", nullable = false)
    private Item item;

    // this column will store the alcohol drink price
    @Column(name = "alcohol_price", nullable = false)
    private Integer alcoholPrice;

    // this column will store the alcohol drink alcohol percentage
    @Column(name = "alcohol_percentage", nullable = false, length = 4)
    private String alcoholPercentage;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // this column will store the alcohol drink image as a MEDIUMBLOB
    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String image;

    public Alcohol() {
    }

    public Alcohol(String name, Item item, Integer alcoholPrice, String alcoholPercentage, String image) {
        this.name = name;
        this.item = item;
        this.alcoholPrice = alcoholPrice;
        this.alcoholPercentage = alcoholPercentage;
        this.image = image;
    }

    public String getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public Integer getAlcoholPrice() {
        return alcoholPrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item alcoholItem) {
        this.item = alcoholItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}