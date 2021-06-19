package com.DatabasePrinciple.TelecomManageSystem.service;

import com.DatabasePrinciple.TelecomManageSystem.model.CustomService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * author:
 **/
public interface CustomServiceService {
    boolean register(String fullname, String password, String email)
            throws RuntimeException;

    CustomService login(String email, String password)
            throws RuntimeException;

    CustomService getCustomService(Integer Id)
            throws RuntimeException;

    List<CustomService> getAllCustomService()
            throws RuntimeException;

    int updateEmail(@Param("customServiceId") Integer customServiceId, @Param("newEmail") String newEmail)
            throws RuntimeException;

    int updatePassword(@Param("customServiceId") Integer customServiceId, @Param("newPassword") String newPassword)
            throws RuntimeException;

    CustomService checkEmail(String newEmail)
            throws RuntimeException;
}
