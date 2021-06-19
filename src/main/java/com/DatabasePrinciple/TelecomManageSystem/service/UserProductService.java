package com.DatabasePrinciple.TelecomManageSystem.service;

import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.model.UserProduct;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 * author: jason
 **/
public interface UserProductService {
    List<UserProduct> findUserProduct(Integer UserId)
            throws RuntimeException;

    int createUserProduct(UserProduct userProduct)
            throws RuntimeException;

    UserProduct getUserProduct(Integer upId)
            throws RuntimeException;

    int setState(Integer userProductId,Integer state) throws RuntimeException;

    int deleteUserProduct(Integer userProductId) throws RuntimeException;
}
