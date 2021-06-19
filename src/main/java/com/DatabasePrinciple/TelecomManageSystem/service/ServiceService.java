package com.DatabasePrinciple.TelecomManageSystem.service;

import com.DatabasePrinciple.TelecomManageSystem.model.Service;

import java.util.List;

/**
 * description:
 * author: jason
 **/
public interface ServiceService {
    List<Service> getAllService()
        throws RuntimeException;

    Service findService(Integer serviceId)
        throws RuntimeException;
}
