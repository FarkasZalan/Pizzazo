package com.example.demo.Controller;

import com.example.demo.Repositorys.*;
import com.example.demo.entity.*;
import com.example.demo.model.RendelesItem;
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
public class Iranyito {
    @Autowired
    private final RendelhetoTermekRepo termekekRepo;
    private final PizzaRepo pizzakRepo;
    private final HamburgerRepo hamburgerRepo;
    private final AlkoholosItalRepo alkoholosItalRepo;
    private final AlkoholmentesItalRepo alkoholmentesItalRepo;
    private final RendelesRepo rendelesRepo;
    private final FelhasznaloRepo felhasznaloRepo;
    int fizetendo = 0;
    boolean beVanJelentekzve = false;
    Felhasznalo bejelentkezett;
    boolean vanFiok = false;
    Map<RendelhetoTermek, Integer> bevasarloLista = new HashMap<>();
    List<Rendeles> bejelentkezettFelhasznaloOsszesRendelese = new ArrayList<>();
    boolean adminVanBent = false;
    String modositandoPizzaNeve;
    String modositandoHamburgerNeve;
    String modositandoAlkoholosNeve;
    String modositandoAlkoholmentesNeve;
    String regiKep = "";
    @Autowired
    private EmailKuldes emailKuldes;

    public Iranyito(RendelhetoTermekRepo termekekRepo, PizzaRepo pizzakRepo, HamburgerRepo hamburgerRepo, AlkoholosItalRepo alkoholosItalRepo, AlkoholmentesItalRepo alkoholmentesItalRepo, RendelesRepo rendelesRepo, FelhasznaloRepo felhasznaloRepo) {
        this.termekekRepo = termekekRepo;
        this.pizzakRepo = pizzakRepo;
        this.hamburgerRepo = hamburgerRepo;
        this.alkoholosItalRepo = alkoholosItalRepo;
        this.alkoholmentesItalRepo = alkoholmentesItalRepo;
        this.rendelesRepo = rendelesRepo;
        this.felhasznaloRepo = felhasznaloRepo;
    }

    @RequestMapping(value = "/")
    public String fooldal(Model model) {
        model.addAttribute("navi", beVanJelentekzve);
        model.addAttribute("admin", adminVanBent);
        return "Basic/Fooldal";
    }

    @RequestMapping(value = "/kapcsolat")
    public String kapcsolat(Model model) {
        model.addAttribute("navi", beVanJelentekzve);
        model.addAttribute("admin", adminVanBent);
        return "Basic/Kapcsolat";
    }

    @RequestMapping(value = "/regisztracio")
    public String regisztracio(Model model) {
        model.addAttribute("fooldal", felhasznaloRepo.felhasznalokListaja());
        return "Fiokok/Regisztracio";
    }

    @RequestMapping(value = "/regisztracio_process")
    public String regisztracio_process(@RequestParam("nev") String nev, @RequestParam("email") String email, @RequestParam("lakcim") String lakcim,
                                       @RequestParam("telefonszam") String telefonszam, @RequestParam("jelszo") String jelszo, @RequestParam("jelszo_ujra") String jelszo_ujra,
                                       RedirectAttributes redirectAttributes) {
        String mindenJo = "";
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (jelszo.equals(jelszo_ujra)) {
            Felhasznalo felhasznalo = new Felhasznalo(email, nev, lakcim, telefonszam, jelszo);
            mindenJo = letrehozas.felhasznaloLetrehoz(felhasznalo);

        } else {
            redirectAttributes.addFlashAttribute("Uzenet", "Nem egyeznek a megadott jelszavak!");
            return "redirect:/regisztracio";
        }
        if (mindenJo.equals("Sikeres mentés!")) {
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres regisztráció!");
            emailKuldes.uzenetKikuldese(email, "Köszönjük, hogy beregisztráltál hozzánk " + nev + ", reméljük megtalálod számodra a legmeggyőzőbb ételt!", "Sikeres regisztráció");
            return "redirect:/bejelentkezes";
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", mindenJo);
            return "redirect:/regisztracio";
        }
    }

    @RequestMapping(value = "/bejelentkezes")
    public String bejelentkezes(Model model) {
        model.addAttribute("fooldal", felhasznaloRepo.felhasznalokListaja());

        return "Fiokok/Bejelentkezes";
    }

