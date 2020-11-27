<template>
  <div>11
    <div id="container"></div>

  </div>
</template>

<script>
    let demo;
    export default {
        name: "PcPreview",
        data(){
          return{
            url:"https://office-cn-hangzhou.imm.aliyuncs.com/office/w/1de848b9a86461bf9202199ec73bbb6c544bc42c?hidecmb=1&simple=1&_w_tokentype=1",
            accessToken:"0086a1c47c2348f19b2865f017fc739fv2",
            refreshToken:"",
          }
        },
        created() {
          setTimeout(a=>{

          },2000);
          demo = aliyun.config({
            mount: document.querySelector('#container'),
            url:this.url
          })
          console.log(demo.iframe)
          //根据自身的业务需求，通过异步请求或者模板输出的方式获取token。
          //设置token。
          demo.setToken({token: this.accessToken})
        }
    }
</script>

<style scoped>

</style>
