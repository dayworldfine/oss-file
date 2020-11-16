<template>
  <div>
    <el-dialog
      title="添加"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="addZone">
        <el-form :label-position="labelPosition"  :model="formLabelAlign" class="addZone-form">
          <el-form-item label="分区名称" class="addZone-form-item">
            <el-input v-model="formLabelAlign.zoneName"></el-input>
          </el-form-item>
          <el-form-item label="分区前缀" class="addZone-form-item">
            <el-input v-model="formLabelAlign.zonePrefix"></el-input>
          </el-form-item>
          <el-form-item label="分区密码" class="addZone-form-item">
            <el-input v-model="formLabelAlign.zonePwd"></el-input>
          </el-form-item>
          <el-form-item label="是否公开" class="addZone-form-item">
<!--            <el-input v-model="formLabelAlign.isOpen"></el-input>-->
            <el-radio v-model="formLabelAlign.isOpen" label=0>否</el-radio>
            <el-radio v-model="formLabelAlign.isOpen" label=1>是</el-radio>
          </el-form-item>
        </el-form>
        <div class="addZone-button-all">
          <Button @click="cancel()" class="addZone-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="addZone-button">确 定</Button>
        </div>

      </div>

    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "AddZone",
      props: {
        addZoneVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          labelPosition: 'right',
          formLabelAlign: {
            zoneName: '',
            zonePrefix: '',
            zonePwd: '',
            isOpen:'',
          },
          showBoolean: false,
        };
      },
      watch: {
        addZoneVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.formLabelAlign= {};
          this.cancel();
        },
        cancel() {
          this.$emit("closeAddZone");
        },
        confirm() {
          this.$emit("confirmAddZone", this.formLabelAlign);
        }

      }
    }
</script>

<style scoped>
  .addZone {
    display: flex;
    flex-direction: column;
  }

  .addZoneInput {
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

  .addZone-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .addZone-button {
    margin-right: 22px;
  }
  .addZone-form{
    display: flex;
    flex-direction: column;
    padding: 0px;
  }
  .addZone-form-item{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
</style>
