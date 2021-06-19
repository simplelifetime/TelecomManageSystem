package com.DatabasePrinciple.TelecomManageSystem.mapper;

import com.DatabasePrinciple.TelecomManageSystem.model.Business;
import com.DatabasePrinciple.TelecomManageSystem.model.Service;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 * author: jason
 **/
@Mapper
public interface BusinessMapper {
    @Insert("INSERT INTO business(uid,cid,upid,state) " +
            "VALUES (#{userId},#{customServiceId},#{upId},0);")
    @Options()
    int createBusiness(@Param("userId") Integer userId, @Param("customServiceId") Integer customServiceId,
                       @Param("upId") Integer upId);

    @Results(
            id = "businessList", value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "upid", column = "upid")
    }
    )
    @Select("SELECT * FROM business")
    List<Business> getAllBusiness();

    @Results(
            id = "businessList1", value = {
            @Result(property = "uid", column = "uid"),
            @Result(property = "cid", column = "cid"),
            @Result(property = "upid", column = "upid")
    }
    )
    @Select("SELECT * " +
            "FROM business " +
            "WHERE cid = #{cid}")
    List<Business> getBusinessByCustomService(Integer cid);

    @Select("SELECT * " +
            "FROM business " +
            "WHERE id = #{upId}")
    @ResultType(Business.class)
    Business getBusiness(Integer upId);

    @Update("UPDATE business " +
            "SET state = #{state} " +
            "WHERE id = #{businessId}")
    @Options()
    int setState(@Param("businessId") Integer businessId,@Param("state") Integer state);

    @Delete("DELETE from business " +
            "where id = #{businessId}")
    int deleteBusiness(Integer businessId);
}
