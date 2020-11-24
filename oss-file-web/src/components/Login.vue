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
      created() {
        let _this =this
        document.onkeydown = function(e) {
          let key = window.event.keyCode;
          if (key == 13) {
            if (_this.showBoolean){
              _this.confirm()
            }
          }
        }
      },
      methods: {
        ...mapActions([

        ]),
        ...mapMutations([
          'setIsLogin',
          'setUserId',
          'setUserNickName',
          'setUserImg',
          'setUserRole'
        ]),
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
            if (undefined==res){
              this.$message.error("网络错误，请稍后再试！")
              return;
            }
            if (10000==res.error){
                this.$message.success("登录成功")
                this.setIsLogin(true);
                this.setUserId(res.data.identity.userId);
                this.setUserNickName(res.data.identity.userNickName);
                this.setUserImg(res.data.identity.userImg);
                this.setUserRole(res.data.identity.userRoleName);
                localStorage.setItem("account",this.formLabelAlign.account);
                localStorage.setItem("passWord",this.formLabelAlign.passWord);
                localStorage.setItem("token",res.data.token);

              this.$emit("confirmLogin", this.formLabelAlign);
            }else {
              this.$message.error(res.message)
            }

          })

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
    border-radius: 10px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #352f2a;
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
  .addZone-button{
    margin-left: 5px;
  }
</style>
