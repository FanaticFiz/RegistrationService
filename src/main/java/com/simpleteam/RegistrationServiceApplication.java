package com.simpleteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Registration service.
 *
 */
@SpringBootApplication
public class RegistrationServiceApplication {

    /**
     * Creates a new instance.
     */
    protected RegistrationServiceApplication() {
    }

    /**
     * Start here.
     * @param args Not use any parameters.
     */
    public static void main(final String[] args) {

        SpringApplication.run(RegistrationServiceApplication.class, args);
    }

}
