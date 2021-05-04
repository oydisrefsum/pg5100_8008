package org.tsdes.frontend.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * file copied from https://github.com/arcuri82/testing_security_development_enterprise_systems/blob/master/intro/exercise-solutions/quiz-game/part-11/frontend/src/main/java/org/tsdes/intro/exercises/quizgame/frontend/controller/UserInfoController.java
 */
@Named
@RequestScoped
public class UserInfoController {


    public String getUserName(){
        return ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
