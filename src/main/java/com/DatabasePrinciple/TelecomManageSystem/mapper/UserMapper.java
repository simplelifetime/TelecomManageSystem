package com.DatabasePrinciple.TelecomManageSystem.mapper;

import com.DatabasePrinciple.TelecomManageSystem.model.User;
import org.apache.ibatis.annotations.*;

/**
 * UserMapper
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(fullname,password,email,account) " +
            "VALUES (#{fullname},#{password},#{email},0);")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(User user);

    @Select("SELECT * " +
            "FROM user " +
            "WHERE email = #{email} and password = #{password}")
    @ResultType(User.class)
    User login(User user);

    @Select("SELECT * " +
            "FROM user " +
            "WHERE email = #{email}")
    @ResultType(User.class)
    User checkEmail(User user);

    @Select("SELECT * " +
            "FROM user " +
            "WHERE id = #{id}")
    @ResultType(User.class)
    User findUser(User user);


    @Update("UPDATE user " +
            "SET account = #{newValue} " +
            "WHERE id = #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updateAccount(@Param("userId") Integer userId,@Param("newValue") Float newValue);

    @Update("UPDATE user " +
            "SET email = #{newEmail} " +
            "WHERE id = #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updateEmail(@Param("userId") Integer userId,@Param("newEmail") String newEmail);

    @Update("UPDATE user " +
            "SET password = #{newPassword} " +
            "WHERE id = #{userId}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int updatePassword(@Param("userId") Integer userId,@Param("newPassword") String newPassword);
}
