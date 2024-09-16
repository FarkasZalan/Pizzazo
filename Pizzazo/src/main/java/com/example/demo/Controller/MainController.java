package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.*;
import com.example.demo.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.*;

@Controller
@Service
// the implementation of all functions is organized into classes
// where I have written a detailed explanation for each
public class MainController {
    // Repositories for various entities
    @Autowired
    private final ItemRepo itemsRepo;
    private final PizzaRepo pizzasRepo;
    private final HamburgerRepo hamburgerRepo;
    private final AlcoholRepo alcoholRepo;
    private final AlcoholFreeRepo alcoholFreeRepo;
    private final OrdersRepo ordersRepo;
    private final UsersRepo usersRepo;

    int totalAmount = 0;
    
    // Reference to the currently logged-in user
    boolean userLoggenIn = false;
    Users loggedUser;
    
    boolean alreadyHaveAccount = false;
    Map<Item, Integer> cartList = new HashMap<>();
    List<Orders> userShoppingHistory = new ArrayList<>();
    
    boolean userIsAdmin = false;
    
    String modifyPizzaName;
    String modifyHamburgerName;
    String modifyAlcoholName;
    String modifyAlcoholFreeName;
    String oldImageForItem = "";

    // Service class for Email sending
    private EmailSend emailSend;

    public MainController(ItemRepo itemsRepo, PizzaRepo pizzasRepo, HamburgerRepo hamburgerRepo, AlcoholRepo alcoholRepo, AlcoholFreeRepo alcoholFreeRepo, OrdersRepo ordersRepo, UsersRepo usersRepo) {
        this.itemsRepo = itemsRepo;
        this.pizzasRepo = pizzasRepo;
        this.hamburgerRepo = hamburgerRepo;
        this.alcoholRepo = alcoholRepo;
        this.alcoholFreeRepo = alcoholFreeRepo;
        this.ordersRepo = ordersRepo;
        this.usersRepo = usersRepo;
    }

    @RequestMapping(value = "/")
    public String mainPage(Model model) {
        model.addAttribute("navi", userLoggenIn);
        model.addAttribute("admin", userIsAdmin);
        return "Basic/MainPage";
    }

    @RequestMapping(value = "/kapcsolat")
    public String connect(Model model) {
        model.addAttribute("navi", userLoggenIn);
        model.addAttribute("admin", userIsAdmin);
        return "Basic/Connect";
    }

    @RequestMapping(value = "/regisztracio")
    public String register(Model model) {
        model.addAttribute("fooldal", usersRepo.listOfTheUsers());
        return "Accounts/Registation";
    }

