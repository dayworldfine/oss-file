<template>
  <div class="pc">
    <div class="outer">
      <div class="title">
        <div></div>
        <Button type="warning" class="button" @click="useHelp()">使用说明</Button>
        <div></div>
        如有疑问或建议：请联系管理员13738700108(微信同号)
      </div>
      <div class="content">
        <div class="user" v-if="isLogin">
          <img loading="lazy" :src="this.$urlUserImgPerfix+userImg" class="user-img"/>
          <div class="user-name">{{userNickName}}</div>
          <div class="button-all">
            <Button type="warning" class="button" @click="updateImg()">修改头像</Button>
            <Button type="warning" class="button" @click="updateName()">修改昵称</Button>
            <Button type="warning" class="button" @click="myRole()">我的角色</Button>
            <Button type="warning" class="button" @click="myZone()">我的分区</Button>
            <Button type="warning" class="button" @click="enterRole()">输入角色密匙</Button>
            <Button type="warning" class="button" @click="enterZone()">输入分区密匙</Button>
            <Button type="warning" class="button" @click="allotZone()" v-if="userRole.indexOf('superAdmin')>-1">分配分区</Button>
            <Button type="warning" class="button" @click="allotRole()" v-if="userRole.indexOf('superAdmin')>-1">分配角色</Button>
            <Button type="warning" class="button" @click="addZone()" v-if="userRole.indexOf('superAdmin')>-1">添加分区</Button>
            <Button type="warning" class="button" @click="outLogin()">退出登录</Button>
          </div>
        </div>
        <div class="user" v-if="!isLogin">
          <img loading="lazy" :src="this.$urlUserImgPerfix+userImg" class="user-img"/>
          <div class="user-name">未登录</div>
          <Button type="warning" class="button" @click="register()">注册</Button>
          <Button type="warning" class="button" @click="login()">登录</Button>
          <Button type="warning" class="button" @click="forGetPwd()">忘记密码</Button>
        </div>
        <div class="document">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <UpdateName :updateNameVisible="updateNameVisible" @confirmUpdateName="confirmUpdateName" @closeUpdateName="closeUpdateName"></UpdateName>
    <UpdateImg :updateImgVisible="updateImgVisible" @confirmUpdateImg="confirmUpdateImg" @closeUpdateImg="closeUpdateImg"></UpdateImg>
    <MyRole :myRoleVisible="myRoleVisible"  @closeMyRole="closeMyRole"></MyRole>
    <MyZone :myZoneVisible="myZoneVisible"  @closeMyZone="closeMyZone"></MyZone>
    <EnterRole :enterRoleVisible="enterRoleVisible" @confirmEnterRole="confirmEnterRole" @closeEnterRole="closeEnterRole"></EnterRole>
    <EnterZone :enterZoneVisible="enterZoneVisible" @confirmEnterZone="confirmEnterZone" @closeEnterZone="closeEnterZone"></EnterZone>
    <AddZone :addZoneVisible="addZoneVisible" @confirmAddZone="confirmAddZone" @closeAddZone="closeAddZone"></AddZone>
    <AllotZone :allotZoneVisible="allotZoneVisible" @confirmAllotZone="confirmAllotZone" @closeAllotZone="closeAllotZone"></AllotZone>
    <AllotRole :allotRoleVisible="allotRoleVisible" @confirmAllotRole="confirmAllotRole" @closeAllotRole="closeAllotRole"></AllotRole>
    <Register :registerVisible="registerVisible" @confirmRegister="confirmRegister" @closeRegister="closeRegister"></Register>
    <Login :loginVisible="loginVisible" @confirmLogin="confirmLogin" @closeLogin="closeLogin"></Login>
    <ForGetPwd :forGetPwdVisible="forGetPwdVisible" @confirmForGetPwd="confirmForGetPwd" @closeForGetPwd="closeForGetPwd"></ForGetPwd>
    <UseHelp :useHelpVisible="useHelpVisible" @confirmUseHelp="confirmUseHelp" @closeUseHelp="closeUseHelp"></UseHelp>
  </div>
</template>

