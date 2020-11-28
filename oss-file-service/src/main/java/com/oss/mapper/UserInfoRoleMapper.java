package com.oss.mapper;

import com.oss.model.UserInfoRole;
import com.oss.provider.UserInfoRoleSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoRoleMapper {
    @Delete({
        "delete from t_user_info_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user_info_role (id, create_time, ",
        "update_time, version, ",
        "user_id, role_id)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT})"
    })
    int insert(UserInfoRole record);

    @InsertProvider(type= UserInfoRoleSqlProvider.class, method="insertSelective")
    int insertSelective(UserInfoRole record);

    @Select({
        "select",
        "id, create_time, update_time, version, user_id, role_id",
        "from t_user_info_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    UserInfoRole selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserInfoRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfoRole record);

    @Update({
        "update t_user_info_role",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "role_id = #{roleId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserInfoRole record);

    @Delete({
            "delete from t_user_info_role",
            "where user_id in (${userIdJoin})"
    })
    Integer deleteByUserId(@Param("userIdJoin") String userIdJoin);

    @Insert({
            "<script>",
            "insert into t_user_info_role(id, create_time, update_time, version, user_id, role_id) values ",
            "<foreach collection='userInfoRoleList' item='item' index='index' separator=','>",
            "(#{item.id}, #{item.createTime}, #{item.updateTime}, #{item.version}, #{item.userId}, #{item.roleId})",
            "</foreach>",
            "</script>"
    })
    Integer insertUserInfoRoleList(@Param(value="userInfoRoleList") List<UserInfoRole> userInfoRoleList);

    /**
     * 查询用户是否已经拥有这个角色
     * @param userId
     * @param roleId
     * @return
     */
    @Select({
            "select id, create_time,update_time ,version,user_id,role_id ",
            "from t_user_info_role ",
            "where user_id=#{userId,jdbcType=BIGINT} and role_id = #{roleId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
            @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    UserInfoRole selectByRoleId(long userId, Long roleId);

    /**
     * 统计关系数据
     * @return
     */
    @Select({
            "select count(1) from t_user_info_role"
    })
    Integer countUserInfoRole();

    /**
     * 根据用户id查询角色code
     * @param userId
     * @return
     */
    @Select({
            "select `code` from t_role  where id in ",
            "(select role_id from t_user_info_role where user_id = #{userId,jdbcType=BIGINT})"
    })
    List<String> selectRoleCodeByUserId(long userId);
}
