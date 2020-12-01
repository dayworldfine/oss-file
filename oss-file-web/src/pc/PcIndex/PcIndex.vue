<template>
  <div class="PcIndex">
    <div class="fun-button-all">
      <div class="fun-button" @click="goToZone()">
        <img class="button-img" src="static/go.png"/>
        <div class="fun-button-text" >进入分区</div>
      </div>
<!--      <div class="fun-button" v-show="userRole.indexOf('superAdmin')>=0">-->
<!--        <img class="button-img" src="static/add.png"/>-->
<!--        <div class="fun-button-text">添加分区</div>-->
<!--      </div>-->
      <div class="fun-button" v-show="userRole.indexOf('superAdmin')>=0" @click="delZone()">
        <img class="button-img" src="static/delete.png"/>
        <div class="fun-button-text" >删除分区</div>
      </div>
      <div class="fun-search" :class="userRole.indexOf('superAdmin')>-1?'fun-search-superAdmin':'fun-search-general'"
          >
        <input v-model="zoneSearch"  placeholder="请输入分区名称" class="fun-search-input"/>
        <img class="fun-search-img" src="static/search.png"  @click="search()" />
      </div>
    </div>
    <div class="forAll">
      <div class="document-for" v-for="(item,index) in zoneList" :key="index"
           :class="putOnIndex==index?'document-for-putOn':''"
           @click="pichOn(index)"
           @dblclick="goToZone()">
        <img loading="lazy"
             src="static/file.png"
             class="user-img" />
        <div class="document-for-font">{{item.zoneName}}</div>
      </div>
    </div>
    <div class="file-page">
      <Page :current="zonePage" :total="zoneTotal" :page-size="zoneSize" @on-change="changePage"   simple/>
    </div>


  </div>
</template>

<script>
  import {mapActions,mapState,mapGetters,mapMutations} from "vuex";
  import ZoneService from "@/service/ZoneService";

  export default {
    name: "PcIndex",
    data(){
      return{
        zoneSearch:'',
        zoneSize:24,
        putOnIndex:-1,
        fileList:[
          {
            name:121,
            type:12312,
          },
          {
            name:121,
            type:12312,
          },
        ]
      }
    },
    created() {
      localStorage.removeItem('zoneId')
    },
    watch:{
      zoneSearch(value1){
        this.setZoneSearchKey(value1);
      }
    },
    computed:{
      ...mapState([
        "userRole",
        'zoneList',
        'zonePage',
        'zoneTotal',
        'zoneSearchKey',
      ])
    },
    methods:{
      ...mapActions([
        'sendSms',
        'getZoneList',
      ]),
      ...mapMutations([
        'setZoneList',
        'setZoneSearchKey',
        'setPresentZoneId',
        'setZonePage'
      ]),
      /** 当前选中状态*/
      pichOn(index){
        this.putOnIndex =index;
        console.log("pichOn",index)
      },
      /** 进入分区*/
      goToZone(){
        if (this.putOnIndex<0){
          //意外才会走到这里
          return;
        }
        localStorage.setItem("zoneId",this.zoneList[this.putOnIndex].id)
        this.$router.push({name:"PcDetail",query:{zoneId:this.zoneList[this.putOnIndex].id}});
      },
      /** 删除分区*/
      delZone(){
        if (this.putOnIndex<0){
          this.$message.warning("请选择分区")
          return;
        }
        this.$confirm('此操作将删除分区及该分区所有文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          ZoneService.deleteZoneById({zoneId:this.zoneList[this.putOnIndex].id}).then((res)=>{
            console.log("res",res)
            if (10000==res.error){
              this.$message.success("删除成功")
              this.getZoneList({name:this.zoneSearch,page:this.zonePage,size:this.zoneSize})
            }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      /** 搜索事件*/
      search(){
        this.setZonePage(1)
        this.getZoneList({name:this.zoneSearch,page:this.zonePage,size:this.zoneSize})
      },
      /** 页面事件发生改变*/
      changePage(page){
        console.log("页面",page)
        this.setZonePage(page)
        this.getZoneList({name:this.zoneSearch,page:this.zonePage,size:this.zoneSize})
      },
    }
  }
</script>

<style scoped >
  .PcIndex{
    /*width: 100%;*/
    height: 100vh;
    width: 1500px;
    box-sizing: border-box;
  }
  .fun-button-all{
    height: 50px;
    /*border: 1px solid salmon;*/
    border: 1px solid rgb(240, 240, 240);
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
  }
  .fun-button{
    /*padding: 5px 20px 5px 20px;*/
    padding: 2.5px 10px 2.5px 10px;
    margin: 5px 10px 5px 10px;
    display: flex;
    cursor:pointer;
    justify-content: flex-start;
  }
  .fun-button:hover{
    background-color: rgb(218, 245, 255);
    border: 1px solid rgb(55, 213, 255);
  }
  .fun-search{
    position: relative;
    display: flex;
    justify-content: flex-start;
    padding-top: 10px;
  }
  .fun-search-superAdmin{
    margin-left: 910px;
  }
  .fun-search-general{
    margin-left: 1060px;
  }
  .fun-search-input{
    outline-style: none ;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    /*color: #bfae90;*/
    height: 20px;
    font-family: "Microsoft soft";
  }
  .fun-search-img{
    width: 25px;
    height: 25px;
    margin-left: 10px;
    cursor:pointer;
    /*margin-top: 10px;*/
  }
  .fun-search-img:active{
    cursor:pointer;
    background-color: rgb(218, 245, 255);
    border: 1px solid rgb(55, 213, 255);
  }
  .fun-button-text{
    margin-left: 20px;
    margin-top: 5px;
    text-align: left;
    width: 70px;
  }
  .button-img{
    width: 30px;
    height: 30px;
  }
  .forAll{
    width: 1500px;
    display: flex;
    flex-wrap: wrap;
    flex-direction: row;
    justify-content: flex-start;
    padding: 10px 30px 10px 30px;
    box-sizing: border-box;
    align-content:flex-start;
    height: 65vh;
    overflow-y: hidden;
  }
  .document-for {
    width: 160px;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    cursor:pointer;
    margin: 10px;
    height: 17vh;
  }
  .document-for:hover{
    background-color:rgb(242, 250, 255);
  }
  .document-for-putOn{
    background-color: rgb(218, 245, 255);
    border: 1px solid rgb(55, 213, 255);
  }
  .document-for-font{
    margin-bottom: 10px;
  }
  .user-img {
    width: 80px;
    height: 80px;
    margin: 20px 40px 20px 40px;
    box-sizing: border-box;
  }
  .button-file {
    display: flex;
    flex-direction: row;
    box-sizing: border-box;
  }
  .file-page{
    display: flex;
    justify-content: flex-end;
    padding-right: 40px;
    margin-bottom: 30px;
  }
</style>
