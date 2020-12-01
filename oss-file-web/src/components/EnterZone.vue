<template>
  <div>
    <el-dialog
      title="输入分区密匙"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="enterZone">
        <input v-model="roleKey" placeholder="请输入分区密匙" class="enterZoneInput"></input>
        <div class="enterZone-button-all">
          <Button @click="cancel()" class="enterZone-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="enterZone-button">确 定</Button>
        </div>

      </div>

    </el-dialog>
  </div>
</template>

<script>
  import ZoneService from "@/service/ZoneService";
  import {mapActions,mapState,mapMutations} from "vuex";

    export default {
        name: "EnterZone",
      props: {
        enterZoneVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          roleKey:'',
          showBoolean:false,
        };
      },
      watch:{
        enterZoneVisible(bool){
          this.showBoolean=bool;
        }
      },
      methods:{
        ...mapActions([
          'getZoneList',
        ]),
        ...mapMutations([
          'setUserRole'
        ]),
        close() {
          this.roleKey='';
          this.cancel();
        },
        cancel(){
          this.$emit("closeEnterZone");
        },
        confirm(){
          ZoneService.getZoneKey({pwd:this.roleKey}).then((res)=>{
            if (10000==res.error){
              this.$message.success("恭喜你获得新的分区")
              this.getZoneList({name:'',page:1,size:24});
              this.$emit("confirmEnterZone");
            }
          })
        }
      }
    }
</script>

<style scoped>
  .enterZone{
    display: flex;
    flex-direction: column;
  }
  .enterZoneInput{
    outline-style: none ;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    /*color: #bfae90;*/
    height: 40px;
    width: 280px;
    font-family: "Microsoft soft";
    margin-left: 30px;
  }
  .enterZone-button-all{
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
  .enterZone-button{
    margin-right: 22px;
  }
</style>
