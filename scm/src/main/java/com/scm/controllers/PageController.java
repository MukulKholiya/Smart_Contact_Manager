package com.scm.controllers;

import com.scm.entities.User;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;

// import ch.qos.logback.core.model.Model;

@Controller
public class PageController {

    @Autowired
    UserService userService;
    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
    
    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        return "services";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);

        return "register";
    }

    // registering form
    @RequestMapping(value = "/do-register",method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult,HttpSession session){
        System.out.println("Process Registration");
        System.out.println(userForm);
        if(bindingResult.hasErrors()){
            return "register";
        }

        User user = new User();
        user.setUserName(userForm.getName());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getContact());
        user.setProfilePic("/images/profilepic.png");
        user.setAbout(userForm.getAbout());

        User savedUser = userService.saveUser(user);
        System.out.println(savedUser.toString());

        Message message = new Message("Registration is successful!",MessageType.green);
        session.setAttribute("message", message);



        return "redirect:/login";
    }
}
