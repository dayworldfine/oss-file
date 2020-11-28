<template>
  <div>
    <el-dialog
      title="上传文件"
      :visible.sync="showBoolean"
      @close="close()"
      width="30%"
      center>
      <div class="uploadFile">
        <el-upload
          class="upload-demo"
          ref="upload"
          :multiple="true"
          :action=action
          :headers=header
          :on-success="handleSuccess"
          :on-change="handleChange"
          :on-error="handleError"
          :file-list="fileList"
          :auto-upload="false"
          :data="uploadData"
          name="file">
          <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
          <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
          <!--      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
        </el-upload>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {mapMutations} from "vuex";

    export default {
        name: "UploadFile",
      props: {
        uploadFileVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          header:{token:localStorage.getItem("token")},
          action:this.$BaseUrl.URL_HTTP_PREFIX+'/file/uploadFile',
          name: '',
          showBoolean: false,
          fileList:[],
          errorList:[],
          successList:[],
          uploadData:{zoneId:localStorage.getItem('zoneId')}
        };
      },
      watch: {
        uploadFileVisible(bool) {
          this.showBoolean = bool;
          if (!bool){
            this.fileList=[]
            this.errorList=[]
            this.successList=[]
          }
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeUploadFile");
        },
        confirm() {
          console.log("上传触发事件",this.fileList)
          if (this.fileList.length==0){
            this.$message.warning("请选择文件")
            return;
          }
          this.$refs.upload.submit();
          // this.$emit("confirmUploadFile", this.name);
        },
        submitUpload() {
          console.log("点击上传到服务器fileList",this.fileList)
          this.$refs.upload.submit();
        },

        handleSuccess(response,file,fileList){
          this.fileList=fileList
          console.log(" this.fileList", this.fileList)
          console.log(response,file, fileList);
          if (10000==response.error ){
            this.successList.unshift(file.name+" 上传成功")
            // this.$message.success("上传成功")
            this.$notify({
              title: "上传成功",
              message: "文件名为："+file.name+" 上传成功",
            });
          }else {
            this.fileList[0].status="ready";
            this.errorList.unshift(file.name+"上传出现： "+response.message)
            // this.$message.error(file.name+"上传出现： "+response.message)
            this.$notify({
              title: "上传失败",
              message:"文件名为："+ file.name+" 上传失败,原因："+response.message,
              duration:0
            });
          }
          console.log(" this.fileList", this.fileList)
          console.log(" this. this.errorList", this.errorList)
          this.$forceUpdate()
        },
        handleChange(file,fileList){
          this.fileList=fileList
          console.log("handleChange",this.fileList)
          console.log("handleChange",file, fileList);
        },
        handleError(err,file,fileList){
          this.errorList.unshift(file.name+"上传出现： 服务器错误")
          this.$message.error(file.name+"上传出现：服务器错误")
        },
        handleRequest() {
          console.log("自定义上传开始,",this.fileList)
          let formData = new FormData();
          formData.append("file",this.fileList[0].raw)
          this.$service.upload.uploadFile(formData).then(res=>{
            console.log("res",res)
          })
        },


      }
    }
</script>

<style scoped>
  .uploadFile {
    display: flex;
    flex-direction: column;

  }

  .uploadFileInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #bfae90;
    height: 40px;
    width: 280px;
    font-family: "Microsoft soft";
    margin-left: 30px;
  }

  .uploadFile-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .uploadFile-button {
    margin-right: 22px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
    border: 1px solid #42b983;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
