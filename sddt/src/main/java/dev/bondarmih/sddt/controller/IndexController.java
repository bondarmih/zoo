package dev.bondarmih.sddt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bondarm on 22.09.16.
 */
@Controller

public class IndexController {

    @RequestMapping("/home")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            request.getSession().invalidate();
            //authentication = null;
        }
        return "redirect:/app/login?logout";
    }

    @RequestMapping(value = "/admin")
    public String adminPage() {
        return "admin";
    }
}
