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
    public Users userModify(Users users, String email, String name, String address, String phone, String password) {
        String newName = "";
        String newAddress = "";
        String newPhone = "";
        String newPassword = "";
        // check which variable is null or empty
        // and then gave them the user old datas
        // so it will not change at the saving
        if (name == null || name.isEmpty()) {
            newName = users.getName();
        }
        assert name != null;
        // if the given parameter doesn't empty that means the user want to change this item
        // so it will be the new variable value
        if (!name.isEmpty()) {
            newName = name;
        }
        if (address == null || address.isEmpty()) {
            newAddress = users.getAddress();
        }
        assert address != null;
        if (!address.isEmpty()) {
            newAddress = address;
        }
        if (phone == null || phone.isEmpty()) {
            newPhone = users.getPhone();
        }
        assert phone != null;
        if (!phone.isEmpty()) {
            newPhone = phone;
        }
        if (password == null || password.isEmpty()) {
            newPassword = users.getPassword();
        }
        assert password != null;
        if (!password.isEmpty()) {
            newPassword = password;
        }
        // when all the variables are checked then save the user
        users = new Users(email, newName, newAddress, newPhone, newPassword);
        usersRepo.save(users);
        return users;
    }

    public void pizzaModify(Pizza pizza, String name, int price, String toppings) {
        // same like the user modify
        int newPrice = 0;
        String newToppings = "";
        if (price == 0) {
            newPrice = pizza.getPizzaPrice();
        }
        if (price != 0) {
            newPrice = price;
        }
        if (toppings == null || toppings.isEmpty()) {
            newToppings = pizza.getToppings();
        }
        assert toppings != null;
        if (!toppings.isEmpty()) {
            newToppings = toppings;
        }
        // if all variables are checked then save with the repos
        Item newPizza = new Item(name, newPrice);
        // newPrice and newToppings are obvious,
        // the name is necesary to find the correct item
        pizzaRepo.update(newPrice, newToppings, name);
        itemRepo.save(newPizza);
        pizzaRepo.save(pizza);
    }

    public void hamburgerModify(Hamburger hamburger, String name, int price, String content) {
        int newPrice = 0;
        String newContent = "";
        if (price == 0) {
            newPrice = hamburger.getHamburger_price();
        }
        if (price != 0) {
            newPrice = price;
        }
        if (content == null || content.isEmpty()) {
            newContent = hamburger.getHamburgerContent();
        }
        assert content != null;
        if (!content.isEmpty()) {
            newContent = content;
        }
        Item ujHamburger = new Item(name, newPrice);
        hamburgerRepo.update(newPrice, newContent, name);
        itemRepo.save(ujHamburger);
        hamburgerRepo.save(hamburger);
    }

    public void alkoholosModositas(Alcohol alcohol, String name, int ar, String alkoholSzazalek) {
        int ujar = 0;
        String ujalkoholszazalek = "";
        if (ar == 0) {
            ujar = alcohol.getAlcoholPrice();
        }
        if (ar != 0) {
            ujar = ar;
        }
        if (alkoholSzazalek == null || alkoholSzazalek.isEmpty()) {
            ujalkoholszazalek = alcohol.getAlcoholPercentage();
        }
        assert alkoholSzazalek != null;
        if (!alkoholSzazalek.isEmpty()) {
            ujalkoholszazalek = alkoholSzazalek;
        }
        Item ujAlkoholos = new Item(name, ujar);
        alcoholRepo.update(ujar, ujalkoholszazalek, name);
        itemRepo.save(ujAlkoholos);
        alcoholRepo.save(alcohol);
    }

    public void alkoholmentesModositas(AlcoholFree alcoholFree, String name, int ar, String cukortartalom) {
        int ujar = 0;
        String ujCukortartalom = "";
        if (ar == 0) {
            ujar = alcoholFree.getAlcoholFreePrice();
        }
        if (ar != 0) {
            ujar = ar;
        }
        if (cukortartalom == null || cukortartalom.isEmpty()) {
            ujCukortartalom = alcoholFree.getSugarContent();
        }
        assert cukortartalom != null;
        if (!cukortartalom.isEmpty()) {
            ujCukortartalom = cukortartalom;
        }
        Item ujMentes = new Item(name, ujar);
        alcoholFreeRepo.update(ujar, ujCukortartalom, name);
        itemRepo.save(ujMentes);
        alcoholFreeRepo.save(alcoholFree);
    }
}
