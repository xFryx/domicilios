<template>
    <v-sheet>
        <ToolbarBasico :titulo="titulo" 
                :activa_tool="true"
                :icono="'mdi-application-import'" 
                :accion="'Mapa de pedidos'"
                @activar="$emit('activar')"
        
        />
        <Paginacion v-show="step==1" @buscar="buscar($event)" :cantidad_paginas="cantidad_paginas" />
        <Load :load="load"/>
        <v-text-field v-show="step==1" prepend-icon="mdi-magnify" v-model="search" filled rounded dense label="Buscador"></v-text-field>
        <v-window v-model="step">
            <v-window-item :value="1">
                <v-list
                    subheader
                    two-line
                    rounded
                    >
                    <v-subheader inset>Bandeja de entrada</v-subheader>
                    <v-list-item-group
                            v-model="caso_revisar"
                            color="purple darken-3"
                        >
                            <v-list-item
                                v-for="info in $store.state.datos_sesion.pedidos_filtrados"
                                :key="info.id"
                                @click="informacion_seleccionada=info"
                            >
                                <v-list-item-avatar>
                                <v-icon
                                    class="indigo lighten-1"
                                    dark
                                >
                                    mdi-clipboard-text
                                </v-icon>
                                </v-list-item-avatar>

                                <v-list-item-content>
                                <v-list-item-title v-text="info.title"></v-list-item-title>

                                <v-list-item-subtitle v-text="info.subtitle"></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-action>
                                <v-btn icon>
                                    <v-icon :color="estado(info)">mdi-information</v-icon>
                                </v-btn>
                                </v-list-item-action>
                            </v-list-item>
                        </v-list-item-group>
                    </v-list>
            </v-window-item>
            <v-window-item :value="2">
                
                <slot :informacion_seleccionada="informacion_seleccionada"></slot>
            </v-window-item>
        </v-window>
    </v-sheet>
</template>
<script>
import {EventBus} from '../../events/event-bus'
export default {
    props: {
        titulo: {
            type: String,
            default: "Bandeja de entrada"
        },
        load: {
            type: Boolean,
            default: false
        },
        informacion: {
            type: Array,
            required: false,
        },
        cantidad_paginas: {
            type: Number,
            default: 1
        }
    },
    data(){
        return {
            step: 1,
            search: "",
            caso_revisar: null,
            informacion_seleccionada: {
                title:"",
                subtitle:"",
                data: {
                    token: "",
                }
            }
        }
    },
    watch: {
        caso_revisar(val){
            console.log(val)
            if(typeof val != 'undefined'){
                 this.step=2
            }
           
        },
        search(val){
            val = val.toLowerCase()
            this.$store.state.datos_sesion.pedidos_filtrados = 
                this.$store.state.datos_sesion.pedidos.filter( item => item.title.toLowerCase().indexOf(val) !== -1 )
        }
    },
    created(){

    },
    mounted () {

        EventBus.$on('back', (payLoad) => {
            console.log('Hola')
            this.step = 1
            console.log(payLoad)
        });
    },
    methods: {
        buscar(page){
            console.log(page)
            this.$emit('listar_informacion',page)
        },
        estado(info){
            console.log(info.estado)
            if(info.estado=='Pendientes'){
                return 'green'
            }else{
                return 'grey lighten-1'
            }
            
        },
    },
}
</script>