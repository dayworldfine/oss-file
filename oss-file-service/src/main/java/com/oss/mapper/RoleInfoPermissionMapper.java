package com.oss.mapper;

import com.oss.model.RoleInfoPermission;
import com.oss.provider.RoleInfoPermissionSqlProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface RoleInfoPermissionMapper {
    @Delete({
        "delete from t_role_info_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_role_info_permission (id, create_time, ",
        "update_time, version, ",
        "role_id, permission_id)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{roleId,jdbcType=BIGINT}, #{permissionId,jdbcType=BIGINT})"
    })
    int insert(RoleInfoPermission record);

    @InsertProvider(type= RoleInfoPermissionSqlProvider.class, method="insertSelective")
    int insertSelective(RoleInfoPermission record);

    @Select({
        "select",
        "id, create_time, update_time, version, role_id, permission_id",
        "from t_role_info_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT)
    })
    RoleInfoPermission selectByPrimaryKey(Long id);

    @UpdateProvider(type=RoleInfoPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(RoleInfoPermission record);

    @Update({
        "update t_role_info_permission",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "role_id = #{roleId,jdbcType=BIGINT},",
          "permission_id = #{permissionId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(RoleInfoPermission record);
}
