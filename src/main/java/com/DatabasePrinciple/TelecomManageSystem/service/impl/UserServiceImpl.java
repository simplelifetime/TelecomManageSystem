package com.DatabasePrinciple.TelecomManageSystem.service.impl;

import com.DatabasePrinciple.TelecomManageSystem.mapper.UserMapper;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean register(String fullname, String password, String email) throws RuntimeException {
        User user = new User(fullname, password, email, 0);
        return userMapper.save(user) > 0;
    }

    @Override
    public User login(String email, String password) throws RuntimeException {
        return userMapper.login(new User(password, email));
    }

    @Override
    public User checkEmail(String email) throws RuntimeException {
        return userMapper.checkEmail(new User(email));
    }

    public User findUser(Integer userId) throws RuntimeException {
        return userMapper.findUser(new User(userId));
    }

    public boolean updateAccount(User user, Float newValue) throws RuntimeException {
        return userMapper.updateAccount(user.getId(), newValue) > 0;
    }

    public boolean updateEmail(User user, String newEmail)
            throws RuntimeException {
        return userMapper.updateEmail(user.getId(),newEmail) > 0;
    }

    public boolean updatePassword(User user, String newPassword)
            throws RuntimeException{
        return userMapper.updatePassword(user.getId(),newPassword) > 0;
    }
}
