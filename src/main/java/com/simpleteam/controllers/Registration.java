package com.simpleteam.controllers;

import com.simpleteam.entity.User;
import com.simpleteam.jms.MessageSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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

    /**
     * Use message sender.
     */
    @Autowired
    private MessageSender simpleMessageSender;

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
     * @param user Entity User
     * @return String for ViewResolver, for find specific view.
     */
    @RequestMapping(value = "/registration")
    public final String registration(final Model model, final User user) {
        log.info("RequestMethod GET");

        model.addAttribute("successRegistered", 0);
        return "registration";
    }

    /**
     * Catches POST request to '/registration'.
     *
     * @param model        Map for add attributes
     * @param user         User
     * @param redirectAttr for send attribute to another page
     * @param bindingResult  BindingResult
     * @return String for ViewResolver, for find specific view.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public final String photoHandler(final Model model, final RedirectAttributes redirectAttr,
                                     @Valid final User user, final BindingResult bindingResult) {
        log.info(String.format("RequestMethod POST. Email: %s Password: %s", user.getEmail(), user.getPassword()));

        if (bindingResult.hasErrors()) {
            log.info("Validation failed!");
            model.addAttribute("errorPass", bindingResult.getAllErrors().get(0).getDefaultMessage());
            model.addAttribute("successRegistered", 0);
            return "registration";
        }

        simpleMessageSender.send(String.format("You have successfully create account with eMail: %s on our site."
                + " Your password: %s", user.getEmail(), user.getPassword()));

        model.addAttribute("successRegistered", 1);
        return "registration";
    }

}
