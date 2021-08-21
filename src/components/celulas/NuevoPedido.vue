<template>
    <v-sheet max-width="800" class="mx-auto">
        <v-form ref="form" v-model="valid">
            <v-stepper v-model="e1">
                <v-stepper-header>
                <v-stepper-step
                    :complete="e1 > 1"
                    step="1"
                >
                    Informacion solicitante
                </v-stepper-step>

                <v-divider></v-divider>

                <v-stepper-step
                    :complete="e1 > 2"
                    step="2"
                >
                    Informacion del destinatario
                </v-stepper-step>

                <v-divider></v-divider>

                <v-stepper-step step="3">
                    Domiciliario
                </v-stepper-step>
                </v-stepper-header>

                <v-stepper-items>
                <v-stepper-content step="1">
                    <v-card>
                        <v-row>
                            <v-col cols="12" sm="6">
                                <v-text-field 
                                :rules="[v => !!v || 'Es requerida un nombre']"
                                required
                                 v-model="pedido.solicitante.nombre" prepend-icon="mdi-account" filled rounded dense label="Nombre" ></v-text-field>
                            </v-col>
                            <v-col class="mx-auto" cols="12" sm="6">
                                <v-text-field 
                                :rules="[v => !!v || 'Es requerida una direccion']"
                                required
                                v-model="pedido.solicitante.direccion" prepend-icon="mdi-home" filled rounded dense label="Direccion " ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            
                            <v-col class="mx-auto" cols="12" sm="6">
                                <v-text-field
                                :rules="[v => !!v || 'Es requerida un telefono de contacto']"
                                    required 
                                type="number"
                                v-model="pedido.solicitante.celular" prepend-icon="mdi-phone" filled rounded dense label="Telefono de contacto" ></v-text-field>
                            </v-col>    
                            <v-col class="mx-auto" cols="12" sm="6">
                                <DatePicker @fecha="pedido.solicitante.hora=$event" :label="'Hora de entrega'" />
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col>
                                <v-textarea v-model="pedido.solicitante.observacion" rows="3" filled dense label="Observacion">

                                </v-textarea>
                            </v-col>
                        </v-row>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn
                            dark
                            color="indigo"
                            @click="e1 = 2"
                            >
                            Siguiente
                            </v-btn>
                        </v-card-actions>
                    </v-card>

                    
                </v-stepper-content>
                <v-stepper-content step="2">
                    <v-card>
                        <v-row>
                            <v-col cols="12" sm="6">
                                <v-text-field 
                                :rules="[v => !!v || 'Es requerida un nombre del destinatario']"
                                    required
                                 v-model="pedido.destinatario.nombre" prepend-icon="mdi-account" filled rounded dense label="Nombre" ></v-text-field>
                            </v-col>
                            <v-col class="mx-auto" cols="12" sm="6">
                                <v-text-field 
                                :rules="[v => !!v || 'Es requerida una direccion del destinatario']"
                                    required
                                v-model="pedido.destinatario.direccion" prepend-icon="mdi-home" filled rounded dense label="Direccion " ></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row>
                            
                            <v-col class="mx-auto" cols="12" sm="6">
                                <v-text-field 
                                :rules="[v => !!v || 'Es requerida un celular para el destinatario']"
                                    required
                                    type="number"
                                v-model="pedido.destinatario.celular" prepend-icon="mdi-phone" filled rounded dense label="Telefono de contacto" ></v-text-field>
                            </v-col>    
                            <v-col class="mx-auto" cols="12" sm="6">
                                <DatePicker @fecha="pedido.destinatario.hora=$event" :label="'Hora de recibido'" />
                            </v-col>
                        </v-row>
                        <v-card-actions>
                            <v-btn @click="e1--" color="red" text>
                                Retroceder
                            </v-btn>
                            <v-spacer></v-spacer>
                            <v-btn
                                dark
                                color="indigo"
                                @click="e1 = 3"
                            >
                                Continuar
                            </v-btn>
                        </v-card-actions>
                        
                    </v-card>
                </v-stepper-content>

                <v-stepper-content step="3">
                    <v-card>
                    
            
                        <v-card-text>
                            <v-autocomplete
                                    :items="$store.state.domiciliarios.domiciliarios" 
                                    :item-text="'nombre'" v-model="pedido.domiciliario" 
                                    return-object filled rounded dense
                                    label="Domiciliario"
                                    :rules="[v => !!v || 'Es necesario elegir un domiciliario']"
                                    required
                                    >

                            </v-autocomplete>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="red" @click="e1--" text>
                            Retroceder
                            </v-btn>
                            <v-spacer></v-spacer>
                            <v-btn
                                style="color:white"
                                color="indigo"
                                @click="nuevo_pedido(pedido)"
                                :disabled="!valid"
                                
                            >
                                Continuar
                            </v-btn>
                        </v-card-actions>
                    </v-card>

                    
                </v-stepper-content>
                </v-stepper-items>
            </v-stepper>
        </v-form>
        
    </v-sheet>
