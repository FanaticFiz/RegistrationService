package com.simpleteam.entity;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

/**
 * User.
 */
public class User {
    /**
     * Id.
     */
    private int id;

    /**
     * Email.
     */
    @Email
    private String email;

    /**
     * Password.
     */
    @Pattern(regexp = "(?=(.*\\d){2})(?=.*!)", message = "must contain at least 2 digits and one \"!\" symbol")
    private String password;

    /**
     * Confirmed - then user confirm yours newEmail.
     */
    private boolean isConfirmed;

    /**
     * Getter.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     * @param newId int
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     * Getter.
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter.
     * @param newEmail String
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * Getter.
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter.
     * @param newPassword String
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * Getter.
     * @return bool
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Setter.
     * @param newConfirmed bool
     */
    public void setConfirmed(final boolean newConfirmed) {
        isConfirmed = newConfirmed;
    }
}
