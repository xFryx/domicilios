import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
import apis from './modules/apis'
import dialogos from './modules/dialogos'
import datos_sesion from './modules/datos_sesion'
import domiciliarios from './modules/domiciliarios'

export default new Vuex.Store({

    modules: {
        apis,
        dialogos,
        datos_sesion,
        domiciliarios,
        
    },
    state: {
        
    },
    mutations: {
      
    },
    actions: {

    }
})