package com.oss.mapper;

import com.github.pagehelper.Page;
import com.oss.model.Zone;
import com.oss.pojo.bo.ZoneBo;
import com.oss.pojo.bo.ZonePwdBo;
import com.oss.pojo.dto.ZoneDto;
import com.oss.provider.ZoneSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneMapper {
    @Delete({
        "delete from t_zone",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_zone (id, create_time, ",
        "update_time, version, ",
        "zone_name, zone_prefix, ",
        "zone_pwd, is_open)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{zoneName,jdbcType=VARCHAR}, #{zonePrefix,jdbcType=VARCHAR}, ",
        "#{zonePwd,jdbcType=VARCHAR}, #{isOpen,jdbcType=TINYINT})"
    })
    int insert(Zone record);

    @InsertProvider(type= ZoneSqlProvider.class, method="insertSelective")
    int insertSelective(Zone record);

    @Select({
        "select",
        "id, create_time, update_time, version, zone_name, zone_prefix, zone_pwd, is_open",
        "from t_zone",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="zone_name", property="zoneName", jdbcType=JdbcType.VARCHAR),
        @Result(column="zone_prefix", property="zonePrefix", jdbcType=JdbcType.VARCHAR),
        @Result(column="zone_pwd", property="zonePwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT)
    })
    Zone selectByPrimaryKey(Long id);

    @UpdateProvider(type=ZoneSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Zone record);

    @Update({
        "update t_zone",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "zone_name = #{zoneName,jdbcType=VARCHAR},",
          "zone_prefix = #{zonePrefix,jdbcType=VARCHAR},",
          "zone_pwd = #{zonePwd,jdbcType=VARCHAR},",
          "is_open = #{isOpen,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Zone record);

    /**
     * 分页查询分区
     * @param userId
     * @return
     */
    @Select({
        "SELECT \n" +
        "z.id as id,\n" +
        "FROM_UNIXTIME(z.create_time/1000 , '%Y-%m-%d %H:%i:%S') as createTime,\n" +
        "FROM_UNIXTIME(z.update_time/1000 , '%Y-%m-%d %H:%i:%S') as updateTime,\n" +
        "z.zone_name as zoneName,\n" +
        "z.zone_prefix as zonePrefix\n" +
        "FROM t_zone z \n" +
        "INNER JOIN t_user_info_zone uiz \n" +
        "on z.id = uiz.zone_id  " +
        "where userId=#{userId,jdbcType=BIGINT} order by z.create_time desc"
    })
    Page<ZoneBo> pageZoneByUserId(Long userId);

    /**
     * 删除分区
     * @param joinZoneIds
     * @return
     */
    @Update({
        "delete from t_zone where id in (${joinZoneIds}) "
    })
    Integer deleteZoneByIds(String joinZoneIds);

    /**
     * 查询前缀
     * @param zoneId
     * @return
     */
    @Select({
         "select zone_prefix  from  t_zone where  id= #{zoneId,jdbcType=BIGINT}"
    })
    String findPrefixById(String zoneId);

    /**
     * 查询分区列表
     * @return
     */
    @Select({
            "SELECT \n" +
                    "z.id as id,\n" +
                    "FROM_UNIXTIME(z.create_time/1000 , '%Y-%m-%d %H:%i:%S') as createTime,\n" +
                    "FROM_UNIXTIME(z.update_time/1000 , '%Y-%m-%d %H:%i:%S') as updateTime,\n" +
                    "z.zone_name as zoneName,\n" +
                    "z.zone_prefix as zonePrefix\n" +
                    "FROM t_zone z \n" +
                    "INNER JOIN t_user_info_zone uiz \n" +
                    "on z.id = uiz.zone_id order by z.create_time desc"
    })
    List<ZoneBo> selectAllZoneList();

    /**
     * 条件查询列表
     * @param userId
     * @param name
     * @return
     */
    @SelectProvider(type=ZoneSqlProvider.class, method="pageZoneByUserIdAndName")
    Page<ZoneBo> pageZoneByUserIdAndName(Long userId, String name);

    /**
     * 根据密匙查看分区
     * @param pwd
     * @return
     */
    @Select({
            "select id,create_time ,update_time ,version,zone_name,zone_prefix, zone_pwd,is_open ",
            " from t_zone where zone_pwd = #{pwd,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="zone_name", property="zoneName", jdbcType=JdbcType.VARCHAR),
            @Result(column="zone_prefix", property="zonePrefix", jdbcType=JdbcType.VARCHAR),
            @Result(column="zone_pwd", property="zonePwd", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_open", property="isOpen", jdbcType=JdbcType.TINYINT)
    })
    Zone selectByPwd(String pwd);

    /**
     * 根据条件查看是否已经存在
     * @param zoneDto
     * @return
     */
    @Select({
            "select count(1) ",
            "from t_zone where zone_name= #{zoneName,jdbcType=VARCHAR} ",
            " or zone_prefix =#{zonePrefix,jdbcType=VARCHAR }",
            " or zone_pwd =#{zonePwd,jdbcType=VARCHAR} "
    })
    Integer findByParam(ZoneDto zoneDto);

    /**
     * 查看我的分区密匙
     * @param userId
     * @return
     */
    @Select({
            "SELECT \n" +
                    "z.id as id,\n" +
                    "z.zone_name as zoneName,\n" +
                    "z.zone_prefix as zonePrefix,\n" +
                    "z.zone_pwd as zonePwd\n" +
                    "FROM\n" +
                    "t_user_info_zone uiz \n" +
                    "INNER JOIN t_zone z\n" +
                    "on uiz.zone_id = z.id\n" +
                    "WHERE uiz.user_id =#{userId,jdbcType=BIGINT}"
    })
    List<ZonePwdBo> selectMyZonePwd(long userId);

    /**
     * 统计
     * @return
     */
    @Select({
            "select count(1) from t_zone"
    })
    Integer countZone();

    /**
     * 查询游客分区
     * @param name
     * @return
     */
    @SelectProvider(type=ZoneSqlProvider.class, method="pageZoneByVisitor")
    Page<ZoneBo> pageZoneByVisitor(String name);

    /**
     * 分配分区查询分区列表
     * @param name
     * @return
     */
    @SelectProvider(type=ZoneSqlProvider.class, method="pageQueryZoneByUserId")
    Page<Zone> pageQueryZoneByUserId(String name);
}
