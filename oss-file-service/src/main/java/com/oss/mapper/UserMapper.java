package com.oss.mapper;

import com.github.pagehelper.Page;
import com.oss.model.User;
import com.oss.provider.UserSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Delete({
        "delete from t_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user (id, create_time, ",
        "update_time, version, ",
        "nick_name, head_portrait,",
        "account, pwd)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{nickName,jdbcType=VARCHAR}, #{headPortrait,jdbcType=VARCHAR},",
        "#{account,jdbcType=BIGINT}, #{pwd,jdbcType=VARCHAR})"
    })
    int insert(User record);

    @InsertProvider(type= UserSqlProvider.class, method="insertSelective")
    int insertSelective(User record);

    @Select({
        "select",
        "id, create_time, update_time, version, nick_name,head_portrait, account, pwd",
        "from t_user",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="nick_name", property="nickName", jdbcType=JdbcType.VARCHAR),
        @Result(column="head_portrait", property="headPortrait", jdbcType=JdbcType.VARCHAR),
        @Result(column="account", property="account", jdbcType=JdbcType.BIGINT),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Long id);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update t_user",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "nick_name = #{nickName,jdbcType=VARCHAR},",
          "head_portrait = #{headPortrait,jdbcType=VARCHAR},",
          "account = #{account,jdbcType=BIGINT},",
          "pwd = #{pwd,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);

    @SelectProvider(type=UserSqlProvider.class, method="pageSelectKeyUser")
    Page<User> pageSelectKeyUser(String paramKey);

    /**
     * 根据手机号查询用户
     * @param account
     * @return
     */
    @Select({
            "select count(1) from t_user where account = #{account,jdbcType=BIGINT}"
    })
    Integer selectByAccount(String account);

    /**
     * 根据手机号查询用户实体
     * @param account
     * @return
     */
    @Select({
            "select * from t_user where account =#{account,jdbcType=BIGINT}"
    })
    User selectUserByAccount(String account);
}
