<template>
  <div>
    <div id="container"></div>

  </div>
</template>

<script>
    import DocumentService from "@/service/DocumentService";

    let demo;
    export default {
        name: "PcPreview",
        data(){
          return{
            refreshToken:'',
            accessToken:'',
          }
        },
        created() {
          //获取预览的地址
          DocumentService.getDocumentUrl(
            {fileId:this.$route.query.fileId}
          ).then((res)=>{
            if (10000==res.error){
              console.log("res",res)
              this.refreshToken=res.data.refreshToken;
              this.accessToken=res.data.accessToken;
              //设置在线预览文档
              demo = aliyun.config({
                mount: document.querySelector('#container'),
                url:res.data.previewUrl,
              })
              console.log(demo.iframe)
              //根据自身的业务需求，通过异步请求或者模板输出的方式获取token。
              //设置token。
              demo.setToken({token:  this.accessToken})
            }

            //启动定时器刷新token
            setTimeout(()=>{
              DocumentService.refreshSign(
                {
                  fileId: this.$route.query.fileId,
                  accessToken:this.accessToken,
                  refreshToken:this.refreshToken
                }
              ).then((reshRes)=>{
                console.log("刷新凭证",reshRes)
                this.refreshToken=reshRes.data.refreshToken;
                this.accessToken=reshRes.data.accessToken;
                demo.setToken({token:  this.accessToken})
              })
            },1500000)

          })
        }
    }
</script>

<style scoped>
#container{
  width: 100%;
  height: 100vh;
  overflow-y: hidden;
}
</style>
