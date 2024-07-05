# Pizzazo

## Felhasználókezelés
A felhasználó regisztrálhat ha még nincs fiókja és a kötelező adatok megadása után már bejelentkezhet, ahol áttekintheti a saját adatait, módosíthatja azokat, illetve megtekintheti a korábban leadott rendeléseit is.

## Admin
Van egy admin felhasználó (email: admin | jelszó: admin) aki az összes regisztrált felhasználót és azok adatait megtekintheti, vissza állíthatja a jelszavaikat alapértelmezettre illetve töröltheti is őket a rendszerből és ezekről mind kap az érintett felhasználó emailes tájékoztatást is. Lehetősége van statisztikai adatokat megtekinteni arról, hogy a melyik volt az eddigi legnagyobb leadott rendelés, melyik a legtöbbet és legkevesebbet rendelt termék.
Ez a felhasználó szerkesztheti is a bolt termékeit oly módon, hogy hozzáadhat új termékeket, a meglévőket törölheti és szerkesztheti is, például új nevet, árat, leírást és képet adhat nekik

## A projektről
Ebben a spring alkalmazásban lehetőség van áttekinteni a pizzázó termékeit illetve ha bejelelntkezett a felhasználó, akkor kosárba tehet bármennyi terméket majd ezt a kosarat bármikor módosíthatja (mennyiség növelése, csökkentése, termék törlése) majd leadhatja a rendelését is.
Rendelés leadását követően a felhasználó emailben kap értesítést arról, hogy a rendelése feldolgozásra kerűlt.

### Egyéb tudnivalók
Az alkalmazás spring keretrendszerben készűlt java nyelven maven és thymeleaf segítségével MySQL adatbázissal illetve futtatható Docker környezetben(mysql image megléte szükséges hozzá) majd a docker-compose up --build parancs kiadása után a localhost:8090 url-en el is indul az alkalmazás.
Meg vannak benne valósítva a CRUD műveletek a megfelelő hibakezelésekkel együtt valamint a JDBC alkalmazása mellett sql lekérdezésekkel is dolgoztam a projekten belül.


