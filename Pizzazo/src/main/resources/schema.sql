CREATE TABLE  IF NOT EXISTS felhasznalo (
    email VARCHAR(128) NOT NULL,
     nev VARCHAR(128) NOT NULL,
      lakcim VARCHAR(128) NOT NULL,
       telefonszam VARCHAR(128) NOT NULL,
        jelszo VARCHAR(128) NOT NULL,
    PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS rendelhetoTermek (
    nev VARCHAR(200) NOT NULL,
    ar INT(10) NOT NULL,
    PRIMARY KEY (nev)
);

CREATE TABLE IF NOT EXISTS rendeles (
    rendelesID INT(10) NOT NULL AUTO_INCREMENT,
    felhasznaloEmailCime VARCHAR(128) NOT NULL ,
     rendelesIdopontja VARCHAR(128) NOT NULL,
      fizetendoOsszeg INT(5) NOT NULL,
      mennyiseg INT(5) NOT NULL,
    PRIMARY KEY (rendelesID),
    FOREIGN KEY (felhasznaloEmailCime) REFERENCES felhasznalo(email) ON DELETE CASCADE,
    FOREIGN KEY (rendelesID) REFERENCES rendelendoTermekekListaja(rendelesId) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS pizza (
    pizza_neve VARCHAR(50) NOT NULL,
      pizza_ara INT(5) NOT NULL,
     feltet VARCHAR(255) NOT NULL,
     kep VARCHAR(255) NOT NULL,
    PRIMARY KEY (pizza_neve),
    FOREIGN KEY (pizza_neve) REFERENCES rendelhetoTermek(nev) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS hamburger (
    hamburger_neve VARCHAR(50) NOT NULL,
      hamburger_ara INT(5) NOT NULL,
     tartalom VARCHAR(255) NOT NULL,
    kep VARCHAR(255) NOT NULL,
    PRIMARY KEY (hamburger_neve),
    FOREIGN KEY (hamburger_neve) REFERENCES rendelhetoTermek(nev) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alkoholosItal (
    alkoholos_ital_neve VARCHAR(50) NOT NULL,
      alkoholos_ital_ara INT(5) NOT NULL,
     alkohol_szazalek VARCHAR(4) NOT NULL,
    kep VARCHAR(255) NOT NULL,
    PRIMARY KEY (alkoholos_ital_neve),
    FOREIGN KEY (alkoholos_ital_neve) REFERENCES rendelhetoTermek(nev) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS alkoholmentesItal (
    alkoholmentes_ital_neve VARCHAR(50) NOT NULL,
      alkoholmentes_ital_ara INT(5) NOT NULL,
     cukortartalom VARCHAR(4) NOT NULL,
    kep VARCHAR(255) NOT NULL,
    PRIMARY KEY (alkoholmentes_ital_neve),
    FOREIGN KEY (alkoholmentes_ital_neve) REFERENCES rendelhetoTermek(nev) ON DELETE CASCADE
);

--termekNeve listaban cascadolva
alter table rendeles_reszeltes_listaja DROP FOREIGN KEY FKohpyu8g5ttgi45tv08wg85692;
alter table rendeles_reszeltes_listaja ADD constraint FKohpyu8g5ttgi45tv08wg85692 foreign key (termek_neve_key) references rendelhetotermek (nev) ON DELETE CASCADE;

--mezok meret novelese
ALTER TABLE `rendeles` CHANGE `fizetendo_osszeg` `fizetendo_osszeg` INT(255) NOT NULL;
ALTER TABLE `rendeles` CHANGE `rendeles_id` `rendeles_id` INT(255) NOT NULL;
ALTER TABLE `rendeles_reszeltes_listaja` CHANGE `rendelesid` `rendelesid` INT(255) NOT NULL, CHANGE `mennyiseg` `mennyiseg` INT(255) NULL DEFAULT NULL;
ALTER TABLE `rendeles_id` CHANGE `next_val` `next_val` BIGINT(255) NULL DEFAULT NULL;