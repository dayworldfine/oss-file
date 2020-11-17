import http from '@/util/http'


const sendSms = (param = {}) =>{return http.post({url: '/login/sendSms', data: param})}

export default {
  /** 发送短信验证码*/
  sendSms,
}
