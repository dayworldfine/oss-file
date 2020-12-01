<template>
  <div>
    <el-dialog
      title="忘记密码"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="forGetPwd">
        <div class="forGetPwd-div">
          <span>手机号</span><input  class="forGetPwdInput" v-model="formLabelAlign.account"/>
        </div>
        <div class="forGetPwd-div">
          <Button type="warning" class="button" @click="getCode()">{{countDown<=0?'获取验证码':countDown}}</Button>
          <input  class="forGetPwdInput" v-model="formLabelAlign.code"/>
        </div>
        <div class="forGetPwd-div">
          <span>新密码</span><input  class="forGetPwdInput" v-model="formLabelAlign.pwd"/>
        </div>
        <div class="forGetPwd-div">
          <span>再次输入密码</span><input  class="forGetPwdInput" v-model="formLabelAlign.pwdTwo"/>
        </div>
      </div>
      <div class="forGetPwd-button-all">
        <Button @click="cancel()" class="addZone-button">取 消</Button>
        <Button type="primary" @click="confirm()" class="addZone-button">确 定</Button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import LoginService from "@/service/LoginService";
    import {mapMutations, mapState} from "vuex";

    export default {
        name: "ForGetPwd",
      props: {
        forGetPwdVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          labelPosition: 'left',
          formLabelAlign: {
            account: '',
            code: '',
            pwd:'',
            pwdTwo:'',
          },
          showBoolean: false,
        };
      },
      watch: {
        forGetPwdVisible(bool) {
          this.showBoolean = bool;
        }
      },
      computed: {
        ...mapState([
          'countDown'
        ])
      },
      methods: {
        ...mapMutations([
          'setCountDown'
        ]),
        /** 获取验证码*/
        getCode(){
          if (this.countDown<=0){
            let param ={
              "account":this.formLabelAlign.account
            }
            LoginService.sendSms(param).then((res)=>{
              if (10000==res.error){
                this.$message.success("发送成功")
                let time=60;
                this.timer = setInterval(()=>{//定时器开始
                  time--;
                  this.setCountDown(time)
                  if(time<=0){
                    clearInterval(this.timer);// 满足条件时 停止计时
                  }
                },1000)
              }
            });
          }
        },
        close() {
          this.formLabelAlign= {};
          this.cancel();
        },
        cancel() {
          this.$emit("closeForGetPwd");
        },
        confirm() {
          if (this.formLabelAlign.code!= this.formLabelAlign.pwdTwo && this.formLabelAlign.code==''){
            this.$message.error("两次输入的密码不一样")
            return;
          }
          LoginService.retrievePwd(this.formLabelAlign).then((res)=>{
            if (10000==res.error){
              this.$message.success("密码重置成功")
              this.$emit("confirmForGetPwd");
            }
          });
        }

      }
    }
</script>

<style scoped>
  .forGetPwd {
    display: flex;
    flex-direction: column;
  }
  .forGetPwd-div{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-top: 5px;
    line-height: 45px;
  }

  .forGetPwdInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    /*color: #bfae90;*/
    height: 40px;
    width: 200px;
    font-family: "Microsoft soft";
    margin-left: 30px;
    line-height: 45px;
  }
  .button{
    margin-top: 5px;
    width: 100px;
  }

  .forGetPwd-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
</style>
