package cz.muni.fi.pa165.dominatingspecies.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Provides feedback to user in case he failed to log in or has logged out
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully");
        }
        model.setViewName("login");
        return model;
    }

}
