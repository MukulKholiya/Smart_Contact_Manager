package com.scm.controllers;

import com.scm.entities.User;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.helper.Helper;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);
    // User Dashboard
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }
    // User Profile Page
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String userProfile(Model model, Authentication authentication){

        return "user/profile";
    }

    // User add contact


    // User View Contact


    // User search contact


    // User Delete Contact

}