<script>
  import {mapState, mapActions, mapMutations} from 'vuex'
  import LoginService from "@/service/LoginService";
  import FileService from "@/service/FileService";
  export default {
    name: "Pc",
    data() {
      return {
        img : this.$urlUserImgPerfix,
        updateNameVisible: false, //修改名称弹窗
        updateImgVisible:false, //修改头像
        myRoleVisible:false, //我的角色
        myZoneVisible:false,  //我的分区
        enterRoleVisible:false, //输入权限密匙
        enterZoneVisible:false,  //输入分区密匙
        addZoneVisible:false,   //添加分区
        allotZoneVisible:false,  //分配分区
        allotRoleVisible:false,  //分配角色
        registerVisible:false,  //注册
        loginVisible:false,     //登录
        forGetPwdVisible:false, //忘记密码
        useHelpVisible:false,   //使用说明
      }
    },
    created() {
      let account = localStorage.getItem("account");
      let passWord = localStorage.getItem("passWord");
      if (account=='' || account==undefined || passWord=='' || passWord==undefined){
        this.getZoneList({name:'',page:1,size:24});
        return;
      }
      LoginService.login({account:account,passWord:passWord}).then((res)=>{
        console.log("res",res)
        if (undefined==res){
          localStorage.removeItem("account")
          localStorage.removeItem("passWord")
          localStorage.removeItem("token");
        }
        if (10000==res.error){
          this.setIsLogin(true);
          this.setUserId(res.data.identity.userId);
          this.setUserNickName(res.data.identity.userNickName);
          this.setUserImg(res.data.identity.userImg);
          this.setUserRole(res.data.identity.userRoleName);
          localStorage.setItem("token",res.data.token);
        }else {
          localStorage.removeItem("account")
          localStorage.removeItem("passWord")
          localStorage.removeItem("token");
        }
        this.getZoneList({name:'',page:1,size:24});
      })
    },
    computed:{
      ...mapState([
        "isLogin",
        'userId',
        'userNickName',
        'userImg',
        'userRole'
      ])
    },
    methods: {
      ...mapActions([
        'sendSms',
        'getMyRolePwd',
        'getMyZonePwd',
        'getZoneList',
      ]),
      ...mapMutations([
        'setIsLogin',
        'setUserId',
        'setUserNickName',
        'setUserImg',
        'setUserRole',
        'setMyZoneList',
        'setZoneList',
        'setZonePage',
        'setZoneTotal',
        'setZoneSearchKey',
      ]),
      /** 注册*/
      register(){
        this.registerVisible=true;
      },
      /** 登录*/
      login(){
        this.loginVisible=true;
      },
      /** 忘记密码*/
      forGetPwd(){
        this.forGetPwdVisible=true;
      },
      /** 修改头像*/
      updateImg() {
        this.updateImgVisible=true;
      },
      /** 修改名称*/
      updateName(){
        this.updateNameVisible=true;
      },
      /** 我的角色*/
      myRole(){
        this.getMyRolePwd();
        this.myRoleVisible=true;
      },
      /** 我的分区*/
      myZone(){
        this.getMyZonePwd();
        this.myZoneVisible=true;
      },
      /** 输入权限密匙*/
      enterRole(){
        this.enterRoleVisible=true;
      },
      /** 输入分区密匙*/
      enterZone(){
        this.enterZoneVisible=true;
      },
      /** 添加分区*/
      addZone(){
        this.addZoneVisible=true;
      },
      /** 退出登录*/
      outLogin(){
        this.$confirm('确认退出登录吗? ', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          localStorage.clear();
          this.setIsLogin(false)
          this.setUserId('0')
          this.setUserNickName('昵称')
          this.setUserImg('/headImg/defaultUserImg.png')
          this.setUserRole([])
          this.setZonePage(1)
          this.setZoneTotal(1)
          this.setZoneSearchKey('')
          this.getZoneList({name:'',page:1,size:24});
          this.$router.replace("PcIndex")
        }).catch(() => {
        });
      },
      /** 分配分区*/
      allotZone(){
        this.allotZoneVisible=true;
      },
      /** 分配角色*/
      allotRole(){
        this.allotRoleVisible=true;
      },
      /** 使用说明*/
      useHelp(){
        this.useHelpVisible=true;
      },


      /** 修改名称事件 */
      confirmUpdateName(){
        this.updateNameVisible=false;
      },
      closeUpdateName(){
        console.log("关闭")
        this.updateNameVisible=false;
      },
      /** 修改头像事件*/
      confirmUpdateImg(){
        this.updateImgVisible=false;
      },
      closeUpdateImg(){
        this.updateImgVisible=false;
      },
      /** 查看角色事件*/
      closeMyRole(){
        this.myRoleVisible=false;
      },
      closeMyZone(){
        this.myZoneVisible=false;
      },
      /** 输入权限密匙事件 */
      confirmEnterRole(){
        console.log("确认")
        this.enterRoleVisible=false;
      },
      closeEnterRole(){
        console.log("关闭")
        this.enterRoleVisible=false;
      },
      /** 输入分区密匙事件 */
      confirmEnterZone(){
        console.log("确认")
        this.enterZoneVisible=false;
      },
      closeEnterZone(){
        console.log("关闭")
        this.enterZoneVisible=false;
      },
      /** 添加分区事件 */
      confirmAddZone(){
        console.log("确认")
        this.addZoneVisible=false;
      },
      closeAddZone(){
        console.log("关闭")
        this.addZoneVisible=false;
      },
      /** 分配分区事件*/
      confirmAllotZone(){
        this.allotZoneVisible=false;
      },
      closeAllotZone(){
        this.allotZoneVisible=false;
      },
      /**分配角色事件*/
      confirmAllotRole(){
        this.allotRoleVisible=false;
      },
      closeAllotRole(){
        this.allotRoleVisible=false;
      },
      /** 注册事件*/
      confirmRegister(){
        this.registerVisible=false;
      },
      closeRegister(){
        this.registerVisible=false;
      },
      /** 登录*/
      confirmLogin(){
        this.loginVisible=false;
      },
      closeLogin(){
        this.loginVisible=false;
      },
      /** 忘记密码*/
      confirmForGetPwd(){
        this.forGetPwdVisible=false;
      },
      closeForGetPwd(){
        this.forGetPwdVisible=false;
      },
      /** 使用说明*/
      confirmUseHelp(){
        this.useHelpVisible=false;
      },
      closeUseHelp(){
        this.useHelpVisible=false;
      }
    }
  }
