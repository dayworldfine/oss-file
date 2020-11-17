<template>
  <div>
    <el-dialog
      title="修改昵称"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="allotRole">
        <input v-model="name" placeholder="请输入昵称" class="allotRoleInput"></input>
        <div class="allotRole-button-all">
          <Button @click="cancel()" class="allotRole-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="allotRole-button">确 定</Button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "AllotRole",
      props: {
        allotRoleVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          name: '',
          showBoolean: false,
        };
      },
      watch: {
        allotRoleVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeAllotRole");
        },
        confirm() {
          this.$emit("confirmAllotRole", this.name);
        }

      }
    }
</script>

<style scoped>
  .allotRole {
    display: flex;
    flex-direction: column;
  }

  .allotRoleInput {
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

  .allotRole-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .allotRole-button {
    margin-right: 22px;
  }
</style>
