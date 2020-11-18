<template>
  <div>
    <el-dialog
      title=""
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
            <div>
              <div v-for="item in 10" class="allotZone-for">
                <span>张飞</span>
                <img src="https://img.tomtangmu.com/images/2020/11/14/binli.jpg"/>
              </div>
            </div>
          </div>
          <div class="allotZone-center"><i class="el-icon-mobile-phone"></i></div>
          <div class="allotZone-zone">
            <div>
              <input v-model="name" placeholder="分区名称" class="allotZoneInput"></input>
              <Button type="warning" class="search">搜索</Button>
            </div>
            <div>
              <div v-for="item in 10">121</div>
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
