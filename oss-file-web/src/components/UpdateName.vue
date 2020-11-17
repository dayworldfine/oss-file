<template>
  <div>
    <el-dialog
      title="修改昵称"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="updateName">
        <input v-model="name" placeholder="请输入昵称" class="updateNameInput"></input>
        <div class="updateName-button-all">
          <Button @click="cancel()" class="updateName-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="updateName-button">确 定</Button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "UpdateName",
    props: {
      updateNameVisible: {
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
      updateNameVisible(bool) {
        this.showBoolean = bool;
      }
    },
    methods: {
      close() {
        this.name='';
        this.cancel();
      },
      cancel() {
        this.$emit("closeUpdateName");
      },
      confirm() {
        this.$emit("confirmUpdateName", this.name);
      }

    }
  }
</script>

<style scoped>
  .updateName {
    display: flex;
    flex-direction: column;
  }

  .updateNameInput {
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

  .updateName-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .updateName-button {
    margin-right: 22px;
  }
</style>
