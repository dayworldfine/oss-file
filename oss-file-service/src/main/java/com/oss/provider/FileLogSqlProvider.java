package com.oss.provider;

import com.oss.model.FileLog;
import org.apache.ibatis.jdbc.SQL;

public class FileLogSqlProvider {

    public String insertSelective(FileLog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_file_log");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=BIGINT}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=BIGINT}");
        }

        if (record.getVersion() != null) {
            sql.VALUES("version", "#{version,jdbcType=BIGINT}");
        }

        if (record.getFileId() != null) {
            sql.VALUES("file_id", "#{fileId,jdbcType=BIGINT}");
        }

        if (record.getFileName() != null) {
            sql.VALUES("file_name", "#{fileName,jdbcType=VARCHAR}");
        }

        if (record.getOperationType() != null) {
            sql.VALUES("operation_type", "#{operationType,jdbcType=TINYINT}");
        }

        if (record.getZoneId() != null) {
            sql.VALUES("zone_id", "#{zoneId,jdbcType=BIGINT}");
        }

        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }


        return sql.toString();
    }

    public String updateByPrimaryKeySelective(FileLog record) {
        SQL sql = new SQL();
        sql.UPDATE("t_file_log");

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }

        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=BIGINT}");
        }

        if (record.getFileId() != null) {
            sql.SET("file_id = #{fileId,jdbcType=BIGINT}");
        }

        if (record.getFileName() != null) {
            sql.SET("file_name = #{fileName,jdbcType=VARCHAR}");
        }

        if (record.getOperationType() != null) {
            sql.SET("operation_type = #{operationType,jdbcType=TINYINT}");
        }


        if (record.getZoneId() != null) {
            sql.SET("zone_id = #{zoneId,jdbcType=BIGINT}");
        }

        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }


        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }
}
