package com.scm.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication){

        // AuthenticationPrincipal principal = (AuthenticationPrincipal) authentication.getPrincipal();
        if(authentication instanceof OAuth2AuthenticationToken){
            var oauth2 = (OAuth2AuthenticationToken)authentication;
            var clientId = oauth2.getAuthorizedClientRegistrationId();
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String userName = "";
            if(clientId.equalsIgnoreCase("google")){
                userName = oauth2User.getAttribute("email").toString();
            }
            else{
                userName = oauth2User.getAttribute("email") !=null ? oauth2User.getAttribute("email").toString() : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            return userName;
        }
        else{
            return authentication.getName();
        }
    }

}
