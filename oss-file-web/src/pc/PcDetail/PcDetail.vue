<template>
  <div class="PcDetail">
    <div class="fun-button-all">
      <div class="fun-button">
        <img class="button-img" src="/static/goBack.png"/>
        <div class="fun-button-text" @click="goBack()">后退</div>
      </div>
      <div class="fun-button">
        <img class="button-img" src="/static/download.png"/>
        <div class="fun-button-text">下载</div>
      </div>
      <div class="fun-button">
        <img class="button-img" src="/static/preview.png"/>
        <div class="fun-button-text">在线预览</div>
      </div>
      <div class="fun-button">
        <img class="button-img" src="/static/upload.png"/>
        <div class="fun-button-text">上传</div>
      </div>
      <div class="fun-button">
        <img class="button-img" src="/static/delete.png"/>
        <div class="fun-button-text">删除</div>
      </div>
      <div class="fun-search">
        <input  placeholder="请输入分区名称" class="fun-search-input"/>
        <img class="fun-search-img" src="/static/search.png"/>

      </div>
    </div>
    <div class="forAll">
      <div class="document-for" v-for="(item,index) in 30" :key="index"
           :class="putOnIndex==index?'document-for-putOn':''"
           @click="pichOn(index)">
        <img loading="lazy"
             src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg"
             class="user-img" />
        <div class="document-for-font">分区名字</div>
        <div class="document-for-font">下载预览量：1</div>
        <!--      <div class="button-file">-->
        <!--        <Button type="warning" class="button">下载</Button>-->
        <!--        <Button type="warning" class="button">预览</Button>-->
        <!--        <Button type="warning" class="button">删除</Button>-->
        <!--      </div>-->
      </div>
    </div>
    <div class="file-page">
      <Page :total="100"  @on-change="changePage"   simple/>
    </div>


  </div>
</template>

<script>
    export default {
        name: "PcDetail",
      data(){
        return{
          putOnIndex:-1,
          fileList:[
            {
              name:121,
              type:12312,
            },
            {
              name:121,
              type:12312,
            }
          ]
        }
      },
      methods:{
        /** 当前选中状态*/
        pichOn(index){
          this.putOnIndex =index;
          console.log("pichOn",index)
        },
        /** 进入分区*/
        goBack(){
          console.log("goBack")
        },
        /** 添加分区*/
        addZone(){
          console.log("addZone")
        },
        /** 页面事件发生改变*/
        changePage(page){
          console.log("页面",page)
        },
      }
    }
</script>

<style scoped>
  .PcDetail{
    /*width: 100%;*/
    height: 100%;
    width: 1500px;
    box-sizing: border-box;
  }
  .fun-button-all{
    height: 50px;
    border: 1px solid salmon;
    display: flex;
    flex-direction: row;
    /*justify-content: flex-start;*/
  }
  .fun-button{
    /*padding: 5px 20px 5px 20px;*/
    padding: 2.5px 10px 2.5px 10px;
    margin: 5px 10px 5px 10px;
    display: flex;
    cursor:pointer;
  }
  .fun-button:hover{
    background-color: rgb(218, 245, 255);
    border: 1px solid rgb(55, 213, 255);
  }
  .fun-search{
    position: absolute;
    right: 65px;
    display: flex;
    justify-content: flex-start;
    padding-top: 10px;
  }
  .fun-search-input{
    outline-style: none ;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #bfae90;
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
  }
  .document-for {
    width: 160px;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    cursor:pointer;
    margin: 10px;
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
