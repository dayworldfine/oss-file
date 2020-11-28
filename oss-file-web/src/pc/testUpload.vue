<template>
  <div>
    <!--
      multiple: 是否支持多选文件
      action：必选参数，上传的地址
      on-preview :点击文件列表中已上传的文件时的钩子
      on-success: 文件上传成功时的钩子
      on-change:  文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
      file-list:  上传的文件列表, 例如: [{name: 'food.jpg', url: 'https://xxx.cdn.com/xxx.jpg'}]
      auto-upload: 是否在选取文件后立即进行上传
      name: 上传的文件字段名
      文件状态 ready success fail
    -->
    <el-upload
      class="upload-demo"
      ref="upload"
      :multiple="true"
      action="http://localhost:9735/file/uploadFile"
      :on-success="handleSuccess"
      :on-change="handleChange"
      :on-error="handleError"
      :file-list="fileList"
      :auto-upload="false"
      name="file">
      <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
      <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
<!--      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
    </el-upload>
  </div>
</template>

<script>
  import * as upload from "../service/upload/upload";

  export default {
    name: "testUpload",
    data() {
      return {
        fileList: [],
        errorList:[],
        successList:[]

      };
    },
    methods: {
      submitUpload() {
        console.log("点击上传到服务器fileList",this.fileList)
        this.$refs.upload.submit();
      },

      handleSuccess(response,file,fileList){
        this.fileList=fileList
        console.log(" this.fileList", this.fileList)
        console.log("handleSuccess",this.fileList)
        console.log(response,file, fileList);
        if (response.code ==200){
          this.successList.unshift(file.name+" 上传成功")
          this.$message.success("上传成功")
        }else {
          this.fileList[0].status="ready";
          this.errorList.unshift(file.name+"上传出现： "+response.message)
          this.$message.error(file.name+"上传出现： "+response.message)
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

    }
  }
</script>

<style scoped>

</style>
