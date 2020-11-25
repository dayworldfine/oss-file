package com.oss.mapper;

import com.github.pagehelper.Page;
import com.oss.model.Role;
import com.oss.pojo.dto.RoleListDto;
import com.oss.provider.RoleSqlProvider;
import org.apache.ibatis.annotations.*;
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
        "role_name, pwd,is_open,code )",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{roleName,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR},#{isOpen,jdbcType=TINYINT},#{code,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @InsertProvider(type= RoleSqlProvider.class, method="insertSelective")
    int insertSelective(Role record);

    @Select({
        "select",
        "id, create_time, update_time, version, role_name, pwd ,is_open,code ",
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
        @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)
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
          "code = #{code,jdbcType=VARCHAR} ",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);

    @SelectProvider(type=RoleSqlProvider.class, method="getRoleList")
    Page<Role> getRoleList(String name);

    /**
     * 根据密匙查询角色
     * @param pwd
     * @return
     */
    @Select({
            "select id, create_time, update_time, version, role_name,pwd, is_open,code ",
            " from t_role where pwd = #{pwd,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
            @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT),
            @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR)

    })
    Role selectByPwd(String pwd);

    /**
     * 查询是否有角色
     * @return
     */
    @Select({
            "select count(1) from t_role"
    })
    Integer countRole();

}
