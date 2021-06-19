package com.DatabasePrinciple.TelecomManageSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @RequestMapping("/")
    String index() {
        return "redirect:login";
    }

    @RequestMapping("/ex")
    String error() {
        throw new RuntimeException("出错");
    }
}
