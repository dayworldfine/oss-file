<template>
  <div>
    <el-dialog
      title="注册账号"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="register">
       <div class="register-div">
         <span>手机号</span><input  class="registerInput" v-model="formLabelAlign.account"/>
       </div>
        <div class="register-div">
          <span>昵称</span><input  class="registerInput" v-model="formLabelAlign.nickName"/>
        </div>
        <div class="register-div">
          <Button type="warning" class="button" @click="getCode()">{{countDown<=0?'获取验证码':countDown}}</Button>
          <input  class="registerInput" v-model="formLabelAlign.code"/>
        </div>
        <div class="register-div">
          <span>密码</span><input  class="registerInput" v-model="formLabelAlign.pwd"/>
        </div>
        <div class="register-div">
          <span>再次输入密码</span><input  class="registerInput"  v-model="formLabelAlign.pwdTwo"/>
        </div>
      </div>
      <div class="register-button-all">
        <Button @click="cancel()" class="addZone-button">取 消</Button>
        <Button type="primary" @click="confirm()" class="addZone-button">确 定</Button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    import {mapActions,mapState,mapMutations} from "vuex";
    import LoginService from "@/service/LoginService";

    export default {
        name: "Register",
      props: {
        registerVisible: {
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
            nickName: '',
            code: '',
            pwd:'',
            pwdTwo:'',
          },
          showBoolean: false,
          timer:null,   //定时器
        };
      },
      watch: {
        registerVisible(bool) {
          this.showBoolean = bool;
        }
      },
      computed: {
        ...mapState([
          'countDown'
        ])
      },
      methods: {
        ...mapActions([
          // 'sendSms',
        ]),
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
          this.$emit("closeRegister");
        },
        /** 确定按钮 */
        confirm() {
          if (this.formLabelAlign.code!= this.formLabelAlign.pwdTwo && this.formLabelAlign.code==''){
            this.$message.error("两次输入的密码不一样")
            return;
          }
          LoginService.register(this.formLabelAlign).then((res)=>{
            if (10000==res.error){
              console.log("12312")
              this.$message.success("注册成功")
              this.$emit("confirmRegister", this.formLabelAlign);
            }
          });

        }

      }
    }
</script>

<style scoped>
  .register {
    display: flex;
    flex-direction: column;
  }
  .register-div{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-top: 5px;
    line-height: 45px;
  }

  .registerInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 10px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #bfae90;
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

  .register-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
</style>
