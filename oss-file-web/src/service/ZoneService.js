import http from '@/util/http'

/** 获取分区列表*/
const getZoneList = (param = {}) =>{return http.post({url: '/zone/getZoneList', data: param})}

/** 查询我的分区所有密匙*/
const getMyZonePwd = (param = {}) =>{return http.post({url: '/zone/getMyZonePwd', data: param})}

/** 查询输入分区密匙*/
const getZoneKey = (param = {}) =>{return http.post({url: '/zone/getZoneKey', data: param})}


export default {
  getMyZonePwd,                                          /** 查询我的分区所有密匙*/
  getZoneKey,                                           /** 查询输入分区密匙*/
  getZoneList,                                          /** 获取分区列表*/
}
