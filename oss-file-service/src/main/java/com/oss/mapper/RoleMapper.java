package com.oss.mapper;

import com.oss.model.Role;
import com.oss.provider.RoleSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    @Delete({
        "delete from t_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_role (id, create_time, ",
        "update_time, version, ",
        "role_name, pwd,is_open )",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{roleName,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR},#{isOpen,jdbcType=TINYINT})"
    })
    int insert(Role record);

    @InsertProvider(type= RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "id, create_time, update_time, version, role_name, pwd ,is_open ",
        "from t_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT)
    })
    Role selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update t_role",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "role_name = #{roleName,jdbcType=VARCHAR}, ",
          "pwd = #{pwd,jdbcType=VARCHAR}, ",
          "is_open = #{isOpen,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);

    @Select({
            "select",
            "id, create_time, update_time, version, role_name, pwd ,is_open ",
            "from t_role",
            "where is_open = 1"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT)
    })
    List<Role> getRoleList();

    /**
     * 根据密匙查询角色
     * @param pwd
     * @return
     */
    @Select({
            "select id, create_time, update_time, version, role_name,pwd, is_open ",
            " from t_role where pwd = #{pwd,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT)
    })
    Role selectByPwd(String pwd);
}
