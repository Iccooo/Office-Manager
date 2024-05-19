package com.main.office.Controller;

import com.main.office.Service.RegisterService;
import com.main.office.Мodel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        boolean isUserRegistered = registerService.register(user);

        if (!isUserRegistered) {
            System.out.println("User already registered");
            return "register";
        } else {
            return "redirect:/main/page";
        }
    }
}
