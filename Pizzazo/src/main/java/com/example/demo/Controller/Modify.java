package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Modify {
    // for the @Autowired warning
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private final UsersRepo usersRepo;
    private final PizzaRepo pizzaRepo;
    private final ItemRepo itemRepo;
    private final HamburgerRepo hamburgerRepo;
    private final AlcoholRepo alcoholRepo;
    private final AlcoholFreeRepo alcoholFreeRepo;

    public Modify(UsersRepo usersRepo, PizzaRepo pizzaRepo, ItemRepo itemRepo, HamburgerRepo hamburgerRepo, AlcoholRepo alcoholRepo, AlcoholFreeRepo alcoholFreeRepo) {
        this.usersRepo = usersRepo;
        this.pizzaRepo = pizzaRepo;
        this.itemRepo = itemRepo;
        this.hamburgerRepo = hamburgerRepo;
        this.alcoholRepo = alcoholRepo;
        this.alcoholFreeRepo = alcoholFreeRepo;
    }

    // These parameters are the variables the user want to modify
    // If the user don't want to modify just 1 for example then the
    // other parameters will be null
    public Users userModify(Users loggedUser, String email, String name, String address, String phone, String password) {
        String newName = "";
        String newAddress = "";
        String newPhone = "";
        String newPassword = "";
        // check which variable is null or empty
        // and then gave them the user old datas
        // so it will not change at the saving
        if (name == null || name.isEmpty()) {
            newName = loggedUser.getName();
        }
        assert name != null;
        // if the given parameter doesn't empty that means the user want to change this item
        // so it will be the new variable value
        if (!name.isEmpty()) {
            newName = name;
        }
        if (address == null || address.isEmpty()) {
            newAddress = loggedUser.getAddress();
        }
        assert address != null;
        if (!address.isEmpty()) {
            newAddress = address;
        }
        if (phone == null || phone.isEmpty()) {
            newPhone = loggedUser.getPhone();
        }
        assert phone != null;
        if (!phone.isEmpty()) {
            newPhone = phone;
        }
        if (password == null || password.isEmpty()) {
            newPassword = loggedUser.getPassword();
        }
        assert password != null;
        if (!password.isEmpty()) {
            newPassword = password;
        }
        // when all the variables are checked then save the user
        loggedUser = new Users(email, newName, newAddress, newPhone, newPassword);
        usersRepo.save(loggedUser);
        return loggedUser;
    }

    public void pizzaModify(Pizza currentPizza, String name, int price, String toppings) {
        // same like the user modify
        int newPrice = 0;
        String newToppings = "";
        if (price == 0) {
            newPrice = currentPizza.getPizzaPrice();
        }
        if (price != 0) {
            newPrice = price;
        }
        if (toppings == null || toppings.isEmpty()) {
            newToppings = currentPizza.getToppings();
        }
        assert toppings != null;
        if (!toppings.isEmpty()) {
            newToppings = toppings;
        }
        // if all variables are checked then save with the repos
        Item newPizzaItem = new Item(name, newPrice);
        // newPrice and newToppings are obvious,
        // the name is necesary to find the correct item
        pizzaRepo.update(newPrice, newToppings, name);
        itemRepo.save(newPizzaItem);
        pizzaRepo.save(currentPizza);
    }

    public void hamburgerModify(Hamburger currentHamburger, String name, int price, String content) {
        int newPrice = 0;
        String newContent = "";
        if (price == 0) {
            newPrice = currentHamburger.getHamburger_price();
        }
        if (price != 0) {
            newPrice = price;
        }
        if (content == null || content.isEmpty()) {
            newContent = currentHamburger.getHamburgerContent();
        }
        assert content != null;
        if (!content.isEmpty()) {
            newContent = content;
        }
        Item newHamburgerItem = new Item(name, newPrice);
        hamburgerRepo.update(newPrice, newContent, name);
        itemRepo.save(newHamburgerItem);
        hamburgerRepo.save(currentHamburger);
    }

    public void alcoholModification(Alcohol currentAlcohol, String name, int ar, String alkoholSzazalek) {
        int newPrice = 0;
        String newAlcoholPercentage = "";
        if (ar == 0) {
            newPrice = currentAlcohol.getAlcoholPrice();
        }
        if (ar != 0) {
            newPrice = ar;
        }
        if (alkoholSzazalek == null || alkoholSzazalek.isEmpty()) {
            newAlcoholPercentage = currentAlcohol.getAlcoholPercentage();
        }
        assert alkoholSzazalek != null;
        if (!alkoholSzazalek.isEmpty()) {
            newAlcoholPercentage = alkoholSzazalek;
        }
        Item newAlcoholItem = new Item(name, newPrice);
        alcoholRepo.update(newPrice, newAlcoholPercentage, name);
        itemRepo.save(newAlcoholItem);
        alcoholRepo.save(currentAlcohol);
    }

    public void alcoholFreeModification(AlcoholFree currentAlcoholFree, String name, int ar, String cukortartalom) {
        int newPrice = 0;
        String newSugarContent = "";
        if (ar == 0) {
            newPrice = currentAlcoholFree.getAlcoholFreePrice();
        }
        if (ar != 0) {
            newPrice = ar;
        }
        if (cukortartalom == null || cukortartalom.isEmpty()) {
            newSugarContent = currentAlcoholFree.getSugarContent();
        }
        assert cukortartalom != null;
        if (!cukortartalom.isEmpty()) {
            newSugarContent = cukortartalom;
        }
        Item newAlcoholFreeItem = new Item(name, newPrice);
        alcoholFreeRepo.update(newPrice, newSugarContent, name);
        itemRepo.save(newAlcoholFreeItem);
        alcoholFreeRepo.save(currentAlcoholFree);
    }
}
