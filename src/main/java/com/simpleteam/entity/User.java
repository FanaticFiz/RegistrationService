package com.simpleteam.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

public class User {

    private int id;

    @Email
    private String email;

    @Pattern(regexp = "(?=(.*\\d){2})(?=.*!)", message = "Password must contain at least 2 digits and one \"!\" symbol")
    private String password;

    private boolean isConfirmed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}
