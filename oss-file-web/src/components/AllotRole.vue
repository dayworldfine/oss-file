<template>
  <div>
    <el-dialog
      title="分配角色"
      :visible.sync="showBoolean"
      @close="close()"
      width="40%"
      center>
      <div class="allotRole">
        <div class="allotRole-top">
          <div class="allotRole-user">
            <div class="allotRole-searchInput">
              <input v-model="name" placeholder="昵称/手机号" class="allotRoleInput"></input>
              <Button type="warning" class="search">搜索</Button>
            </div>
            <div class="allotRole-forDiv" @scroll="scrollUser">
              <div v-for="item in 15" class="allotRole-for">
                <span>张飞</span>
                <img src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg" class="allotRole-for-img"/>
                <span>13900000000</span>
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
            </div>
          </div>
          <div class="allotRole-center"><i class="el-icon-mobile-phone"></i></div>
          <div class="allotRole-zone">
            <div>
              <input v-model="name" placeholder="分区名称" class="allotRoleInput"></input>
              <Button type="warning" class="search">搜索</Button>
            </div>
            <div class="allotRole-forDiv-zone" @scroll="scrollZone">
              <div v-for="item in 15" class="allotRole-for">
                <span>张飞</span>
                <img src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg" class="allotRole-for-img"/>
                <span>13900000000</span>
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
            </div>
          </div>
        </div>
        <div class="allotRole-button-all">
          <Button @click="cancel()" class="allotRole-button">取 消</Button>
          <Button type="primary" @click="confirm()" class="allotRole-button">确 定</Button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
    export default {
        name: "AllotRole",
      props: {
        allotRoleVisible: {
          type: Boolean,
          default: false,
          required: true,
        },
      },
      data() {
        return {
          name: '',
          showBoolean: false,
          checked:false
        };
      },
      watch: {
        allotRoleVisible(bool) {
          this.showBoolean = bool;
        }
      },
      methods: {
        close() {
          this.name='';
          this.cancel();
        },
        cancel() {
          this.$emit("closeAllotRole");
        },
        confirm() {
          this.$emit("confirmAllotRole", this.name);
        },
        scrollUser(e){
          console.log("e1", e.target.scrollHeight);
          console.log("e2", e.target.scrollTop);
          console.log("e3", e.target.clientHeight);
          console.log("e4", e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight);
          let height = e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight;
        },
        scrollZone(e){
          let height = e.target.scrollHeight -
            e.target.scrollTop -
            e.target.clientHeight;
        }

      }
    }
</script>

<style scoped>
  .allotRole {
    display: flex;
    flex-direction: column;
  }
  .allotRole-top{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .allotRole-user{
    display: flex;
    flex-direction: column;
  }
  .allotRole-searchInput{
    display: flex;

  }
  .search{
    height: 28px;
    margin-left: 5px;
  }

  .allotRoleInput {
    outline-style: none;
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 13px 14px;
    /*width: 620px;*/
    font-size: 14px;
    color: #bfae90;
    height: 20px;
    width: 250px;
    font-family: "Microsoft soft";
    /*margin-left: 30px;*/
  }
  .allotRole-center{
    margin-top: 130px;
  }
  .allotRole-forDiv{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotRole-forDiv-zone{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotRole-for{
    display: flex;
    justify-content: space-around;
    height: 40px;
    line-height: 40px;
    background-color: #eaeaea;
    border: 1px solid #b7b7b7;
    box-sizing: border-box;
  }
  .allotRole-for-img{
    width: 40px;
    height: 40px;
  }

  .allotRole-button-all {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
  }

  .allotRole-button {
    margin-right: 22px;
  }
</style>
