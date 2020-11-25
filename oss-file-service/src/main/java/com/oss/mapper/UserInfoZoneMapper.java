package com.oss.mapper;

import com.oss.model.UserInfoZone;
import com.oss.provider.UserInfoZoneSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoZoneMapper {
    @Delete({
        "delete from t_user_info_zone",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user_info_zone (id, create_time, ",
        "update_time, version, ",
        "user_id, zone_id)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{zoneId,jdbcType=BIGINT})"
    })
    int insert(UserInfoZone record);

    @InsertProvider(type= UserInfoZoneSqlProvider.class, method="insertSelective")
    int insertSelective(UserInfoZone record);

    @Select({
        "select",
        "id, create_time, update_time, version, user_id, zone_id",
        "from t_user_info_zone",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="zone_id", property="zoneId", jdbcType=JdbcType.BIGINT)
    })
    UserInfoZone selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserInfoZoneSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfoZone record);

    @Update({
        "update t_user_info_zone",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "zone_id = #{zoneId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserInfoZone record);

    /**
     * 删除地区
     * @param userJoin
     * @return
     */
    @Delete({
            "delete from t_user_info_zone",
            "where user_id in (${userJoin})"
    })
    Integer deleteByUserId(@Param("userJoin") String userJoin);

    /**
     * 添加地区
     * @param userInfoZoneList
     * @return
     */
    @Insert({
            "<script>",
            "insert into t_user_info_zone(id, create_time, update_time, version, user_id, zone_id) values ",
            "<foreach collection='userInfoZoneList' item='item' index='index' separator=','>",
            "(#{item.id}, #{item.createTime}, #{item.updateTime}, #{item.version}, #{item.userId}, #{item.zoneId})",
            "</foreach>",
            "</script>"
    })
    Integer insertUserInfoZoneList(@Param("userInfoZoneList") List<UserInfoZone> userInfoZoneList);

    /**
     * 根据 查看用户是否已经拥有该分区
     * @param userId
     * @param zoneId
     * @return
     */
    @Select({
            "select ",
            "id,create_time, update_time, version, user_id, zone_id ",
            " from t_user_info_zone ",
            " where user_id = #{userId,jdbcType=BIGINT} and zone_id = #{zoneId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="zone_id", property="zoneId", jdbcType=JdbcType.BIGINT)
    })
    UserInfoZone selectByZoneId(Long userId, Long zoneId);
}