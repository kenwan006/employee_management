import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import VueRouter from 'vue-router'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap/dist/js/bootstrap.min.js'
import * as charts from 'echarts';
import router from './router';

import VueI18n from 'vue-i18n';
import lang from 'element-ui/lib/locale/lang/en'
import locale from 'element-ui/lib/locale'

Vue.use(ElementUI);
Vue.use(VueRouter);
Vue.use(VueI18n); // Add this line to use VueI18n
Vue.prototype.$charts=charts;

locale.use(lang)
const i18n = new VueI18n({
  locale: 'en' //el-paginaiton default lang is Zh, we need to import change it to en
})

new Vue({
  el: '#app',
  router,
  i18n, // Add this line to add i18n instance to the root Vue instance
  beforeCreate() {
    Vue.prototype.$bus = this //安装全局事件总线，$bus 就是当前应用的vm
  },
  render: h => h(App)
})
