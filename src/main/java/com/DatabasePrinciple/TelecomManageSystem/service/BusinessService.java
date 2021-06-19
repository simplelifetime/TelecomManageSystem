package com.DatabasePrinciple.TelecomManageSystem.service;

import com.DatabasePrinciple.TelecomManageSystem.model.Business;
import com.DatabasePrinciple.TelecomManageSystem.model.Service;

import java.util.List;

/**
 * description:
 * author: jason
 **/
public interface BusinessService {
    Boolean createBusiness(Integer userId, Integer customServiceId, Integer upId)
            throws RuntimeException;

    List<Business> getAllBusiness()
            throws RuntimeException;

    List<Business> getBusinessByCustomService(Integer cid)
            throws RuntimeException;

    Business getBusiness(Integer upId) throws RuntimeException;

    int setState(Integer businessId,Integer state) throws RuntimeException;

    int deleteBusiness(Integer businessId) throws RuntimeException;
}
