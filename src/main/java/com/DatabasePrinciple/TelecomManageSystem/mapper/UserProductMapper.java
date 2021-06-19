package com.DatabasePrinciple.TelecomManageSystem.mapper;

import com.DatabasePrinciple.TelecomManageSystem.model.Service;
import com.DatabasePrinciple.TelecomManageSystem.model.User;
import com.DatabasePrinciple.TelecomManageSystem.model.UserProduct;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 * author: jason
 **/
@Mapper
public interface UserProductMapper {

    @Results(
            id = "userProductList", value = {
            @Result(property = "s_time", column = "s_time"),
            @Result(property = "e_time", column = "e_time"),
            @Result(property = "state", column = "state"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "sid", column = "sid"),
            @Result(property = "autoRecharge", column = "autoRecharge")
    }
    )
    @Select("SELECT * FROM userproduct where uid = #{userId}")
    List<UserProduct> findUserProduct(Integer userId);

    @Insert("INSERT INTO userproduct(s_time,e_time,state,uid,sid,autorecharge) " +
            "VALUES (#{s_time},#{e_time},0,#{uid},#{sid},0)")
    @Options(useGeneratedKeys=true, keyProperty = "id")
    int createUserProduct(UserProduct userProduct);

    @Select("SELECT * " +
            "FROM userproduct " +
            "WHERE id = #{upId}")
    @ResultType(UserProduct.class)
    UserProduct getUserProduct(Integer upId);

    @Update("UPDATE userproduct " +
            "SET state = #{state} " +
            "WHERE id = #{userProductId}")
    @Options()
    int setState(@Param("userProductId") Integer userProductId,@Param("state") Integer state);

    @Delete("DELETE from userproduct " +
            "where id = #{userProductId}")
    int deleteUserProduct(Integer userProductId);
}
