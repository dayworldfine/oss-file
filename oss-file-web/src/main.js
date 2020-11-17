import Vue from 'vue'
import App from './App'

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

import 'lib-flexible'
import router from './router'
import service from "@/service/serviceList";

Vue.prototype.$service = service;


Vue.config.productionTip = false
Vue.use(ElementUI)
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

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
