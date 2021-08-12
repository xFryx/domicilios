<template>
    <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="fecha_hijo"
        transition="scale-transition"
        offset-y
        min-width="auto"
    >
        <template v-slot:activator="{ on, attrs }">
        <v-text-field
            v-model="fecha_hijo"
            :label="label"
            prepend-icon="mdi-calendar"
            readonly
            filled 
            rounded dense
            v-bind="attrs"
            v-on="on"
            required :rules="[v => !!v || 'Es requerido una fecha de inicio']"
        ></v-text-field>
        </template>
        <v-date-picker
        v-model="fecha_hijo"
        no-title
        scrollable
        >
        <v-spacer></v-spacer>
        <v-btn
            text
            color="primary"
            @click="menu = false"
        >
            Cancelar
        </v-btn>
        <v-btn
            text
            color="primary"
            @click="$refs.menu.save(fecha_hijo);$emit('fecha',fecha_hijo)"
        >
            OK
        </v-btn>
        </v-date-picker>
    </v-menu>
</template>
<script>
export default {
    props: {
        label: {
            type: String,
            required: true,
        },
        fecha: {
            type: String,
            default: new Date().toISOString().substr(0, 10)
        }
    },
    data(){
        return {
            menu: false,
            fecha_hijo: new Date().toISOString().substr(0, 10)
        }
    },
    created(){
        this.fecha_hijo = this.fecha
        this.$emit('fecha',this.fecha_hijo)
    }
}
</script>