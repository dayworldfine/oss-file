import Vue from 'vue'
import App from './App'

import store from "./store"

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import { Button, Page } from 'view-design';
import 'view-design/dist/styles/iview.css';

import UpdateName  from "@/components/UpdateName";
import UpdateImg  from "@/components/UpdateImg";
import MyRole  from "@/components/MyRole";
import MyZone  from "@/components/MyZone";
import EnterRole  from "@/components/EnterRole";
import EnterZone  from "@/components/EnterZone";
import AddZone  from "@/components/AddZone";
import AllotZone  from "@/components/AllotZone";
import AllotRole  from "@/components/AllotRole";
import Register  from "@/components/Register";
import Login  from "@/components/Login";
import ForGetPwd  from "@/components/ForGetPwd";

import 'lib-flexible'
import router from './router'
// 移动互动事件
import vueTouch from 'kim-vue-touch'



Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(vueTouch)
Vue.component('Button', Button);
Vue.component('Page', Page);
Vue.component('UpdateName', UpdateName);
Vue.component('UpdateImg', UpdateImg);
Vue.component('MyRole', MyRole);
Vue.component('MyZone', MyZone);
Vue.component('EnterRole', EnterRole);
Vue.component('EnterZone', EnterZone);
Vue.component('AddZone', AddZone);
Vue.component('AllotZone', AllotZone);
Vue.component('AllotRole', AllotRole);
Vue.component('Register', Register);
Vue.component('Login', Login);
Vue.component('ForGetPwd', ForGetPwd);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
