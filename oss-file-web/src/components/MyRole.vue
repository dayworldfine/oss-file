<template>
  <div>
    <el-dialog
      title="我的角色"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="myRole">
        <div>111</div>
        <div>111</div>
        <div>111</div>
      </div>

    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "MyRole",
      props: {
        myRoleVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          showBoolean: false,
        };
      },
      watch: {
        myRoleVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeMyRole");
        },

      }
    }
</script>

<style scoped>
  .myRole {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

</style>
