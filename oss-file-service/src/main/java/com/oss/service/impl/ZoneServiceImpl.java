package com.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.oss.mapper.UserInfoZoneMapper;
import com.oss.mapper.ZoneMapper;
import com.oss.model.UserInfoZone;
import com.oss.model.Zone;
import com.oss.pojo.bo.ZoneBo;
import com.oss.pojo.bo.ZonePwdBo;
import com.oss.pojo.dto.ZoneDto;
import com.oss.pojo.dto.ZoneListDto;
import com.oss.service.ZoneService;
import com.oss.tool.ErrorCodes;
import com.oss.tool.ResponseResult;
import com.oss.tool.start.UserStart;
import com.oss.tool.util.OssUtil;
import com.oss.tool.util.SnowUtil;
import com.oss.tool.util.ValidateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ZoneServiceImpl implements ZoneService {

    @Autowired
    private ZoneMapper zoneMapper;

    @Autowired
    private UserInfoZoneMapper userInfoZoneMapper;

    @Autowired
    private OssUtil ossUtil;

    @Override
    public ResponseResult<PageInfo<ZoneBo>> pageZoneByUserId(Long userId, ZoneListDto zoneListDto) {
        PageHelper.startPage(zoneListDto.getPage(),zoneListDto.getSize());
        // 如果userId为空 则为游客
        Page<ZoneBo> zoneBos =null;
        if (ValidateUtil.isNotEmpty(userId)){
            zoneBos  = zoneMapper.pageZoneByUserIdAndName(userId,zoneListDto.getName());
        }else {
            zoneBos  = zoneMapper.pageZoneByVisitor(zoneListDto.getName());
        }
        PageInfo<ZoneBo> pageInfo =new PageInfo<ZoneBo>(zoneBos);

        return  ResponseResult.responseSuccessResult(pageInfo);
    }

    @Override
    public ResponseResult deleteZoneById(String zoneId) {

        String prefix = zoneMapper.selectPrefixByZoneId(zoneId);
        if (ValidateUtil.isEmpty(prefix)){
            return ResponseResult.responseSuccessResult(ErrorCodes.ZONE_NOT_FOUND);
        }
        //删除oss上的路径
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(ossUtil.getEndpoint(), ossUtil.getAccessKeyId(), ossUtil.getAccessKeySecret());
        // 列举所有包含指定前缀的文件并删除。
        String nextMarker = null;
        ObjectListing objectListing = null;
        do {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(ossUtil.getBucketName())
                    .withPrefix(prefix)
                    .withMarker(nextMarker);
            objectListing = ossClient.listObjects(listObjectsRequest);
            if (objectListing.getObjectSummaries().size() > 0) {
                List<String> keys = new ArrayList<String>();
                for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                    System.out.println("key name: " + s.getKey());
                    keys.add(s.getKey());
                }
                DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(ossUtil.getBucketName()).withKeys(keys);
                ossClient.deleteObjects(deleteObjectsRequest);
            }
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
        // 关闭OSSClient。
        ossClient.shutdown();

        //删除数据库的数据
        Integer num = zoneMapper.deleteZoneById(zoneId);
        ResponseResult<Integer> result = ResponseResult.responseSuccessResult(num);
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
        if (ValidateUtil.isCountEmpty(insert)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_ERROR);
        }
        //添加主账号的分区关联的信息
        UserInfoZone userInfoZone =new UserInfoZone();
        userInfoZone.setId(SnowUtil.generateId());
        userInfoZone.setCreateTime(System.currentTimeMillis());
        userInfoZone.setUpdateTime(System.currentTimeMillis());
        userInfoZone.setVersion(1l);
        userInfoZone.setZoneId(zone.getId());
        userInfoZone.setUserId(Long.valueOf(UserStart.userId));
        int num = userInfoZoneMapper.insert(userInfoZone);
        if (ValidateUtil.isCountEmpty(num)){
            return ResponseResult.responseResultWithErrorCode(ErrorCodes.ADD_ERROR);
        }
        return ResponseResult.responseOK();
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
    public ResponseResult<List<ZonePwdBo>> getMyZonePwd(long userId) {
        List<ZonePwdBo> zonePwdBoList = zoneMapper.selectMyZonePwd(userId);
        return  ResponseResult.responseSuccessResult(zonePwdBoList);
    }

    @Override
    public ResponseResult start() {
        Integer countZone = zoneMapper.countZone();
        if (ValidateUtil.isNotCountEmpty(countZone)){
            return ResponseResult.responseOK();
        }
        Zone zone = new Zone();
        zone.setId(1l);
        zone.setCreateTime(System.currentTimeMillis());
        zone.setUpdateTime(System.currentTimeMillis());
        zone.setVersion(1l);
        zone.setZoneName("游客专区");
        zone.setZonePrefix("visitor");
        zone.setZonePwd("");
        zone.setIsOpen(1);
        zoneMapper.insert(zone);
        return ResponseResult.responseOK();
    }

    @Override
    public ResponseResult<Page<Zone>> pageQueryZoneByUserId(ZoneListDto zoneListDto) {
        PageHelper.startPage(zoneListDto.getPage(),zoneListDto.getSize());
        Page<Zone>  zoneList  = zoneMapper.pageQueryZoneByUserId(zoneListDto.getName());
        return ResponseResult.responseSuccessResult(zoneList);
    }
}
