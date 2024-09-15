package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class ShoppingHistory {
    // for the @Autowired warning
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private final ItemRepo itemRepo;
    private final OrdersRepo ordersRepo;

    public ShoppingHistory(ItemRepo itemRepo, PizzaRepo pizzakRepo, HamburgerRepo hamburgerRepo, AlcoholRepo alcoholRepo, AlcoholFreeRepo alcoholFreeRepo, OrdersRepo ordersRepo, UsersRepo usersRepo) {
        this.itemRepo = itemRepo;
        this.ordersRepo = ordersRepo;
    }

    // this will return with orders from the user shopping history
    public List<Orders> shoppingHistory(List<Orders> shoppingHistory, String email) {
        // this variable will help to check if the order is already in the list or not
        boolean alreadyCounted = false;
        // with a for loop we check if one of the orders contains the logged in user id (email)
        for (Orders orders : ordersRepo.listOfOrders()) {
            if (orders.getUserEmail().getId().equals(email)) {
                // if found a match with the emails then check if the history list already
                // contains this order
                for (Orders orders1 : shoppingHistory) {
                    if (Objects.equals(orders1.getId(), orders.getId())) {
                        // if already in the list then break from this loop and check the next order
                        alreadyCounted = true;
                        break;
                    }
                    alreadyCounted = false;
                }
                if (!alreadyCounted) {
                    // if the order is not is the list yet then add to the list
                    shoppingHistory.add(orders);
                }
            }
        }
        return shoppingHistory;
    }
}