</template>
<script>
import DatePicker from '../celulas/DatePicker.vue'
export default {
    components: {
        DatePicker
    },
    data(){
        return {
            valid: true,
            e1: 1,
            domiciliarios : [],
            pedido: {
                domiciliario: null,
                solicitante: {
                    nombre: '',
                    celular: null,
                    direccion: '',
                    hora: '',
                    observacion: "",
                },
                destinatario: {
                    nombre: '',
                    celular: null,
                    direccion: '',
                    hora: '',
                    //observacion: "",
                },
            }
        }
    },
    created(){
        this.$store.dispatch('apis/listar_domiciliarios')
       
    },
    methods: {
      
        async nuevo_pedido(pedido){
            console.log(pedido)
            try {
                let solicitante = await this.$store.dispatch('apis/llamado_post',{
                    url: "http://localhost:8081/one/solicitante/Solicitantes",
                    body: {
                        nombre: pedido.solicitante.nombre,
                        telefono: pedido.solicitante.celular.toString()
                        //direccion: 
                    },
                    tipo_header: "otro"
                })
                console.log(solicitante)
                console.log('ddasda')
                let pedido_body = {
                    idSol: solicitante.data.id,
                    idDomi: pedido.domiciliario.id,

                    horaSalida: new Date(pedido.solicitante.hora) ,
                    estado: "Pendientes",
                    nombreDes: pedido.destinatario.nombre,
                    dirDes: pedido.destinatario.direccion,
                    coorDes: "",
                    horaDes: new Date(pedido.destinatario.hora),
                    dirSalida: pedido.solicitante.direccion,
                    coorSalida: "",
                    //horaDes: new Date(pedido.solicitante.hora),
                    telefonoDes: pedido.destinatario.celular.toString(),
                    observacion: pedido.solicitante.observacion,
                    id_Domiciliario: pedido.domiciliario.objectId,
                    id_Solicitante: solicitante.data.id
                    //direccion: 
                    }
                let domicilio = await this.$store.dispatch('apis/llamado_post',{
                    url: "http://localhost:8081/one/domicilio/domicilios",
                    body: pedido_body,
                    tipo_header: "otro"
                })
                console.log(domicilio)
                let body_2 = {
                            mensajero: {
                                        nombre: pedido.domiciliario.nombre,
                                        celular: pedido.domiciliario.celular
                                    },
                                    solicitante: {
                                        nombre: solicitante.data.nombre,
                                        celular: solicitante.data.telefono,
                                        direccion: pedido.solicitante.direccion,
                                        hora: new Date(pedido.solicitante.hora),
                                        observacion:pedido.solicitante.observacion

                                    },
                                    destinatario: {
                                        nombre: pedido.destinatario.nombre,
                                        celular:  pedido.destinatario.celular,
                                        direccion:  pedido.destinatario.direccion,
                                        hora:  new Date(pedido.destinatario.hora),
                                    }
                        }
                this.$store.state.datos_sesion.pedidos.unshift(
                    {
                        id: domicilio.data.idDomicilio ,
                        title: pedido.destinatario.direccion,
                        subtitle: pedido.destinatario.hora,
                        data: {...body_2},
                        estado: "Pendientes"
                    })
                this.$store.state.datos_sesion.pedidos_filtrados.unshift(
                    {
                        id: domicilio.data.idDomicilio ,
                        title: pedido.destinatario.direccion,
                        subtitle: pedido.destinatario.hora,
                        data:  {...body_2},
                        estado: "Pendientes"
                    })
                  
                
                console.log(this.$store.state.datos_sesion.pedidos)
                this.$store.commit('dialogos/respuesta',{
                    color: "indigo",
                    tipo: 'Exitoso',
                    mensaje: 'El pedido ha sido colocado de forma exitosa...'
                })

                this.pedido = {
                    domiciliario: null,
                    solicitante: {
                        nombre: '',
                        celular: null,
                        direccion: '',
                        hora: '',
                        observacion: "",
                    },
                    destinatario: {
                        nombre: '',
                        celular: null,
                        direccion: '',
                        hora: '',
                        //observacion: "",
                    },
                }
                this.$emit('cerrar_pedido')
            } catch (error) {
                this.$store.commit('dialogos/respuesta',{
                    color: "red",
                    tipo: 'ERROR',
                    mensaje: 'Algo ha salido mal creando el pedido...'+error.message
                })
                console.log(error)
            }
        }
    },
}
</script>