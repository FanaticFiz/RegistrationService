package com.simpleTeam.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Success controller.
 *
 * @author Fiz
 * @version 0.1
 */
@Controller
public class Success {
    /**
     * Get logger.
     */
    private final Logger log = Logger.getLogger(Success.class);

    /**
     * Catches GET request to '/success'.
     *
     * @return String for ViewResolver, for find specific view.
     */
    @RequestMapping(value = "/success")
    public final String successGet() {
        log.info("RequestMethod GET");

        return "success";
    }

}
