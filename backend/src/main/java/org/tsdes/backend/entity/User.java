package org.tsdes.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name="USER")
public class User {

    @Id
    @NotBlank
    private String username;

    private String firstname;

    private String lastname;

    @NotNull
    @Column(unique=true)
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Boolean enabled;
/*
    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;*/


    public User() {
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


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
