package com.beer.beer_forecast.model;

import jakarta.persistence.*;

@Entity
@Table(name="employees")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

    private String name;
    private String email;
    private String password;
    private Boolean admin;

    public Login() {
    }

    public Login(String name, String email, String password, Boolean admin) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }
    public Login(String email, String password) {
    this.email = email;
    this.password = password;
}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
