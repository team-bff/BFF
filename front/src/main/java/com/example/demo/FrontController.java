package com.example.demo;

import com.example.demo.reservation.Reservation;
import com.example.demo.reservation.ReservationForm;
import com.example.demo.users.Users;
import com.example.demo.users.UsersForm;
import com.example.demo.vehicules.Vehicules;
import com.example.demo.vehicules.VehiculesForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FrontController {
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/addUser")
    public String showAddPersonPage(Model model) {

        UsersForm usersForm = new UsersForm();
        model.addAttribute("usersForm", usersForm);

        return "addUser";
    }

    @GetMapping("/usersList")
    public String showPersonPage(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        List<Users> users = restTemplate.getForObject("http://localhost:8080/users", List.class);
        model.addAttribute("users", users);

        return "usersList";
    }

    @GetMapping("/users/{id}")
    public String showOnePerson(Model model, @PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();

        List<Users> users = restTemplate.getForObject("http://localhost:8080/users/" + id, List.class);
        model.addAttribute("users", users);

        return "usersList";
    }

    @GetMapping("/reservationList/{id}")
    public String showReservationListPage(Model model, @PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();

        List<Reservation> reservation = restTemplate.getForObject("http://localhost:8082/reservation/all/" + id, List.class);
        model.addAttribute("reservation", reservation);

        return "reservationList";
    }

    @RequestMapping(value = {"/reservation/{id}"}, method = RequestMethod.GET)
    public String showReservationPage(Model model, @PathVariable int id) {

        ReservationForm reservationForm = new ReservationForm();
        model.addAttribute("reservationForm", reservationForm);
        model.addAttribute("id", id);
        return "reservation";
    }

    @RequestMapping(value = {"/reservation/{id}"}, method = RequestMethod.POST)
    public String editReservationPage(Model model, //
                                      @ModelAttribute("reservationForm") ReservationForm reservationForm) {
        int id = reservationForm.getId();
        int userId = reservationForm.getUserId();
        int vehiculeId = reservationForm.getVehiculeId();

        RestTemplate restTemplate = new RestTemplate();
        Reservation reservation = new Reservation(id, userId, vehiculeId);
        restTemplate.put("http://localhost:8081/reservaton/" + id, reservation, Reservation.class);

        return "redirect:/reservationList";
    }


    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("usersForm") UsersForm usersForm) {
        String name = usersForm.getName();
        String lastname = usersForm.getLastname();
        long numberLicence = usersForm.getNumberLicence();
        RestTemplate restTemplate = new RestTemplate();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Users> users = restTemplate.getForObject("http://localhost:8080/users", List.class);
        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(usersForm.getBirthday());
            Date yearObtention = simpleDateFormat.parse(usersForm.getYearObtention());


            Users newUser = new Users(name, lastname, numberLicence, yearObtention, birthday);
            new RestTemplate().postForObject("http://localhost:8080/users", newUser, Users.class);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/usersList";

    }


    @GetMapping("/vehiculesList")
    public String showVehicules(Model model) {
        RestTemplate restTemplate = new RestTemplate();

        List<Vehicules> vehicules = restTemplate.getForObject("http://localhost:8081/vehicules", List.class);
        model.addAttribute("vehicules", vehicules);

        return "vehiculesList";
    }

    @GetMapping("/addVehicule")
    public String showAddVehiculePage(Model model) {

        VehiculesForm vehiculesForm = new VehiculesForm();
        model.addAttribute("vehiculesForm", vehiculesForm);

        return "addVehicule";
    }

    @RequestMapping(value = "/addVehicule", method = RequestMethod.POST)
    public String saveVehicule(Model model,
                               @ModelAttribute("usersForm") VehiculesForm vehiculesForm) {

        String marque = vehiculesForm.getMarque();
        String modele = vehiculesForm.getModele();
        String immatriculation = vehiculesForm.getImmatriculation();
        int cvf = vehiculesForm.getCvf();
        boolean available = vehiculesForm.isAvailable();
        int categorie = vehiculesForm.getCategorie();

        RestTemplate restTemplate = new RestTemplate();

        Vehicules newVehicule = new Vehicules(marque, modele, immatriculation, cvf, available, categorie);

        restTemplate.postForObject("http://localhost:8081/vehicule", newVehicule, Vehicules.class);

        List<Vehicules> vehicules = restTemplate.getForObject("http://localhost:8081/vehicules", List.class);


        return "redirect:/vehiculesList";
    }

    @GetMapping("/vehicules/{id}")
    public String viewVehicule(Model model, @PathVariable int id) {

        Vehicules vehicule = new RestTemplate().getForObject("http://localhost:8081/vehicules/" + id, Vehicules.class);
        VehiculesForm vehiculesForm = new VehiculesForm(vehicule.getMarque(), vehicule.getModele(), vehicule.getImmatriculation(), vehicule.getCvf(), vehicule.isAvailable(), vehicule.getCategorie());
        model.addAttribute("vehiculesForm", vehiculesForm);

        return "vehicules";
    }

    @PostMapping(value = {"/vehicules/{id}"})
    public String saveTheUpdate(Model model, @PathVariable int id, @ModelAttribute("vehiculesForm") VehiculesForm vehiculesForm) {

        String marque = vehiculesForm.getMarque();
        String modele = vehiculesForm.getModele();
        String immatriculation = vehiculesForm.getImmatriculation();
        int cvf = vehiculesForm.getCvf();
        boolean available = vehiculesForm.isAvailable();
        int categorie = vehiculesForm.getCategorie();
        Vehicules[] vehicules = new RestTemplate().getForObject("http://localhost:8081/vehicules", Vehicules[].class);

        assert vehicules != null;
        for (Vehicules vehicule : vehicules) {
            if (id == vehicule.getId()) {
                vehicule.setMarque(marque);
                vehicule.setCategorie(categorie);
                vehicule.setAvailable(available);
                vehicule.setImmatriculation(immatriculation);
                vehicule.setCvf(cvf);
                vehicule.setModele(modele);
                new RestTemplate().put("http://localhost:8081/vehicules/" + id, vehicule, Vehicules.class);
            }
        }
        return "redirect:/vehiculesList";

    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        new RestTemplate().delete("http://localhost:8081/delete/" + id);
        return "redirect:/vehiculesList";
    }

    @GetMapping("/vehicule/{id}")
    public String viewOneVehicule(Model model, @PathVariable int id) {

        Vehicules vehicules = new RestTemplate().getForObject("http://localhost:8081/vehicules/" + id, Vehicules.class);
        assert vehicules != null;
        Vehicules vehicule = new Vehicules(vehicules.getMarque(), vehicules.getModele(), vehicules.getImmatriculation(), vehicules.getCvf(), vehicules.isAvailable(), vehicules.getCategorie());
        model.addAttribute("vehicule", vehicule);

        return "vehicule";
    }

    @GetMapping("/addReservation")
    public String addReservation(Model model) {
        Vehicules[] vehicules = new RestTemplate().getForObject("http://localhost:8081/vehicules/", Vehicules[].class);
        Users[] users = new RestTemplate().getForObject("http://localhost:8080/users/", Users[].class);

        ReservationForm reservationForm = new ReservationForm();
        model.addAttribute("reservationForm", reservationForm);
        model.addAttribute("vehicules", vehicules);
        model.addAttribute("users", users);


        return "addReservation";
    }
    @PostMapping("/addReservation")
    public String addNewReservationToDB(Model model, @ModelAttribute("reservationForm") ReservationForm reservationForm) {
        int id = reservationForm.getId();
        int userId = reservationForm.getUserId();
        int vehiculeId = reservationForm.getVehiculeId();

        RestTemplate restTemplate = new RestTemplate();
        Reservation reservation = new Reservation(id, userId, vehiculeId);
        restTemplate.postForObject("http://localhost:8082/reservation", reservation, Reservation.class);

        return "redirect:/vehiculesList";
    }
}
