/**
 * Axios 请求分装，支持拦截
 */
import axios from 'axios'
import {URL_HTTP_PREFIX} from '@/param/BaseUrl'
import { MessageBox, Message } from "element-ui";  // 引入
const http = axios.create({
  // baseURL: config.api.base,
  baseURL: URL_HTTP_PREFIX,
  // baseURL:'http://115.236.80.114:7001/iyundao/',
  timeout: 100000, // 请求的超时时间
  // 设置默认请求头，使post请求发送的是formdata格式数据
  // axios的header默认的Content-Type好像是'application/json;charset=UTF-8'
  // 我的项目都是用json格式传输，如果需要更改的话，可以用这种方式修改
  headers: {
    Accept: "application/json, text/plain, */*",
    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
    'Access-Control-Allow-Origin':'*'
    // 'Access-Control-Allow-Origin':"http://192.168.1.3"
  },
  // withCredentials:true
  // withCredentials: true // 允许携带cookie
});

// http request 拦截器
http.interceptors.request.use((option) => {
  // console.log("option",option)
  let reqUrl = option.baseURL + option.url.url;
  let reqName = option.url.url;

  // let reqUrl = option.url

  // if (/^https?:\/\//.test(option.url)) {
  //   reqUrl = option.url
  // }

  // if (!window.ENV.app) {
  //   option.headers['Closer-Agent'] = 'Closer-Download'
  // } else {
  //   if (window.ENV.app && window.ENV.ios) {
  //     option.headers['Closer-Agent'] = 'Closer-Ios'
  //   } else if (window.ENV.app && window.ENV.android) {
  //     option.headers['Closer-Agent'] = 'Closer-Android'
  //   }
  // }

  // if (Cookies.get('uid')) {
  //   option.headers['X-Udid'] = Cookies.get('uid')
  // }
  // if (Cookies.get('aid')) {
  //   option.headers['X-Adid'] = Cookies.get('aid')
  // }
  // if (Cookies.get('GroukAuth') && option.url.indexOf('auth') == -1 && option.url.indexOf('account') == -1 ||
  //   Cookies.get('GroukAuth') && option.url.indexOf('closer_account.bind_phone') != -1
  // ) {
  //   option.headers.Authorization = Cookies.get('GroukAuth')
  // }

  //  待修改
  if (option.url.headers) {
    option.headers = {
      'Content-Type': 'application/json;charset=UTF-8',
    };
    let data = option.url.data;
    option.data = data;
    option.url = reqUrl;
  }else if (option.url.download){
    option.headers = {
      'Content-Type': 'application/x-download',
    };
    let data = option.url.data;
    option.data = data;
    option.url = reqUrl;
  }else{
    let data = option.url.data;
    data = Object.keys(data).map(item => `${item}=${encodeURI(data[item])}`).join('&');
    option.data = data;
    option.url = reqUrl;
  }
  if ( reqName != 'login' ){
    // option.headers['Content-Type'] = 'application/json;charset=UTF-8'
    // option.headers['IYunDao-AssessToken'] = sessionStorage.getItem('IYunDao-AssessToken');
    option.headers['token'] = localStorage.getItem('token');
    // option.headers['IYunDao-AssessToken'] = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjdXJyZW50VGltZU1pbGxpcyI6IjE1Njk3NDQ3MDAxOTkiLCJleHAiOjE1Njk3NTE5MDAsImFjY291bnQiOiJhZG1pbiJ9.d-7WZCk0iAjxp9hfaffZH9C-OBCPEfnzfHiY7P8htrs'
  }

  console.log(`${option.url} == request ==> `, option);

  return option
}, (err) => {
  console.log('interceptors.request == error ==> ', err);
  Message.error("请求失败,请检查网络")
  return Promise.reject(err).catch(err)
});

// http response 拦截器
http.interceptors.response.use((response) => {
  console.log(`${response.config.url} == response ==> `, response);
  try {
    let code = response.data.code;
    let message = response.data.message;
    // if (code !== 200) {
    //   throw {
    //     response: {
    //       status: code
    //     },
    //     message
    //   }
    // }
    // console.log('response',response)
    let data = response.data;

    // data = JSON.parse(data);
    // return  data;
    if (data.error==10000){
      // return Promise.resolve(data);
      return  data;
    }else {
      console.log("到1")
      //过滤这个请求
      if (response.config.url.indexOf("/login/login")<0){
        Message.error(data.message?data.message:"服务器正在开小差")
      }
      return  data;

    }



  } catch (e) {
    console.log("213",e)
    // throw {}
  }
}, (err) => {
  console.log('interceptors.response == error ==> ', err);

  if (err && err.response) {
    switch (err.response.status) {
      case 400:
        err.message = '请求错误';
        // router.push({ name: 'Status', params: { status: 400 }});
        break;
      case 401:
        err.message = '未授权，请登录';
        // router.push({ name: 'Status', params: { status: 400 }});
        break;
      case 403:
        err.message = '拒绝访问';
        // router.push({
        //   name: 'worldcupIndex'
        // });
        break;
      case 404:
        err.message = `请求地址出错: ${err.response.config.url}`;
        // router.push({ name: 'Status', params: { status: 404 }});
        break;
      case 408:
        err.message = '请求超时';
        break;
      case 500:
        err.message = '服务器内部错误';
        // router.push({ name: 'Status', params: { status: 500 }});
        break;
      case 501:
        err.message = '服务未实现';
        break;
      case 502:
        err.message = '网关错误';
        break;
      case 503:
        err.message = '服务不可用';
        break;
      case 504:
        err.message = '网关超时';
        break;
      case 505:
        err.message = 'HTTP版本不受支持';
        break;
      case 1:
        err.message = '数据解析错误';
        break;
      default:
        break
    }
  } else {
    err.message = '网络错误，请稍后再试！'
  }
  console.log("到2")
  if (err && err.response && err.response.data && err.response.data.message) {
    console.warn(err.response.data.message)
  } else {
    console.warn(err.message)
  }
  //过滤这个请求
  if (err.response.config.url.indexOf("/login/login")<0){
    Message.error(err.message?err.message:"网络错误,请稍后再试")
  }

  // let Promise = require('promise-polyfill').default
  return Promise.reject(err).catch(err)
})

export default http
