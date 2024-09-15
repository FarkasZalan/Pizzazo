package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Map;

public class Delete {
    // for the @Autowired warning
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private final ItemRepo itemRepo;
    private final PizzaRepo pizzasRepo;
    private final HamburgerRepo hamburgerRepo;
    private final AlcoholRepo alcoholRepo;
    private final AlcoholFreeRepo alcoholFreeRepo;
    private final OrdersRepo ordersRepo;
    private final UsersRepo usersRepo;

    public Delete(ItemRepo itemRepo, PizzaRepo pizzasRepo, HamburgerRepo hamburgerRepo, AlcoholRepo alcoholRepo, AlcoholFreeRepo alcoholFreeRepo, OrdersRepo ordersRepo, UsersRepo usersRepo) {
        this.itemRepo = itemRepo;
        this.pizzasRepo = pizzasRepo;
        this.hamburgerRepo = hamburgerRepo;
        this.alcoholRepo = alcoholRepo;
        this.alcoholFreeRepo = alcoholFreeRepo;
        this.ordersRepo = ordersRepo;
        this.usersRepo = usersRepo;
    }

    public int itemDelete(String itemName, Map<Item, Integer> cartList, int haveToPayUntilNow) {
        Create create = new Create(itemRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        Iterator<Map.Entry<Item, Integer>> cartListIterator = cartList.entrySet().iterator();
        // go through on the given cart list with a while loop
        while (cartListIterator.hasNext()) {
            Map.Entry<Item, Integer> cartElement = cartListIterator.next();
            // give this variable the cart element current quantity
            int cartQuantity = cartElement.getValue();

            // if found the searched element in the shopping list
            // and the quantity is more then 1 then just lover with 1 the quantity
            // and the total
            if (cartElement.getKey().getItemName().equals(itemName) && cartQuantity > 1) {
                cartQuantity--;
                cartElement.setValue(cartQuantity);
                haveToPayUntilNow -= cartElement.getKey().getPrice();
                break;
            }
            // if the quantity is equals 1 then delete the element from the cart
            if (cartElement.getKey().getItemName().equals(itemName) && cartQuantity <= 1) {
                cartListIterator.remove();
                haveToPayUntilNow -= cartElement.getKey().getPrice();
                break;
            }
        }
        return haveToPayUntilNow;
    }
}
