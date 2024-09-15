package com.example.demo.Repositorys;

import com.example.demo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    // list all items
    @Query(value = "select * from orders", nativeQuery = true)
    List<Orders> listOfOrders();

    // return with the most ordered item
    // it works with 2 table the 'order_detailed_list' and with the 'orders' table
    // check max paid amount from the 'order_detailed_list' table and then searching this order
    // from the 'orders' table and then show the order items (item_name_key), order date and the price
    // and then limit 1 to the highest paid order to show
    @Query(value = "SELECT order_detailed_list.item_name_key AS item_name , SUM(order_detailed_list.quantity) AS quantity, MAX(orders.date_of_the_order) AS idopont FROM order_detailed_list INNER JOIN orders ON order_detailed_list.order_id=orders.order_id GROUP BY item_name ORDER BY quantity DESC LIMIT 1;", nativeQuery = true)
    String mostOrderedItem();

    // same with this but instead of Max it's Min
    @Query(value = "SELECT order_detailed_list.item_name_key AS item_name , SUM(order_detailed_list.quantity) AS quantity, MAX(orders.date_of_the_order) AS idopont FROM order_detailed_list INNER JOIN orders ON order_detailed_list.order_id=orders.order_id GROUP BY item_name ORDER BY quantity LIMIT 1;", nativeQuery = true)
    String leastOrderedItem();

    // here I used 2 tables, "users" and "orders"
    // Firs I find the highest paid order then I search the user who paid this order
    // by the user email (id) compare to the order users_email then limit to 1 and return it
    @Query(value = "SELECT users.email AS users_email,MAX(orders.total_amount) AS osszeg, orders.order_id AS azonosito, orders.date_of_the_order AS date_of_the_order FROM users INNER JOIN orders ON users.email=orders.users_email and orders.total_amount=(SELECT MAX(orders.total_amount) FROM orders);", nativeQuery = true)
    String mostPaid();
}
