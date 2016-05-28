package com.simpleTeam.controllers;

import org.apache.log4j.Logger;
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
     * Catches GET request to '/confirm'.
     *
     * @param code UUID
     * @return Redirect to other view.
     */
    @RequestMapping(value = "/confirm/{code}")
    public final String confirmGet(@PathVariable("code") final String code) {
        log.info("RequestMethod GET, code is: " + code);

        return "redirect:/success";
    }

}
