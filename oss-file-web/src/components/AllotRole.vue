<template>
  <div>
    <el-dialog
      title="分配角色"
      :visible.sync="showBoolean"
      @close="close()"
      width="40%"
      center>
      <div class="allotRole">
        <div class="allotRole-top">
          <div class="allotRole-user">
            <div class="allotRole-searchInput">
              <input v-model="userKey" placeholder="昵称/手机号" class="allotRoleInput"></input>
              <Button type="warning" class="search" @click="searchUser()">搜索</Button>
            </div>
            <div class="allotRole-forDiv" @scroll="scrollUser">
              <div v-for="(item,index) in userList" :key="index" class="allotRole-for">
                <span class="allotRole-for-name">{{item.nickName}}</span>
                <img :src="img+item.headPortrait" class="allotRole-for-img"/>
                <span class="allotRole-for-account">{{item.account}}</span>
                <el-checkbox v-model="item.checked"></el-checkbox>
              </div>
            </div>
          </div>
          <div class="allotRole-center"><i class="el-icon-mobile-phone"></i></div>
          <div class="allotRole-zone">
            <div>
              <input v-model="roleKey" placeholder="分区名称" class="allotRoleInput"></input>
              <Button type="warning" class="search" @click="searchRole()">搜索</Button>
            </div>
            <div class="allotRole-forDiv-zone" @scroll="scrollZone">
              <div v-for="(item,index) in roleList" :key="index" class="allotRole-for">
                <span class="allotRole-for-width">{{item.roleName}}</span>
                <span class="allotRole-for-width">{{item.code}}</span>
                <el-checkbox v-model="item.checked"></el-checkbox>
              </div>
            </div>
          </div>
        </div>
        <div class="allotRole-button-all">
          <Button @click="cancel()" class="allotRole-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="allotRole-button">确 定</Button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import UserService from "@/service/UserService";
    import ZoneService from "@/service/ZoneService";
    import RoleService from "@/service/RoleService";

    export default {
        name: "AllotRole",
      props: {
        allotRoleVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          img:this.$urlUserImgPerfix,
          userKey: '',
          userPage:1,
          userSize:11,
          userList:[],
          roleKey:'',
          rolePage:1,
          roleSize:11,
          roleList:[],
          showBoolean: false,
          checked:false
        };
      },
      watch: {
        allotRoleVisible(bool) {
          this.showBoolean = bool;
          if (bool){
            this.searchUser();
            this.searchRole();
          }else {
            this.userKey='';
            this.userPage=1;
            this.userList=[];
            this.roleKey='';
            this.rolePage=1;
            this.roleList=[];
          }
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeAllotRole");
        },
        confirm() {
          let userIdList = this.userList.filter(a=> a.checked).map(p=>p.id+'');
          let roleIdList = this.roleList.filter(a=> a.checked).map(p=>p.id+'');
          console.log("userIdList",userIdList)
          console.log("roleIdList",roleIdList)
          RoleService.allotRole(
            {
              userIdList:userIdList,
              roleIdList:roleIdList,
            }
          ).then((res)=>{
            if (10000==res.error){
              this.$message.success("分配成功")
              this.$emit("confirmAllotRole");
            }
          });

        },
        /** 搜索用户*/
        searchUser(){
          this.userPage=1;
          console.log(this.userKey,this.userPage,this.userSize);
          UserService.queryUserByParam(
            {paramKey: this.userKey,
              page: this.userPage,
              size: this.userSize}
          ).then((res)=>{
            if (10000==res.error){
              res.data.forEach(a=>{
                a['checked']=false;
              })
              this.userList=res.data;
            }
          });
        },
        /** 搜索角色*/
        searchRole(){
          this.rolePage=1;
          console.log(this.roleKey,this.rolePage,this.roleSize);
          RoleService.getRoleList(
            {name: this.roleKey,
              page: this.rolePage,
              size: this.roleSize}
          ).then((res)=>{
            console.log("res",res)
            if (10000==res.error){
              res.data.forEach(a=>{
                a['checked']=false;
              })
              this.roleList=res.data;
            }
          });
        },
        /** 滚轮到底部用户*/
        scrollUser(e){
          console.log("e4", e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight);
          let height = e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight;
          if (height<=0){
            UserService.queryUserByParam(
              {paramKey: this.userKey,
                page: this.userPage+1,
                size: this.userSize}
            ).then((res)=>{
              if (10000==res.error){
                this.userPage =this.userPage+1;
                res.data.forEach(a=>{
                  a['checked']=false;
                })
                this.userList=this.userList.concat(res.data);
              }
            });
          }
        },
        /** 滚轮到底部分区*/
        scrollZone(e){
          let height = e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight;
          if (height<=0){
            RoleService.getRoleList(
              {name: this.roleKey,
                page: this.rolePage,
                size: this.roleSize}
            ).then((res)=>{
              console.log("res",res)
              if (10000==res.error){
                res.data.forEach(a=>{
                  a['checked']=false;
                })
                this.roleList=res.data;
              }
            });
          }
        }

      }
    }
</script>

<style scoped>
  .allotRole {
    display: flex;
    flex-direction: column;
  }
  .allotRole-top{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .allotRole-user{
    display: flex;
    flex-direction: column;
  }
  .allotRole-searchInput{
    display: flex;

  }
  .search{
    height: 28px;
    margin-left: 5px;
  }

  .allotRoleInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    /*color: #bfae90;*/
    height: 20px;
    width: 250px;
    font-family: "Microsoft soft";
    /*margin-left: 30px;*/
  }
  .allotRole-center{
    margin-top: 130px;
  }
  .allotRole-forDiv{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotRole-for-width{
    width: 100px;
  }
  .allotRole-forDiv-zone{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotRole-for{
    display: flex;
    justify-content: space-around;
    height: 40px;
    line-height: 40px;
    background-color: #eaeaea;
    border: 1px solid #b7b7b7;
    box-sizing: border-box;
  }
  .allotRole-for-name{
    width: 60px;
  }
  .allotRole-for-account{
    width: 100px;
  }
  .allotRole-for-img{
    width: 40px;
    height: 40px;
  }

  .allotRole-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .allotRole-button {
    margin-right: 22px;
  }
</style>
