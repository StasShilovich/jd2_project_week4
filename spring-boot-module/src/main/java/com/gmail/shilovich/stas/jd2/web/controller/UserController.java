package com.gmail.shilovich.stas.jd2.web.controller;

import com.gmail.shilovich.stas.jd2.servicemodule.UserService;
import com.gmail.shilovich.stas.jd2.servicemodule.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@EnableAutoConfiguration
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUserList(Model model) {
        List<UserDTO> users = userService.getUsers();
        model.addAttribute("users", users);
        return "users";
    }
}