    @RequestMapping(value = "/bejelentkezes_process")
    public String bejelentkezes_process(@RequestParam("email") String email, @RequestParam("jelszo") String jelszo, RedirectAttributes redirectAttributes) {
        Belepes belepes = new Belepes(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        String uzenet = belepes.belepes(email, jelszo);
        adminVanBent = email.equals("admin");

        if (uzenet.isEmpty()) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs ilyen emaillel regisztált felhasználó!");
            return "redirect:/bejelentkezes";
        }
        if (uzenet.equals("Van email")) {
            redirectAttributes.addFlashAttribute("Uzenet", "Rossz jelszó!");
            return "redirect:/bejelentkezes";
        }
        if (uzenet.equals("Sikeres belépés")) {
            beVanJelentekzve = true;
            bejelentkezett = belepes.felhasznaloMegtalal(email, jelszo);
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres belépés! Üdv újra itt, " + bejelentkezett.getNev());
            if (adminVanBent) {
                return "redirect:/admin";
            }
            return "redirect:/fiok";
        }
        return "Fiokok/Bejelentkezes";
    }

    @RequestMapping(value = "/kilepes")
    public String kilepes(Model model, RedirectAttributes redirectAttributes) {
        bejelentkezett = new Felhasznalo("", "", "", "", "");
        vanFiok = false;
        adminVanBent = false;
        beVanJelentekzve = false;
        bevasarloLista.clear();
        fizetendo = 0;

        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres kilépés!");
        return "redirect:/bejelentkezes";
    }

    @RequestMapping(value = "/fiok")
    public String fiok(Model model) {
        model.addAttribute("adatok", bejelentkezett);
        model.addAttribute("admin", adminVanBent);
        if (beVanJelentekzve) {
            return "Fiokok/Fiok";
        } else {
            return "Fiokok/Bejelentkezes";
        }
    }

