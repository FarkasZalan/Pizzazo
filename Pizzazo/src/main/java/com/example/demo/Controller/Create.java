package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Item;
import com.example.demo.entity.Orders;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Service
@RestController
public class Create {
    @Autowired
    private ItemRepo itemsRepo;
    private OrdersRepo ordersRepo;
    private UsersRepo usersRepo;
    private int haveToPayUntilNow = 0;
    private String orderDate = "";

    public Create(ItemRepo itemsRepo, PizzaRepo pizzasRepo, HamburgerRepo hamburgerRepo, AlcoholRepo alcoholRepo, AlcoholFreeRepo alcoholFreeRepo, OrdersRepo ordersRepo, UsersRepo usersRepo) {
        this.itemsRepo = itemsRepo;
        this.ordersRepo = ordersRepo;
        this.usersRepo = usersRepo;
    }

    public Create(int haveToPayUntilNow) {
        this.haveToPayUntilNow = haveToPayUntilNow;
    }

    Create() {
    }

    public int getHaveToPayUntilNow() {
        return haveToPayUntilNow;
    }

    public void setHaveToPayUntilNow(int haveToPayUntilNow) {
        this.haveToPayUntilNow = haveToPayUntilNow;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String userCreate(Users users) {
        boolean userExists = false;
        if (!(users.getId().contains("@") && (users.getId().contains(".")))) {
            return "Érvénytelen email cím, az email címben kell lennie '@'-nak és egy érvényes domainnek";
        }
        for (Users users1 : usersRepo.listOfTheUsers()) {
            // if user already exists with the given email then return with the
            // email is alredy registered message
            if (Objects.equals(users.getId(), users1.getId())) {
                userExists = true;
                break;
            }
        }
        // if the email is not registered yet then save the user in the repo
        if (!userExists) {
            usersRepo.save(users);
            usersRepo.listOfTheUsers().add(users);
            return "Sikeres mentés!";
        }
        return "Ezzel az email címmel már regisztráltak!";
    }

    public int orderCreate(Map<Item, Integer> cartList, Users users, int total) {
        // get the current time for the order date
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
        orderDate = dateFormat.format(date);

        // save the order
        Orders orders = new Orders(total, orderDate, users, cartList);
        ordersRepo.save(orders);
        return orders.getId();
    }

    public Map<Item, Integer> addElementToCart(Map<Item, Integer> cartList, String pizzaName) {
        boolean alreadyInTheCart = false;
        // search the product in the item list
        for (Item items : itemsRepo.listOfItems()) {
            if (pizzaName.equals(items.getItemName())) {
                // if found the product in the item list and
                // the cart list is empty then put inside with the quantity 1
                if (cartList.size() == 0) {
                    cartList.put(items, 1);
                } else {
                    // if the cart isn't empty then go through the list
                    // if the given element is already in the cart
                    for (Map.Entry<Item, Integer> cartElement : cartList.entrySet()) {
                        // if the element is already in the cart then increase the quantity for this item
                        if (cartElement.getKey().getItemName().contains(pizzaName)) {
                            Integer quantity = cartElement.getValue();
                            quantity++;
                            cartElement.setValue(quantity);
                            alreadyInTheCart = true;
                        }
                    }
                    // if not found this element in the cart then put inside with the quantity 1
                    if (!alreadyInTheCart) {
                        cartList.put(items, 1);
                    }
                }
                haveToPayUntilNow += items.getPrice();
            }
        }
        return cartList;
    }
}
