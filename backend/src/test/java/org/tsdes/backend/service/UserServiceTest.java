package org.tsdes.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase {

    @Autowired
    UserService userService;

    @Test
    public void testGetUserWithWrongId() {

        assertThrows(IllegalArgumentException.class, () -> userService.getUserWithId("Stein") );

    }

    @Test
    public void testAlreadyCreatedUser(){
        userService.createUser("Hei", "h", "ei", "hei@mail.com", "passord");

        assertFalse(userService.createUser("Hei", "h", "ei", "hei@mail.com", "passord"));

    }

    @Test
    public void testCreateUserWithUser(){
        User user = new User();
        user.setEmail("mail@mail.com");
        user.setUsername("username");
        user.setPassword("password");
        user.setLastname("name");
        user.setFirstname("first");
        user.setEnabled(true);

        String username = userService.creatUserWithUser(user);
        User user1 = userService.getUserWithId(username);

        assertEquals(username, user.getUsername());
        assertEquals(user1.getEmail(), user.getEmail());
        assertEquals(user1.getFirstname(), user.getFirstname());
        assertEquals(user1.getLastname(), user.getLastname());
        assertEquals(user1.getPassword(), user.getPassword());
        assertEquals(user1.getEnabled(), user.getEnabled());
    }
}
