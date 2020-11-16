package com.oss.mapper;

import com.oss.model.FileLog;
import com.oss.provider.FileLogSqlProvider;
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

@Repository
public interface FileLogMapper {
    @Delete({
        "delete from t_file_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_file_log (id, create_time, ",
        "update_time, version, ",
        "file_id, file_name, ",
        "operation_type,  ",
        " zone_id, ",
        "user_id)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{fileId,jdbcType=BIGINT}, #{fileName,jdbcType=VARCHAR}, ",
        "#{operationType,jdbcType=TINYINT},  ",
        "#{zoneId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT})"
    })
    int insert(FileLog record);

    @InsertProvider(type= FileLogSqlProvider.class, method="insertSelective")
    int insertSelective(FileLog record);

    @Select({
        "select",
        "id, create_time, update_time, version, file_id, file_name, operation_type,  ",
        "zone_id, user_id ",
        "from t_file_log",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="file_id", property="fileId", jdbcType=JdbcType.BIGINT),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="operation_type", property="operationType", jdbcType=JdbcType.TINYINT),
        @Result(column="zone_id", property="zoneId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT)
    })
    FileLog selectByPrimaryKey(Long id);

    @UpdateProvider(type=FileLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(FileLog record);

    @Update({
        "update t_file_log",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "file_id = #{fileId,jdbcType=BIGINT},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "operation_type = #{operationType,jdbcType=TINYINT},",
          "zone_id = #{zoneId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT} ",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(FileLog record);
}
