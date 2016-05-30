package com.simpleteam.controllers;

import com.simpleteam.utils.SimpleMailSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Registration controller.
 * Also redirect '/' to photo...
 *
 * @author Fiz
 * @version 0.1
 */
@Controller
public class Registration {
    /**
     * Get logger.
     */
    private final Logger log = Logger.getLogger(Registration.class);

    @Autowired
    SimpleMailSender mailSender;

    /**
     * Just redirect to '/registration'.
     *
     * @return 'registration' template
     */
    @RequestMapping("/")
    public final String home() {
        log.info("redirect to '/registration' ");

        return "redirect:/registration";
    }

    /**
     * Catches GET request to '/registration'.
     *
     * @param model Map for add attributes
     * @return String for ViewResolver, for find specific view.
     */
    @RequestMapping(value = "/registration")
    public final String registration(final Model model) {
        log.info("RequestMethod GET");

        model.addAttribute("successRegistered", 0);
        return "registration";
    }

    /**
     * Catches POST request to '/registration'.
     *
     * @param model Map for add attributes
     * @param email email
     * @param pass password
     * @param redirectAttr for send attribute to another page
     * @return String for ViewResolver, for find specific view.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public final String photoHandler(final Model model, final RedirectAttributes redirectAttr,
                                     @RequestParam("email") final String email,
                                     @RequestParam("password") final String pass) {
        log.info("RequestMethod POST. Email: " + email);
        log.info("Pass: " + pass);

        mailSender.send(email,
                "The best subject!",
                "You have successfully create account on our site. Your password: " + pass);

        model.addAttribute("successRegistered", 1);
        return "registration";
    }

}
