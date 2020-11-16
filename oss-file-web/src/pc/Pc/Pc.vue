<template>
  <div class="pc">
    <div class="outer">
      <div class="title">欢迎</div>
      <div class="content">
        <div class="user">
          <img loading="lazy" src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg" class="user-img"/>
          <div class="user-name">王某</div>
          <div class="button-all">
            <Button type="warning" class="button" @click="updateImg()">修改头像</Button>
            <Button type="warning" class="button" @click="updateName()">修改昵称</Button>
            <Button type="warning" class="button" @click="myRole()">我的角色</Button>
            <Button type="warning" class="button">我的分区</Button>
            <Button type="warning" class="button" @click="enterRole()">输入权限密匙</Button>
            <Button type="warning" class="button" @click="enterZone()">输入分区密匙</Button>
            <Button type="warning" class="button">分配分区</Button>
            <Button type="warning" class="button">分配角色</Button>
            <Button type="warning" class="button" @click="addZone()">添加分区</Button>
          </div>
        </div>
        <div class="document">
          <router-view></router-view>
        </div>
      </div>
    </div>
    <update-name :updateNameVisible="updateNameVisible" @confirmUpdateName="confirmUpdateName" @closeUpdateName="closeUpdateName"></update-name>
    <MyRole :myRoleVisible="myRoleVisible"  @closeMyRole="closeMyRole"></MyRole>
    <EnterRole :enterRoleVisible="enterRoleVisible" @confirmEnterRole="confirmEnterRole" @closeEnterRole="closeEnterRole"></EnterRole>
    <EnterZone :enterZoneVisible="enterZoneVisible" @confirmEnterZone="confirmEnterZone" @closeEnterZone="closeEnterZone"></EnterZone>
    <AddZone :addZoneVisible="addZoneVisible" @confirmAddZone="confirmAddZone" @closeAddZone="closeAddZone"></AddZone>
  </div>
</template>

<script>
  export default {
    name: "Pc",
    data() {
      return {
        updateNameVisible: false, //修改名称弹窗
        myRoleVisible:false, //我的角色
        enterRoleVisible:false, //输入权限密匙
        enterZoneVisible:false,  //输入分区密匙
        addZoneVisible:false,   //添加分区
      }
    },
    methods: {
      /** 修改头像*/
      updateImg() {
        console.log("sxzc")
      },
      /** 修改名称*/
      updateName(){
        this.updateNameVisible=true;
      },
      /** 我的角色*/
      myRole(){
        this.myRoleVisible=true;
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



      /** 修改名称事件 */
      confirmUpdateName(name){
        console.log("确认",name)
        this.updateNameVisible=false;
      },
      closeUpdateName(){
        console.log("关闭")
        this.updateNameVisible=false;
      },
      /** 查看角色事件*/
      closeMyRole(){
        this.myRoleVisible=false;
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
    }
  }
</script>

<style scoped>
  .pc {
    width: 1920px;
    /*height: 1080px;*/
    height: 100%;
    /*box-sizing: border-box;*/
    margin: 0;
    padding: 0;
    height: 100%;
    width: 100%;
    overflow: hidden;
    background-color: #FFFFFF;
  }

  .outer {
    margin: 50px;
    width: 1820px;
    /*height: 880px;*/
    border: 1px solid #c2abab;
    display: flex;
    flex-direction: column;
  }

  .title {
    height: 100px;
    padding: 20px;
    font-size: 16px;
    box-sizing: border-box;
    /*border: 1px solid salmon;*/
    background-color: rgb(238, 240, 246);
  }

  .content {
    display: flex;
    flex-direction: row;
  }

  .user {
    width: 320px;
    box-sizing: border-box;
    padding-top: 30px;
    box-sizing: border-box;
    border: 1px solid salmon;
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
    /*width: 1500px;*/
    /*display: flex;*/
    /*flex-wrap: wrap;*/
    /*flex-direction: row;*/
    /*justify-content: space-around;*/
  }

</style>
