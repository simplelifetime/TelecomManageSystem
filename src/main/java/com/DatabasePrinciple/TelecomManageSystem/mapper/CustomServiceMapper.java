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
public interface CustomServiceMapper {
    @Insert("INSERT INTO customservice(fullname,password,email,account) " +
            "VALUES (#{fullname},#{username},#{email},0);")
    @Options(useGeneratedKeys = true)
    int save(CustomService customService);

    @Select("SELECT * " +
            "FROM customservice " +
            "WHERE email = #{email} and password = #{password}")
    @ResultType(CustomService.class)
    CustomService login(CustomService customService);

    @Results(
            id = "customServiceList", value = {
            @Result(property = "fullname", column = "fullname"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password")
    }
    )
    @Select("SELECT * FROM customservice")
    List<CustomService> getAllCustomService();

    @Select("SELECT * FROM customservice WHERE id = #{id}")
    @ResultType(CustomService.class)
    CustomService getCustomService(Integer id);

    @Update("UPDATE customService " +
            "SET email = #{newEmail} " +
            "WHERE id = #{customServiceId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updateEmail(@Param("customServiceId") Integer customServiceId,@Param("newEmail") String newEmail);

    @Update("UPDATE customService " +
            "SET password = #{newPassword} " +
            "WHERE id = #{customServiceId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updatePassword(@Param("customServiceId") Integer customServiceId,@Param("newPassword") String newPassword);

    @Select("SELECT * " +
            "FROM customservice " +
            "WHERE email = #{email}")
    @ResultType(CustomService.class)
    CustomService checkEmail(CustomService customService);
}
