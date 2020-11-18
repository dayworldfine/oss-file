<template>
  <div>
    <el-dialog
      title="注册账号"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="forGetPwd">
        <div class="forGetPwd-div">
          <span>手机号</span><input  class="forGetPwdInput" v-model="formLabelAlign.account"/>
        </div>
        <div class="forGetPwd-div">
          <span>昵称</span><input  class="forGetPwdInput" v-model="formLabelAlign.nickName"/>
        </div>
        <div class="forGetPwd-div">
          <Button type="warning" class="button">获取验证码</Button>
          <input  class="forGetPwdInput" v-model="formLabelAlign.code"/>
        </div>
        <div class="forGetPwd-div">
          <span>新密码</span><input  class="forGetPwdInput" v-model="formLabelAlign.pwd"/>
        </div>
        <div class="forGetPwd-div">
          <span>再次输入密码</span><input  class="forGetPwdInput"/>
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
            nickName: '',
            code: '',
            pwd:'',
          },
          showBoolean: false,
        };
      },
      watch: {
        forGetPwdVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.formLabelAlign= {};
          this.cancel();
        },
        cancel() {
          this.$emit("closeForGetPwd");
        },
        confirm() {
          this.$emit("confirmForGetPwd", this.formLabelAlign);
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

  .forGetPwd-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
</style>
