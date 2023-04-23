# Pizzazo
egyetemi projekt

## Felhasználókezelés
A felhasználó regisztrálhat ha még nincs fiókja és a kötelező adatok megadása után már bejelentkezhet, ahol áttekintheti a saját adatait, módosíthatja azokat, illetve megtekintheti a korábban leadott rendeléseit is.

## Admin
Van egy admin felhasználó (email: admin | jelszó: admin) aki az összes regisztrált felhasználót és azok adatait megtekintheti, vissza állíthatja a jelszavaikat alapértelmezettre illetve töröltheti is őket a rendszerből. Lehetősége van statisztikai adatokat megtekinteni arról, hogy a melyik volt az eddigi legnagyobb leadott rendelés, melyik a legtöbbet és legkevesebbet rendelt termék.
Ez a felhasználó szerkesztheti is a bolt termékeit oly módon, hogy hozzáadhat új termékeket, a meglévőket törölheti és szerkesztheti is, például új nevet, árat, leírást és képet adhat nekik

## A projektről
Ebben a spring alkalmazásban lehetőség van áttekinteni a pizzázó termékeit illetve ha bejelelntkezett a felhasználó, akkor kosárba tehet bármennyi terméket majd ezt a kosarat bármikor módosíthatja (mennyiség növelése, csökkentése, termék törlése) majd leadhatja a rendelését is.

### Egyéb tudnivalók
Az alkalmazás spring keretrendszerben készűlt java nyelven maven és thymeleaf segítségével localhost adatbázissal.
Az oldal elérése indítás után a localhost:8080-as porton érhető el.
Meg vannak benne valósítva a CRUD műveletek a megfelelő hibakezelésekkel együtt valamint a JDBC alkalmazása mellett sql lekérdezésekkel is dolgoztam a projekten belül.


