<template>
    <EstructuraBasica class="mx-5">
        <template v-slot:main>
            <ToolbarBasico :titulo="'Domiciliarios'" @activar="crear()" :accion="'Crear domiciliario'" :icono="'mdi-moped-outline'" />
            <v-row>
                <v-col class="mx-auto" cols="6" sm="4" v-for="(domiciliario,index) in $store.state.domiciliarios.domiciliarios" :key="index">
                    <Domiciliario 
                        :informacion="domiciliario" 
                        @eliminar="eliminar_asignar(domiciliario)"
                        @editar="editar_asignar(domiciliario)"

                    />
                </v-col>
            </v-row>
            <v-dialog persistent v-model="dialogo_crud_domiciliario" max-width="600">
                <Card :titulo="'Crear domiciliario'" @activar="dialogo_crud_domiciliario=false">
                    <template v-slot:contenido>
                        <CrudDomiciliario ref="crud" 
                          @editar="editar_domiciliario()"
                          @crear="crear_domiciliario()" 
                        />
                    </template>
                </Card>
            </v-dialog>
            <v-dialog persistent v-model="dialogo_confirmar_eliminacion" max-width="400">
                <v-card>
                    <v-card-text>
                        <br>
                        <v-divider></v-divider>
                        <h3 style="text-align:center"> Estás seguro que deseas eliminar al domiciliario: </h3>
                        <h3 style="text-align:center"> {{$store.state.domiciliarios.domiciliario.nombre}} </h3>
                        <v-divider></v-divider>
                        <br>
                    </v-card-text>
                    <v-card-actions>
                        <v-btn text color="red" @click="dialogo_confirmar_eliminacion=false"> Cancelar </v-btn>
                        <v-spacer></v-spacer>
                        <v-btn color="purple darken-4" dark @click="eliminar_domiciliario()" > Aceptar </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </template>
   </EstructuraBasica>
</template>
<script>
import Domiciliario from "../../components/celulas/Usuario.vue"
import CrudDomiciliario from "../../components/celulas/CrudDomiciliario.vue"

export default {
    components: {
        Domiciliario,
        CrudDomiciliario
    },
    data(){
        return {
            //domiciliarios: [],
            dialogo_crud_domiciliario: false,
            domiciliario_editar: {
                nombre: "",
                celular: "",

            },
            dialogo_confirmar_eliminacion: false
        }
    },
    created(){
        this.$store.dispatch('apis/listar_domiciliarios')
    },
    methods: {
        eliminar_asignar(domiciliario){
            this.$store.state.domiciliarios.domiciliario = {...domiciliario}
            this.dialogo_confirmar_eliminacion = true
            
        },
        async eliminar_domiciliario(){
            try {

                let domiciliario = await this.$store.dispatch('apis/llamado_delete',{
                   url: "http://localhost:8081/one/domiciliario/domiciliarios/"+this.$store.state.domiciliarios.domiciliario.objectId,
                   tipo_header: "otro"
                })
                console.log(domiciliario)

                let index = this.$store.state.domiciliarios.domiciliarios.map(function(e) { return e.objectId; }).indexOf(this.$store.state.domiciliarios.domiciliario.objectId);
                this.$store.state.domiciliarios.domiciliarios.splice(index,1)

                this.$store.commit('dialogos/respuesta',{
                    tipo: "Exitoso",
                    color: 'green',
                    mensaje: "El usuario ha sido eliminado de forma exitosa "
                })

            } catch (error) {
                console.log(error)
                this.$store.commit('dialogos/respuesta',{
                    tipo: "ERROR",
                    color: 'red',
                    mensaje: "Algo ha salido mal eliminando el usuario... "+error.message
                })
            }

            this.dialogo_confirmar_eliminacion = false
            
        },
        crear(){
            
            this.$store.state.domiciliarios.domiciliario = {
                nombre: "",
                celular: null,
                //objectId: "",
                direccion: "",
                cedula: null,
                placa_moto: "",
            }

            try {
                this.$refs.crud.reset()
            } catch (error) {
                console.log(error.message)
            }
            
            this.dialogo_crud_domiciliario=true
        },
        async crear_domiciliario(){
           try {
               let domiciliario = await this.$store.dispatch('apis/llamado_post',{
                   url: "http://localhost:8081/one/domiciliario/domiciliarios",
                   body: {
                       nombre: this.$store.state.domiciliarios.domiciliario.nombre,
                       celular: parseInt( this.$store.state.domiciliarios.domiciliario.celular),
                       cedula: parseInt( this.$store.state.domiciliarios.domiciliario.cedula),
                       placa_moto: this.$store.state.domiciliarios.domiciliario.placa_moto,
                       direccion: this.$store.state.domiciliarios.domiciliario.direccion
                   },
                   tipo_header: "otro"
               })

                console.log(domiciliario)
                this.$store.state.domiciliarios.domiciliarios.push({...domiciliario.data})

                this.$store.commit('dialogos/respuesta',{
                    tipo: "Exitoso",
                    color: 'green',
                    mensaje: "El usuario ha sido creado de forma exitosa "
                })
            } catch (error) {
                console.log(error)
                this.$store.commit('dialogos/respuesta',{
                    tipo: "ERROR",
                    color: 'red',
                    mensaje: "Algo ha salido mal creando el usuario... "+error.message
                })
            }
             this.reset()
            
        },
        editar_asignar(domiciliario){
            console.log(domiciliario)
            
            this.$store.state.domiciliarios.domiciliario = {...domiciliario}
            this.dialogo_crud_domiciliario = true
        },
        async editar_domiciliario(){
            try {

                let domiciliario = await this.$store.dispatch('apis/llamado_put',{
                   url: "http://localhost:8081/one/domiciliario/domiciliarios/"+this.$store.state.domiciliarios.domiciliario.objectId,
                   body: {
                       nombre: this.$store.state.domiciliarios.domiciliario.nombre,
                       celular: parseInt( this.$store.state.domiciliarios.domiciliario.celular),
                       cedula: parseInt( this.$store.state.domiciliarios.domiciliario.cedula),
                       placa_moto: this.$store.state.domiciliarios.domiciliario.placa_moto,
                       direccion: this.$store.state.domiciliarios.domiciliario.direccion
                   },
                   tipo_header: "otro"
               })

                console.log(domiciliario)
                let index = this.$store.state.domiciliarios.domiciliarios.map(function(e) { return e.objectId; }).indexOf(this.$store.state.domiciliarios.domiciliario.objectId);
                
                console.log(index)
                Object.assign(this.$store.state.domiciliarios.domiciliarios[index],this.$store.state.domiciliarios.domiciliario)
                
                this.$store.commit('dialogos/respuesta',{
                    tipo: "Exitoso",
                    color: 'green',
                    mensaje: "El usuario ha sido editado de forma exitosa "
                })
            } catch (error) {
                console.log(error)
                this.$store.commit('dialogos/respuesta',{
                    tipo: "ERROR",
                    color: 'red',
                    mensaje: "Algo ha salido mal editando el usuario... "+error.message
                })
            }
            this.reset()
        },
        reset(){
            this.dialogo_crud_domiciliario = false
             this.$store.state.domiciliarios.domiciliario = {
                nombre: "",
                celular: null,
                //objectId: "",
                direccion: "",
                cedula: null,
                placa_moto: "",
            }
            this.$refs.crud.reset()
        },
        
    }
}
</script>