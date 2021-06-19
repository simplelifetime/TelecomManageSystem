package com.DatabasePrinciple.TelecomManageSystem.service.impl;

import com.DatabasePrinciple.TelecomManageSystem.mapper.BusinessMapper;
import com.DatabasePrinciple.TelecomManageSystem.mapper.CustomServiceMapper;
import com.DatabasePrinciple.TelecomManageSystem.model.Business;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 * author: jason
 **/
@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    BusinessMapper businessMapper;

    public Boolean createBusiness(Integer userId, Integer customServiceId, Integer upId) {
        return businessMapper.createBusiness(userId, customServiceId, upId) > 0;
    }

    public List<Business> getAllBusiness()
            throws RuntimeException {
        return businessMapper.getAllBusiness();
    }

    public List<Business> getBusinessByCustomService(Integer cid)
            throws RuntimeException {
        return businessMapper.getBusinessByCustomService(cid);
    }

    public Business getBusiness(Integer upId) throws RuntimeException {
        return businessMapper.getBusiness(upId);
    }

    public int setState(Integer businessId,Integer state) throws RuntimeException{
        return businessMapper.setState(businessId,state);
    }

    public int deleteBusiness(Integer businessId) throws RuntimeException{
        return businessMapper.deleteBusiness(businessId);
    }
}
