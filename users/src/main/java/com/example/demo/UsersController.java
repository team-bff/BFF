package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Optional;


@RestController
public class UsersController {
    private int id;
    private String lastname;
    private long numberLicence;
    private int year;
    private int month;
    private int date;


    @Autowired
    private UserDao userDao;

    @GetMapping("/users")
    public Iterable<Users> listUsers() {
        Iterable<Users> users = userDao.findAll();
        return users;
    }

    @GetMapping("users/{id}")
    public Optional<Users> getUser(@PathVariable int id) {
        return userDao.findById(id);
    }

    @PostMapping("/users")
    public void setUser(@RequestBody Users newUser) {
        userDao.save(newUser);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDao.deleteById(id);
    }

}
