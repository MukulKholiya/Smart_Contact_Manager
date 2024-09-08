package com.scm.config;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;

import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Autowired
    private UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("AuthenticationSuccessHandler");

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String clientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

        var oauthUser = (DefaultOAuth2User)authentication.getPrincipal();

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
//        user.setPassword("Default");
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setRoleList(List.of("USER"));


        if(clientRegistrationId.equalsIgnoreCase("google")){
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePic(oauthUser.getAttribute("picture").toString());
            user.setUserName(oauthUser.getAttribute("name").toString());
            user.setProviderUserId(oauthUser.getName());
            user.setProvider(Providers.GOOGLE);
            user.setAbout("This account is created using Google");



        } else if (clientRegistrationId.equalsIgnoreCase("github")) {
            String email = oauthUser.getAttribute("email")!=null ? oauthUser.getAttribute("email").toString()
                    : oauthUser.getAttribute("login").toString()+"@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            String providerUserId = oauthUser.getName();

            user.setEmail(email);
            user.setProfilePic(picture);
            user.setUserName(name);
            user.setProvider(Providers.GITHUB);
            user.setProviderUserId(providerUserId);
            user.setAbout("This account is created using Github");


        }
//        Saving Data into Database
        /*DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
//        logger.info(user.getName());
//
//        user.getAttributes().forEach((key,value)->{
//            logger.info("{} ==> {}",key,value);
//
//        });
        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        User googleUser = new User();
        googleUser.setUserName(name);
        googleUser.setEmail(email);
        googleUser.setProfilePic(picture);
        googleUser.setPassword("Default");
        googleUser.setUserId(UUID.randomUUID().toString());
        googleUser.setProvider(Providers.GOOGLE);
        googleUser.setEnabled(true);
        googleUser.setEmailVerified(true);
        googleUser.setProviderUserId(user.getName());
        googleUser.setRoleList(List.of("USER"));

        */User savedUser= userRepository.findByEmail(user.getEmail()).orElse(null);
        if(savedUser==null){
            userRepository.save(user);
            logger.info("User Saved"+ user.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request,response,"/user/profile");

    }
}
