package com.main.office.Controller;

import com.main.office.Service.LoginService;
import com.main.office.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
public class LoginController {
    @Autowired
    private LoginService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new User());
        return mav;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user ) {

        User oauthUser = userService.login(user.getUsername(),user.getEmail(), user.getPassword());

        System.out.print(oauthUser);
        if(Objects.nonNull(oauthUser))
        {
            return "redirect:/main/page";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
        return "redirect:/";
    }
}