    @RequestMapping(value = "/felhasznaloModositasa")
    public String felhasznaloModositasa(Model model) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        model.addAttribute("felhasznalo", bejelentkezett);
        model.addAttribute("fooldal", felhasznaloRepo.felhasznalokListaja());
        return "Fiokok/FiokModositasa";
    }

    @RequestMapping(value = "/adatok_modositasa")
    public String adatokModositasa(@RequestParam("nev") String nev, @RequestParam("lakcim") String lakcim,
                                   @RequestParam("telefonszam") String telefonszam, @RequestParam("jelszo") String jelszo, @RequestParam("jelszo_ujra") String jelszo_ujra,
                                   RedirectAttributes redirectAttributes) {
        Modositas modositas = new Modositas(felhasznaloRepo, pizzakRepo, termekekRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo);
        if (jelszo.equals(jelszo_ujra)) {
            bejelentkezett = modositas.felhasznaloModositas(bejelentkezett, bejelentkezett.getId(), nev, lakcim, telefonszam, jelszo);
            redirectAttributes.addFlashAttribute("Uzenet", "Sikeres mentés!");
            emailKuldes.uzenetKikuldese(bejelentkezett.getId(), bejelentkezett.getNev() + ", a profil adataid az alábbiak szerint módosultak:\nNév: " + bejelentkezett.getNev() + "\nLakcím: " + bejelentkezett.getLakcim() + "\nTelefonszám: " + bejelentkezett.getTelefonszam() + "\nJelszó: " + bejelentkezett.getJelszo(), "Profil módosult");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", "Nem egyeznek a megadott jelszavak!");
        }
        return "redirect:/felhasznaloModositasa";
    }

    @RequestMapping(value = "/rendelesekListaja")
    public String rendelesekListaja(Model model, RedirectAttributes redirectAttributes) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        BevasarlasElozmenyek elozmenyek = new BevasarlasElozmenyek(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        bejelentkezettFelhasznaloOsszesRendelese = elozmenyek.elozmenyek(bejelentkezettFelhasznaloOsszesRendelese, bejelentkezett.getId());
        if (bejelentkezettFelhasznaloOsszesRendelese.size() == 0) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs egyetlen listázható rendelés sem!");
            return "redirect:/fiok";
        }

        List<RendelesItem> rendelesItems = new ArrayList<>();
        Iterator<Rendeles> it = bejelentkezettFelhasznaloOsszesRendelese.iterator();
        while (it.hasNext()) {
            Rendeles elem = it.next();
            StringBuilder termekNevek = new StringBuilder();
            StringBuilder termekEgysegArak = new StringBuilder();
            StringBuilder termekMennyiseg = new StringBuilder();
            for (RendelhetoTermek rendelhetoTermek : elem.getTermekNeve().keySet()) {
                if (elem.getFelhasznaloEmailCime().getId().equals(bejelentkezett.getId())) {
                    termekNevek.append(rendelhetoTermek.getNev()).append("<br/>");
                    termekEgysegArak.append(rendelhetoTermek.getAr()).append("<br/>");
                }
            }

            for (Integer mennyiseg : elem.getTermekNeve().values()) {
                if (elem.getFelhasznaloEmailCime().getId().equals(bejelentkezett.getId())) {
                    termekMennyiseg.append(mennyiseg.toString()).append("<br/>");
                }
            }
            if (!termekNevek.toString().equals("")) {
                RendelesItem item = new RendelesItem(elem.getId().toString(), elem.getRendelesIdopontja(), termekNevek.toString(), termekMennyiseg.toString(), termekEgysegArak.toString(), elem.getFizetendoOsszeg().toString());
                rendelesItems.add(item);
            }
        }
        if (rendelesItems.size() == 0) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs egyetlen listázható rendelés sem!");
            return "redirect:/fiok";
        }
        model.addAttribute("rendelesek", rendelesItems);
        return "Fiokok/RendelesekListaja";
    }

    @RequestMapping(value = "/termekek")
    public String termekek(Model model) {
        model.addAttribute("termekek", termekekRepo.rendelhetotermekekListaja());
        model.addAttribute("pizzak", pizzakRepo.pizzakListaja());
        model.addAttribute("hamburgerek", hamburgerRepo.hamburgerekListaja());
        model.addAttribute("alkoholosak", alkoholosItalRepo.alkoholosItalokListaja());
        model.addAttribute("alkoholmentesek", alkoholmentesItalRepo.alkoholmentesItalokListaja());
        model.addAttribute("navi", beVanJelentekzve);
        model.addAttribute("admin", adminVanBent);
        return "Basic/Termekek";
    }

    @RequestMapping(value = "/pizzat_rendel")
    public String pizzat_rendel(@RequestParam("pizzaNeve") String pizzaneve, RedirectAttributes redirectAttributes) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (beVanJelentekzve) {
            bevasarloLista = letrehozas.pizzaHozzaadasa(bevasarloLista, pizzaneve);
            fizetendo += letrehozas.getEddigFizetendo();
            redirectAttributes.addFlashAttribute("Uzenet", pizzaneve + " sikeresen hozzáadva a kosárhoz");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
        }
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/hamburgert_rendel")
    public String hamburgert_rendel(@RequestParam("hamburgerNeve") String hamburgerNeve, RedirectAttributes redirectAttributes) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (beVanJelentekzve) {
            bevasarloLista = letrehozas.hamburgerHozzaadasa(bevasarloLista, hamburgerNeve);
            fizetendo += letrehozas.getEddigFizetendo();
            redirectAttributes.addFlashAttribute("Uzenet", hamburgerNeve + " sikeresen hozzáadva a kosárhoz");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
        }
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/alkoholosat_rendel")
    public String alkoholosat_rendel(@RequestParam("alkoholosItalNeve") String alkoholosItalNeve, RedirectAttributes redirectAttributes) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (beVanJelentekzve) {
            bevasarloLista = letrehozas.alkoholosItalHozzaadasa(bevasarloLista, alkoholosItalNeve);
            fizetendo += letrehozas.getEddigFizetendo();
            redirectAttributes.addFlashAttribute("Uzenet", alkoholosItalNeve + " sikeresen hozzáadva a kosárhoz");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
        }
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/alkoholmenteset_rendel")
    public String alkoholmenteset_rendel(@RequestParam("alkoholmentesItalNeve") String alkoholmentesItalNeve, RedirectAttributes redirectAttributes) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (beVanJelentekzve) {

            bevasarloLista = letrehozas.alkoholmentesItalHozzaadasa(bevasarloLista, alkoholmentesItalNeve);
            fizetendo += letrehozas.getEddigFizetendo();
            redirectAttributes.addFlashAttribute("Uzenet", alkoholmentesItalNeve + " sikeresen hozzáadva a kosárhoz");
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
        }
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/kosar")
    public String kosar(Model model, RedirectAttributes redirectAttributes) {
        if (!beVanJelentekzve) {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
            return "redirect:/bejelentkezes";
        }
        if (bevasarloLista.size() == 0) {
            redirectAttributes.addFlashAttribute("Uzenet", "Üres a kosarad! Adj hozzá új termékeket most!");
            return "redirect:/termekek";
        }
        model.addAttribute("bevasarloLista", bevasarloLista);
        model.addAttribute("vegosszeg", fizetendo);
        model.addAttribute("navi", beVanJelentekzve);
        model.addAttribute("admin", adminVanBent);
        return "RendelesKezeles/Kosar";
    }

    @RequestMapping(value = "/kosar_csokkentese")
    public String kosar_csokkentese(@RequestParam("torlendoNeve") String torlendoNeve, RedirectAttributes redirectAttributes) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        Torles torles = new Torles(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (beVanJelentekzve) {
            fizetendo = torles.termekTorlese(torlendoNeve, bevasarloLista, fizetendo);
            return "redirect:/kosar";
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
            return "redirect:/termekek";
        }
    }

    @RequestMapping(value = "/kosar_novelese")
    public String kosar_novelese(@RequestParam("hozzaAdandoNeve") String hozzaAdandoNeve, RedirectAttributes redirectAttributes) {
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        Torles torles = new Torles(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        if (beVanJelentekzve) {
            bevasarloLista = letrehozas.termekHozzaadasa(bevasarloLista, hozzaAdandoNeve);
            fizetendo += letrehozas.getEddigFizetendo();
            return "redirect:/kosar";
        } else {
            redirectAttributes.addFlashAttribute("Uzenet", " Előbb be kell jelentkezned ahhoz, hogy elkezdhesd összeállítani a kosaradat!");
            return "redirect:/termekek";
        }
    }

    @RequestMapping(value = "/rendeles")
    public String rendeles(Model model, RedirectAttributes redirectAttributes) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        if (bevasarloLista.size() == 0) {
            bevasarloLista.size();
            redirectAttributes.addFlashAttribute("Uzenet", "Üres a kosarad! Adj hozzá új termékeket most!");
            return "redirect:/termekek";
        }
        model.addAttribute("adatok", bejelentkezett);
        model.addAttribute("bevasarloLista", bevasarloLista);
        model.addAttribute("vegosszeg", fizetendo);
        model.addAttribute("navi", beVanJelentekzve);
        model.addAttribute("admin", adminVanBent);
        return "RendelesKezeles/Rendeles";
    }

    @RequestMapping(value = "/nyugta")
    public String rendeles_leadasa(Model model) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        StringBuilder rendeltTermekek = new StringBuilder();
        Letrehozas letrehozas = new Letrehozas(termekekRepo, pizzakRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo, rendelesRepo, felhasznaloRepo);
        for (Map.Entry<RendelhetoTermek, Integer> listabanLevo : bevasarloLista.entrySet()) {
            rendeltTermekek.append(listabanLevo.getKey().getNev()).append(" ").append(listabanLevo.getValue().toString()).append(" db\n");
        }
        rendeltTermekek.append("\n\nVégösszeg: ").append(fizetendo).append("\nFizetésmód: Fizetés a futárnál kártyával");
        String emalCime = "";
        if(adminVanBent) {
            emalCime = "admin@admin.com";
        } else {
            emalCime = bejelentkezett.getId();
        }
        emailKuldes.uzenetKikuldese(emalCime, "Köszönjük, hogy tőlünk rendeltél, " + bejelentkezett.getNev() + "!\n\nRendelésed részletei:\n\nRendelő adatai:\nRendelő neve: " + bejelentkezett.getNev() + "\nKiszállítás címe: " + bejelentkezett.getLakcim() + "\nRendelő elérhetősége: " + bejelentkezett.getTelefonszam() + "\n\n" + rendeltTermekek, "Rendelés leadva");
        int azonosito = letrehozas.rendelesLetrehozasa(bevasarloLista, bejelentkezett, fizetendo);
        String idopont = letrehozas.getIdopont();
        StringBuilder termekek = new StringBuilder();
        StringBuilder mennyiseg = new StringBuilder();
        for (RendelhetoTermek termek : bevasarloLista.keySet()) {
            termekek.append(termek.getNev()).append("<br/>");
        }
        for (Integer termek : bevasarloLista.values()) {
            mennyiseg.append(termek.toString()).append("<br/>");
        }
        model.addAttribute("adatok", bejelentkezett);
        model.addAttribute("rendelesAzonosito", azonosito);
        model.addAttribute("rendelesIdopontja", idopont);
        model.addAttribute("bevasarloLista", termekek);
        model.addAttribute("mennyiseg", mennyiseg);
        model.addAttribute("vegosszeg", fizetendo);
        model.addAttribute("navi", beVanJelentekzve);
        model.addAttribute("admin", adminVanBent);
        bevasarloLista.clear();
        fizetendo = 0;
        return "RendelesKezeles/Nyugta";
    }

    @RequestMapping(value = "/uj_rendeles")
    public String uj_rendeles() {
        bevasarloLista.clear();
        fizetendo = 0;
        return "redirect:/termekek";
    }

    @RequestMapping(value = "/admin")
    public String adminFiok(Model model) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        model.addAttribute("adatok", bejelentkezett);
        model.addAttribute("admin", adminVanBent);
        return "AdminFiok/Admin";
    }

    @RequestMapping(value = "/statisztikak")
    public String statisztikak(Model model, RedirectAttributes redirectAttributes) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }

        String legtobbetFizetett = rendelesRepo.legtobbetFizetett();

        if (!Objects.equals(legtobbetFizetett, "null,null,null,null")) {
            String[] legtobbetfizetettdarabok = legtobbetFizetett.split(",");
            String legtobbNev = legtobbetfizetettdarabok[0];
            String legtobbFizetettOsszeg = legtobbetfizetettdarabok[1];
            String legtobbFizetettId = legtobbetfizetettdarabok[2];
            String legtobbFizetettIdopont = legtobbetfizetettdarabok[3];

            model.addAttribute("legtobbNev", legtobbNev);
            model.addAttribute("legtobbFizetettOsszeg", legtobbFizetettOsszeg);
            model.addAttribute("legtobbFizetettId", legtobbFizetettId);
            model.addAttribute("legtobbFizetettIdopont", legtobbFizetettIdopont);
        }

        String legkelendobbTermek = rendelesRepo.legtobbetRendeltTermekek();
        if (legkelendobbTermek != null) {
            String[] legkelendobbDarab = legkelendobbTermek.split(",");
            String legtobbTermekNeve = legkelendobbDarab[0];
            String legtobbOsszMennyisege = legkelendobbDarab[1];
            String legtobbUtolsoRendelesIdopontja = legkelendobbDarab[2];

            model.addAttribute("legtobbTermekNeve", legtobbTermekNeve);
            model.addAttribute("legtobbOsszMennyisege", legtobbOsszMennyisege);
            model.addAttribute("legtobbUtolsoRendelesIdopontja", legtobbUtolsoRendelesIdopontja);
        }

        String kevesbeKelendoTermek = rendelesRepo.legkevesebbetRendeltTermekek();
        if (kevesbeKelendoTermek != null) {
            String[] kevesbeKelendobbDarab = kevesbeKelendoTermek.split(",");
            String kevesbeKelendoTermekNeve = kevesbeKelendobbDarab[0];
            String kevesbeKelendoOsszMennyisege = kevesbeKelendobbDarab[1];
            String kevesbeKelendoUtolsoRendelesIdopontja = kevesbeKelendobbDarab[2];

            model.addAttribute("kevesbeKelendoTermekNeve", kevesbeKelendoTermekNeve);
            model.addAttribute("kevesbeKelendoOsszMennyisege", kevesbeKelendoOsszMennyisege);
            model.addAttribute("kevesbeKelendoUtolsoRendelesIdopontja", kevesbeKelendoUtolsoRendelesIdopontja);
        }
        if (legtobbetFizetett.equals("null,null,null,null") && legkelendobbTermek == null && kevesbeKelendoTermek == null) {
            redirectAttributes.addFlashAttribute("Uzenet", "Nincs egyetlen listázható statisztika sem!");
            return "redirect:/admin";
        }
        return "AdminFiok/Statisztikak";
    }

    @RequestMapping(value = "/felhasznalot_kezeles")
    public String FelhasznalokKezelese(Model model) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        List<Felhasznalo> felhasznalokAdminNelkul = new ArrayList<>();
        for (Felhasznalo felhasznalo : felhasznaloRepo.felhasznalokListaja()) {
            if (!felhasznalo.getId().equals("admin")) {
                felhasznalokAdminNelkul.add(felhasznalo);
            }
        }
        model.addAttribute("felhasznalok", felhasznalokAdminNelkul);
        model.addAttribute("admin", adminVanBent);
        return "AdminFiok/FelhasznalokKezelese";
    }

    @RequestMapping(value = "/felhasznalo_torlese")
    public String felhasznalo_torlese(@RequestParam("torlendoNeve") String torlendoNeve, RedirectAttributes redirectAttributes) {
        felhasznaloRepo.deleteById(torlendoNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres törlés!");
        emailKuldes.uzenetKikuldese(torlendoNeve, "Sajnálattak értesítűnk, hogy törölték fiókodat a rendszerünkből", "Fiók törölve");
        return "redirect:/felhasznalot_kezeles";
    }

    @RequestMapping(value = "/jelszo_alaphelyzet")
    public String jelszo_alaphelyzet(@RequestParam("torlendoNeve") String modositando, RedirectAttributes redirectAttributes) {

        for (Felhasznalo felhasznalo : felhasznaloRepo.felhasznalokListaja()) {
            if (felhasznalo.getId().equals(modositando)) {
                felhasznalo.setJelszo("1234");
                felhasznaloRepo.save(felhasznalo);
            }
        }
        redirectAttributes.addFlashAttribute("Uzenet", modositando + " jelszava sikeresen megváltozott a következőre: 1234");
        emailKuldes.uzenetKikuldese(modositando, "Értesítjük, hogy jelszavát alaphelyzetbe állították ami a következő: 1234", "Jelszó alaphelyzetbe kerűlt");
        return "redirect:/felhasznalot_kezeles";
    }

    @RequestMapping(value = "/termekek_kezelese")
    public String termekek_kezelese(Model model) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        model.addAttribute("termekek", termekekRepo.rendelhetotermekekListaja());
        model.addAttribute("pizzak", pizzakRepo.pizzakListaja());
        model.addAttribute("hamburgerek", hamburgerRepo.hamburgerekListaja());
        model.addAttribute("alkoholosak", alkoholosItalRepo.alkoholosItalokListaja());
        model.addAttribute("alkoholmentesek", alkoholmentesItalRepo.alkoholmentesItalokListaja());
        model.addAttribute("admin", adminVanBent);
        return "AdminFiok/TermekKezeles";
    }

    @RequestMapping(value = "/termek_torlese")
    public String termek_torlese(@RequestParam("torlendoNeve") String torlendoNeve, RedirectAttributes redirectAttributes) {
        termekekRepo.deleteById(torlendoNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres törlés!");
        return "redirect:/termekek_kezelese";
    }

    @RequestMapping(value = "/termekHozzaad")
    public String termekHozzaad(Model model) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        return "AdminFiok/TermekHozzaadasa";
    }

    @RequestMapping(value = "/termek_hozzaad")
    public String termek_hozzaad(@RequestParam("nev") String nev, @RequestParam("ar") int ar, @RequestParam("kiegeszito") String kiegeszito,
                                 @RequestParam(value = "kategoria", required = false, defaultValue = "") String kategoria,
                                 @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {

        assert img != null;
        double kepMerete = img.getSize();
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

        for (RendelhetoTermek rendelhetoTermek : termekekRepo.rendelhetotermekekListaja()) {
            if (rendelhetoTermek.getNev().equals(nev)) {
                redirectAttributes.addFlashAttribute("Uzenet", "Már van egy " + nev + " nevű termék a listában!");
                return "redirect:/termekHozzaad";
            }
        }

        if (kategoria.equals("")) {
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

        try {
            kep = Base64.getEncoder().encodeToString(img.getBytes());
            switch (kategoria) {
                case "pizza" -> {

                    RendelhetoTermek pizzaTermek = new RendelhetoTermek(nev, ar);
                    Pizza pizza = new Pizza(nev, pizzaTermek, ar, kiegeszito, "");
                    redirectAttributes.addFlashAttribute("Uzenet", nev + " sikeresen hozzáadva a termékek közé!");
                    termekekRepo.save(pizzaTermek);
                    pizzakRepo.save(pizza);
                    pizzakRepo.termekKepe(kep, pizza.getNev());
                }
                case "hamburger" -> {
                    RendelhetoTermek hamburgerTermek = new RendelhetoTermek(nev, ar);
                    Hamburger hamburger = new Hamburger(nev, hamburgerTermek, ar, kiegeszito, "");
                    redirectAttributes.addFlashAttribute("Uzenet", nev + " sikeresen hozzáadva a termékek közé!");
                    termekekRepo.save(hamburgerTermek);
                    hamburgerRepo.save(hamburger);
                    hamburgerRepo.termekKepe(kep, hamburger.getNev());
                }
                case "alkoholos" -> {
                    RendelhetoTermek alkoholosTermek = new RendelhetoTermek(nev, ar);
                    AlkoholosItal alkoholos = new AlkoholosItal(nev, alkoholosTermek, ar, kiegeszito, "");
                    redirectAttributes.addFlashAttribute("Uzenet", nev + " sikeresen hozzáadva a termékek közé!");
                    termekekRepo.save(alkoholosTermek);
                    alkoholosItalRepo.save(alkoholos);
                    alkoholosItalRepo.termekKepe(kep, alkoholos.getNev());
                }
                case "alkoholmentes" -> {
                    RendelhetoTermek alkoholmentesTermek = new RendelhetoTermek(nev, ar);
                    AlkoholmentesItal alkoholmentes = new AlkoholmentesItal(nev, alkoholmentesTermek, ar, kiegeszito, "");
                    termekekRepo.save(alkoholmentesTermek);
                    alkoholmentesItalRepo.save(alkoholmentes);
                    alkoholosItalRepo.termekKepe(kep, alkoholmentes.getNev());
                    redirectAttributes.addFlashAttribute("Uzenet", nev + " sikeresen hozzáadva a termékek közé!");
                }
            }
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("Uzenet", "Váratlan hiba történt, kérjük próbáld újra!");
            return "redirect:/termekHozzaad";
        }
        return "redirect:/termekHozzaad";
    }

    @RequestMapping(value = "/pizza_adat_modositas")
    public String pizza_adat_modositas(Model model, @RequestParam(value = "modositandoPizzaNeve", required = false) String nev) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        if (nev != null) {
            modositandoPizzaNeve = nev;
        }
        Pizza modositando = pizzakRepo.findById(modositandoPizzaNeve).get();
        model.addAttribute("pizza", modositando);
        return "Modositasok/PizzaModositas";
    }

    @RequestMapping(value = "/hamburger_adat_modositas")
    public String hamburger_adat_modositas(Model model, @RequestParam(value = "modositandoHamburgerNeve", required = false) String nev) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        if (nev != null) {
            modositandoHamburgerNeve = nev;
        }
        Hamburger modositando = hamburgerRepo.findById(modositandoHamburgerNeve).get();
        model.addAttribute("hamburger", modositando);
        return "Modositasok/HamburgerModositas";
    }

    @RequestMapping(value = "/alkoholos_adat_modositas")
    public String alkoholos_adat_modositas(Model model, @RequestParam(value = "modositandoAlkoholosNeve", required = false) String nev) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        if (nev != null) {
            modositandoAlkoholosNeve = nev;
        }
        AlkoholosItal modositando = alkoholosItalRepo.findById(modositandoAlkoholosNeve).get();
        model.addAttribute("alkoholos", modositando);
        return "Modositasok/AlkoholosModositas";
    }

    @RequestMapping(value = "/alkoholmentes_adat_modositas")
    public String alkoholmentes_adat_modositas(Model model, @RequestParam(value = "modositandoAlkoholmentesNeve", required = false) String nev) {
        if (bejelentkezett == null) {
            return "Fiokok/Bejelentkezes";
        }
        if (nev != null) {
            modositandoAlkoholmentesNeve = nev;
        }
        AlkoholmentesItal modositando = alkoholmentesItalRepo.findById(modositandoAlkoholmentesNeve).get();
        model.addAttribute("alkoholmentes", modositando);
        return "Modositasok/AlkoholmentesModositas";
    }

    @RequestMapping(value = "/pizza_adat_modositas_process")
    public String pizza_adat_modositas_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                               @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modositas modositas = new Modositas(felhasznaloRepo, pizzakRepo, termekekRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo);
        if (ar != 0 || kiegeszito != null) {
            Pizza modositando = pizzakRepo.findById(modositandoPizzaNeve).get();
            modositas.pizzaModositas(modositando, modositando.getNev(), ar, kiegeszito);
            if (!modositando.getKep().equals("")) {
                regiKep = modositando.getKep();
            }
            String modositandoKep = "";
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
                    modositandoKep = Base64.getEncoder().encodeToString(img.getBytes());
                    if (modositando.getFeltet().equals(kiegeszito) && regiKep.equals(modositandoKep) && modositando.getPizzaAra() == ar) {
                        redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                        return "redirect:/pizza_adat_modositas";
                    }
                    if (modositandoKep.equals("") && regiKep.equals("")) {
                        pizzakRepo.termekKepe(modositandoKep, modositando.getNev());
                    }
                    if (!modositandoKep.equals("")) {
                        pizzakRepo.termekKepe(modositandoKep, modositando.getNev());
                    }
                    if (!regiKep.equals("") && regiKep.equals(modositandoKep)) {
                        pizzakRepo.termekKepe(regiKep, modositando.getNev());
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
        return "Modositasok/PizzaModositas";
    }

    @RequestMapping(value = "/hamburger_adat_modositas_process")
    public String hamburger_adat_modositas_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                                   @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modositas modositas = new Modositas(felhasznaloRepo, pizzakRepo, termekekRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo);
        if (ar != 0 || kiegeszito != null) {
            Hamburger modositando = hamburgerRepo.findById(modositandoHamburgerNeve).get();
            modositas.hamburgerModositas(modositando, modositando.getNev(), ar, kiegeszito);
            if (!modositando.getKep().equals("")) {
                regiKep = modositando.getKep();
            }
            String modositandoKep = "";
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
                    modositandoKep = Base64.getEncoder().encodeToString(img.getBytes());
                    if (modositando.getTartalom().equals(kiegeszito) && regiKep.equals(modositandoKep) && modositando.getHamburgerAra() == ar) {
                        redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                        return "redirect:/hamburger_adat_modositas";
                    }
                    if (modositandoKep.equals("") && regiKep.equals("")) {
                        hamburgerRepo.termekKepe(modositandoKep, modositando.getNev());
                    }
                    if (!modositandoKep.equals("")) {
                        hamburgerRepo.termekKepe(modositandoKep, modositando.getNev());
                    }
                    if (!regiKep.equals("") && regiKep.equals(modositandoKep)) {
                        hamburgerRepo.termekKepe(regiKep, modositando.getNev());
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
        return "Modositasok/HamburgerModositas";
    }

    @RequestMapping(value = "/alkoholos_adat_modositas_process")
    public String alkoholos_adat_modositas_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                                   @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modositas modositas = new Modositas(felhasznaloRepo, pizzakRepo, termekekRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo);
        if (kiegeszito.length() > 4) {
            redirectAttributes.addFlashAttribute("Uzenet", "Az alkoholszázalékot a következő formátumban add meg pl. 100%");
            return "redirect:/alkoholos_adat_modositas";
        }
        AlkoholosItal modositando = alkoholosItalRepo.findById(modositandoAlkoholosNeve).get();
        modositas.alkoholosModositas(modositando, modositando.getNev(), ar, kiegeszito);
        if (!modositando.getKep().equals("")) {
            regiKep = modositando.getKep();
        }
        String modositandoKep = "";
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
                modositandoKep = Base64.getEncoder().encodeToString(img.getBytes());
                if (modositando.getAlhoholSzazalek().equals(kiegeszito) && regiKep.equals(modositandoKep) && modositando.getAlhoholosItalAra() == ar) {
                    redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                    return "redirect:/alkoholos_adat_modositas";
                }
                if (modositandoKep.equals("") && regiKep.equals("")) {
                    alkoholosItalRepo.termekKepe(modositandoKep, modositando.getNev());
                }
                if (!modositandoKep.equals("")) {
                    alkoholosItalRepo.termekKepe(modositandoKep, modositando.getNev());
                }
                if (!regiKep.equals("") && regiKep.equals(modositandoKep)) {
                    alkoholosItalRepo.termekKepe(regiKep, modositando.getNev());
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
    public String alkoholmentes_adat_modositas_process(@RequestParam(value = "ar", required = false, defaultValue = "0") int ar, @RequestParam(value = "kiegeszito", required = false) String kiegeszito,
                                                       @RequestParam(name = "img", required = false) MultipartFile img, RedirectAttributes redirectAttributes) {
        Modositas modositas = new Modositas(felhasznaloRepo, pizzakRepo, termekekRepo, hamburgerRepo, alkoholosItalRepo, alkoholmentesItalRepo);
        if (kiegeszito.length() > 4) {
            redirectAttributes.addFlashAttribute("Uzenet", "A cukortartalmat a következő formátumban add meg pl. 100% vagy zero");
            return "redirect:/alkoholmentes_adat_modositas";
        }
        AlkoholmentesItal modositando = alkoholmentesItalRepo.findById(modositandoAlkoholmentesNeve).get();
        modositas.alkoholmentesModositas(modositando, modositando.getNev(), ar, kiegeszito);
        if (!modositando.getKep().equals("")) {
            regiKep = modositando.getKep();
        }
        String modositandoKep = "";
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
                modositandoKep = Base64.getEncoder().encodeToString(img.getBytes());
                if (modositando.getCukortartalom().equals(kiegeszito) && regiKep.equals(modositandoKep) && modositando.getAlkoholmentesItalAra() == ar) {
                    redirectAttributes.addFlashAttribute("Uzenet", "Nem történt módosítás!");
                    return "redirect:/alkoholmentes_adat_modositas";
                }
                if (modositandoKep.equals("") && regiKep.equals("")) {
                    alkoholmentesItalRepo.termekKepe(modositandoKep, modositando.getNev());
                }
                if (!modositandoKep.equals("")) {
                    alkoholmentesItalRepo.termekKepe(modositandoKep, modositando.getNev());
                }
                if (!regiKep.equals("") && regiKep.equals(modositandoKep)) {
                    alkoholmentesItalRepo.termekKepe(regiKep, modositando.getNev());
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
    public String pizza_kep_torlese(RedirectAttributes redirectAttributes) {
        regiKep = "";
        pizzakRepo.termekKepe(regiKep, modositandoPizzaNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/pizza_adat_modositas";
    }

    @RequestMapping(value = "hamburger_kep_torlese")
    public String hamburger_kep_torlese(RedirectAttributes redirectAttributes) {
        regiKep = "";
        hamburgerRepo.termekKepe(regiKep, modositandoHamburgerNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/hamburger_adat_modositas";
    }

    @RequestMapping(value = "alkoholos_kep_torlese")
    public String alkoholos_kep_torlese(RedirectAttributes redirectAttributes) {
        regiKep = "";
        alkoholosItalRepo.termekKepe(regiKep, modositandoAlkoholosNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/alkoholos_adat_modositas";
    }

    @RequestMapping(value = "alkoholmentes_kep_torlese")
    public String alkoholmentes_kep_torlese(RedirectAttributes redirectAttributes) {
        regiKep = "";
        alkoholmentesItalRepo.termekKepe(regiKep, modositandoAlkoholmentesNeve);
        redirectAttributes.addFlashAttribute("Uzenet", "Sikeres képeltávolítás");
        return "redirect:/alkoholmentes_adat_modositas";
    }
}
