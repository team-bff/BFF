package com.example.demo;

import com.example.demo.reservation.Reservation;
import com.example.demo.reservation.ReservationForm;
import com.example.demo.users.Users;
import com.example.demo.users.UsersForm;
import com.example.demo.vehicules.Vehicules;
import com.example.demo.vehicules.VehiculesForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class FrontController {
    @Value("${error.message}")
    private String errorMessage;
    @Value("${error.messageAge}")
    private String errorMessageAge;

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

        List<Reservation> reservation = restTemplate.getForObject("http://localhost:8082/reservations", List.class);
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
        restTemplate.put("http://localhost:8081/reservation/" + id, reservation, Reservation.class);

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
        Date currentDate = null;
        try {
            birthday = simpleDateFormat.parse(usersForm.getBirthday());
            currentDate = simpleDateFormat.parse("2003-10-18");
            Date yearObtention = simpleDateFormat.parse(usersForm.getYearObtention());


            Users newUser = new Users(name, lastname, numberLicence, yearObtention, birthday);
            if ((birthday != null) && (birthday.before(currentDate))) {
                new RestTemplate().postForObject("http://localhost:8080/users", newUser, Users.class);
            } else {
                model.addAttribute("errorMessage", errorMessageAge);
                return "addUser";
            }

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

        int categorie = vehiculesForm.getCategorie();

        RestTemplate restTemplate = new RestTemplate();

        Vehicules newVehicule = new Vehicules(marque, modele, immatriculation, cvf, categorie);

        restTemplate.postForObject("http://localhost:8081/vehicule", newVehicule, Vehicules.class);

        List<Vehicules> vehicules = restTemplate.getForObject("http://localhost:8081/vehicules", List.class);


        return "redirect:/vehiculesList";
    }

    @GetMapping("/vehicules/{id}")
    public String viewVehicule(Model model, @PathVariable int id) {

        Vehicules vehicule = new RestTemplate().getForObject("http://localhost:8081/vehicules/" + id, Vehicules.class);
        VehiculesForm vehiculesForm = new VehiculesForm(vehicule.getMarque(), vehicule.getModele(), vehicule.getImmatriculation(), vehicule.getCvf(), vehicule.getCategorie());
        model.addAttribute("vehiculesForm", vehiculesForm);

        return "vehicules";
    }

    @PostMapping(value = {"/vehicules/{id}"})
    public String saveTheUpdate(Model model, @PathVariable int id, @ModelAttribute("vehiculesForm") VehiculesForm vehiculesForm) throws ParseException {

        String marque = vehiculesForm.getMarque();
        String modele = vehiculesForm.getModele();
        String immatriculation = vehiculesForm.getImmatriculation();
        int cvf = vehiculesForm.getCvf();
        int categorie = vehiculesForm.getCategorie();
        Vehicules[] vehicules = new RestTemplate().getForObject("http://localhost:8081/vehicules", Vehicules[].class);

        assert vehicules != null;
        for (Vehicules vehicule : vehicules) {
            if (id == vehicule.getId()) {

                vehicule.setMarque(marque);
                vehicule.setCategorie(categorie);

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
        Vehicules vehicule = new Vehicules(vehicules.getMarque(), vehicules.getModele(), vehicules.getImmatriculation(), vehicules.getCvf(), vehicules.getCategorie());
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
    public String addNewReservationToDB(Model model, @ModelAttribute("reservationForm") ReservationForm reservationForm) throws ParseException {
        int id = reservationForm.getId();
        int userId = reservationForm.getUserId();
        int vehiculeId = reservationForm.getVehiculeId();
        long km = reservationForm.getKm();
        Date firstDate = reservationForm.getFirstDate();
        Date lastDate = reservationForm.getLastDate();
        boolean available = true;
        Reservation[] reservations = new RestTemplate().getForObject("http://localhost:8082/reservation/byvehicule/" + vehiculeId, Reservation[].class);
        Vehicules vehicules = new RestTemplate().getForObject("http://localhost:8081/vehicules/" + vehiculeId, Vehicules.class);

        vehicules.getCvf();

        RestTemplate restTemplate = new RestTemplate();
        Reservation newReservation = new Reservation(id, userId, vehiculeId, km, firstDate, lastDate);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDateless21 = null;
        Date currentDateless25 = null;
        Users users = new RestTemplate().getForObject("http://localhost:8080/users/" + userId, Users.class);
        Date userBirthday = users.getBirthday();
        currentDateless25 = simpleDateFormat.parse("1996-10-28");
        currentDateless21 = simpleDateFormat.parse("2000-10-28");
        if ((vehicules.getCvf() > 8 && userBirthday.after(currentDateless21)) || (vehicules.getCvf() > 13 && userBirthday.after(currentDateless25))) {
            available = false;
            model.addAttribute("errorMessage", errorMessageAge);
        } else {
            for (Reservation reservation : reservations) {

                if ((!newReservation.getFirstDate().after(reservation.getFirstDate()))
                        || (newReservation.getFirstDate().before(reservation.getLastDate()))
                        || (!newReservation.getLastDate().after(reservation.getFirstDate()))
                        || ((newReservation.getFirstDate().equals(reservation.getFirstDate()))
                        && (newReservation.getLastDate().equals(reservation.getLastDate())))) {

                    available = false;
                    model.addAttribute("errorMessage", errorMessage);
                }
            }
        }
        if (available) {
            restTemplate.postForObject("http://localhost:8082/reservation", newReservation, Reservation.class);
            return "redirect:/vehiculesList";
        } else {
            return "addReservation";
        }
    }

}