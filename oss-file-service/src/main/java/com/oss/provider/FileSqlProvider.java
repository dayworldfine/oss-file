package com.oss.provider;

import com.oss.model.File;
import com.oss.pojo.dto.FileListDto;
import com.oss.tool.util.ValidateUtil;
import org.apache.ibatis.jdbc.SQL;

public class FileSqlProvider {

    public String insertSelective(File record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_file");

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


        if (record.getFileName() != null) {
            sql.VALUES("file_name", "#{fileName,jdbcType=VARCHAR}");
        }

        if (record.getSuffix() != null) {
            sql.VALUES("suffix", "#{suffix,jdbcType=VARCHAR}");
        }

        if (record.getUrl() != null) {
            sql.VALUES("url", "#{url,jdbcType=VARCHAR}");
        }

        if (record.getUploadUserId() != null) {
            sql.VALUES("upload_user_id", "#{uploadUserId,jdbcType=BIGINT}");
        }


        if (record.getDownloadStatistics() != null) {
            sql.VALUES("download_statistics", "#{downloadStatistics,jdbcType=BIGINT}");
        }

        if (record.getPreviewStatistics() != null) {
            sql.VALUES("preview_statistics", "#{previewStatistics,jdbcType=BIGINT}");
        }

        if (record.getZoneId() != null) {
            sql.VALUES("zone_id", "#{zoneId,jdbcType=BIGINT}");
        }

        return sql.toString();
    }

    public String updateByPrimaryKeySelective(File record) {
        SQL sql = new SQL();
        sql.UPDATE("t_file");

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }

        if (record.getVersion() != null) {
            sql.SET("version = #{version,jdbcType=BIGINT}");
        }


        if (record.getFileName() != null) {
            sql.SET("file_name = #{fileName,jdbcType=VARCHAR}");
        }

        if (record.getSuffix() != null) {
            sql.SET("suffix = #{suffix,jdbcType=VARCHAR}");
        }

        if (record.getUrl() != null) {
            sql.SET("url = #{url,jdbcType=VARCHAR}");
        }

        if (record.getUploadUserId() != null) {
            sql.SET("upload_user_id = #{uploadUserId,jdbcType=BIGINT}");
        }


        if (record.getDownloadStatistics() != null) {
            sql.SET("download_statistics = #{downloadStatistics,jdbcType=BIGINT}");
        }

        if (record.getPreviewStatistics() != null) {
            sql.SET("preview_statistics = #{previewStatistics,jdbcType=BIGINT}");
        }

        if (record.getZoneId() != null) {
            sql.SET("zone_id = #{zoneId,jdbcType=BIGINT}");
        }

        sql.WHERE("id = #{id,jdbcType=BIGINT}");

        return sql.toString();
    }


    public String addFileStatistics(int type, String fileId) {
        SQL sql = new SQL();
        sql.UPDATE("t_file");
        if (type == 0) {
            sql.SET("download_statistics = (download_statistics +1)");
        }else {
            sql.SET("preview_statistics = (preview_statistics +1)");
        }
        sql.WHERE("id = #{fileId,jdbcType=BIGINT}");

        return sql.toString();
    }

    public String pageFileByZoneId(FileListDto fileListDto){
        SQL sql = new SQL();
        sql.SELECT("f.id as id");
        sql.SELECT("FROM_UNIXTIME(f.create_time/1000,'%Y-%m-%d %H:%i:%s' ) as createTime");
        sql.SELECT("FROM_UNIXTIME(f.update_time/1000,'%Y-%m-%d %H:%i:%s' ) as updateTime");
        sql.SELECT("f.file_name as fileName");
        sql.SELECT("f.suffix as suffix");
        sql.SELECT("f.url as url");
        sql.SELECT("f.download_statistics as downloadStatistics");
        sql.SELECT("f.preview_statistics as previewStatistics");
        sql.SELECT("u.id as uploadUserId");
        sql.SELECT("u.nick_name as uploadUserName");
        sql.SELECT("u.head_portrait as uploadUserImg");
        sql.FROM("t_file f LEFT JOIN t_user u  on f.upload_user_id = u.id");
        sql.WHERE(" f.zone_id=#{zoneId,jdbcType=BIGINT}");
        if (ValidateUtil.isNotEmpty(fileListDto.getName())){
            sql.AND();
            sql.WHERE("f.file_name like concat('%','#{name,jdbcType=VARCHAR}','%')");
        }
        sql.ORDER_BY("f.create_time desc");


        return sql.toString();
    }
}
