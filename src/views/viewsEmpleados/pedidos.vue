<template>
    <EstructuraBasica class="mx-5">
        <template v-slot:main>
            <v-row>
                <v-col cols="12" sm="4">
                    <ToolbarBasico :titulo="'Pedidos'" 
                            :activa_tool="true"
                            :icono="'mdi-moped-outline'" 
                            :accion="'Nuevo pedido'"
                            @activar="nuevo_pedido=true"
                    
                    />
                    <v-list
                    subheader
                    two-line
                    rounded
                    >
                    <v-subheader inset>Bandeja</v-subheader>
                    <v-list-item-group
                            v-model="caso_revisar"
                            color="purple darken-1"
                        >
                            <v-list-item @click="filtrar('Todos')">
                                <v-list-item-avatar>
                                <v-icon
                                    class="indigo lighten-1"
                                    dark
                                >
                                    mdi-clipboard-text
                                </v-icon>
                                </v-list-item-avatar>

                                <v-list-item-content>
                                <v-list-item-title v-text="'Todos'"></v-list-item-title>

                                <v-list-item-subtitle v-text="'Los domicilios'"></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-action>
                                <v-btn icon>
                                    <v-icon color="grey lighten-1">mdi-information</v-icon>
                                </v-btn>
                                </v-list-item-action>
                            </v-list-item>
                            <v-list-item @click="filtrar('Pendientes')">
                                <v-list-item-avatar>
                                <v-icon
                                    class="indigo lighten-1"
                                    dark
                                >
                                    mdi-clipboard-text
                                </v-icon>
                                </v-list-item-avatar>

                                <v-list-item-content>
                                <v-list-item-title v-text="'Pendientes'"></v-list-item-title>

                                <v-list-item-subtitle v-text="'A entregar'"></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-action>
                                <v-btn icon>
                                    <v-icon color="grey lighten-1">mdi-information</v-icon>
                                </v-btn>
                                </v-list-item-action>
                            </v-list-item>
                            <v-list-item @click="filtrar('Cerrados')">
                                <v-list-item-avatar>
                                <v-icon
                                    class="indigo lighten-1"
                                    dark
                                >
                                    mdi-clipboard-text
                                </v-icon>
                                </v-list-item-avatar>

                                <v-list-item-content>
                                <v-list-item-title v-text="'Cerrados'"></v-list-item-title>

                                <v-list-item-subtitle v-text="''"></v-list-item-subtitle>
                                </v-list-item-content>

                                <v-list-item-action>
                                <v-btn icon>
                                    <v-icon color="grey lighten-1">mdi-information</v-icon>
                                </v-btn>
                                </v-list-item-action>
                            </v-list-item>
                        </v-list-item-group>                    
                    </v-list>
                </v-col>
                <v-col cols="12" sm="8">
                    <v-window v-model="bandeja">
                        <v-window-item :value="1">
                            <BandejaEntrada
                                @listar_informacion="listar($event)" 
                                :cantidad_paginas="cantidad_paginas" 
                                :load="load" 
                                :informacion="$store.state.datos_sesion.pedidos"
                            
                                @activar="bandeja++" 
                                
                            >
                                <template v-slot:default="informacion_seleccionada">
                                <v-sheet class="ma-7">
                                        <ToolbarBasico :activar_tool="estado(informacion_seleccionada.informacion_seleccionada.estado)" 
                                                        @activar="cancelar_pedido(informacion_seleccionada.informacion_seleccionada)" 
                                                        :accion="'Cerrar pedido'"
                                                        :titulo="informacion_seleccionada.informacion_seleccionada.title" >
                                            <template v-slot:accion_retroceder >
                                                <v-btn icon @click="retroceder()">
                                                    <v-icon>
                                                        mdi-arrow-left
                                                    </v-icon>
                                                </v-btn>
                                            </template>
                                        </ToolbarBasico>
                                        <br>
                                        <v-container>
                                            
                                            <DescripcionPedido :informacion="informacion_seleccionada.informacion_seleccionada" />
                                        </v-container>
                                    </v-sheet>
                                </template>
                            </BandejaEntrada>
                        </v-window-item>
                        <v-window-item :value="2">
                            <ToolbarBasico :activar_tool="false" :titulo="'Mapa de pedidos'" >
                                <template v-slot:accion_retroceder >
                                    <v-btn icon @click="bandeja--">
                                        <v-icon>
                                            mdi-arrow-left
                                        </v-icon>
                                    </v-btn>
                                </template>
                            </ToolbarBasico>
                            <GmapMap
                                :center="center"
                                :zoom="7"
                                map-type-id="terrain"
                                style="width: 100%; height: 500px"
                                >
                                <GmapMarker
                                    :key="index"
                                    v-for="(m, index) in markers"
                                    :position="m.position"
                                    :clickable="true"
                                    :draggable="true"
                                    @click="informacion_pedido(m)"
                                />
                                </GmapMap>
                        </v-window-item>
                    </v-window>
                    
                </v-col>
            </v-row>
            <v-dialog v-model="nuevo_pedido" persistent max-width="600" >
                <Card :titulo="'Nuevo pedido'" @activar="nuevo_pedido=false" >
                    <template v-slot:contenido>
                        <NuevoPedido @cerrar_pedido="nuevo_pedido=false" />
                    </template>
                </Card>
            </v-dialog>
        </template>
    </EstructuraBasica>
