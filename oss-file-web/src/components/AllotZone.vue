<template>
  <div>
    <el-dialog
      title="分配分区"
      :visible.sync="showBoolean"
      @close="close()"
      width="40%"
      center>
      <div class="allotZone">
        <div class="allotZone-top">
          <div class="allotZone-user">
            <div class="allotZone-searchInput">
              <input v-model="userKey" placeholder="昵称/手机号" class="allotZoneInput"></input>
              <Button type="warning" class="search" @click="searchUser()">搜索</Button>
            </div>
            <div class="allotZone-forDiv" @scroll="scrollUser">
              <div v-for="(item,index) in userList" :key="index" class="allotZone-for">
                <span class="allotZone-forDiv-name">{{item.nickName}}</span>
                <img loading="lazy" :src="img+item.headPortrait" class="allotZone-for-img"/>
                <span class="allotZone-forDiv-account">{{item.account}}</span>
                <el-checkbox v-model="item.checked"></el-checkbox>
              </div>
            </div>
          </div>
          <div class="allotZone-center"><i class="el-icon-mobile-phone"></i></div>
          <div class="allotZone-zone">
            <div>
              <input v-model="zoneKey" placeholder="分区名称" class="allotZoneInput"></input>
              <Button type="warning" class="search"  @click="searchZone()">搜索</Button>
            </div>
            <div class="allotZone-forDiv-zone" @scroll="scrollZone">
              <div v-for="(item,index) in zoneList" :key="index" class="allotZone-for">
                <span class="allotZone-for-width">{{item.zoneName}}</span>
                <span class="allotZone-for-width">{{item.zonePrefix}}</span>
                <el-checkbox v-model="item.checked"></el-checkbox>
              </div>
            </div>
          </div>
        </div>
        <div class="allotZone-button-all">
          <Button @click="cancel()" class="allotZone-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="allotZone-button">确 定</Button>
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
        name: "AllotZone",
      props: {
        allotZoneVisible: {
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
          zoneKey:'',
          zonePage:1,
          zoneSize:11,
          zoneList:[],
          showBoolean: false,
          checked:false,
        };
      },
      watch: {
        allotZoneVisible(bool) {
          this.showBoolean = bool;
          if (bool){
            this.searchUser();
            this.searchZone();
          }else {
            this.userPage=1;
            this.userList=[];
            this.zonePage=1;
            this.zoneList=[];
          }

        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeAllotZone");
        },
        confirm() {
          let userIdList = this.userList.filter(a=> a.checked).map(p=>p.id+'');
          let zoneIdList = this.zoneList.filter(a=> a.checked).map(p=>p.id+'');
          console.log("userIdList",userIdList)
          console.log("zoneIdList",zoneIdList)
          RoleService.allotZone(
            {
              userIdList:userIdList,
              zoneIdList:zoneIdList,
              }
            ).then((res)=>{
              if (10000==res.error){
                this.$message.success("分配成功")
                this.$emit("confirmAllotZone", this.name);
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
        /** 搜索分区*/
        searchZone(){
          this.zonePage=1;
          console.log(this.zoneKey,this.zonePage,this.zoneSize);
          ZoneService.queryZoneByParam(
            {name: this.zoneKey,
              page: this.zonePage,
              size: this.zoneSize}
          ).then((res)=>{
            console.log("res",res)
            if (10000==res.error){
              res.data.forEach(a=>{
                a['checked']=false;
              })
              this.zoneList=res.data;
            }
          });
        },
        /** 用户下拉到底触发事件*/
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
        /** 分区下拉到底触发事件*/
        scrollZone(e){
          console.log("e4", e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight);
          let height = e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight;
          if (height<=0){
            ZoneService.queryZoneByParam(
              {name: this.zoneKey,
                page: this.zonePage+1,
                size: this.zoneSize}
            ).then((res)=>{
              console.log("res",res)
              if (10000==res.error){
                this.zonePage =this.zonePage+1;
                res.data.forEach(a=>{
                  a['checked']=false;
                })
                this.zoneList=this.zoneList.concat(res.data);
              }
            });
          }
        }


      }
    }
</script>

<style scoped>
  .allotZone {
    display: flex;
    flex-direction: column;
  }
  .allotZone-top{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .allotZone-user{
    display: flex;
    flex-direction: column;
  }
  .allotZone-searchInput{
    display: flex;

  }
  .search{
    height: 28px;
    margin-left: 5px;
  }

  .allotZoneInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #bfae90;
    height: 20px;
    width: 250px;
    font-family: "Microsoft soft";
    /*margin-left: 30px;*/
  }
  .allotZone-center{
    margin-top: 130px;
  }
  .allotZone-forDiv{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotZone-forDiv-zone{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotZone-for-width{
    width: 100px;
  }
  .allotZone-for{
    display: flex;
    justify-content: space-around;
    height: 40px;
    line-height: 40px;
    background-color: #eaeaea;
    border: 1px solid #b7b7b7;
    box-sizing: border-box;
  }
  .allotZone-for-img{
    width: 40px;
    height: 40px;
  }
  .allotZone-forDiv-name{
    width: 60px;
  }
  .allotZone-forDiv-account{
    width: 100px;
  }

  .allotZone-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .allotZone-button {
    margin-right: 22px;
  }
</style>
