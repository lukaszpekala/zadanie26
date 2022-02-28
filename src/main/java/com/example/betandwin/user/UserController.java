package com.example.betandwin.user;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView register(User user) {
        String username = user.getUsername();
        String rawPassword = user.getPassword();
        userService.registerUser(username, rawPassword);
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("register", true);
        return modelAndView;
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(required = false) String error,
                            Model model) {
        boolean showErrorMessage = error != null;
        model.addAttribute("showErrorMessage", showErrorMessage);
        return "login";
    }

    @GetMapping("/profile")
    public String edit(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.findByUsername(name);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile")
    public ModelAndView edit(@RequestParam(required = false) String newPassword, User user) {
        boolean updated = userService.updateProfile(user, newPassword);
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("updated", updated);
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
