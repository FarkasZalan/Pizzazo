package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class Login {
    // for the @Autowired warning
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private final ItemRepo itemRepo;
    private final UsersRepo usersRepo;

    // Constructor for dependency injection of repositories
    public Login(ItemRepo itemRepo, PizzaRepo pizzasRepo, HamburgerRepo hamburgerRepo, AlcoholRepo alcoholRepo, AlcoholFreeRepo alcoholFreeRepo, OrdersRepo ordersRepo, UsersRepo usersRepo) {
        this.itemRepo = itemRepo;
        this.usersRepo = usersRepo;
    }

    public String userLogin(String email, String password) {
        String message = "";
        // check if the given email is in the users list
        // (there are registered an account with this email)
        for (Users userElement : usersRepo.listOfTheUsers()) {
            if (Objects.equals(userElement.getId(), email)) {
                message = "Van email";
            }
            if (Objects.equals(userElement.getPassword(), password) && Objects.equals(userElement.getId(), email)) {
                // if the email found and the password is correct then
                // it will return with this string and show on the html area later
                message = "Sikeres belépés";
                break;
            }
        }
        return message;
    }

    public Users findUser(String email) {
        Users foundUser = new Users("", "", "", "", "");
        // go through the users list
        // and if found a user with this email then return with this user
        for (Users user : usersRepo.listOfTheUsers()) {
            if (user.getId().equals(email)) {
                foundUser.setId(user.getId());
                foundUser.setName(user.getName());
                foundUser.setAddress(user.getAddress());
                foundUser.setPhone(user.getPhone());
                foundUser.setPassword(user.getPassword());
            }
        }
        return foundUser;
    }
}
