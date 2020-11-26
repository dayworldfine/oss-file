<template>
  <div>
    <el-dialog
      title="添加分区"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="addZone">
        <div class="addZone">
          <div class="addZone-div">
            <span>分区名称</span><input  class="addZoneInput" v-model="formLabelAlign.zoneName"/>
          </div>
          <div class="addZone-div">
            <span>分区前缀</span><input  class="addZoneInput" v-model="formLabelAlign.zonePrefix"/>
          </div>
          <div class="addZone-div">
            <span>分区密码</span><input  class="addZoneInput" v-model="formLabelAlign.zonePwd"/>
          </div>
          <div class="addZone-div">
            <span>是否公开</span>
            <el-radio v-model="formLabelAlign.isOpen" label="0" class="addZone-div-radio">否</el-radio>
            <el-radio v-model="formLabelAlign.isOpen" label="1" class="addZone-div-radio">是</el-radio>
          </div>
        </div>
        <div class="addZone-button-all">
          <Button @click="cancel()" class="addZone-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="addZone-button">确 定</Button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapActions,mapState,mapGetters,mapMutations} from "vuex";
  import ZoneService from "@/service/ZoneService";
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
          name:'',
          page:1,
          size:24,
          showBoolean: false,
        };
      },
      computed:{
        ...mapState([
          'zoneSearchKey'
        ])
      },
      watch: {
        addZoneVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        ...mapActions([
          'getZoneList'
        ]),
        ...mapMutations([
          'setZoneSearchKey'
        ]),
        close() {
          this.formLabelAlign= {};
          this.cancel();
        },
        cancel() {
          this.$emit("closeAddZone");
        },
        confirm() {
          ZoneService.addZone(this.formLabelAlign).then((res)=>{
            console.log("res",res)
            if (10000==res.error){
              this.$message.success("添加成功")
              this.getZoneList({name:this.zoneSearchKey,page:this.page,size:this.size})
              this.$emit("confirmAddZone");
            }
          });
        }

      }
    }
</script>

<style scoped>
  .addZone {
    display: flex;
    flex-direction: column;
  }
  .addZone {
    display: flex;
    flex-direction: column;
  }
  .addZone-div{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-top: 5px;
    line-height: 45px;
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
    width: 200px;
    font-family: "Microsoft soft";
    margin-left: 30px;
    line-height: 45px;
  }
  .addZone-div-radio{
    margin-top: 15px;
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
</style>
