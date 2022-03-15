package com.example.betandwin.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String admin(Model model) {
        List<AUser> users = userService.findAllWithoutCurrentUser();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/role")
    public String editRole(@RequestParam Long id, Model model) {
        AUser user = userService.findById(id);
        boolean isAdmin = userService.isUserAnAdmin(user);
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", isAdmin);
        return "/admin/role";
    }

    @PostMapping("/role")
    public String editRole(@RequestParam boolean admin, AUser user) {
        userService.updateUserRole(user.getId(), admin);
        return "redirect:/admin";
    }
}
