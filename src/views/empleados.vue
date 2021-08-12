<template>
    <EstructuraBasica>
        <template v-slot:appbar>
            <AppBar :rutas="rutas" />
        </template>
        <template v-slot:main>
            <router-view/>
        </template>
    </EstructuraBasica>
</template>
<script>
import AppBar from '../components/celulas/AppBar.vue'
export default {
    components: {
        AppBar
    },
    data(){
        return {
            rutas: [
                {
                  icon: 'mdi-moped-outline',
                  text: 'Crear domiciliarios',
                  redireccion: 'domiciliarios'
                },
                {
                  icon: 'mdi-email-multiple-outline',
                  text: 'Pedidos',
                  redireccion: 'pedidos',
                },
                {
                  icon: 'mdi-account',
                  text: 'Perfil',
                  redireccion: 'perfil',
                },
            ]
        }
    },
    created(){

    }
}
</script>