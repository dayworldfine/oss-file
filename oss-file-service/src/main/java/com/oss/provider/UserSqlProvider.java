package com.oss.provider;

import com.oss.model.User;
import com.oss.tool.util.ValidateUtil;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_user");

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


        if (record.getNickName() != null) {
            sql.VALUES("nick_name", "#{nickName,jdbcType=VARCHAR}");
        }

        if (record.getAccount() != null) {
            sql.VALUES("account", "#{account,jdbcType=BIGINT}");
        }

        if (record.getPwd() != null) {
            sql.VALUES("pwd", "#{pwd,jdbcType=VARCHAR}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("t_user");

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }

        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=BIGINT}");
        }


        if (record.getNickName() != null) {
            sql.SET("nick_name = #{nickName,jdbcType=VARCHAR}");
        }

        if (record.getAccount() != null) {
            sql.SET("account = #{account,jdbcType=BIGINT}");
        }

        if (record.getPwd() != null) {
            sql.SET("pwd = #{pwd,jdbcType=VARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }


    public String pageSelectKeyUser(String paramKey) {
        SQL sql = new SQL();
        sql.SELECT("id as id," +
                "create_time as createTime," +
                "update_time as updateTime," +
                "version as version," +
                "nick_name as nickName," +
                "head_portrait as headPortrait," +
                "account as account," +
                "pwd as pwd");

        sql.FROM("t_user");
        sql.WHERE("id !=1");
        if (ValidateUtil.isNotEmpty(paramKey)){
            sql.AND();
            sql.WHERE("nick_name like concat('%',#{paramKey,jdbcType=VARCHAR},'%') or " +
                    "account like concat('%',#{paramKey,jdbcType=VARCHAR},'%') ");
        }
        sql.ORDER_BY("create_time desc");




        return sql.toString();
    }
}
