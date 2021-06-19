package com.DatabasePrinciple.TelecomManageSystem.service.impl;

import com.DatabasePrinciple.TelecomManageSystem.mapper.UserProductMapper;
import com.DatabasePrinciple.TelecomManageSystem.model.UserProduct;
import com.DatabasePrinciple.TelecomManageSystem.service.UserProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 * author: jason
 **/

@Service
@Transactional
public class UserProductServiceImpl implements UserProductService {
    @Autowired
    UserProductMapper userProductMapper;

    public List<UserProduct> findUserProduct(Integer UserId) {
        return userProductMapper.findUserProduct(UserId);
    }

    public int createUserProduct(UserProduct userProduct) {
        return userProductMapper.createUserProduct(userProduct);
    }

    public UserProduct getUserProduct(Integer upId)
            throws RuntimeException{
        return userProductMapper.getUserProduct(upId);
    }

    public int setState(Integer userProductId,Integer state) throws RuntimeException{
        return userProductMapper.setState(userProductId,state);
    }

    public int deleteUserProduct(Integer userProductId) throws RuntimeException{
        return userProductMapper.deleteUserProduct(userProductId);
    }
}
