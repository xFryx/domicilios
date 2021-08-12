<template>
    <v-content>
      <v-navigation-drawer
      v-model="drawer"
      app
      temporary
      color="white" 
    >
      <v-list dense>
        <v-list-item @click="$router.push({name: ruta.redireccion })" v-for="(ruta,index) in rutas" :key="index" link>
          
            <v-list-item-icon>
            <v-icon>{{ ruta.icon }}</v-icon>
              </v-list-item-icon>

              <v-list-item-content>
                <v-list-item-title>{{ ruta.text }}</v-list-item-title>
              </v-list-item-content>
         
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
      <v-app-bar
        app
        color="purple darken-1"
        dark
      >
         <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title> {{titulo}} </v-toolbar-title>
        

        <v-spacer></v-spacer>
        <v-btn @click="$router.push({name: icono.redireccion })" icon dark v-for="(icono,index) in rutas" :key="index" >
          <v-icon> {{icono.icon}} </v-icon>
        </v-btn>
        <v-btn @click="$vuetify.theme.dark=!$vuetify.theme.dark" icon>
          <v-icon v-if="$vuetify.theme.dark==false" >mdi-weather-night</v-icon>
          <v-icon v-else>mdi-white-balance-sunny</v-icon>
        </v-btn>
        <v-btn @click="salir()" icon>
          <v-icon>mdi-logout</v-icon>
        </v-btn>
      </v-app-bar>
    </v-content>
    
</template>
<script>
//import Menus from './Menus.vue'
export default {
    components: {
        //Menus,
    },
    props: {
        rutas: {
            type: Array,
        },
        titulo: {
        type: String,
        default: "Domicilios"
      }
    },
    data(){
        return {
            drawer: false,
            
        }
    },
    created(){
      console.log('asdasda')
      console.log(this.rutas)
    },
    methods: {
      salir(){
        //this.$store.state.datos_sesion.datos = null
        this.$router.push('/')

      }
    }
}
</script>