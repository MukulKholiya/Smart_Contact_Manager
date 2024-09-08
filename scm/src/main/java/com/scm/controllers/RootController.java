package com.scm.controllers;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    public UserService userService;

    @ModelAttribute
    public void addLoggedInUserInfo(Model model, Authentication authentication){
        if(authentication == null){
            return;
        }
        String userName = Helper.getEmailOfLoggedInUser(authentication);
        logger.info(userName);
        User userByEmail = userService.getUserByEmail(userName);

        if(userByEmail == null){
            model.addAttribute("userLoggedIn", null);
        }
        else{
            model.addAttribute("userLoggedIn", userByEmail);
        }


    }
}
