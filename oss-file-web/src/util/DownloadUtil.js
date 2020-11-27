import axios from 'axios'
import { MessageBox, Message } from "element-ui";  // 引入
export  function exportExcel(url,fileName, options = {}) {
  return new Promise((resolve, reject) => {
    console.log(`${url} 请求数据，参数=>`, JSON.stringify(options))
    axios.defaults.headers['content-type'] = 'application/json;charset=UTF-8'
    axios.defaults.headers['Access-Control-Allow-Origin'] = '*'
    axios.defaults.headers['Access-Control-Allow-Methods'] = '*'
    axios.defaults.headers['Access-Control-Allow-Headers'] = 'X-Requested-With'
    axios({
      method: 'get',
      url: url, // 请求地址
      data: options, // 参数
      responseType: 'blob' // 表明返回服务器返回的数据类型
    }).then(
      response => {
        resolve(response.data)
        let blob = new Blob([response.data], {})
        console.log(blob)
        if (window.navigator.msSaveOrOpenBlob) {
          navigator.msSaveBlob(blob, fileName)
        } else {
          var link = document.createElement('a')
          link.href = window.URL.createObjectURL(blob)
          link.download = fileName
          link.click()
          //释放内存
          window.URL.revokeObjectURL(link.href)
        }
      },
      err => {
        Message.error("该资源有误,请稍后重试")
        reject(err)
      }
    )
  })
}
