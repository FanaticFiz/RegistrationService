package com.simpleteam.controllers;

import com.simpleteam.entity.User;
import com.simpleteam.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Confirm controller.
 *
 * @author Fiz
 * @version 0.1
 */
@Controller
public class Confirm {
    /**
     * Get logger.
     */
    private final Logger log = Logger.getLogger(Confirm.class);

    /**
     * Use user repository.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Catches GET request to '/confirm'.
     *
     * @param code UUID
     * @return Redirect to other view.
     */
    @RequestMapping(value = "/confirm/{code}")
    public final String confirmGet(@PathVariable("code") final String code) {
        log.info("RequestMethod GET, code is: " + code);

        User user = userRepository.findByUuid(code);
        if (user == null) {
            log.warn("UUID not found in DB.");
            return "redirect:/error";
        } else {
            user.setConfirmed(true);
            userRepository.save(user);

            log.info("User found and activated");
            return "redirect:/success";
        }
    }

}
