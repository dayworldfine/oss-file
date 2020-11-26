import http from '@/util/http'

/** 查看文件列表*/
const getFileList = (param = {}) =>{return http.post({url: '/file/getFileList', data: param})}

export default {
  getFileList,                                          /** 查看文件列表*/
}
