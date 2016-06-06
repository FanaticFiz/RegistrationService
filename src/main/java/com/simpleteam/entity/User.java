package com.simpleteam.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * User entity.
 *
 * @version 0.2
 */
@Entity
@Table(name = "users")
public class User {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Email.
     */
    @Email
    @Column(name = "email", unique = true)
    private String email;

    /**
     * Password.
     */
    @Pattern(regexp = "(?=(.*\\d){2})(.*)(!+)",
            message = "Password must contain at least 2 digits and one \"!\" symbol")
    @Column(name = "password", columnDefinition = "character varying(30)")
    private String password;

    /**
     * Confirmed - then user confirm yours newEmail.
     */
    @Column(name = "is_confirmed")
    private boolean isConfirmed;

    /**
     * UUID.
     */
    @Column
    private String uuid;

    /**
     * Getter.
     *
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter.
     *
     * @param newId int
     */
    public void setId(final int newId) {
        this.id = newId;
    }

    /**
     * Getter.
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter.
     *
     * @param newEmail String
     */
    public void setEmail(final String newEmail) {
        this.email = newEmail;
    }

    /**
     * Getter.
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter.
     *
     * @param newPassword String
     */
    public void setPassword(final String newPassword) {
        this.password = newPassword;
    }

    /**
     * Getter.
     *
     * @return bool
     */
    public boolean isConfirmed() {
        return isConfirmed;
    }

    /**
     * Setter.
     *
     * @param newConfirmed bool
     */
    public void setConfirmed(final boolean newConfirmed) {
        isConfirmed = newConfirmed;
    }

    /**
     * Getter.
     *
     * @return String UUID.
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Setter.
     *
     * @param newUUID String.
     */
    public void setUuid(final String newUUID) {
        this.uuid = newUUID;
    }

}
