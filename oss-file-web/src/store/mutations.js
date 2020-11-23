
export const setCode = (state, data) =>{
  state.code = data
}

/** 改变验证码倒计时*/
export const setCountDown = (state, data) =>{
  state.countDown = data
}

/** 改变用户登录状态*/
export const setIsLogin = (state, data) =>{
  state.isLogin = data
}

/** 改变用户id*/
export const setUserId = (state, data) =>{
  state.userId = data
}

/** 改变用户名称*/
export const setUserNickName = (state, data) =>{
  state.userNickName = data
}

/** 改变用户头像*/
export const setUserImg = (state, data) =>{
  state.userImg = data
}

/** 改变用户权限*/
export const setUserRole = (state, data) =>{
  state.userRole = data
}
