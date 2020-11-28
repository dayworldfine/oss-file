import http from '@/util/http'

/** 发送短信验证码*/
const sendSms = (param = {}) =>{return http.post({url: '/login/sendSms', data: param})}
/** 注册*/
const register = (param = {}) =>{return http.post({url: '/login/register', data: param})}
/** 登录*/
const login = (param = {}) =>{return http.post({url: '/login/login', data: param})}
/** 找回密码*/
const retrievePwd = (param = {}) =>{return http.post({url: '/login/retrievePwd', data: param})}

export default {
  sendSms,                                          /** 发送短信验证码*/
  register,                                         /** 注册*/
  login,                                            /** 登录*/
  retrievePwd,                                            /** 找回密码*/
}
