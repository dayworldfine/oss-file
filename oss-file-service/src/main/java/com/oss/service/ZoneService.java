package com.oss.service;

import com.github.pagehelper.Page;
import com.oss.tool.BaseService;
import com.oss.pojo.bo.ZoneBo;
import com.oss.pojo.dto.ZoneDto;
import com.oss.pojo.dto.ZoneListDto;
import com.oss.tool.ResponseResult;


public interface ZoneService extends BaseService {

    /**
     * 查询用户拥有的区域
     * @param userId
     */
    ResponseResult<Page<ZoneBo>> pageZoneByUserId(Long userId, ZoneListDto zoneListDto);

    /**
     * 删除区域
     * @param zoneIds
     * @return
     */
    ResponseResult<Integer> deleteZoneByIds(String[] zoneIds);

    /**
     * 添加分区
     * @param zoneDto
     * @return
     */
    ResponseResult addZone(ZoneDto zoneDto);

    /**
     * 根据id查询前缀名称
     * @param zoneId
     * @return
     */
    ResponseResult findPrefixById(String zoneId);

    /**
     * 查询地区列表
     * @return
     */
    ResponseResult selectAllZoneList();

    /**
     *获取我的分区信息
     * @param userId
     * @return
     */
    ResponseResult getMyZonePwd(long userId);

    /**
     * 启动
     * @return
     */
    ResponseResult start();
}
