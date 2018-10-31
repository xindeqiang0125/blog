package com.xdq.blog.manager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Value("${blog.login.username}")
    private String username;
    @Value("${blog.login.password}")
    private String password;

    @RequestMapping("/login")
    public String login(String username,String password,HttpSession session){
        if (this.username.equals(username)&&this.password.equals(password)){
            session.setAttribute("user",username);
            return "redirect:/admin";
        }else {
            return "redirect:/";
        }
    }

    @RequestMapping("/logout")
    public String login(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
