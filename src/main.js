import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router/index'
import store from './store/index'

import Load from './components/atomos/Load'
import Paginacion from './components/atomos/Paginacion'
import EstructuraBasica from './components/moleculas/EstructuraBasica'
import ToolbarBasico from './components/atomos/ToolbarBasico'
import Card from './components/celulas/Card'
//import CardDescriptiva from './components/celulas/CardDescriptiva'
import * as VueGoogleMaps from 'vue2-google-maps'
 
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCz5oX23J0O3PUDIS-SRHMTT4DNfl4vCFY',
    libraries: 'places', // This is required if you use the Autocomplete plugin
    // OR: libraries: 'places,drawing'
    // OR: libraries: 'places,drawing,visualization'
    // (as you require)
 
    //// If you want to set the version, you can do so:
    // v: '3.26',
  },
})
//Vue.component('Toolbar',Toolbar)
Vue.component('Card',Card)
Vue.component('ToolbarBasico',ToolbarBasico)
Vue.component('EstructuraBasica',EstructuraBasica)
//Vue.component('CardDescriptiva',CardDescriptiva)
Vue.component('Paginacion',Paginacion)
Vue.component('Load',Load)

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
