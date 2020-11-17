import LoginService from '@/service/LoginService'


export function sendSms({commit}) {
  LoginService.sendSms().then((res)=>{
    console.log("this is getList work",res)
    commit('getCode',1)
  })
}

// export function getUserListByDepartId({commit},param) {
//   UserService.getUserListByDepartId(param).then((res)=>{
//     commit('setUserList',res)
//   })
// }
