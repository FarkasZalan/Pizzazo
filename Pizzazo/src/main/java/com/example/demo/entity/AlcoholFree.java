package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
// Alcohol-free drinks table is 'alcoholFree'
@Table(name = "alcohol_free")
public class AlcoholFree {
    // I used the Alcohol-free drinks ID as the Alcohol-free name and I identify this variable as 'name'
    @Id
    @Column(name = "alcohol_free_name", nullable = false, length = 50)
    private String name;

    // I create a connection between the 'item' table and the 'alcoholFree' table
    // and if the item is deleted from the table then it will be deleted from the other one too
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "alcohol_free_name", nullable = false)
    private Item item;

    // this column will store the alcohol-free drink price
    @Column(name = "alcohol_free_price", nullable = false)
    private Integer alcoholFreePrice;

    // this column will store the alcohol-free drink sugar content
    @Column(name = "sugar_content", nullable = false, length = 4)
    private String sugarContent;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // this column will store the alcohol-free drink image as a MEDIUMBLOB
    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "MEDIUMBLOB")
    private String image;

    public AlcoholFree() {
    }

    public AlcoholFree(String name, Item item, Integer alcoholFreePrice, String sugarContent, String image) {
        this.name = name;
        this.item = item;
        this.alcoholFreePrice = alcoholFreePrice;
        this.sugarContent = sugarContent;
        this.image = image;
    }

    public String getSugarContent() {
        return sugarContent;
    }

    public void setSugarContent(String sugarContent) {
        this.sugarContent = sugarContent;
    }

    public Integer getAlcoholFreePrice() {
        return alcoholFreePrice;
    }

    public void setAlcoholFreePrice(Integer alcoholFreePrice) {
        this.alcoholFreePrice = alcoholFreePrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item alcoholFreeItem) {
        this.item = alcoholFreeItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}