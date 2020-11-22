
export const userLogin = (state, data) =>{
  state.userInfo = data
}

export const setCode = (state, data) =>{
  state.code = data
}

/** 改变验证码倒计时*/
export const setCountDown = (state, data) =>{
  state.countDown = data
}
