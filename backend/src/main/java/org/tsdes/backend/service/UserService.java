package org.tsdes.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsdes.backend.entity.User;

import javax.persistence.EntityManager;
import java.util.Collections;

/**
 * Created by arcuri82 on 13-Dec-17.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private EntityManager em;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean createUser(String username, String firstname, String lastname, String email, String password) {

        String hashedPassword = passwordEncoder.encode(password);

        if (em.find(User.class, username) != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setEnabled(true);

        em.persist(user);

        return true;
    }
    public String creatUserWithUser(User user){
        em.persist(user);
        return user.getUsername();
    }
    public User getUserWithId(String id){
        User user = em.find(User.class, id);
        if(user == null){
            throw new IllegalArgumentException("User with id "+id+" does not exist");
        }
        return user;
    }
}