    @RequestMapping(value = "/regisztracio_process")
    public String registation_process(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("address") String address,
                                      @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("password_again") String password_again,
                                      RedirectAttributes redirectAttributes) {
        String message = "";
        Create create = new Create(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        if (password.equals(password_again)) {
            Users users = new Users(email, name, address, phone, password);
            message = create.userCreate(users);

        } else {
            redirectAttributes.addFlashAttribute("Uzenet", "Nem egyeznek a megadott jelszavak!");
            return "redirect:/regisztracio";
        }
        if (message.equals("Sikeres mentés!")) {
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres regisztráció!");
            emailSend.send(email, "Köszönjük, hogy beregisztráltál hozzánk " + name + ", reméljük megtalálod számodra a legmeggyőzőbb ételt!", "Sikeres regisztráció");
            return "redirect:/bejelentkezes";
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", message);
            return "redirect:/regisztracio";
        }
    }

    @RequestMapping(value = "/bejelentkezes")
    public String login(Model model) {
        model.addAttribute("fooldal", usersRepo.listOfTheUsers());

        return "Accounts/Login";
    }

    @RequestMapping(value = "/bejelentkezes_process")
    public String login_process(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        Login login = new Login(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        String message = login.userLogin(email, password);
        userIsAdmin = email.equals("admin");

        if (message.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs ilyen emaillel regisztált felhasználó!");
            return "redirect:/bejelentkezes";
        }
        if (message.equals("Van email")) {
            redirectAttributes.addFlashAttribute("Uzenet", "Rossz jelszó!");
            return "redirect:/bejelentkezes";
        }
        if (message.equals("Sikeres belépés")) {
            userLoggenIn = true;
            loggedUser = login.findUser(email);
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres belépés! Üdv újra itt, " + loggedUser.getName());
            if (userIsAdmin) {
                return "redirect:/admin";
            }
            return "redirect:/fiok";
        }
        return "Accounts/Login";
    }

    @RequestMapping(value = "/kilepes")
    public String logOut(RedirectAttributes redirectAttributes) {
        loggedUser = new Users("", "", "", "", "");
        alreadyHaveAccount = false;
        userIsAdmin = false;
        userLoggenIn = false;
        cartList.clear();
        totalAmount = 0;

        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres kilépés!");
        return "redirect:/bejelentkezes";
    }

    @RequestMapping(value = "/fiok")
    public String account(Model model) {
        model.addAttribute("adatok", loggedUser);
        model.addAttribute("admin", userIsAdmin);
        if (userLoggenIn) {
            return "Accounts/UserAccount";
        } else {
            return "Accounts/Login";
        }
    }

    @RequestMapping(value = "/felhasznaloModositasa")
    public String userModification(Model model) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        model.addAttribute("felhasznalo", loggedUser);
        model.addAttribute("fooldal", usersRepo.listOfTheUsers());
        return "Accounts/AccountModification";
    }

    @RequestMapping(value = "/adatok_modositasa")
    public String dataChange(@RequestParam("name") String name, @RequestParam("address") String address,
                             @RequestParam("phone") String phone, @RequestParam("password") String password, @RequestParam("password_again") String password_again,
                             RedirectAttributes redirectAttributes) {
        Modify modify = new Modify(usersRepo, pizzasRepo, itemsRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo);
        if (password.equals(password_again)) {
            loggedUser = modify.userModify(loggedUser, loggedUser.getId(), name, address, phone, password);
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres mentés!");
            emailSend.send(loggedUser.getId(), loggedUser.getName() + ", a profil adataid az alábbiak szerint módosultak:\nNév: " + loggedUser.getName() + "\nLakcím: " + loggedUser.getAddress() + "\nTelefonszám: " + loggedUser.getPhone() + "\nJelszó: " + loggedUser.getPassword(), "Profil módosult");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", "Nem egyeznek a megadott jelszavak!");
        }
        return "redirect:/felhasznaloModositasa";
    }

    @RequestMapping(value = "/rendelesekListaja")
    public String orderHistory(Model model, RedirectAttributes redirectAttributes) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        ShoppingHistory shoppingHistory = new ShoppingHistory(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        userShoppingHistory = shoppingHistory.shoppingHistory(userShoppingHistory, loggedUser.getId());
        if (userShoppingHistory.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs egyetlen listázható rendelés sem!");
            return "redirect:/fiok";
        }
        // this model help display the orders
        List<OrderModel> orderModels = new ArrayList<>();
        // go through the user shopping history by element and using StringBuilder
        // to handle the order elements
        for (Orders orderElement : userShoppingHistory) {
            StringBuilder itemName = new StringBuilder();
            StringBuilder itemUnitPrice = new StringBuilder();
            StringBuilder itemQuantity = new StringBuilder();
            for (Item item : orderElement.getItemName().keySet()) {
                if (orderElement.getUserEmail().getId().equals(loggedUser.getId())) {
                    itemName.append(item.getItemName()).append("<br/>");
                    itemUnitPrice.append(item.getPrice()).append("<br/>");
                }
            }

            for (Integer quantity : orderElement.getItemName().values()) {
                if (orderElement.getUserEmail().getId().equals(loggedUser.getId())) {
                    itemQuantity.append(quantity.toString()).append("<br/>");
                }
            }
            if (!itemName.toString().isEmpty()) {
                OrderModel item = new OrderModel(orderElement.getId().toString(), orderElement.getOrderDate(), itemName.toString(), itemQuantity.toString(), itemUnitPrice.toString(), orderElement.getTotalAmount().toString());
                orderModels.add(item);
            }
        }
        if (orderModels.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs egyetlen listázható rendelés sem!");
            return "redirect:/fiok";
        }
        model.addAttribute("rendelesek", orderModels);
        return "Accounts/OrderHistory";
    }

    @RequestMapping(value = "/termekek")
    public String items(Model model) {
        model.addAttribute("termekek", itemsRepo.listOfItems());
        model.addAttribute("pizzak", pizzasRepo.listOfPizzas());
        model.addAttribute("hamburgerek", hamburgerRepo.listOfHamburgers());
        model.addAttribute("alkoholosak", alcoholRepo.listOfAlcohol());
        model.addAttribute("alkoholmentesek", alcoholFreeRepo.listOfAlcoholFree());
        model.addAttribute("navi", userLoggenIn);
        model.addAttribute("admin", userIsAdmin);
        return "Basic/Products";
    }

    @RequestMapping(value = "/pizzat_rendel")
    public String orderPizza(@RequestParam("pizzaNeve") String pizzaneve, RedirectAttributes redirectAttributes) {
        return getString(pizzaneve, redirectAttributes);
    }

    private String getString(@RequestParam("pizzaNeve") String pizzaneve, RedirectAttributes redirectAttributes) {
        Create create = new Create(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        if (userLoggenIn) {
            cartList = create.addElementToCart(cartList, pizzaneve);
            totalAmount += create.getHaveToPayUntilNow();
            redirectAttributes.addFlashAttribute("Uzenet", pizzaneve + " sikeresen hozzáadva a kosárhoz");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
        }
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/hamburgert_rendel")
    public String order_hamburger(@RequestParam("hamburgerNeve") String hamburgerNeve, RedirectAttributes redirectAttributes) {
        return getString(hamburgerNeve, redirectAttributes);
    }

    @RequestMapping(value = "/alkoholosat_rendel")
    public String order_alcohol(@RequestParam("alkoholosItalNeve") String alkoholosItalNeve, RedirectAttributes redirectAttributes) {
        return getString(alkoholosItalNeve, redirectAttributes);
    }

    @RequestMapping(value = "/alkoholmenteset_rendel")
    public String order_alcohol_free(@RequestParam("alkoholmentesItalNeve") String alkoholmentesItalNeve, RedirectAttributes redirectAttributes) {
        return getString(alkoholmentesItalNeve, redirectAttributes);
    }

    @RequestMapping(value = "/kosar")
    public String cart(Model model, RedirectAttributes redirectAttributes) {
        if (!userLoggenIn) {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
            return "redirect:/bejelentkezes";
        }
        if (cartList.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Üres a kosarad! Adj hozzá új termékeket most!");
            return "redirect:/termekek";
        }
        model.addAttribute("bevasarloLista", cartList);
        model.addAttribute("vegosszeg", totalAmount);
        model.addAttribute("navi", userLoggenIn);
        model.addAttribute("admin", userIsAdmin);
        return "OrderManagement/Cart";
    }

    @RequestMapping(value = "/kosar_csokkentese")
    public String cart_decrase(@RequestParam("torlendoNeve") String torlendoNeve, RedirectAttributes redirectAttributes) {
        Create create = new Create(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        Delete delete = new Delete(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        if (userLoggenIn) {
            totalAmount = delete.itemDelete(torlendoNeve, cartList, totalAmount);
            return "redirect:/kosar";
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
            return "redirect:/termekek";
        }
    }

    @RequestMapping(value = "/kosar_novelese")
    public String cart_incrase(@RequestParam("hozzaAdandoNeve") String hozzaAdandoNeve, RedirectAttributes redirectAttributes) {
        Create create = new Create(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        Delete delete = new Delete(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        if (userLoggenIn) {
            cartList = create.addElementToCart(cartList, hozzaAdandoNeve);
            totalAmount += create.getHaveToPayUntilNow();
            return "redirect:/kosar";
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
            return "redirect:/termekek";
        }
    }

    @RequestMapping(value = "/rendeles")
    public String order(Model model, RedirectAttributes redirectAttributes) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        if (cartList.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Üres a kosarad! Adj hozzá új termékeket most!");
            return "redirect:/termekek";
        }
        model.addAttribute("adatok", loggedUser);
        model.addAttribute("bevasarloLista", cartList);
        model.addAttribute("vegosszeg", totalAmount);
        model.addAttribute("navi", userLoggenIn);
        model.addAttribute("admin", userIsAdmin);
        return "OrderManagement/Order";
    }

    @RequestMapping(value = "/nyugta")
    public String recipe(Model model) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        StringBuilder orderItems = new StringBuilder();
        Create create = new Create(itemsRepo, pizzasRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo, ordersRepo, usersRepo);
        for (Map.Entry<Item, Integer> cartElement : cartList.entrySet()) {
            orderItems.append(cartElement.getKey().getItemName()).append(" ").append(cartElement.getValue().toString()).append(" db\n");
        }
        orderItems.append("\n\nVégösszeg: ").append(totalAmount).append("\nFizetésmód: Fizetés a futárnál kártyával");
        String userEmail = "";
        if(userIsAdmin) {
            userEmail = "admin@admin.com";
        } else {
            userEmail = loggedUser.getId();
        }
        emailSend.send(userEmail, "Köszönjük, hogy tőlünk rendeltél, " + loggedUser.getName() + "!\n\nRendelésed részletei:\n\nRendelő adatai:\nRendelő neve: " + loggedUser.getName() + "\nKiszállítás címe: " + loggedUser.getAddress() + "\nRendelő elérhetősége: " + loggedUser.getPhone() + "\n\n" + orderItems, "Rendelés leadva");
        int azonosito = create.orderCreate(cartList, loggedUser, totalAmount);
        String idopont = create.getOrderDate();
        StringBuilder termekek = new StringBuilder();
        StringBuilder quantity = new StringBuilder();
        for (Item termek : cartList.keySet()) {
            termekek.append(termek.getItemName()).append("<br/>");
        }
        for (Integer termek : cartList.values()) {
            quantity.append(termek.toString()).append("<br/>");
        }
        model.addAttribute("adatok", loggedUser);
        model.addAttribute("rendelesAzonosito", azonosito);
        model.addAttribute("rendelesIdopontja", idopont);
        model.addAttribute("bevasarloLista", termekek);
        model.addAttribute("quantity", quantity);
        model.addAttribute("vegosszeg", totalAmount);
        model.addAttribute("navi", userLoggenIn);
        model.addAttribute("admin", userIsAdmin);
        cartList.clear();
        totalAmount = 0;
        return "OrderManagement/Recipe";
    }

    @RequestMapping(value = "/uj_rendeles")
    public String newOrder() {
        cartList.clear();
        totalAmount = 0;
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/admin")
    public String adminAccount(Model model) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        model.addAttribute("adatok", loggedUser);
        model.addAttribute("admin", userIsAdmin);
        return "AdminAccount/AdminPage";
    }

    @RequestMapping(value = "/statisztikak")
    public String statistics(Model model, RedirectAttributes redirectAttributes) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }

        String mostPaid = ordersRepo.mostPaid();

        if (!Objects.equals(mostPaid, "null,null,null,null")) {
            String[] legtobbetfizetettdarabok = mostPaid.split(",");
            String legtobbNev = legtobbetfizetettdarabok[0];
            String legtobbFizetettOsszeg = legtobbetfizetettdarabok[1];
            String legtobbFizetettId = legtobbetfizetettdarabok[2];
            String legtobbFizetettIdopont = legtobbetfizetettdarabok[3];

            model.addAttribute("legtobbNev", legtobbNev);
            model.addAttribute("legtobbFizetettOsszeg", legtobbFizetettOsszeg);
            model.addAttribute("legtobbFizetettId", legtobbFizetettId);
            model.addAttribute("legtobbFizetettIdopont", legtobbFizetettIdopont);
        }

        String legkelendobbTermek = ordersRepo.mostOrderedItem();
        if (legkelendobbTermek != null) {
            String[] legkelendobbDarab = legkelendobbTermek.split(",");
            String legtobbTermekNeve = legkelendobbDarab[0];
            String legtobbOsszMennyisege = legkelendobbDarab[1];
            String legtobbUtolsoRendelesIdopontja = legkelendobbDarab[2];

            model.addAttribute("legtobbTermekNeve", legtobbTermekNeve);
            model.addAttribute("legtobbOsszMennyisege", legtobbOsszMennyisege);
            model.addAttribute("legtobbUtolsoRendelesIdopontja", legtobbUtolsoRendelesIdopontja);
        }

        String kevesbeKelendoTermek = ordersRepo.leastOrderedItem();
        if (kevesbeKelendoTermek != null) {
            String[] kevesbeKelendobbDarab = kevesbeKelendoTermek.split(",");
            String kevesbeKelendoTermekNeve = kevesbeKelendobbDarab[0];
            String kevesbeKelendoOsszMennyisege = kevesbeKelendobbDarab[1];
            String kevesbeKelendoUtolsoRendelesIdopontja = kevesbeKelendobbDarab[2];

            model.addAttribute("kevesbeKelendoTermekNeve", kevesbeKelendoTermekNeve);
            model.addAttribute("kevesbeKelendoOsszMennyisege", kevesbeKelendoOsszMennyisege);
            model.addAttribute("kevesbeKelendoUtolsoRendelesIdopontja", kevesbeKelendoUtolsoRendelesIdopontja);
        }
        if (mostPaid.equals("null,null,null,null") && legkelendobbTermek == null && kevesbeKelendoTermek == null) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs egyetlen listázható statisztika sem!");
            return "redirect:/admin";
        }
        return "AdminFiok/Statisztikak";
    }

    @RequestMapping(value = "/felhasznalot_kezeles")
    public String user_management(Model model) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        List<Users> felhasznalokAdminNelkul = new ArrayList<>();
        for (Users users : usersRepo.listOfTheUsers()) {
            if (!users.getId().equals("admin")) {
                felhasznalokAdminNelkul.add(users);
            }
        }
        model.addAttribute("felhasznalok", felhasznalokAdminNelkul);
        model.addAttribute("admin", userIsAdmin);
        return "AdminAccount/UserManagement";
    }

    @RequestMapping(value = "/felhasznalo_torlese")
    public String delete_user(@RequestParam("torlendoNeve") String torlendoNeve, RedirectAttributes redirectAttributes) {
        usersRepo.deleteById(torlendoNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres törlés!");
        emailSend.send(torlendoNeve, "Sajnálattak értesítűnk, hogy törölték fiókodat a rendszerünkből", "Fiók törölve");
        return "redirect:/felhasznalot_kezeles";
    }

    @RequestMapping(value = "/jelszo_alaphelyzet")
    public String password_set_to_default(@RequestParam("torlendoNeve") String modositando, RedirectAttributes redirectAttributes) {

        for (Users users : usersRepo.listOfTheUsers()) {
            if (users.getId().equals(modositando)) {
                users.setPassword("1234");
                usersRepo.save(users);
            }
        }
        redirectAttributes.addFlashAttribute("Uzenet", modositando + " jelszava sikeresen megváltozott a következőre: 1234");
        emailSend.send(modositando, "Értesítjük, hogy jelszavát alaphelyzetbe állították ami a következő: 1234", "Jelszó alaphelyzetbe kerűlt");
        return "redirect:/felhasznalot_kezeles";
    }

    @RequestMapping(value = "/termekek_kezelese")
    public String product_management(Model model) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        model.addAttribute("termekek", itemsRepo.listOfItems());
        model.addAttribute("pizzak", pizzasRepo.listOfPizzas());
        model.addAttribute("hamburgerek", hamburgerRepo.listOfHamburgers());
        model.addAttribute("alkoholosak", alcoholRepo.listOfAlcohol());
        model.addAttribute("alkoholmentesek", alcoholFreeRepo.listOfAlcoholFree());
        model.addAttribute("admin", userIsAdmin);
        return "AdminAccount/ItemManagement";
    }

    @RequestMapping(value = "/termek_torlese")
    public String delete_item(@RequestParam("torlendoNeve") String torlendoNeve, RedirectAttributes redirectAttributes) {
        itemsRepo.deleteById(torlendoNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres törlés!");
        return "redirect:/termekek_kezelese";
    }

    @RequestMapping(value = "/termekHozzaad")
    public String add_item(Model model) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        return "AdminAccount/AddProduct";
    }

    @RequestMapping(value = "/termek_hozzaad")
    public String add_item_process(@RequestParam("name") String name, @RequestParam("ar") int ar, @RequestParam("kiegeszito") String kiegeszito,
                                   @RequestParam(value = "kategoria", required = false, defaultValue = "") String kategoria,
                                   @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {

        assert img != null;
        double kepMerete = img.getSize();
        // handle the image sources of error
        if (kepMerete > 800000) {
            redirectAttributes.addFlashAttribute("Uzenet", "Túl nagy méretű képet próbálsz meg feltölteni, a max méret 0,8Mb! Az általad feltöltött kép mérete: " + kepMerete / 1000000 + "Mb");
            return "redirect:/termekHozzaad";
        }
        String kepNeve = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
        String kep = "";
        if (kepNeve.contains("..")) {
            redirectAttributes.addFlashAttribute("Uzenet", "Érvénytelen képformátum!");
            return "redirect:/termekHozzaad";
        }
        // handle basic sources of error
        for (Item item : itemsRepo.listOfItems()) {
            if (item.getItemName().equals(name)) {
                redirectAttributes.addFlashAttribute("Uzenet", "Már van egy " + name + " nevű termék a listában!");
                return "redirect:/termekHozzaad";
            }
        }

        if (kategoria.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Jelölj be egy kategóriát!");
            return "redirect:/termekHozzaad";
        }
        if (kategoria.equals("alkoholos") && kiegeszito.length() > 4) {
            redirectAttributes.addFlashAttribute("Uzenet", "Az alkoholszázalékot a következő formátumban add meg pl. 100%");
            return "redirect:/termekHozzaad";
        }

        if (kategoria.equals("alkoholmentes") && kiegeszito.length() > 4) {
            redirectAttributes.addFlashAttribute("Uzenet", "A cukortartalmat a következő formátumban add meg pl. 100% vagy zero");
            return "redirect:/termekHozzaad";
        }
        // if not found error
        try {
            // convert the given image
            kep = Base64.getEncoder().encodeToString(img.getBytes());
            switch (kategoria) {
                // create the item
                case "pizza" -> {

                    Item pizzaTermek = new Item(name, ar);
                    Pizza pizza = new Pizza(name, pizzaTermek, ar, kiegeszito, "");
                    redirectAttributes.addFlashAttribute("Uzenet", name + " sikeresen hozzáadva a termékek közé!");
                    itemsRepo.save(pizzaTermek);
                    pizzasRepo.save(pizza);
                    pizzasRepo.itemImage(kep, pizza.getName());
                }
                case "hamburger" -> {
                    Item hamburgerTermek = new Item(name, ar);
                    Hamburger hamburger = new Hamburger(name, hamburgerTermek, ar, kiegeszito, "");
                    redirectAttributes.addFlashAttribute("Uzenet", name + " sikeresen hozzáadva a termékek közé!");
                    itemsRepo.save(hamburgerTermek);
                    hamburgerRepo.save(hamburger);
                    hamburgerRepo.itemImage(kep, hamburger.getName());
                }
                case "alkoholos" -> {
                    Item alkoholosTermek = new Item(name, ar);
                    Alcohol alkoholos = new Alcohol(name, alkoholosTermek, ar, kiegeszito, "");
                    redirectAttributes.addFlashAttribute("Uzenet", name + " sikeresen hozzáadva a termékek közé!");
                    itemsRepo.save(alkoholosTermek);
                    alcoholRepo.save(alkoholos);
                    alcoholRepo.itemImage(kep, alkoholos.getName());
                }
                case "alkoholmentes" -> {
                    Item alkoholmentesTermek = new Item(name, ar);
                    AlcoholFree alkoholmentes = new AlcoholFree(name, alkoholmentesTermek, ar, kiegeszito, "");
                    itemsRepo.save(alkoholmentesTermek);
                    alcoholFreeRepo.save(alkoholmentes);
                    alcoholRepo.itemImage(kep, alkoholmentes.getName());
                    redirectAttributes.addFlashAttribute("Uzenet", name + " sikeresen hozzáadva a termékek közé!");
                }
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("Uzenet", "Váratlan hiba történt, kérjük próbáld újra!");
            return "redirect:/termekHozzaad";
        }
        return "redirect:/termekHozzaad";
    }

    @RequestMapping(value = "/pizza_adat_modositas")
    public String pizza_modification(Model model, @RequestParam(value = "modositandoPizzaNeve", required = false) String name) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        if (name != null) {
            modifyPizzaName = name;
        }
        Pizza modositando = pizzasRepo.findById(modifyPizzaName).get();
        model.addAttribute("pizza", modositando);
        return "Modification/PizzaModification";
    }

    @RequestMapping(value = "/hamburger_adat_modositas")
    public String hamburger_modification(Model model, @RequestParam(value = "modositandoHamburgerNeve", required = false) String name) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        if (name != null) {
            modifyHamburgerName = name;
        }
        Hamburger modositando = hamburgerRepo.findById(modifyHamburgerName).get();
        model.addAttribute("hamburger", modositando);
        return "Modification/HamburgerModification";
    }

    @RequestMapping(value = "/alkoholos_adat_modositas")
    public String alcohol_modification(Model model, @RequestParam(value = "modositandoAlkoholosNeve", required = false) String name) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        if (name != null) {
            modifyAlcoholName = name;
        }
        Alcohol modositando = alcoholRepo.findById(modifyAlcoholName).get();
        model.addAttribute("alkoholos", modositando);
        return "Modification/AlcoholModification";
    }