</script>

<style scoped>
  .pc {
    width: 1920px;
    /*height: 1080px;*/
    /*box-sizing: border-box;*/
    margin: 0;
    padding: 0;
    /*height: 100%;*/
    height: 100vh;
    /*width: 100%;*/
    min-width: 1024px;
    overflow-y: hidden;
    background-color: #FFFFFF;
  }

  .outer {
    margin: 50px;
    width: 1820px;
    /*width: 95%;*/
    /*height: 880px;*/
    border: 1px solid #e0e1e1;
    display: flex;
    flex-direction: column;
  }

  .title {
    height: 100px;
    padding: 20px;
    font-size: 16px;
    box-sizing: border-box;
    /*border: 1px solid salmon;*/
    /*background-color: rgb(238, 240, 246);*/
    background-color: rgb(238, 240, 246);
  }

  .content {
    display: flex;
    flex-direction: row;
    height: 75vh;
  }

  .user {
    width: 320px;
    min-width: 320px;
    box-sizing: border-box;
    padding-top: 30px;
    box-sizing: border-box;
    /*border: 1px solid salmon;*/
    border: 1px solid rgb(232, 234, 240);
    background-color: rgb(249, 250, 251);
  }

  .user-name {
    margin: 20px 0px 20px 0px;
    font-size: 16px;
    color: #42b983;
  }

  .user-img {
    width: 100px;
    height: 100px;
  }

  .button-all {
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: space-around;
    align-items: flex-start;
    box-sizing: border-box;
  }

  .button {
    margin-top: 30px;
    width: 130px;
  }

  .document {
    box-sizing: border-box;
  }

</style>
