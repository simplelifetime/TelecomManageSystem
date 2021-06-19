package com.DatabasePrinciple.TelecomManageSystem.service;

import com.DatabasePrinciple.TelecomManageSystem.model.User;
import org.springframework.stereotype.Service;


public interface UserService {
    boolean register(String fullname, String password, String email)
            throws RuntimeException;

    User login(String email, String password)
            throws RuntimeException;

    User checkEmail(String email)
            throws RuntimeException;

    User findUser(Integer UserId)
            throws RuntimeException;

    boolean updateAccount(User user,Float newValue)
            throws RuntimeException;

    boolean updateEmail(User user,String newEmail)
            throws RuntimeException;

    boolean updatePassword(User user,String newPassword)
            throws RuntimeException;
}
