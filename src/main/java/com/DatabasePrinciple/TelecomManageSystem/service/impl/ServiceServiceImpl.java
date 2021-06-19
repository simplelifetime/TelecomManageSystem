package com.DatabasePrinciple.TelecomManageSystem.service.impl;

import com.DatabasePrinciple.TelecomManageSystem.mapper.ServiceMapper;
import com.DatabasePrinciple.TelecomManageSystem.model.Service;
import com.DatabasePrinciple.TelecomManageSystem.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 * author: jason
 **/

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public List<Service> getAllService() throws RuntimeException {
        return serviceMapper.getAllService();
    }

    public Service findService(Integer serviceId){
        return serviceMapper.findService(serviceId);
    }
}
