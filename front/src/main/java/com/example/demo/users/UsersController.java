package com.example.demo.users;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UsersController {
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
      List<Users> users = restTemplate.getForObject("http://localhost:8080/users", List.class);

            Users newUser = new Users(name, lastname, numberLicence, yearObtention, birthday);
            restTemplate.postForObject("http://localhost:8080/users", newUser, Users.class);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "redirect:/usersList";

    }
}
