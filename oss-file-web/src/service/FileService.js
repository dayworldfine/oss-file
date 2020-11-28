import http from '@/util/http'

/** 查看文件列表*/
const getFileList = (param = {}) =>{return http.post({url: '/file/getFileList', data: param})}

/** 下载文件*/
const downloadFile = (param = {}) =>{return http.post({url: '/file/downloadFile', data: param})}

/** 删除文件*/
const delFileById = (param = {}) =>{return http.post({url: '/file/delFileById', data: param})}

export default {
  getFileList,                                          /** 查看文件列表*/
  downloadFile,                                           /** 下载文件*/
  delFileById,                                        /** 删除文件*/
}
