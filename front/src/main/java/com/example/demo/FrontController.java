package com.example.demo;

import com.example.demo.reservation.Reservation;
import com.example.demo.reservation.ReservationForm;
import com.example.demo.users.Users;
import com.example.demo.users.UsersForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        model.addAttribute("id",id);
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
            restTemplate.put("http://localhost:8081/reservaton/"+id,reservation,Reservation.class);

            return "redirect:/reservationList";
        }


    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("usersForm") UsersForm usersForm) {
        String name = usersForm.getName();
        String lastname = usersForm.getLastname();
        long numberLicence = usersForm.getNumberLicence();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date birthday = null;
        try {
            birthday = simpleDateFormat.parse(usersForm.getBirthday());
            Date yearObtention = simpleDateFormat.parse(usersForm.getYearObtention());

            RestTemplate restTemplate = new RestTemplate();

            Users newUser = new Users(name, lastname, numberLicence, yearObtention, birthday);
            restTemplate.postForObject("http://localhost:8080/users", newUser, Users.class);

            List<Users> users = restTemplate.getForObject("http://localhost:8080/users", List.class);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/usersList";

    }

}
