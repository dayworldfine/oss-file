const state = {
  isLogin: 0 ,                        // 登录状态
  userId:'0',                          //用户id
  userNickName:'昵称',                  //用户昵称
  userImg:"/headImg/defaultUserImg.png",                          //用户头像
  userRole:[],             //权限superAdmin
  countDown:0,                        //验证码倒计时
  myRoleList:[],                       //我的角色
  myZoneList:[],                       //我的角色
  zoneList:[],                           //分区列表
  zonePage:1,                             //分区页数
  zoneTotal:0,                            //分区总条数
  zoneSearchKey:'',                       //分区搜索关键字
  filePage:1,                             //文件页数
  fileTotal:0,                            //文件总条数
  fileList:[],                          //文件列表
  // presentZoneId:-1,                     //当前的分区id
}

export default state
