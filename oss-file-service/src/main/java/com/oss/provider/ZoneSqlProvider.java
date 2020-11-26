package com.oss.provider;

import com.oss.model.Zone;
import com.oss.tool.util.ValidateUtil;
import org.apache.ibatis.jdbc.SQL;

public class ZoneSqlProvider {

    public String insertSelective(Zone record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_zone");

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

        if (record.getZoneName() != null) {
            sql.VALUES("zone_name", "#{zoneName,jdbcType=VARCHAR}");
        }

        if (record.getZonePrefix() != null) {
            sql.VALUES("zone_prefix", "#{zonePrefix,jdbcType=VARCHAR}");
        }

        if (record.getZonePwd() != null) {
            sql.VALUES("zone_pwd", "#{zonePwd,jdbcType=VARCHAR}");
        }

        if (record.getIsOpen() != null) {
            sql.VALUES("is_open", "#{isOpen,jdbcType=TINYINT}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Zone record) {
        SQL sql = new SQL();
        sql.UPDATE("t_zone");

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }

        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=BIGINT}");
        }

        if (record.getZoneName() != null) {
            sql.SET("zone_name = #{zoneName,jdbcType=VARCHAR}");
        }

        if (record.getZonePrefix() != null) {
            sql.SET("zone_prefix = #{zonePrefix,jdbcType=VARCHAR}");
        }

        if (record.getZonePwd() != null) {
            sql.SET("zone_pwd = #{zonePwd,jdbcType=VARCHAR}");
        }

        if (record.getIsOpen() != null) {
            sql.SET("is_open = #{isOpen,jdbcType=TINYINT}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }

    public String pageZoneByUserIdAndName(Long userId, String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT  " +
                "z.id as id, " +
                "FROM_UNIXTIME(z.create_time/1000 , '%Y-%m-%d %H:%i:%S') as createTime, " +
                "FROM_UNIXTIME(z.update_time/1000 , '%Y-%m-%d %H:%i:%S') as updateTime, " +
                "z.zone_name as zoneName, " +
                "z.zone_prefix as zonePrefix " +
                "FROM t_zone z " +
                "WHERE( z.id in  " +
                "( " +
                "SELECT  " +
                "uiz.zone_id  " +
                "FROM " +
                "t_user_info_zone uiz " +
                "WHERE user_id = " + userId+
                ") " +
                "OR z.is_open =1 " +
                ")");

        if (ValidateUtil.isNotEmpty(name)){
            sb.append(" and z.zone_name like '%"+name+"%'");
        }

        sb.append(" order by z.create_time desc");

        return sb.toString();
    }

    /**
     * 游客专区大全
     * @param name
     * @return
     */
    public String pageZoneByVisitor( String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("   SELECT " +
                "        z.id as id," +
                "        FROM_UNIXTIME(z.create_time/1000 , '%Y-%m-%d %H:%i:%S') as createTime," +
                "        FROM_UNIXTIME(z.update_time/1000 , '%Y-%m-%d %H:%i:%S') as updateTime," +
                "        z.zone_name as zoneName," +
                "        z.zone_prefix as zonePrefix " +
                "        FROM t_zone z " +
                "        where and z.is_open =1 ");
        if (ValidateUtil.isNotEmpty(name)){
            sb.append(" and z.zone_name like '%"+name+"%'");
        }

        sb.append(" order by z.create_time desc");

        return sb.toString();
    }

    /**
     * 分配分区查询分区列表
     * @return
     */
    public String pageQueryZoneByUserId(String name){
        SQL sql = new SQL();
        sql.SELECT("*");
        sql.FROM("t_zone");
        sql.WHERE("is_open=0 ");
        if (ValidateUtil.isNotEmpty(name)){
            sql.AND();
            sql.WHERE("zone_name like concat('%',#{name,jdbcType=VARCHAR},'%')");
        }
        sql.ORDER_BY("create_time desc");
        return sql.toString();
    }
}
