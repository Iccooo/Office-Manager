package com.main.office.Controller;

import com.main.office.Service.RegisterService;
import com.main.office.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping("/register")
    public String register(@ModelAttribute("user") User user) {
        boolean isUserRegistered = registerService.register(user);

        if (!isUserRegistered) {
            System.out.println("User already registered");
            return "redirect:/register";
        } else {
            return "redirect:/main/page";
        }
    }
}
