package com.scm.controllers;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    private Logger logger = LoggerFactory.getLogger(ContactController.class);
    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;
//    add contact page handler
    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
        return "user/add_contact";
    }
    @RequestMapping(value = "/process-contact",method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult bindingResult, Authentication authentication, HttpSession httpSession){
//        processing form data
        if(bindingResult.hasErrors()){
            httpSession.setAttribute("message",
                    Message.builder()
                            .content("Fill all the details correctly")
                            .type(MessageType.red).build());
            return "user/add_contact";
        }

        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        logger.info(contactForm.getProfileImage().getOriginalFilename());
        String fileURL = imageService.uploadImage(contactForm.getProfileImage());

        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setEmail(contactForm.getEmail());
        contact.setFavourite(contactForm.isFavourite());
        contact.setContact(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setLinnkedIn(contactForm.getLinkedIn());
        contact.setPicture(fileURL);
        contact.setUser(user);
        contactService.save(contact);

        httpSession.setAttribute("message",
                Message.builder()
                        .content("Your contact is added successfully")
                        .type(MessageType.green).build());
        return "redirect:/user/contacts/add";

    }
}
