package com.DatabasePrinciple.TelecomManageSystem.controller;

import com.DatabasePrinciple.TelecomManageSystem.model.*;
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
public class CustomServiceController {
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


    CustomService findCustomService(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        return customServiceService.getCustomService(userId);
    }

    @RequestMapping(value = "/customService")
    @ResponseBody
    ModelAndView Main(HttpServletRequest request) {
        CustomService customService = findCustomService(request);
        ModelAndView mv = new ModelAndView("/customService/index");
        mv.addObject("customService", customService);
        List<Business> businessList = businessService.getBusinessByCustomService(customService.getId());
        mv.addObject("businessList", businessList);
        mv.addObject("userSerivce", userService);
        mv.addObject("serviceService",serviceService);
        mv.addObject("userProductService",userProductService);
        return mv;
    }

    @RequestMapping(value = "/customService/orders")
    @ResponseBody
    ModelAndView orders(HttpServletRequest request){
        CustomService customService = findCustomService(request);
        ModelAndView mv = new ModelAndView("/customService/orders");
        mv.addObject("customService", customService);
        List<Business> businessList = businessService.getBusinessByCustomService(customService.getId());
        mv.addObject("businessList", businessList);
        mv.addObject("userSerivce", userService);
        mv.addObject("serviceService",serviceService);
        mv.addObject("userProductService",userProductService);
        return mv;
    }

    @RequestMapping(value = "/customService/information")
    @ResponseBody
    ModelAndView information(HttpServletRequest request) {
        CustomService customService = findCustomService(request);
        ModelAndView mv = new ModelAndView("/customService/information");
        mv.addObject("customService", customService);
        mv.addObject("status1",0);
        mv.addObject("status2",0);
        return mv;
    }

    @RequestMapping(value = "/customService/information", method = RequestMethod.POST)
    @ResponseBody
    ModelAndView changeInfo(@RequestParam(name = "newEmail",required = false) String newEmail,
                            @RequestParam(name = "oldPassword",required = false) String oldPassword,
                            @RequestParam(name = "newPassword",required = false) String newPassword,
                            @RequestParam(name = "rePassword",required = false) String rePassword,
                            HttpServletRequest request) {
        CustomService customService = findCustomService(request);
        ModelAndView mv = new ModelAndView("/customService/information");
        mv.addObject("customService", customService);
        mv.addObject("msg","请填写相关信息");
        mv.addObject("status1",0);
        mv.addObject("status2",0);
        if(!(newEmail==null)&&!newEmail.equals("")){
            CustomService customService1 = customServiceService.checkEmail(newEmail);
            if(customService1!=null){
                mv.addObject("msg","邮箱已被使用");
            }
            else{
                customServiceService.updateEmail(customService.getId(),newEmail);
                mv.addObject("msg","邮箱修改完成");
            }
            mv.addObject("status1",1);
        }
        else if(!(oldPassword==null)&&!oldPassword.equals("")){
            if(newPassword.equals("")||rePassword.equals("")){
                mv.addObject("msg","请完整填写修改密码信息");
            }
            else{
                if(!customService.getPassword().equals(oldPassword)){
                    mv.addObject("msg","原密码错误");
                }
                else if(!newPassword.equals(rePassword)){
                    mv.addObject("msg","两次输入密码不一致");
                }
                else {
                    customServiceService.updatePassword(customService.getId(), newPassword);
                    mv.addObject("msg", "密码修改完成");
                }
            }
            mv.addObject("status2",1);
        }
        return mv;
    }

    @RequestMapping(value = "/customService/order/{businessId}")
    @ResponseBody
    ModelAndView order(HttpServletRequest request,
                       @PathVariable("businessId") Integer businessId) {
        CustomService customService = findCustomService(request);
        UserProduct userProduct = userProductService.getUserProduct(businessService.getBusiness(businessId).getUpid());
        Service service = serviceService.findService(userProduct.getSid());
        User user = userService.findUser(userProduct.getUid());
        ModelAndView mv = new ModelAndView("/customService/order");
        mv.addObject("businessId",businessId);
        mv.addObject("user", user);
        mv.addObject("service", service);
        mv.addObject("customService",customService);
        mv.addObject("realtime",new Timestamp(new Date().getTime()));
        mv.addObject("time",userProduct.getS_time());
        mv.addObject("msg","订单待确认");
        return mv;
    }

    @RequestMapping(value = "/user/order/{businessId}/confirm")
    @ResponseBody
    ModelAndView confirm(HttpServletRequest request,
                         @PathVariable("businessId") Integer businessId) {
        CustomService customService = findCustomService(request);
        UserProduct userProduct = userProductService.getUserProduct(businessService.getBusiness(businessId).getUpid());
        Service service = serviceService.findService(userProduct.getSid());
        User user = userService.findUser(userProduct.getUid());
        ModelAndView mv = new ModelAndView("/customService/order");
        businessService.setState(businessId,1);
        userProductService.setState(userProduct.getId(),1);
        mv.addObject("user", user);
        mv.addObject("service", service);
        mv.addObject("customService",customService);
        mv.addObject("realtime",new Timestamp(new Date().getTime()));
        mv.addObject("time",userProduct.getS_time());
        mv.addObject("msg","订单已创建");
        return mv;
    }

    @RequestMapping(value = "/user/order/{businessId}/reject")
    @ResponseBody
    ModelAndView reject(HttpServletRequest request,
                        @PathVariable("businessId") Integer businessId) {
        CustomService customService = findCustomService(request);
        UserProduct userProduct = userProductService.getUserProduct(businessService.getBusiness(businessId).getUpid());
        Service service = serviceService.findService(userProduct.getSid());
        User user = userService.findUser(userProduct.getUid());
        ModelAndView mv = new ModelAndView("/customService/order");
        mv.addObject("user", user);
        mv.addObject("service", service);
        mv.addObject("customService",customService);
        mv.addObject("time",new Timestamp(new Date().getTime()));
        mv.addObject("msg","订单取消");
        userService.updateAccount(user,user.getAccount()+service.getPrice());
        businessService.deleteBusiness(businessId);
        userProductService.deleteUserProduct(userProduct.getId());
        return mv;
    }
}
