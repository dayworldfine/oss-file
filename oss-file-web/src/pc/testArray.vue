<template>
    <div></div>
</template>

<script>
    export default {
      name: "testArray",
      created() {
        this.testArray();
      },
      methods:{
          testArray(){
            let arr =["firstAdmin","Admin","A"]
            console.log(arr.indexOf("A"));
          }
      }
    }
</script>

<style scoped>

</style>
