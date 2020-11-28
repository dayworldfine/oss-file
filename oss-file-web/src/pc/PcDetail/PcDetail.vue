<template>
  <div class="PcDetail">
    <div class="fun-button-all">
      <div class="fun-button"  @click="goBack()">
        <img class="button-img" src="/static/goBack.png"/>
        <div class="fun-button-text">后退</div>
      </div>
      <div class="fun-button" @click="downloadFile()">
        <img class="button-img" src="/static/download.png"/>
        <div class="fun-button-text">下载</div>
      </div>
      <div class="fun-button" @click="previewFile()">
        <img class="button-img" src="/static/preview.png"/>
        <div class="fun-button-text">在线预览</div>
      </div>
      <div class="fun-button" @click="uploadFile()">
        <img class="button-img" src="/static/upload.png"/>
        <div class="fun-button-text">上传</div>
      </div>
      <div class="fun-button" @click="delFile()">
        <img class="button-img" src="/static/delete.png"/>
        <div class="fun-button-text">删除</div>
      </div>
      <div class="fun-search" >
        <input v-model="searchName" placeholder="请输入文件名称" class="fun-search-input"/>
        <img class="fun-search-img" src="/static/search.png" @click="searchFile()"/>

      </div>
    </div>
    <div class="forAll">
      <div class="document-for" v-for="(item,index) in fileList" :key="index"
           :class="putOnIndex==index?'document-for-putOn':''"
           @click="pichOn(index)">
        <img loading="lazy"
             :src="fileSuffix(12)"
             class="user-img" />
        <div class="document-for-font">{{item.fileName+item.suffix}}</div>
        <div class="document-for-font">下载预览量：{{Number(item.downloadStatistics)+Number(item.previewStatistics)}}</div>
        <!--      <div class="button-file">-->
        <!--        <Button type="warning" class="button">下载</Button>-->
        <!--        <Button type="warning" class="button">预览</Button>-->
        <!--        <Button type="warning" class="button">删除</Button>-->
        <!--      </div>-->
      </div>
    </div>
    <div class="file-page">
      <Page :current="filePage" :total="fileTotal" :page-size="fileSize" @on-change="changePage" simple/>
    </div>
    <UploadFile :uploadFileVisible="uploadFileVisible" @confirmUploadFile="confirmUploadFile" @closeUploadFile="closeUploadFile"></UploadFile>

  </div>
</template>

<script>
  import {mapActions,mapState,mapGetters,mapMutations} from "vuex";
  import {exportExcel} from '@/util/DownloadUtil'
  import FileService from "@/service/FileService";
  import DocumentService from "@/service/DocumentService";
  import {checkDocumentType} from '@/util/ValidateUtil'
  import ZoneService from "@/service/ZoneService";
    export default {
        name: "PcDetail",
      created() {
        this.getFileList(
          {
            zoneId:localStorage.getItem("zoneId"),
            name:this.searchName,
            page:this.filePage,
            size:this.fileSize,
          }
        )
      },
      data(){
        return{
          uploadFileVisible:false, //修改头像
          fileSize:24,
          putOnIndex:-1,
          searchName:'',
        }
      },
      //计算属性
      computed:{
        ...mapState([
          'filePage',
          'fileTotal',
          'fileList',
        ]),
        fileSuffix(){
          return (type)=>{
            let prefix ="/static/";
            let suffix ="";
            switch (type) {
                case "jpg":
                  suffix ="excel.png";
                break;
                default:
                  suffix ="zip.png";
                  break;
            }
            return prefix+suffix;

          };
        }
      },
      methods:{
        ...mapActions([
          'getFileList'
        ]),
        /** 修改头像事件*/
        confirmUploadFile(){
          this.uploadFileVisible=false;
        },
        closeUploadFile(){
          this.getFileList(
            {
              zoneId:localStorage.getItem("zoneId"),
              name:this.searchName,
              page:this.filePage,
              size:this.fileSize,
            }
          )
          this.uploadFileVisible=false;
        },
        /** 当前选中状态*/
        pichOn(index){
          this.putOnIndex =index;
          console.log("pichOn",index)
        },
        /** 返回*/
        goBack(){
          this.$router.go(-1);
        },
        /** 下载文件*/
        downloadFile(){
          if (this.putOnIndex<0){
            this.$message.warning("请选择文件")
            return;
          }

          exportExcel("download"+ this.fileList[this.putOnIndex].url,
            this.fileList[this.putOnIndex].fileName + this.fileList[this.putOnIndex].suffix,{})
          // exportExcel("download/headImg/wbh.jpg","wbh.jpg",{})

          FileService.downloadFile({fileId:this.fileList[this.putOnIndex].id}).then((res)=>{
            if (10000==res.error){
              this.fileList[this.putOnIndex].downloadStatistics=Number(this.fileList[this.putOnIndex].downloadStatistics)+1+"";
            }
          })
        },
        /** 在线预览*/
        previewFile(){
          if (this.putOnIndex<0){
            this.$message.warning("请选择文件")
            return;
          }
          if (!checkDocumentType(this.fileList[this.putOnIndex].suffix)){
            this.$message.warning("该文档不支持在线预览，请下载使用")
            return;
          }
          let details = this.$router.resolve({
            name: "PcPreview",
            query: {
              fileId:this.fileList[this.putOnIndex].id
            },
          });
          window.open(details.href, "_blank")

        },
        /** 上传文件*/
        uploadFile(){
          this.uploadFileVisible=true;
        },
        /** 删除文件*/
        delFile(){
          if (this.putOnIndex<0){
            this.$message.warning("请选择文件")
            return;
          }
          this.$confirm('确认要删除文件? ', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            FileService.delFileById({fileId:this.fileList[this.putOnIndex].id}).then((res)=>{
              if (10000==res.error){
                this.$message.success("删除成功")
                this.getFileList({zoneId:localStorage.getItem("zoneId"), name:this.searchName, page:this.filePage, size:this.fileSize})
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        },
        /** 搜素文件*/
        searchFile(){
          this.getFileList({zoneId:localStorage.getItem("zoneId"), name:this.searchName, page:this.filePage, size:this.fileSize})
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
