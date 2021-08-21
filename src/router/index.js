import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)
import store from '../store'
import apis from '../store/modules/apis'
console.log(store)
//console.log(datos_sesion)
//Vue.use(VueRouter)
//console.log(dataUser)

const routes = [

    {
        path: '/',
        name: 'login',
        component: () => import(/* webpackChunkName: "login" */ '../views/login.vue')
    },
    {
        path: '/empleados',
        name: 'empleados',
        component: () => import(/* webpackChunkName: "home_jefe" */ '../views/empleados.vue'),
        
            beforeEnter: (to, from, next) => {
                console.log(to, from)
                console.log(apis.state.token)
            
                try {
                    
                    if(apis.state.token!=null){
                        next()
                    } else{
                        next({
                            path: '/',
                        })
                    }
                    

                } catch (error) {
                    console.log(error)
                }
            
            },

        children: [
            {
                path: '/empleados',
                redirect: 'pedidos',
            },
            {
                path: 'pedidos',
                name: 'pedidos',
                component: () => import(/* webpackChunkName: "pedidos" */ '../views/viewsEmpleados/pedidos.vue')
            },
            {
                path: 'domiciliarios',
                name: 'domiciliarios',
                component: () => import(/* webpackChunkName: "domiciliarios" */ '../views/viewsEmpleados/domiciliarios.vue')
            },
            {
                path: 'perfil',
                name: 'perfil',
                component: () => import(/* webpackChunkName: "perfil" */ '../views/viewsEmpleados/perfil.vue')
            },
        ]
    },
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
    scrollBehavior(to, from, savedPosition) {
        console.log(to, from, savedPosition)
        return { x: 0, y: 0 }

    },

})


export default router