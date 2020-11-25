import http from '@/util/http'


const joinActivity = (param = {}) =>{ return   http.post({url:'positivity/activityScore', data: param}) }

/** 修改用户名*/
const updateUserName = (param = {}) =>{return http.post({url: '/user/updateUserName', data: param})}

/** 条件查询用户*/
const queryUserByParam = (param = {}) =>{return http.post({url: '/user/queryUserByParam', data: param})}

export default {
  joinActivity,
  updateUserName,                     /** 修改用户名*/
  queryUserByParam,                     /** 条件查询用户*/
}
