CREATE TABLE IF NOT EXISTS users (
    users_email VARCHAR(128) NOT NULL,
    user_name VARCHAR(128) NOT NULL,
    address VARCHAR(128) NOT NULL,
    phone VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS item (
    item_name VARCHAR(200) NOT NULL,
    price INT(10) NOT NULL,
    PRIMARY KEY (nev)
);

CREATE TABLE IF NOT EXISTS orders (
    orderID INT(10) NOT NULL AUTO_INCREMENT,
    usersEmail VARCHAR(128) NOT NULL ,
    date_of_the_order VARCHAR(128) NOT NULL,
    totalAmount INT(5) NOT NULL,
    quantity INT(5) NOT NULL,
    PRIMARY KEY (orderID),
    FOREIGN KEY (usersEmail) REFERENCES users(email) ON DELETE CASCADE,
    FOREIGN KEY (orderID) REFERENCES shoppingList(orderID) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS pizza (
    pizza_name VARCHAR(200) NOT NULL,
    pizza_price INT(5) NOT NULL,
    toppings VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    PRIMARY KEY (pizza_name),
    FOREIGN KEY (pizza_name) REFERENCES item(itemName) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS hamburger (
    hamburger_name VARCHAR(200) NOT NULL,
    hamburger_price INT(5) NOT NULL,
    hamburger_content VARCHAR(255) NOT NULL,
    image VARCHAR(255) NOT NULL,
    PRIMARY KEY (hamburger_name),
    FOREIGN KEY (hamburger_name) REFERENCES item(itemName) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alcohol (
    alcohol_name VARCHAR(200) NOT NULL,
    alcohol_price INT(5) NOT NULL,
    alcohol_percentage VARCHAR(4) NOT NULL,
    image VARCHAR(255) NOT NULL,
    PRIMARY KEY (alcohol_name),
    FOREIGN KEY (alcohol_name) REFERENCES item(itemName) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alcohol_free (
    alcohol_free_name VARCHAR(200) NOT NULL,
    alcohol_free_price INT(5) NOT NULL,
    sugar_content VARCHAR(4) NOT NULL,
    image VARCHAR(255) NOT NULL,
    PRIMARY KEY (alcohol_free_name),
    FOREIGN KEY (alcohol_free_name) REFERENCES item(itemName) ON DELETE CASCADE
);

-- -- --termekNeve listaban cascadolva
-- alter table order_detailed_list DROP FOREIGN KEY FKohpyu8g5ttgi45tv08wg85692;
-- alter table order_detailed_list ADD constraint FKohpyu8g5ttgi45tv08wg85692 foreign key (item_name_key) references item (itemName) ON DELETE CASCADE;

--mezok meret novelese
ALTER TABLE `orders` CHANGE `total_amount` `total_amount` INT(255) NOT NULL;
ALTER TABLE `orders` CHANGE `order_id` `order_id` INT(255) NOT NULL;
ALTER TABLE `order_detailed_list` CHANGE `order_id` `order_id` INT(255) NOT NULL, CHANGE `quantity` `quantity` INT(255) NULL DEFAULT NULL;
ALTER TABLE `order_id` CHANGE `next_val` `next_val` BIGINT(255) NULL DEFAULT NULL;