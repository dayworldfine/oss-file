<template>
  <div>
    <el-dialog
      title="登录"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="login">
        <div class="login-div">
          <span>手机号</span><input  class="loginInput" v-model="formLabelAlign.account"/>
        </div>
        <div class="login-div">
          <span>密码</span><input  class="loginInput" v-model="formLabelAlign.passWord"/>
        </div>
      </div>
      <div class="login-button-all">
        <Button @click="cancel()" class="addZone-button">取 消</Button>
        <Button type="primary" @click="confirm()" class="addZone-button">登录</Button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapActions,mapState,mapMutations} from "vuex";
  import LoginService from "@/service/LoginService";
    export default {
        name: "Login",
      props: {
        loginVisible: {
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
            passWord: '',
          },
          showBoolean: false,
        };
      },
      watch: {
        loginVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.formLabelAlign= {};
          this.cancel();
        },
        cancel() {
          this.$emit("closeLogin");
        },
        confirm() {
          LoginService.login(this.formLabelAlign).then((res)=>{
            console.log("res",res)
            if (10000==res.error){

            }
          })
          this.$emit("confirmLogin", this.formLabelAlign);
        }

      }
    }
</script>

<style scoped>
  .login {
    display: flex;
    flex-direction: column;
  }
  .login-div{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-top: 5px;
    line-height: 45px;
  }

  .loginInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #bfae90;
    height: 40px;
    width: 250px;
    font-family: "Microsoft soft";
    margin-left: 30px;
    line-height: 45px;
  }

  .login-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
</style>
