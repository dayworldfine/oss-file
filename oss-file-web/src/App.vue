<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
  //App.vue
  mounted() {
    // if (this._isMobile()) {
    //   // alert("手机端");
    //   this.$router.replace('/mbIndex');
    // } else {
    //   // alert("pc端");
    //   this.$router.replace('/PcIndex');
    // }
    if (this._isMobile()) {
      this.$router.replace('/mbIndex');
    }
  },
  methods:{

//App.vue
    _isMobile() {
      let flag = navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone)/i)
      return flag;
    }
  }

}
</script>

<style>
html,
body,
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin: 0;
  padding: 0;
  height: 100%;
  width: 100%;
  overflow-x: hidden;
}
</style>
