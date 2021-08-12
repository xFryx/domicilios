<template>
    <v-card>
        <ToolbarBasico 
                  :titulo="titulo" 
                  @activar="$emit('activar')"
                  :color="color"
                  :activar_tool="activar_tool"
                  
        />
        <v-progress-linear indeterminate color="purple" v-show="load"></v-progress-linear>
        <v-card-text>
            <slot name="contenido" ></slot>
        </v-card-text>
        
    </v-card>
</template>
<script>
export default {
    props: {
        titulo: {
            type: String,
            required: true,
        },
        load: {
            type: Boolean,
            default: false,
        },
        color:{
            type: String,
        },
        activar_tool: {
            type: Boolean,
            default: true,
        }
    },
    data(){
        return {

        }
    }
}
</script>