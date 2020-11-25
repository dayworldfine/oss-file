<template>
  <div>
    <el-dialog
      title="我的分区"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="myZone">
        <div class="myZone-top">
          <div class="myZone-div-width">分区名称</div>
          <div class="myZone-div-width">分区密码</div>
        </div>
        <div class="myZone-div" v-for="(item,index) in myZoneList" :key="index">
          <div class="myZone-div-width">{{item.zoneName}}</div>
          <div class="myZone-div-width">{{item.zonePwd}}</div>
        </div>
      </div>

    </el-dialog>
  </div>
</template>

<script>
    import {mapState} from "vuex";

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
      computed:{
        ...mapState([
          'myZoneList'
        ])
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
  }
  .myZone-top{
    display: flex;
    justify-content: space-around;
    color: #42b983;
  }
  .myZone-div{
    display: flex;
    justify-content: space-around;
    /*align-items:baseline;*/
    margin-top: 10px;
  }
  .myZone-div-width{
    width: 200px;
    text-align: center;
  }
</style>
