<template>
  <div>
    <el-dialog
      title="输入角色密匙"
      :visible.sync="showBoolean"
      @close="close()"
      width="20%"
      center>
      <div class="enterRole">
        <input v-model="roleKey" placeholder="请输入权限密匙" class="enterRoleInput"></input>
        <div class="enterRole-button-all">
          <Button @click="cancel()" class="enterRole-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="enterRole-button">确 定</Button>
        </div>

      </div>

    </el-dialog>
  </div>
</template>

<script>
    import RoleService from "@/service/RoleService";
    import {mapActions,mapState,mapMutations} from "vuex";
    export default {
        name: "EnterRole",
      props: {
        enterRoleVisible: {
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
        enterRoleVisible(bool){
          this.showBoolean=bool;
        }
      },
      methods:{
        ...mapMutations([
          'setUserRole'
        ]),
        close() {
          this.roleKey='';
          this.cancel();
        },
        cancel(){
          this.$emit("closeEnterRole");
        },
        confirm(){
          RoleService.getRoleKey({pwd:this.roleKey}).then((res)=>{
            console.log("res",res)
            if (10000==res.error){
                this.setUserRole(res.data)
              this.$emit("confirmEnterRole");
            }
          });

        }

      }
    }
</script>

<style scoped>
  .enterRole{
    display: flex;
    flex-direction: column;
  }
  .enterRoleInput{
    outline-style: none ;
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
  .enterRole-button-all{
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }
  .enterRole-button{
    margin-right: 22px;
  }
</style>
