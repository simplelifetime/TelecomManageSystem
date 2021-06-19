package com.DatabasePrinciple.TelecomManageSystem.service.impl;

import com.DatabasePrinciple.TelecomManageSystem.mapper.CustomServiceMapper;
import com.DatabasePrinciple.TelecomManageSystem.mapper.UserMapper;
import com.DatabasePrinciple.TelecomManageSystem.model.CustomService;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.service.CustomServiceService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description: 客服功能实现
 * author: jason
 **/
@Service
@Transactional
public class CustomServiceServiceImpl implements CustomServiceService {

    @Autowired
    CustomServiceMapper customServiceMapper;

    @Override
    public boolean register(String fullname, String password, String email) throws RuntimeException {
        CustomService customService = new CustomService(fullname,password,email);
        return customServiceMapper.save(customService) > 0;
    }

    @Override
    public CustomService login(String email, String password) throws RuntimeException {
        return customServiceMapper.login(new CustomService(password,email));
    }

    @Override
    public List<CustomService> getAllCustomService() throws RuntimeException {
        return customServiceMapper.getAllCustomService();
    }

    @Override
    public CustomService getCustomService(Integer id){
        return customServiceMapper.getCustomService(id);
    }

    @Override
    public int updateEmail(@Param("customServiceId") Integer customServiceId, @Param("newEmail") String newEmail)
            throws RuntimeException{
        return customServiceMapper.updateEmail(customServiceId,newEmail);
    }

    @Override
    public int updatePassword(@Param("customServiceId") Integer customServiceId, @Param("newPassword") String newPassword)
            throws RuntimeException{
        return customServiceMapper.updatePassword(customServiceId,newPassword);
    }

    @Override
    public CustomService checkEmail(String newEmail)
        throws RuntimeException{
        return customServiceMapper.checkEmail(new CustomService(newEmail));
    }
}
