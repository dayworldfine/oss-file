import http from '@/util/http'


const joinActivity = (param = {}) =>{ return   http.post({url:'positivity/activityScore', data: param}) }


/** 修改用户名*/
const updateUserName = (param = {}) =>{return http.post({url: '/user/updateUserName', data: param})}

export default {
  joinActivity,
  updateUserName,                     /** 修改用户名*/
}
