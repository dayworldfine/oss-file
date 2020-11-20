import http from '@/util/http'

/** 发送短信验证码*/
const sendSms = (param = {}) =>{return http.post({url: '/login/sendSms', data: param})}

export default {

  sendSms,                                          /** 发送短信验证码*/
}
