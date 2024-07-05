--Rendelhető termék feltöltése, Pizzák

INSERT  into rendelhetoTermek(nev, ar) VALUES ('48 cm 4 sajtos pizza',2700)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('32 cm 4 sajtos pizza',2110)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('28 cm 4 sajtos pizza',1600)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('48 cm Magyaros pizza',3100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('32 cm Magyaros pizza',2560)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('28 cm Magyaros pizza',2100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek(nev, ar) VALUES ('48 cm Gyros pizza',2700)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('32 cm Gyros pizza',2110)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('28 cm Gyros pizza',1600)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('48 cm Songoku pizza',3100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('32 cm Songoku pizza',2560)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('28 cm Songoku pizza',2100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('48 cm Húsimádó pizza',3100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('32 cm Húsimádó pizza',2560)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('28 cm Húsimádó pizza',2100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('48 cm Ínyenc pizza',3100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('32 cm Ínyenc pizza',2560)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('28 cm Ínyenc pizza',2100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

--Rendelhető termék feltöltése, Hamburgerek

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Nagy Csirke burger',2700)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Közepes Csirke burger',2100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kicsi Csirke burger',1600)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Nagy Marha burger',3100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Közepes Marha burger',2560)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kicsi Marha burger',1990)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Nagy Gourmet burger',2480)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Közepes Gourmet burger',2100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kicsi Gourmet burger',1890)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Nagy Sima burger',2270)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Közepes Sima burger',1990)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kicsi Sima burger',1650)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Nagy Fitnessburger',1590)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Közepes Fitnessburger',1290)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kicsi Fitnessburger',990)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);


--Rendelhető termék feltöltése, Alkoholos italok

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Heineken Sör 0.5 liter',340)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Heineken Sör 0.3 liter',290)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Frittmann Rose bor 1 liter',2300)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Gere Rose bor 0.5 liter',1780)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kalumba GIN 0.5 liter',6500)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Kalumba GIN white 1 liter',8700)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Jack Daniels Whiskey 1 liter',10100)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Jack Daniels Whiskey 0.7 liter almás',8900)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Jagermaister 1 liter',7800)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Jagermaister 0.5 liter Manifest',5600)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);

--Rendelhető termék feltöltése, Alkoholmentes italok

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Fanta 2.5 liter',670)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Fanta 0.5 liter',340)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Coca Cola 2.5 liter',690)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Coca Cola 0.5 liter',360)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Szénsavmentes ásványvíz(ízesített) 2.5 liter',320)
ON DUPLICATE KEY UPDATE nev = VALUES (nev), ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Szénsavmentes ásványvíz 0.5 liter',190)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Márka limonádé 2.5 liter',780)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Márka limonádé 0.5 liter',410)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);

INSERT  into rendelhetoTermek (nev, ar) VALUES ('Szénsavas ásványvíz(ízesített) 2.5 liter',320)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);
INSERT  into rendelhetoTermek (nev, ar) VALUES ('Szénsavas ásványvíz 0.5 liter',190)
ON DUPLICATE KEY UPDATE nev = VALUES (nev) , ar = VALUES(ar);

--Pizza feltöltése

INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('48 cm Gyros pizza',2700,'Tzatziki alap, Gyros hús, Sajt, Lila hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('32 cm Gyros pizza',2290,'Tzatziki alap, Gyros hús, Sajt, Lila hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('28 cm Gyros pizza',1800,'Tzatziki alap, Gyros hús, Sajt, Lila hagyma', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);

INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('48 cm Magyaros pizza',2560,'Paradicsomos alap, sonka, gomba kukorica, sajt, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('32 cm Magyaros pizza', 2190,'Paradicsomos alap, sonka, gomba kukorica, sajt, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('28 cm Magyaros pizza',1670,'Paradicsomos alap, sonka, gomba kukorica', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);

INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('48 cm 4 sajtos pizza',2590,'négy fajta sajt, paradicsomos alap, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('32 cm 4 sajtos pizza',2110,'négy fajta sajt, paradicsomos alap, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('28 cm 4 sajtos pizza',1600,'négy fajta sajt, paradicsomos alap', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);

INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('48 cm Húsimádó pizza',3100,'Paradicsomos alap, sajt, sonka, kolbász, bacon, szalámi, sajt, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('32 cm Húsimádó pizza', 2560,'Paradicsomos alap, sajt, sonka, kolbász, bacon, szalámi, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('28 cm Húsimádó pizza',2100,'Paradicsomos alap, sajt, sonka, kolbász, bacon, szalámi', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);

INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('48 cm Ínyenc pizza',3100,'Tejfölös alap, sajt, kolbász, bacon, gomba, csemege ubi, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('32 cm Ínyenc pizza', 2560,'Tejfölös alap, sajt, kolbász, bacon, gomba, csemege ubi, +bacon', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);
INSERT  into pizza (pizza_neve, pizza_ara, feltet, kep) VALUES ('28 cm Ínyenc pizza',2100,'Tejfölös alap, sajt, kolbász, bacon, gomba, csemege ubi', "")
ON DUPLICATE KEY UPDATE pizza_neve = VALUES (pizza_neve), pizza_ara = VALUES(pizza_ara), feltet = VALUES(feltet), kep = VALUES(kep);

--Hamburger feltöltése
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Nagy Csirke burger' ,2700,'csirke hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Közepes Csirke burger',2100,'csirke hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Kicsi Csirke burger',1600,'csirke hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);

INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Nagy Marha burger',3100,'marha hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Közepes Marha burger',2560,'marha hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Kicsi Marha burger',1990,'marha hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);

INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Nagy Gourmet burger' ,2480,'csirke hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Közepes Gourmet burger',2100,'csirke hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Kicsi Gourmet burger',1890,'csirke hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);

INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Nagy Sima burger',2270,'marha hús, majonéz, saláta, hagyma, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Közepes Sima burger',1990,'marha hús, majonéz, saláta, hagyma, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Kicsi Sima burger',1650,'marha hús, majonéz, saláta, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);

INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Nagy Fitnessburger',1590,'csike hús, tartár, saláta, sajt, +bacon és extra sajt', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Közepes Fitnessburger',1290,'csike hús, tartár, saláta, sajt, +bacon', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);
INSERT  into hamburger (hamburger_neve, hamburger_ara, tartalom, kep) VALUES ('Kicsi Fitnessburger',990,'csike hús, tartár, saláta, sajt, hagyma', "")
ON DUPLICATE KEY UPDATE hamburger_neve = VALUES (hamburger_neve), hamburger_ara = VALUES(hamburger_ara), tartalom = VALUES(tartalom), kep = VALUES(kep);

--Alkoholos ital feltöltése
INSERT INTO alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Heineken Sör 0.5 liter', 340, '4.5%', "")
    ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES(alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);
INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Heineken Sör 0.3 liter',290,'zero', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);

INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Frittmann Rose bor 1 liter',2300,'12%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);
INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Gere Rose bor 0.5 liter',1780,'10%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);

INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Kalumba GIN 0.5 liter' ,6500,'30%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);
INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Kalumba GIN white 1 liter',8700,'35%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);

INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Jack Daniels Whiskey 1 liter',10100,'45%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);
INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Jack Daniels Whiskey 0.7 liter almás',8900,'40%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);

INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Jagermaister 1 liter',7800,'40%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);
INSERT  into alkoholosItal (alkoholos_ital_neve, alkoholos_ital_ara, alkohol_szazalek, kep) VALUES ('Jagermaister 0.5 liter Manifest',5600,'35%', "")
ON DUPLICATE KEY UPDATE alkoholos_ital_neve = VALUES (alkoholos_ital_neve), alkoholos_ital_ara = VALUES(alkoholos_ital_ara), alkohol_szazalek = VALUES(alkohol_szazalek), kep = VALUES(kep);

--Alkoholmentes ital feltöltése
INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Fanta 2.5 liter',670,'20%', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);
INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Fanta 0.5 liter',340,'zero', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve),  alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);

INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Coca Cola 2.5 liter',690,'25%', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);
INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Coca Cola 0.5 liter',360,'zero', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);

INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Szénsavmentes ásványvíz(ízesített) 2.5 liter',320,'10%', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);
INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Szénsavmentes ásványvíz 0.5 liter',190,'zero', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve),  alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);

INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Márka limonádé 2.5 liter',780,'40%', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);
INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Márka limonádé 0.5 liter',410,'zero', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);

INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Szénsavas ásványvíz(ízesített) 2.5 liter',320,'10%', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);
INSERT  into alkoholmentesItal (alkoholmentes_ital_neve, alkoholmentes_ital_ara, cukortartalom, kep) VALUES ('Szénsavas ásványvíz 0.5 liter',190,'zero', "")
ON DUPLICATE KEY UPDATE alkoholmentes_ital_neve = VALUES (alkoholmentes_ital_neve), alkoholmentes_ital_ara = VALUES(alkoholmentes_ital_ara), cukortartalom = VALUES(cukortartalom), kep = VALUES(kep);

--Admin

INSERT  into felhasznalo (email, nev, lakcim, telefonszam, jelszo) VALUES ('admin', 'Admin', 'SZTE Irinyi épület', '+36 30 000 0000','admin')
ON DUPLICATE KEY UPDATE email = VALUES (email), nev = VALUES(nev), lakcim = VALUES(lakcim), telefonszam = VALUES(telefonszam), jelszo = VALUES(jelszo);
