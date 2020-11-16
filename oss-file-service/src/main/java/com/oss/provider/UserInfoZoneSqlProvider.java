package com.oss.provider;

import com.oss.model.UserInfoZone;
import org.apache.ibatis.jdbc.SQL;

public class UserInfoZoneSqlProvider {

    public String insertSelective(UserInfoZone record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_user_info_zone");
        
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
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getZoneId() != null) {
            sql.VALUES("zone_id", "#{zoneId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserInfoZone record) {
        SQL sql = new SQL();
        sql.UPDATE("t_user_info_zone");
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }
        
        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getZoneId() != null) {
            sql.SET("zone_id = #{zoneId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}