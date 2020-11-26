package com.oss.mapper;

import com.github.pagehelper.Page;

import com.oss.model.File;
import com.oss.pojo.bo.FileBo;
import com.oss.pojo.dto.FileListDto;
import com.oss.provider.FileSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMapper {
    @Delete({
        "delete from t_file",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_file (id, create_time, ",
        "update_time, version, ",
        "file_name, ",
        "suffix, url, upload_user_id, ",
        "download_statistics, ",
        "preview_statistics, zone_id)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
        "#{updateTime,jdbcType=BIGINT}, #{version,jdbcType=BIGINT}, ",
        "#{fileName,jdbcType=VARCHAR}, ",
        "#{suffix,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR}, #{uploadUserId,jdbcType=BIGINT}, ",
        " #{downloadStatistics,jdbcType=BIGINT}, ",
        "#{previewStatistics,jdbcType=BIGINT}, #{zoneId,jdbcType=BIGINT})"
    })
    int insert(File record);

    @InsertProvider(type= FileSqlProvider.class, method="insertSelective")
    int insertSelective(File record);

    @Select({
        "select",
        "id, create_time, update_time, version, file_name, suffix, url, ",
        "upload_user_id, download_statistics, preview_statistics, zone_id",
        "from t_file",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
        @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="suffix", property="suffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="upload_user_id", property="uploadUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="download_statistics", property="downloadStatistics", jdbcType=JdbcType.BIGINT),
        @Result(column="preview_statistics", property="previewStatistics", jdbcType=JdbcType.BIGINT),
        @Result(column="zone_id", property="zoneId", jdbcType=JdbcType.BIGINT)
    })
    File selectByPrimaryKey(Long id);

    @UpdateProvider(type=FileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(File record);

    @Update({
        "update t_file",
        "set create_time = #{createTime,jdbcType=BIGINT},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "version = #{version,jdbcType=BIGINT},",
          "file_name = #{fileName,jdbcType=VARCHAR},",
          "suffix = #{suffix,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "upload_user_id = #{uploadUserId,jdbcType=BIGINT},",
          "download_statistics = #{downloadStatistics,jdbcType=BIGINT},",
          "preview_statistics = #{previewStatistics,jdbcType=BIGINT},",
          "zone_id = #{zoneId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(File record);


//    @Select({
//        "SELECT\n" +
//                "f.id as id,\n" +
//                "FROM_UNIXTIME(f.create_time/1000,'%Y-%m-%d %H:%i:%s' ) as createTime,\n" +
//                "FROM_UNIXTIME(f.update_time/1000,'%Y-%m-%d %H:%i:%s' ) as updateTime,\n" +
//                "f.file_name as fileName,\n" +
//                "f.suffix as suffix,\n" +
//                "f.url as url,\n" +
//                "f.download_statistics as downloadStatistics,\n" +
//                "f.preview_statistics as previewStatistics,\n" +
//                "u.id as uploadUserId,\n" +
//                "u.nick_name as uploadUserName,\n" +
//                "u.head_portrait as uploadUserImg\n" +
//                "FROM\n" +
//                "t_file f\n" +
//                "LEFT JOIN t_user u\n" +
//                "on f.upload_user_id = u.id\n" +
//                "WHERE f.zone_id=#{zoneId,jdbcType=BIGINT} \n" +
//                "ORDER BY createTime desc"
//    })
    @SelectProvider(type=FileSqlProvider.class, method="pageFileByZoneId")
    Page<FileBo> pageFileByZoneId(FileListDto fileListDto);

    @Select({
            "select * from t_file ",
            "where file_name = #{fileName,jdbcType=VARCHAR} ",
            "and zone_id = #{zoneId,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="version", property="version", jdbcType=JdbcType.BIGINT),
            @Result(column="file_name", property="fileName", jdbcType=JdbcType.VARCHAR),
            @Result(column="suffix", property="suffix", jdbcType=JdbcType.VARCHAR),
            @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
            @Result(column="upload_user_id", property="uploadUserId", jdbcType=JdbcType.BIGINT),
            @Result(column="download_statistics", property="downloadStatistics", jdbcType=JdbcType.BIGINT),
            @Result(column="preview_statistics", property="previewStatistics", jdbcType=JdbcType.BIGINT),
            @Result(column="zone_id", property="zoneId", jdbcType=JdbcType.BIGINT)
    })
    File findFileByNameAndZoneId(String fileName, String zoneId);

    @UpdateProvider(type=FileSqlProvider.class, method="addFileStatistics")
    Integer addFileStatistics(int type, String fileId);
}
