<template>
        <v-toolbar class="mx-auto" dark :color="color" >
                <slot class="mx-auto" name="accion_retroceder"></slot>
                <v-toolbar-title>
                    {{titulo}}
                </v-toolbar-title>
                <v-spacer></v-spacer>
                <v-tooltip v-if="activar_tool" bottom>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn
                        
                            dark
                            v-bind="attrs"
                            v-on="on"
                            icon
                            @click="$emit('activar')"
                        >
                            <v-icon> {{icono}}  </v-icon>
                        </v-btn>
                    </template>
                    <span> {{accion}} </span>
                </v-tooltip>
        
        </v-toolbar>
</template>
<script>
export default {
    props: {
        titulo: {
            type: String,
            required: true,
        },
        activar_tool: {
            type: Boolean,
            default: true,
        },
        icono: {
            type: String,
            default: 'mdi-close'
        },
        accion: {
            type: String,
            default: 'Cerrar'
        },
        color: {
            type: String,
            default: 'purple darken-4'
        }


    }
}
</script>