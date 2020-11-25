<template>
  <div>
    <el-dialog
      title="我的角色"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="myRole">
        <div class="myRole-top">
          <div>角色身份</div>
          <div>角色编号</div>
          <div>角色密码</div>
        </div>
        <div class="myRole-div" v-for="(item,index) in myRoleList" :key="index">
          <div>{{item.roleName}}</div>
          <div>{{item.code}}</div>
          <div>{{item.pwd}}</div>
        </div>
      </div>

    </el-dialog>
  </div>
</template>

<script>
  import {mapActions,mapState,mapMutations} from "vuex";
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
      computed:{
          ...mapState([
            'myRoleList'
          ])
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
    /*align-items: center;*/
  }
  .myRole-top{
    display: flex;
    justify-content: space-between;
    color: #42b983;
  }
  .myRole-div{
    display: flex;
    justify-content: space-between;
    /*align-items:baseline;*/
    margin-top: 10px;
  }

</style>