</template>
<script>
import BandejaEntrada from '../../components/celulas/BandejaEntrada.vue'
import NuevoPedido from '../../components/celulas/NuevoPedido.vue'
import DescripcionPedido from '../../components/celulas/DescripcionPedido.vue'
import {EventBus} from '../../events/event-bus'
export default {
    components: {
        BandejaEntrada,
        NuevoPedido,
        DescripcionPedido,

    },
    data(){
        return {
            nuevo_pedido: false,
            informacion: [],
            load: false,
            cantidad_paginas: 1,
            //informacion_seleccionada: {},
            bandeja: 1,
            page: 1,
            caso_revisar: 0,
            center: {lat: 10, lng: 10},
            markers: [
                {
                    position: {lat: 10, lng: 10},
                    title: 'Prueba'
                },
                {
                    position: {lat: 20, lng: 10},
                    title: 'Prueba1'
                },
                {
                    position: {lat: 30, lng: 10},
                    title: 'Prueba2'
                },
            ]
        }
    },
    async created(){
        this.listar()
    },
    methods: {
       async listar(){
           this.$store.state.datos_sesion.pedidos = []
           try {
                      let pedidos = await this.$store.dispatch('apis/llamado_get',{
                        url:"http://localhost:8081/one/domicilio/domicilios",
                        tipo_header: "otro",
                    })
                    console.log(pedidos)
                    
                    for (let index = 0; index < pedidos.data.length; index++) {
                        console.log( pedidos.data[index])
                            try {
                                let domiciliario = await this.$store.dispatch('apis/llamado_get',{
                                url: "http://localhost:8081/one/domiciliario/domiciliarios/"+pedidos.data[index].id_Domiciliario,
                                tipo_header: "otro"
                            })
                            let solicitante = await this.$store.dispatch('apis/llamado_get',{
                                url: "http://localhost:8081/one/solicitante/Solicitantes/"+pedidos.data[index].id_Solicitante,
                                tipo_header: "otro"
                            })

                            console.log(domiciliario)
                            console.log(solicitante)

                            this.$store.state.datos_sesion.pedidos.unshift({
                                id: pedidos.data[index].idDomicilio,
                                title: pedidos.data[index].dirDes,
                                subtitle: pedidos.data[index].horaDes,
                                data: {
                                    mensajero: {
                                        nombre: domiciliario.data.nombre,
                                        celular: domiciliario.data.celular
                                    },
                                    solicitante: {
                                        nombre: solicitante.data.nombre,
                                        celular: solicitante.data.telefono,
                                        direccion: pedidos.data[index].dirSalida,
                                        hora: pedidos.data[index].horaSalida,
                                        observacion: pedidos.data[index].observacion

                                    },
                                    destinatario: {
                                        nombre: pedidos.data[index].nombreDes,
                                        celular:  pedidos.data[index].telefonoDes,
                                        direccion:  pedidos.data[index].dirDes,
                                        hora: pedidos.data[index].horaDes,
                                    }
                                },
                                estado:  pedidos.data[index].estado

                            })
                        } catch (error) {
                            console.log(error)
                        }
                       
                        
                    }


                    /*
                   
                    for (let index = 0; index < page; index++) {
                        let estado = 'Pendientes'
                        if(index%2==0){
                            estado ='Cerrados'
                        }
                        this.$store.state.datos_sesion.pedidos.push({
                            id: index,
                            title: 'Kr '+index+' Calle 27 Alcacares',
                            subtitle: '2021-08-0'+index+1,
                            data: {
                                mensajero: {
                                    nombre: 'Fry',
                                    celular: 301523000
                                },
                                solicitante: {
                                    nombre: 'Destinatario '+index+1,
                                    celular: '301555'+index,
                                    direccion: 'Kr '+index+1+' Calle 2 Barrio alcacares',
                                    hora: index+1+':00',
                                    observacion: 'Entragalo bien :D '
                                },
                                destinatario: {
                                    nombre: 'Destinatario '+index+1,
                                    celular: '301555'+index,
                                    direccion: 'Kr '+index+1+' Calle 2 Barrio alcacares',
                                    hora: index+2+':00'
                                }
                            },
                            estado: estado
                        })
                        
                    }
                    */
                    this.$store.state.datos_sesion.pedidos_filtrados = [...this.$store.state.datos_sesion.pedidos]
                 //console.log(page)
           } catch (error) {
               console.log(error)
           }
           
        },
        filtrar(filtro){
            if(filtro=='Todos'){
                this.$store.state.datos_sesion.pedidos_filtrados = [...this.$store.state.datos_sesion.pedidos]
            }else{
                filtro = filtro.toLowerCase()
                this.$store.state.datos_sesion.pedidos_filtrados = 
                    this.$store.state.datos_sesion.pedidos.filter( item => item.estado.toLowerCase().indexOf(filtro) !== -1 )
            }
            
        },
        estado(tipo){
            if(tipo=='Pendientes'){
                return true
            }
            return false
        },
        async cancelar_pedido(pedido){
            console.log(this.$store.state.datos_sesion.pedidos)
            console.log(pedido)
            try {
                let cancelar = await this.$store.dispatch('apis/llamado_put',{
                    url:"http://localhost:8081/one/domicilio/estado/"+pedido.id,
                    body: {
                        estado: 'Cerrados',
                    },
                    tipo_header: "otro"
                })
                console.log(cancelar)
                let index = this.$store.state.datos_sesion.pedidos.map(function(e) { return e.id; }).indexOf(pedido.id);
                
                console.log(index)
                Object.assign(this.$store.state.datos_sesion.pedidos[index],{estado: "Cerrados"})
                this.$store.commit('dialogos/respuesta',{
                    tipo: "Exitoso",
                    color: 'green',
                    mensaje: "El pedido ha sido cerrado de forma exitosa "
                })
            } catch (error) {
                console.log(error)
                this.$store.commit('dialogos/respuesta',{
                    tipo: "ERROR",
                    color: 'red',
                    mensaje: "Algo ha salido mal cerrando el pedido... "+error.message
                })
            }
            
            this.retroceder()

            console.log(this.$store.state.datos_sesion.pedidos)
        },
        retroceder(){
             EventBus.$emit('back', 1);
        },
        informacion_pedido(posicion){
            this.center = posicion.position
            console.log(posicion)
        }
    }
}
</script>