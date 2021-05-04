package org.tsdes.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.tsdes.backend.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * File adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/main/java/org/tsdes/intro/exercises/quizgame/frontend/controller/SignUpController.java
 */
@Named
@RequestScoped
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;


    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String email;

    public String signUpUser(){

        boolean registered = false;
        try {
            registered = userService.createUser(username, firstname, lastname, email,  password);
        }catch (Exception e){
            //nothing to do
        }

        if(registered){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    password,
                    userDetails.getAuthorities());

            authenticationManager.authenticate(token);

            if (token.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(token);
            }

            return "/index.jsf?faces-redirect=true";
        } else {
            return "/signup.jsf?faces-redirect=true&error=true";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname(){ return firstname;}

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname(){ return lastname; }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail(){ return email; }
    public void setEmail(String email) {
        this.email = email;
    }
}
