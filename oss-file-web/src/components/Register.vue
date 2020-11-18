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
          <Button type="warning" class="button">获取验证码</Button>
          <input  class="registerInput" v-model="formLabelAlign.code"/>
        </div>
        <div class="register-div">
          <span>密码</span><input  class="registerInput" v-model="formLabelAlign.pwd"/>
        </div>
        <div class="register-div">
          <span>再次输入密码</span><input  class="registerInput"/>
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
          },
          showBoolean: false,
        };
      },
      watch: {
        registerVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.formLabelAlign= {};
          this.cancel();
        },
        cancel() {
          this.$emit("closeRegister");
        },
        confirm() {
          this.$emit("confirmRegister", this.formLabelAlign);
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
    border-radius: 3px;
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
