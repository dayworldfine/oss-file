import http from '@/util/http'


const joinActivity = (param = {}) =>{
   return   http.post({url:'positivity/activityScore', data: param})
}

export default {
  joinActivity,
}
