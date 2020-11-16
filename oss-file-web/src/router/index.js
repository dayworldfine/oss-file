import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import test from '@/pc/test'
import pcIndex from '@/pc/PcIndex/PcIndex'
import testUpload from '@/pc/testUpload'


Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'pcIndex',
      component: pcIndex
    },
    {
      path: '/Pc',
      name: 'Pc',
      component: resolve => require(['@/pc/Pc/Pc.vue'], resolve),
      children: [
        {
          path: '/PcIndex',
          name: 'PcIndex',
          component: resolve => require(['@/pc/PcIndex/PcIndex.vue'], resolve)
        }
      ]
    },
    {
      path: '/1',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/test',
      name: 'test',
      component: test
    },
    {
      path: '/testUpload',
      name: 'testUpload',
      component: testUpload
    },
    /*------------------pc 分隔符  mb---------------------*/
  ]
})
