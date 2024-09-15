--Items upload, pizzas

INSERT  into item(item_name, price) VALUES ('48 cm 4 sajtos pizza',2700)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('32 cm 4 sajtos pizza',2110)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('28 cm 4 sajtos pizza',1600)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('48 cm Magyaros pizza',3100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('32 cm Magyaros pizza',2560)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('28 cm Magyaros pizza',2100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item(item_name, price) VALUES ('48 cm Gyros pizza',2700)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('32 cm Gyros pizza',2110)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('28 cm Gyros pizza',1600)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('48 cm Songoku pizza',3100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('32 cm Songoku pizza',2560)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('28 cm Songoku pizza',2100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('48 cm Húsimádó pizza',3100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('32 cm Húsimádó pizza',2560)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('28 cm Húsimádó pizza',2100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('48 cm Ínyenc pizza',3100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('32 cm Ínyenc pizza',2560)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('28 cm Ínyenc pizza',2100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

--Items upload, hamburgers

INSERT  into item (item_name, price) VALUES ('Nagy Csirke burger',2700)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Közepes Csirke burger',2100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Kicsi Csirke burger',1600)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Nagy Marha burger',3100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Közepes Marha burger',2560)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Kicsi Marha burger',1990)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Nagy Gourmet burger',2480)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Közepes Gourmet burger',2100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Kicsi Gourmet burger',1890)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Nagy Sima burger',2270)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Közepes Sima burger',1990)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Kicsi Sima burger',1650)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Nagy Fitnessburger',1590)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Közepes Fitnessburger',1290)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Kicsi Fitnessburger',990)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);


--Items upload, alcohol

INSERT  into item (item_name, price) VALUES ('Heineken Sör 0.5 liter',340)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Heineken Sör 0.3 liter',290)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Frittmann Rose bor 1 liter',2300)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Gere Rose bor 0.5 liter',1780)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Kalumba GIN 0.5 liter',6500)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Kalumba GIN white 1 liter',8700)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Jack Daniels Whiskey 1 liter',10100)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Jack Daniels Whiskey 0.7 liter almás',8900)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Jagermaister 1 liter',7800)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Jagermaister 0.5 liter Manifest',5600)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);

--Items upload, alcohol-free

INSERT  into item (item_name, price) VALUES ('Fanta 2.5 liter',670)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Fanta 0.5 liter',340)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Coca Cola 2.5 liter',690)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Coca Cola 0.5 liter',360)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Szénsavmentes ásványvíz(ízesített) 2.5 liter',320)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name), price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Szénsavmentes ásványvíz 0.5 liter',190)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Márka limonádé 2.5 liter',780)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Márka limonádé 0.5 liter',410)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);

INSERT  into item (item_name, price) VALUES ('Szénsavas ásványvíz(ízesített) 2.5 liter',320)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);
INSERT  into item (item_name, price) VALUES ('Szénsavas ásványvíz 0.5 liter',190)
ON DUPLICATE KEY UPDATE item_name = VALUES (item_name) , price = VALUES(price);

--Pizza upload

INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('48 cm Gyros pizza',2700,'Tzatziki alap, Gyros hús, Sajt, Lila hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('32 cm Gyros pizza',2290,'Tzatziki alap, Gyros hús, Sajt, Lila hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('28 cm Gyros pizza',1800,'Tzatziki alap, Gyros hús, Sajt, Lila hagyma', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);

INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('48 cm Magyaros pizza',2560,'Paradicsomos alap, sonka, gomba kukorica, sajt, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('32 cm Magyaros pizza', 2190,'Paradicsomos alap, sonka, gomba kukorica, sajt, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('28 cm Magyaros pizza',1670,'Paradicsomos alap, sonka, gomba kukorica', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);

INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('48 cm 4 sajtos pizza',2590,'négy fajta sajt, paradicsomos alap, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('32 cm 4 sajtos pizza',2110,'négy fajta sajt, paradicsomos alap, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('28 cm 4 sajtos pizza',1600,'négy fajta sajt, paradicsomos alap', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);

INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('48 cm Húsimádó pizza',3100,'Paradicsomos alap, sajt, sonka, kolbász, bacon, szalámi, sajt, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('32 cm Húsimádó pizza', 2560,'Paradicsomos alap, sajt, sonka, kolbász, bacon, szalámi, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('28 cm Húsimádó pizza',2100,'Paradicsomos alap, sajt, sonka, kolbász, bacon, szalámi', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);

INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('48 cm Ínyenc pizza',3100,'Tejfölös alap, sajt, kolbász, bacon, gomba, csemege ubi, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('32 cm Ínyenc pizza', 2560,'Tejfölös alap, sajt, kolbász, bacon, gomba, csemege ubi, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);
INSERT  into pizza (pizza_name, pizza_price, toppings, image) VALUES ('28 cm Ínyenc pizza',2100,'Tejfölös alap, sajt, kolbász, bacon, gomba, csemege ubi', "")
ON DUPLICATE KEY UPDATE pizza_name = VALUES (pizza_name), pizza_price = VALUES(pizza_price), toppings = VALUES(toppings), image = VALUES(image);

--Hamburger upload
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Nagy Csirke burger' ,2700,'csirke hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Közepes Csirke burger',2100,'csirke hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Kicsi Csirke burger',1600,'csirke hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);

INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Nagy Marha burger',3100,'marha hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Közepes Marha burger',2560,'marha hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Kicsi Marha burger',1990,'marha hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);

INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Nagy Gourmet burger' ,2480,'csirke hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Közepes Gourmet burger',2100,'csirke hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Kicsi Gourmet burger',1890,'csirke hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);

INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Nagy Sima burger',2270,'marha hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Közepes Sima burger',1990,'marha hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Kicsi Sima burger',1650,'marha hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);

INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Nagy Fitnessburger',1590,'csike hús, tartár, saláta, sajt, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Közepes Fitnessburger',1290,'csike hús, tartár, saláta, sajt, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);
INSERT  into hamburger (hamburger_name, hamburger_price, hamburger_content, image) VALUES ('Kicsi Fitnessburger',990,'csike hús, tartár, saláta, sajt, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_name = VALUES (hamburger_name), hamburger_price = VALUES(hamburger_price), hamburger_content = VALUES(hamburger_content), image = VALUES(image);

--Alcohol upload
INSERT INTO alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Heineken Sör 0.5 liter', 340, '4.5%', "")
    ON DUPLICATE KEY UPDATE alcohol_name = VALUES(alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);
INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Heineken Sör 0.3 liter',290,'zero', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);

INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Frittmann Rose bor 1 liter',2300,'12%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);
INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Gere Rose bor 0.5 liter',1780,'10%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);

INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Kalumba GIN 0.5 liter' ,6500,'30%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);
INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Kalumba GIN white 1 liter',8700,'35%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);

INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Jack Daniels Whiskey 1 liter',10100,'45%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);
INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Jack Daniels Whiskey 0.7 liter almás',8900,'40%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);

INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Jagermaister 1 liter',7800,'40%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);
INSERT  into alcohol (alcohol_name, alcohol_price, alcohol_percentage, image) VALUES ('Jagermaister 0.5 liter Manifest',5600,'35%', "")
ON DUPLICATE KEY UPDATE alcohol_name = VALUES (alcohol_name), alcohol_price = VALUES(alcohol_price), alcohol_percentage = VALUES(alcohol_percentage), image = VALUES(image);

--Alcohol-free uploade
INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Fanta 2.5 liter',670,'20%', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);
INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Fanta 0.5 liter',340,'zero', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name),  alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);

INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Coca Cola 2.5 liter',690,'25%', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);
INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Coca Cola 0.5 liter',360,'zero', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);

INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Szénsavmentes ásványvíz(ízesített) 2.5 liter',320,'10%', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);
INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Szénsavmentes ásványvíz 0.5 liter',190,'zero', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name),  alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);

INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Márka limonádé 2.5 liter',780,'40%', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);
INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Márka limonádé 0.5 liter',410,'zero', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);

INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Szénsavas ásványvíz(ízesített) 2.5 liter',320,'10%', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);
INSERT  into alcohol_free (alcohol_free_name, alcohol_free_price, sugar_content, image) VALUES ('Szénsavas ásványvíz 0.5 liter',190,'zero', "")
ON DUPLICATE KEY UPDATE alcohol_free_name = VALUES (alcohol_free_name), alcohol_free_price = VALUES(alcohol_free_price), sugar_content = VALUES(sugar_content), image = VALUES(image);

--Admin

INSERT  into users (users_email, user_name, address, phone, password) VALUES ('admin', 'Admin', 'SZTE Irinyi épület', '+36 30 000 0000','admin')
ON DUPLICATE KEY UPDATE users_email = VALUES (users_email), user_name = VALUES(user_name), address = VALUES(address), phone = VALUES(phone), password = VALUES(password);
