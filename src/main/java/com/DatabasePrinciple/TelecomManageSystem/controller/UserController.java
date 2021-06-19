package com.DatabasePrinciple.TelecomManageSystem.controller;

import com.DatabasePrinciple.TelecomManageSystem.model.CustomService;
import com.DatabasePrinciple.TelecomManageSystem.model.Service;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.model.UserProduct;
import com.DatabasePrinciple.TelecomManageSystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * description:
 * author: jason
 **/

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ServiceService serviceService;
    @Autowired
    UserProductService userProductService;
    @Autowired
    CustomServiceService customServiceService;
    @Autowired
    BusinessService businessService;

    User findUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        return userService.findUser(userId);
    }

    List<UserProduct> findUserProduct(User user) {
        return userProductService.findUserProduct(user.getId());
    }

    @RequestMapping(value = "/user")
    @ResponseBody
    ModelAndView Main(HttpServletRequest request) {
        User user = findUser(request);
        List<UserProduct> userProductList = findUserProduct(user);
        ModelAndView mv = new ModelAndView("/user/index");
        mv.addObject("user", user);
        mv.addObject("userProductList", userProductList);
        mv.addObject("serviceSerivce", serviceService);
        return mv;
    }

    @RequestMapping(value = "/user/products")
    @ResponseBody
    ModelAndView products(HttpServletRequest request) {
        User user = findUser(request);
        ModelAndView mv = new ModelAndView("/user/products");
        mv.addObject("userName", user.getFullname());
        List<Service> serviceList = serviceService.getAllService();
        mv.addObject("serviceList", serviceList);
        return mv;
    }

    @RequestMapping(value = "/user/deals")
    @ResponseBody
    ModelAndView deals(HttpServletRequest request) {
        User user = findUser(request);
        ModelAndView mv = new ModelAndView("/user/deals");
        mv.addObject("userName", user.getFullname());
        return mv;
    }

    @RequestMapping(value = "/user/information")
    @ResponseBody
    ModelAndView information(HttpServletRequest request) {
        User user = findUser(request);
        ModelAndView mv = new ModelAndView("/user/information");
        mv.addObject("user", user);
        mv.addObject("status1",0);
        mv.addObject("status2",0);
        return mv;
    }

    @RequestMapping(value = "/user/order/{serviceId}")
    @ResponseBody
    ModelAndView order(HttpServletRequest request,
                       @PathVariable("serviceId") Integer serviceId) {
        User user = findUser(request);
        Service service = serviceService.findService(serviceId);
        ModelAndView mv = new ModelAndView("/user/order");
        mv.addObject("user", user);
        mv.addObject("service", service);
        List<CustomService> customServiceList = customServiceService.getAllCustomService();
        int len = customServiceList.size();
        int random = (int)(Math.random()*len);
        mv.addObject("customService",customServiceList.get(random));
        mv.addObject("time",new Timestamp(new Date().getTime()));
        mv.addObject("msg","订单待创建");
        return mv;
    }

    @RequestMapping(value = "/user/order/{serviceId}/{customServiceId}/confirm")
    @ResponseBody
    ModelAndView confirm(HttpServletRequest request,
                       @PathVariable("serviceId") Integer serviceId,
                       @PathVariable("customServiceId") Integer customServiceId) {
        User user = findUser(request);
        Service service = serviceService.findService(serviceId);
        ModelAndView mv = new ModelAndView("/user/order");
        mv.addObject("user", user);
        mv.addObject("service", service);
        CustomService customService = customServiceService.getCustomService(customServiceId);
        Timestamp s_time = new Timestamp(new Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(s_time);
        c.add(Calendar.MONTH, 1);
        Timestamp e_time = new Timestamp(c.getTimeInMillis());
        UserProduct userProduct = new UserProduct(s_time,e_time,0,user.getId(),serviceId,0);
        userProductService.createUserProduct(userProduct);
        businessService.createBusiness(user.getId(),customServiceId,userProduct.getId());
        userService.updateAccount(user,user.getAccount()-service.getPrice());
        mv.addObject("customService",customService);
        mv.addObject("time",new Timestamp(new Date().getTime()));
        mv.addObject("msg","订单已创建");
        return mv;
    }

    @RequestMapping(value = "/user/order/{serviceId}/{customServiceId}/reject")
    @ResponseBody
    ModelAndView reject(HttpServletRequest request,
                       @PathVariable("serviceId") Integer serviceId,
                       @PathVariable("customServiceId") Integer customServiceId) {
        User user = findUser(request);
        Service service = serviceService.findService(serviceId);
        ModelAndView mv = new ModelAndView("/user/order");
        mv.addObject("user", user);
        mv.addObject("service", service);
        CustomService customService = customServiceService.getCustomService(customServiceId);
        mv.addObject("customService",customService);
        mv.addObject("time",new Timestamp(new Date().getTime()));
        mv.addObject("msg","订单取消");
        return mv;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView charge(@RequestParam(name = "charge") String charge,
                        HttpServletRequest request) {
        User user = findUser(request);
        List<UserProduct> userProductList = findUserProduct(user);
        if (!charge.equals(""))
            userService.updateAccount(user, (Integer.parseInt(charge) + user.getAccount()));
        return new ModelAndView("redirect:/user");
    }

    @RequestMapping(value = "/user/information", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView changeInfo(@RequestParam(name = "newEmail",required = false) String newEmail,
                            @RequestParam(name = "oldPassword",required = false) String oldPassword,
                            @RequestParam(name = "newPassword",required = false) String newPassword,
                            @RequestParam(name = "rePassword",required = false) String rePassword,
                            HttpServletRequest request) {
        User user = findUser(request);
        ModelAndView mv = new ModelAndView("/user/information");
        mv.addObject("msg","请填写相关信息");
        mv.addObject("status1",0);
        mv.addObject("status2",0);
        if(!(newEmail==null)&&!newEmail.equals("")){
            User user1 = userService.checkEmail(newEmail);
            if(user1!=null){
                mv.addObject("msg","邮箱已被使用");
            }
            else{
                userService.updateEmail(user,newEmail);
                mv.addObject("msg","邮箱修改完成");
            }
            mv.addObject("status1",1);
        }
        else if(!(oldPassword==null)&&!oldPassword.equals("")){
            if(newPassword.equals("")||rePassword.equals("")){
                mv.addObject("msg","请完整填写修改密码信息");
            }
            else{
                if(!user.getPassword().equals(oldPassword)){
                    mv.addObject("msg","原密码错误");
                }
                else if(!newPassword.equals(rePassword)){
                    mv.addObject("msg","两次输入密码不一致");
                }
                else {
                    userService.updatePassword(user, newPassword);
                    mv.addObject("msg", "密码修改完成");
                }
            }
            mv.addObject("status2",1);
        }
        return mv;
    }
}
