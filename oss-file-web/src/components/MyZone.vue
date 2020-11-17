<template>
  <div>
    <el-dialog
      title="我的分区"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="myZone">
        <div>111</div>
        <div>111</div>
        <div>111</div>
      </div>

    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "MyZone",
      props: {
        myZoneVisible: {
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
        myZoneVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeMyZone");
        },

      }
    }
</script>

<style scoped>
  .myZone {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
</style>
