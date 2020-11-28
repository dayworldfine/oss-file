import http from '@/util/http'

/** 获取文档预览地址*/
const getDocumentUrl = (param = {}) =>{return http.post({url: '/document/getDocumentUrl', data: param})}

/** 刷新凭证*/
const refreshSign = (param = {}) =>{return http.post({url: '/document/refreshSign', data: param})}

export default {
  getDocumentUrl,                                          /** 获取文档预览地址*/
  refreshSign,                                           /** 刷新凭证*/
}
