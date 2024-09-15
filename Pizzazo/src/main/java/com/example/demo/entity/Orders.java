package com.example.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashMap;
import java.util.Map;

@Entity
// Orders table is 'order'
@Table(name = "orders")
public class Orders {
    // Create a new table as 'order_detailed_list'
    // which will contains the items name, quantity for all item
    // and connect with
    // the 'orders' table with the 'order_id'
    // this table is help to store all products
    // and their associated quantities ordered from them
    @ElementCollection
    @MapKeyColumn(name = "item_name")
    @Column(name = "quantity")
    @CollectionTable(name = "order_detailed_list", joinColumns = {@JoinColumn(name = "order_id")},
            foreignKey = @ForeignKey(
                    name = "orderIdentifier", foreignKeyDefinition = "foreign key (order_id) references orders (order_id) on delete cascade"
            ))
    Map<Item, Integer> itemName = new HashMap<>();

    // this generator called 'order_id' will count the orders,
    // so it will be the id for the orders
    @Id
    @SequenceGenerator(
            name = "order_id",
            sequenceName = "order_id",
            allocationSize = 1
    )

    // and here the generated number will be the 'order_id'
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id")
    @Column(name = "order_id", nullable = false)
    private Integer id;

    // this column will store the order total
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    // this column will store the order date
    @Column(name = "date_of_the_order", nullable = false, length = 128)
    private String orderDate;

    // connect with the table 'users' with the user's email address
    // so later with the statistic it can be used to show the user
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "users_email", nullable = false)
    private Users usersEmail;

    public Orders() {
    }

    public Orders(Integer totalAmount, String orderDate, Users usersEmail, Map<Item, Integer> termekek) {
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.usersEmail = usersEmail;
        this.itemName = termekek;
    }

    public Map<Item, Integer> getItemName() {
        return itemName;
    }

    public void setItemName(Map<Item, Integer> itemName) {
        this.itemName = itemName;
    }

    public Users getUserEmail() {
        return usersEmail;
    }

    public void setFelhasznaloEmailCime(Users usersEmail) {
        this.usersEmail = usersEmail;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}