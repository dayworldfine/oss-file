<template>
  <div>
    <el-dialog
      title=""
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="allotZone">
        <input v-model="name" placeholder="请输入昵称" class="allotZoneInput"></input>
        <div class="allotZone-button-all">
          <Button @click="cancel()" class="allotZone-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="allotZone-button">确 定</Button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "AllotZone",
      props: {
        allotZoneVisible: {
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
        allotZoneVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeAllotZone");
        },
        confirm() {
          this.$emit("confirmAllotZone", this.name);
        }

      }
    }
</script>

<style scoped>
  .allotZone {
    display: flex;
    flex-direction: column;
  }

  .allotZoneInput {
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

  .allotZone-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .allotZone-button {
    margin-right: 22px;
  }
</style>
