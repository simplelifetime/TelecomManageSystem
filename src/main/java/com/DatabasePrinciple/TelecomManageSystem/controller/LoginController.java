package com.DatabasePrinciple.TelecomManageSystem.controller;

import com.DatabasePrinciple.TelecomManageSystem.enums.base.ResultCode;
import com.DatabasePrinciple.TelecomManageSystem.model.CustomService;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.model.base.Result;
import com.DatabasePrinciple.TelecomManageSystem.service.CustomServiceService;
import com.DatabasePrinciple.TelecomManageSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    CustomServiceService customServiceService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String userLoginPage() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView userLogin(@RequestParam(name = "Email") String email,
                           @RequestParam(name = "Password") String password,
                           @RequestParam(name = "Usertype") int usertype,
                           HttpSession session) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        if (usertype == 0) {
            User user = userService.login(email, password);
            if (user == null) {
                mv.addObject("msg","用户名或密码错误");
                mv.addObject("Code","Fail");
            } else {
                System.out.println(user);
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getFullname());
                mv.addObject("Code","Success");
                int id = user.getId();
                return new ModelAndView("redirect:/user");
            }
        }
        else if (usertype == 1) {
            CustomService customService = customServiceService.login(email, password);
            if (customService == null) {
                mv.addObject("msg","用户名或密码错误");
                mv.addObject("Code","Fail");
            } else {
                session.setAttribute("userId", customService.getId());
                session.setAttribute("userName", customService.getFullname());
                mv.addObject("Code","Success");
                int id = customService.getId();
                return new ModelAndView("redirect:/customService");
            }
        }
        return mv;
    }

    /**
     * 注册页
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    String userRegisterPage() {
        return "register";
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView userRegister(@RequestParam(name = "Fullname") String fullname,
                        @RequestParam(name = "Email") String email,
                        @RequestParam(name = "Password") String password,
                        @RequestParam(name = "rePassword") String repassword) {
        ModelAndView mv = new ModelAndView("register");
//        mv.addObject("Code","Fail");
        User user = userService.checkEmail(email);
        if(user!=null){
            mv.addObject("Code","Duplicate");
            return mv;
        }
        else{
            if(userService.register(fullname,password,email)){
                mv.addObject("Code","Success");
            }
            else{
                mv.addObject("Code","Fail");
            }
        }
        return mv;
    }

}
