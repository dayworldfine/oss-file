<template>
  <div>
    <el-dialog
      title="分配分区"
      :visible.sync="showBoolean"
      @close="close()"
      width="40%"
      center>
      <div class="allotZone">
        <div class="allotZone-top">
          <div class="allotZone-user">
            <div class="allotZone-searchInput">
              <input v-model="name" placeholder="昵称/手机号" class="allotZoneInput"></input>
              <Button type="warning" class="search">搜索</Button>
            </div>
            <div class="allotZone-forDiv" @scroll="scrollUser">
              <div v-for="item in 15" class="allotZone-for">
                <span>张飞</span>
                <img src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg" class="allotZone-for-img"/>
                <span>13900000000</span>
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
            </div>
          </div>
          <div class="allotZone-center"><i class="el-icon-mobile-phone"></i></div>
          <div class="allotZone-zone">
            <div>
              <input v-model="name" placeholder="分区名称" class="allotZoneInput"></input>
              <Button type="warning" class="search">搜索</Button>
            </div>
            <div class="allotZone-forDiv-zone" @scroll="scrollZone">
              <div v-for="item in 15" class="allotZone-for">
                <span>张飞</span>
                <img src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg" class="allotZone-for-img"/>
                <span>13900000000</span>
                <el-checkbox v-model="checked"></el-checkbox>
              </div>
            </div>
          </div>
        </div>
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
          checked:false,
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
  .allotZone {
    display: flex;
    flex-direction: column;
  }
  .allotZone-top{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .allotZone-user{
    display: flex;
    flex-direction: column;
  }
  .allotZone-searchInput{
    display: flex;

  }
  .search{
    height: 28px;
    margin-left: 5px;
  }

  .allotZoneInput {
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
  .allotZone-center{
    margin-top: 130px;
  }
  .allotZone-forDiv{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotZone-forDiv-zone{
    margin-top: 10px;
    height: 400px;
    overflow-y: scroll;
  }
  .allotZone-for{
    display: flex;
    justify-content: space-around;
    height: 40px;
    line-height: 40px;
    background-color: #eaeaea;
    border: 1px solid #b7b7b7;
    box-sizing: border-box;
  }
  .allotZone-for-img{
    width: 40px;
    height: 40px;
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