    @RequestMapping(value = "/alkoholmentes_adat_modositas")
    public String alcohol_free_modification(Model model, @RequestParam(value = "modositandoAlkoholmentesNeve", required = false) String name) {
        if (loggedUser == null) {
            return "Accounts/Login";
        }
        if (name != null) {
            modifyAlcoholFreeName = name;
        }
        AlcoholFree modositando = alcoholFreeRepo.findById(modifyAlcoholFreeName).get();
        model.addAttribute("alkoholmentes", modositando);
        return "Modification/AlcoholFreeModification";
    }

    @RequestMapping(value = "/pizza_adat_modositas_process")
    public String pizza_modification_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                             @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modify modify = new Modify(usersRepo, pizzasRepo, itemsRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo);
        if (ar != 0 || kiegeszito != null) {
            // find the element from the item list
            Pizza modositando = pizzasRepo.findById(modifyPizzaName).get();
            modify.pizzaModify(modositando, modositando.getName(), ar, kiegeszito);

            // image error handle
            if (!modositando.getImage().isEmpty()) {
                oldImageForItem = modositando.getImage();
            }
            String newImage = "";
            if (img != null) {
                String kepNeve = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
                if (kepNeve.contains("..")) {
                    redirectAttributes.addFlashAttribute("Uzenet", "Érvénytelen képformátum!");
                    return "redirect:/pizza_adat_modositas";
                }
            }
            try {
                assert img != null;
                float kepMerete = img.getSize();
                if (kepMerete <= 800000) {
                    newImage = Base64.getEncoder().encodeToString(img.getBytes());
                    if (modositando.getToppings().equals(kiegeszito) && oldImageForItem.equals(newImage) && modositando.getPizzaPrice() == ar) {
                        redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                        return "redirect:/pizza_adat_modositas";
                    }
                    // if the item had the default image then setting the new image
                    if (newImage.isEmpty() && oldImageForItem.isEmpty()) {
                        pizzasRepo.itemImage(newImage, modositando.getName());
                    }
                    if (!newImage.isEmpty()) {
                        // if the new image variable is not empty then set this image as new
                        pizzasRepo.itemImage(newImage, modositando.getName());
                    }
                    // if the new image variable is empty then set the old image as new
                    if (!oldImageForItem.isEmpty() && oldImageForItem.equals(newImage)) {
                        pizzasRepo.itemImage(oldImageForItem, modositando.getName());
                    }
                } else {
                    redirectAttributes.addFlashAttribute("Uzenet", "Túl nagy méretű képet próbálsz meg feltölteni, a max méret 0,8Mb! Az általad feltöltött kép mérete: " + kepMerete / 1000000 + "Mb");
                    return "redirect:/pizza_adat_modositas";
                }
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("Uzenet", "Váratlan hiba történt, kérjük próbáld újra!");
                return "redirect:/pizza_adat_modositas";
            }
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres módosítás!");
            return "redirect:/pizza_adat_modositas";
        }
        return "Modification/PizzaModification";
    }

    @RequestMapping(value = "/hamburger_adat_modositas_process")
    public String hamburger_modification_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                                 @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modify modify = new Modify(usersRepo, pizzasRepo, itemsRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo);
        if (ar != 0 || kiegeszito != null) {
            Hamburger modositando = hamburgerRepo.findById(modifyHamburgerName).get();
            modify.hamburgerModify(modositando, modositando.getName(), ar, kiegeszito);
            if (!modositando.getImage().isEmpty()) {
                oldImageForItem = modositando.getImage();
            }
            String newImage = "";
            if (img != null) {
                String kepNeve = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
                if (kepNeve.contains("..")) {
                    redirectAttributes.addFlashAttribute("Uzenet", "Érvénytelen képformátum!");
                    return "redirect:/hamburger_adat_modositas";
                }
            }
            try {
                assert img != null;
                float kepMerete = img.getSize();
                if (kepMerete <= 800000) {
                    newImage = Base64.getEncoder().encodeToString(img.getBytes());
                    if (modositando.getHamburgerContent().equals(kiegeszito) && oldImageForItem.equals(newImage) && modositando.getHamburger_price() == ar) {
                        redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                        return "redirect:/hamburger_adat_modositas";
                    }
                    if (newImage.isEmpty() && oldImageForItem.isEmpty()) {
                        hamburgerRepo.itemImage(newImage, modositando.getName());
                    }
                    if (!newImage.isEmpty()) {
                        hamburgerRepo.itemImage(newImage, modositando.getName());
                    }
                    if (!oldImageForItem.isEmpty() && oldImageForItem.equals(newImage)) {
                        hamburgerRepo.itemImage(oldImageForItem, modositando.getName());
                    }
                } else {
                    redirectAttributes.addFlashAttribute("Uzenet", "Túl nagy méretű képet próbálsz meg feltölteni, a max méret 0,8Mb! Az általad feltöltött kép mérete: " + kepMerete / 1000000 + "Mb");
                    return "redirect:/hamburger_adat_modositas";
                }
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("Uzenet", "Váratlan hiba történt, kérjük próbáld újra!");
                return "redirect:/hamburger_adat_modositas";
            }
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres módosítás!");
            return "redirect:/hamburger_adat_modositas";
        }
        return "Modification/HamburgerModification";
    }

    @RequestMapping(value = "/alkoholos_adat_modositas_process")
    public String alcohol_modification_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                               @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modify modify = new Modify(usersRepo, pizzasRepo, itemsRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo);
        if (kiegeszito.length() > 4) {
            redirectAttributes.addFlashAttribute("Uzenet", "Az alkoholszázalékot a következő formátumban add meg pl. 100%");
            return "redirect:/alkoholos_adat_modositas";
        }
        Alcohol modositando = alcoholRepo.findById(modifyAlcoholName).get();
        modify.alcoholModification(modositando, modositando.getName(), ar, kiegeszito);
        if (!modositando.getImage().isEmpty()) {
            oldImageForItem = modositando.getImage();
        }
        String newImage = "";
        if (img != null) {
            String kepNeve = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
            if (kepNeve.contains("..")) {
                redirectAttributes.addFlashAttribute("Uzenet", "Érvénytelen képformátum!");
                return "redirect:/alkoholos_adat_modositas";
            }
        }
        try {
            assert img != null;
            float kepMerete = img.getSize();
            if (kepMerete <= 800000) {
                newImage = Base64.getEncoder().encodeToString(img.getBytes());
                if (modositando.getAlcoholPercentage().equals(kiegeszito) && oldImageForItem.equals(newImage) && modositando.getAlcoholPrice() == ar) {
                    redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                    return "redirect:/alkoholos_adat_modositas";
                }
                if (newImage.isEmpty() && oldImageForItem.isEmpty()) {
                    alcoholRepo.itemImage(newImage, modositando.getName());
                }
                if (!newImage.isEmpty()) {
                    alcoholRepo.itemImage(newImage, modositando.getName());
                }
                if (!oldImageForItem.isEmpty() && oldImageForItem.equals(newImage)) {
                    alcoholRepo.itemImage(oldImageForItem, modositando.getName());
                }
            } else {
                redirectAttributes.addFlashAttribute("Uzenet", "Túl nagy méretű képet próbálsz meg feltölteni, a max méret 0,8Mb! Az általad feltöltött kép mérete: " + kepMerete / 1000000 + "Mb");
                return "redirect:/alkoholos_adat_modositas";
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("Uzenet", "Váratlan hiba történt, kérjük próbáld újra!");
            return "redirect:/alkoholos_adat_modositas";
        }
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres módosítás!");
        return "redirect:/alkoholos_adat_modositas";
    }

    @RequestMapping(value = "/alkoholmentes_adat_modositas_process")
    public String alcohol_free_modification_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                                    @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modify modify = new Modify(usersRepo, pizzasRepo, itemsRepo, hamburgerRepo, alcoholRepo, alcoholFreeRepo);
        if (kiegeszito.length() > 4) {
            redirectAttributes.addFlashAttribute("Uzenet", "A cukortartalmat a következő formátumban add meg pl. 100% vagy zero");
            return "redirect:/alkoholmentes_adat_modositas";
        }
        AlcoholFree modositando = alcoholFreeRepo.findById(modifyAlcoholFreeName).get();
        modify.alcoholFreeModification(modositando, modositando.getName(), ar, kiegeszito);
        if (!modositando.getImage().isEmpty()) {
            oldImageForItem = modositando.getImage();
        }
        String newImage = "";
        if (img != null) {
            String kepNeve = StringUtils.cleanPath(Objects.requireNonNull(img.getOriginalFilename()));
            if (kepNeve.contains("..")) {
                redirectAttributes.addFlashAttribute("Uzenet", "Érvénytelen képformátum!");
                return "redirect:/alkoholmentes_adat_modositas";
            }
        }
        try {
            assert img != null;
            float kepMerete = img.getSize();
            if (kepMerete <= 800000) {
                newImage = Base64.getEncoder().encodeToString(img.getBytes());
                if (modositando.getSugarContent().equals(kiegeszito) && oldImageForItem.equals(newImage) && modositando.getAlcoholFreePrice() == ar) {
                    redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                    return "redirect:/alkoholmentes_adat_modositas";
                }
                if (newImage.isEmpty() && oldImageForItem.isEmpty()) {
                    alcoholFreeRepo.itemImage(newImage, modositando.getName());
                }
                if (!newImage.isEmpty()) {
                    alcoholFreeRepo.itemImage(newImage, modositando.getName());
                }
                if (!oldImageForItem.isEmpty() && oldImageForItem.equals(newImage)) {
                    alcoholFreeRepo.itemImage(oldImageForItem, modositando.getName());
                }
            } else {
                redirectAttributes.addFlashAttribute("Uzenet", "Túl nagy méretű képet próbálsz meg feltölteni, a max méret 0,8Mb! Az általad feltöltött kép mérete: " + kepMerete / 1000000 + "Mb");
                return "redirect:/alkoholmentes_adat_modositas";
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("Uzenet", "Váratlan hiba történt, kérjük próbáld újra!");
            return "redirect:/alkoholmentes_adat_modositas";
        }
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres módosítás!");
        return "redirect:/alkoholmentes_adat_modositas";
    }

    @RequestMapping(value = "pizza_kep_torlese")
    public String pizza_image_delete(RedirectAttributes redirectAttributes) {
        oldImageForItem = "";
        pizzasRepo.itemImage(oldImageForItem, modifyPizzaName);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/pizza_adat_modositas";
    }

    @RequestMapping(value = "hamburger_kep_torlese")
    public String hamburger_image_delete(RedirectAttributes redirectAttributes) {
        oldImageForItem = "";
        hamburgerRepo.itemImage(oldImageForItem, modifyHamburgerName);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/hamburger_adat_modositas";
    }

    @RequestMapping(value = "alkoholos_kep_torlese")
    public String alcohol_image_delete(RedirectAttributes redirectAttributes) {
        oldImageForItem = "";
        alcoholRepo.itemImage(oldImageForItem, modifyAlcoholName);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/alkoholos_adat_modositas";
    }

    @RequestMapping(value = "alkoholmentes_kep_torlese")
    public String alcohol_free_delete(RedirectAttributes redirectAttributes) {
        oldImageForItem = "";
        alcoholFreeRepo.itemImage(oldImageForItem, modifyAlcoholFreeName);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/alkoholmentes_adat_modositas";
    }
}
