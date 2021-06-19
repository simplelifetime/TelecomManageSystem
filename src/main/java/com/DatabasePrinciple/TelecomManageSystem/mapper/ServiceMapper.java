package com.DatabasePrinciple.TelecomManageSystem.mapper;

import com.DatabasePrinciple.TelecomManageSystem.model.CustomService;
import com.DatabasePrinciple.TelecomManageSystem.model.Service;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * description:
 * author: jason
 **/
@Mapper
public interface ServiceMapper {
    @Results(
            id = "serviceList", value = {
            @Result(property = "fullname", column = "fullname"),
            @Result(property = "price", column = "price"),
            @Result(property = "description", column = "description")
    }
    )
    @Select("SELECT * FROM service")
    List<Service> getAllService();

    @Insert("INSERT INTO service(fullname,price,descrition) " +
            "VALUES (#{fullname},#{price},#{description});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(Service service);

    @Select("SELECT * " +
            "FROM service " +
            "WHERE id = #{serviceId}")
    @ResultType(Service.class)
    Service findService(Integer serviceId);
}
