<template>
  <div>
    <el-dialog
      title="使用说明"
      :visible.sync="showBoolean"
      @close="close()"
      width="30%"
      center>
      <div class="useHelp">
        <div class="useHelp-title">1.上传功能:</div>
        <div class="useHelp-center">只有右上角提示上传成功才为上传成功,大文件请耐心等待</div>
        <div class="useHelp-title">2.预览功能:</div>
        <div class="useHelp-center">文档类的文件才支持在线预览</div>
        <div class="useHelp-title">3.输入分区密匙功能:</div>
        <div class="useHelp-center">输入分区密匙后可以查看改分区的文件，分区密匙由超级管理员创建，已经拥有该分区的用户可以通过我的分区查看密钥</div>
        <div class="useHelp-title">4.输入角色密匙功能:</div>
        <div class="useHelp-center">输入角色密匙后可以获取该角色权限，角色密匙由超级管理员创建，已经拥有该角色的用户可以通过我的角色查看密钥</div>
        <div class="useHelp-title">5.添加分区:</div>
        <div class="useHelp-center">只有超级管理才可以新建分区, 如需要请与管理员联系</div>
        <div class="useHelp-relation">  如有疑问或建议：请联系超级管理员13738700108（微信同号） </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {mapActions, mapMutations} from "vuex";
  import UserService from "@/service/UserService";

  export default {
        name: "UseHelp",
    props: {
      useHelpVisible: {
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
      useHelpVisible(bool) {
        this.showBoolean = bool;
      }
    },
    methods: {
      ...mapActions([

      ]),
      ...mapMutations([
        'setUserNickName',
      ]),
      close() {
        this.cancel();
      },
      cancel() {
        this.$emit("closeUseHelp");
      },
      confirm() {
        this.$emit("confirmUseHelp");
      }

    }
    }
</script>

<style scoped>
  .useHelp {
    display: flex;
    flex-direction: column;
  }
  .useHelp-title{
    margin-top: 10px;
    /*color: #000000;*/
    font-weight: 600;
  }
  .useHelp-center{
    margin-left: 20px;
    margin-top: 5px;
    /*color: #000000;*/
  }
  .useHelp-relation{
    margin-top: 30px;
    margin-bottom: 20px;
    text-align: right;
    color: #fb7272;
  }
</style>
