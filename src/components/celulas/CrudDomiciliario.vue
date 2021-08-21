<template>
    <v-form ref="form" v-model="valid">
        <v-row class="ma-3">
            <v-col>
                 <v-text-field filled rounded dense label="Nombre" 
                    prepend-icon="mdi-account"
                     :rules="[v => !!v || 'Es requerida un nombre']"
                    required
                    v-model="$store.state.domiciliarios.domiciliario.nombre" >

                </v-text-field>
            </v-col>
            <v-col>
                <v-text-field 
                     :rules="[v => !!v || 'Es requerida un numero de telefono']"
                     required
                     prepend-icon="mdi-phone" filled rounded dense label="Celular" 
                     type="number"
                     v-model="$store.state.domiciliarios.domiciliario.celular"
                >

                </v-text-field>
            </v-col>
           
        </v-row>
        <v-row class="ma-3">
            <v-col>
                 <v-text-field filled rounded dense label="Cedula" 
                    prepend-icon="mdi-card"
                     :rules="[v => !!v || 'Es requerida una cedula']"
                    required
                    v-model="$store.state.domiciliarios.domiciliario.cedula" >

                </v-text-field>
            </v-col>
            <v-col>
                <v-text-field 
                     :rules="[v => !!v || 'Es requerida una direcion']"
                     required
                     prepend-icon="mdi-home" filled rounded dense label="Direccion" 
                    
                     v-model="$store.state.domiciliarios.domiciliario.direccion"
                >

                </v-text-field>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="8" class="mx-auto">
                <v-text-field 
                     :rules="[v => !!v || 'Es requerida una placa para la moto']"
                     required
                     prepend-icon="mdi-moped-outline" filled rounded dense label="Placa moto" 
                    
                     v-model="$store.state.domiciliarios.domiciliario.placa_moto"
                >

                </v-text-field>
            </v-col>
        </v-row>
        <v-row>
            <v-btn 
                @click="crear_domiciliario()"
                color="purple darken-4" 
                :disabled="!valid" block style="color:white" large > Aceptar </v-btn>
        </v-row>
        <br>
    </v-form>
</template>
<script>
export default {
    data(){
        return {
            valid: true
        }
    },
    methods: {
        reset(){
            this.$refs.form.resetValidation()
        },
        async crear_domiciliario(){
            console.log('?')
            if( typeof this.$store.state.domiciliarios.domiciliario.objectId == 'undefined'){
                this.$emit('crear')
            }else{
                console.log('editar')
                this.$emit('editar')
            }
        }
        
    }
}
</script>