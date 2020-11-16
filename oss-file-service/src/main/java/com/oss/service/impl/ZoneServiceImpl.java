package com.oss.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Joiner;
import com.oss.config.BaseService;
import com.oss.mapper.ZoneMapper;
import com.oss.model.File;
import com.oss.model.Zone;
import com.oss.pojo.bo.ZoneBo;
import com.oss.pojo.bo.ZonePwdBo;
import com.oss.pojo.dto.ZoneDto;
import com.oss.pojo.dto.ZoneListDto;
import com.oss.service.DocumentService;
import com.oss.service.ZoneService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseModel;
import com.oss.tool.ResponseResult;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import net.sf.saxon.trans.Err;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneMapper zoneMapper;

    @Override
    public ResponseResult<Page<ZoneBo>> pageZoneByUserId(Long userId, ZoneListDto zoneListDto) {
        PageHelper.startPage(zoneListDto.getPage(),zoneListDto.getSize());
//        Page<ZoneBo> zoneBos  = zoneMapper.pageZoneByUserId(userId);
        Page<ZoneBo> zoneBos  = zoneMapper.pageZoneByUserIdAndName(userId,zoneListDto.getName());
        return  ResponseResult.responseSuccessResult(zoneBos);
    }

    @Override
    public ResponseResult<Integer> deleteZoneByIds(String[] zoneIds) {
        String joinZoneIds = Joiner.on(",").join(zoneIds);
        Integer num = zoneMapper.deleteZoneByIds(joinZoneIds);
        ResponseResult<Integer> result = ResponseResult.responseSuccessResult(num);
        if (zoneIds.length==num.intValue()){
            result.setSuccess(true);
        }else {
            result.setErrorCode(20001);
        }
        return result;
    }

    @Override
    public ResponseResult addZone(ZoneDto zoneDto) {
        /** 判断是 某一个条件是否已存在*/
       Integer integer = zoneMapper.findByParam(zoneDto);
        if (ValidateUtil.isNotCountEmpty(integer)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ZONE_REPETITION);
        }
        Zone zone = new Zone();
        BeanUtils.copyProperties(zoneDto,zone);
        zone.setId(SnowUtil.generateId());
        zone.setCreateTime(System.currentTimeMillis());
        zone.setUpdateTime(System.currentTimeMillis());
        zone.setVersion(1l);
        int insert = zoneMapper.insert(zone);
        return ValidateUtil.isCountEmpty(insert)? ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_ERROR):ResponseResult.responseSuccessResult(insert);
    }

    @Override
    public ResponseResult findPrefixById(String zoneId) {
        String prefix = zoneMapper.findPrefixById(zoneId);
        return ValidateUtil.isNotEmpty(prefix)?ResponseResult.responseSuccessResult(prefix):ResponseResult.responseResultWithErrorCode(ErrorCodes.SELECT_NOT_FOUND);
    }

    @Override
    public ResponseResult selectAllZoneList() {
        List<ZoneBo> zoneBos  = zoneMapper.selectAllZoneList();
        return  ResponseResult.responseSuccessResult(zoneBos);
    }

    @Override
    public ResponseResult getMyZonePwd(long userId) {
        List<ZonePwdBo> zonePwdBoList = zoneMapper.selectMyZonePwd(userId);
        return  ResponseResult.responseSuccessResult(zonePwdBoList);
    }
}
