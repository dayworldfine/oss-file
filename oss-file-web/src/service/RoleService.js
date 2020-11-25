import http from '@/util/http'

/** 查询我的角色所有密匙*/
const getMyRolePwd = (param = {}) =>{return http.post({url: '/role/getMyRolePwd', data: param})}

/** 输入角色密钥*/
const getRoleKey = (param = {}) =>{return http.post({url: '/role/getRoleKey', data: param})}

/** 分配分区*/
const allotZone = (param = {}) =>{return http.post({url: '/role/allotZone', data: param})}

/** 分配角色*/
const allotRole = (param = {}) =>{return http.post({url: '/role/allotRole', data: param})}

/** 查询角色列表*/
const getRoleList = (param = {}) =>{return http.post({url: '/role/getRoleList', data: param})}


export default {
  getMyRolePwd,                                          /** 查询我的角色所有密匙*/
  getRoleKey,                                            /** 输入角色密钥*/
  allotZone,                                            /** 分配分区*/
  allotRole,                                            /** 分配角色*/
  getRoleList,                                          /** 查询角色列表*/
}
