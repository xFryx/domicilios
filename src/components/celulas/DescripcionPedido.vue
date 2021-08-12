<template>
    <v-sheet>
        <v-row>
            <v-col>
                <v-expansion-panels>
                    <v-expansion-panel>
                            <v-expansion-panel-header>
                                nformacion del mensajero
                            <template v-slot:actions>
                                <v-icon color="red">mdi-moped-outline</v-icon>
                            </template>
                            </v-expansion-panel-header>
                            <v-expansion-panel-content>
                                <v-row>
                                    <v-col>
                                        <v-text-field required :rules="[v => !!v || 'Es requerido un nombre ']" prepend-icon="mdi-account" label="Nombre" v-model="pedido.data.mensajero.nombre" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                    <v-col>
                                        <v-text-field prepend-icon="mdi-phone"  label="Celular" v-model="pedido.data.mensajero.celular" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-col>
        </v-row>
        <v-row>
            <v-col cols="12">
                <v-expansion-panels>
                    <v-expansion-panel>
                            <v-expansion-panel-header>
                                Informacion del solicitante
                            <template v-slot:actions>
                                <v-icon color="purple darken-4">
                                mdi-account
                                </v-icon>
                            </template>
                            </v-expansion-panel-header>
                            <v-expansion-panel-content>
                                <v-row>
                                    <v-col>
                                        <v-text-field required :rules="[v => !!v || 'Es requerido un nombre ']" prepend-icon="mdi-account" label="Nombre" v-model="pedido.data.solicitante.nombre" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                    <v-col>
                                        <v-text-field prepend-icon="mdi-map-marker-radius-outline" label="Direccion" v-model="pedido.data.solicitante.direccion" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                    <v-col>
                                        <v-text-field prepend-icon="mdi-phone"  label="Celular" v-model="pedido.data.solicitante.celular" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <v-textarea rows="2" filled dense label="Observacion" v-model="pedido.data.solicitante.observacion" ></v-textarea>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <v-text-field prepend-icon="calendar-clock" label="Hora" v-model="pedido.data.solicitante.hora" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-col>
            
        </v-row>
        <v-row>
            <v-col cols="12">
                <v-expansion-panels>
                    <v-expansion-panel>
                            <v-expansion-panel-header>
                                Informacion del destinatario
                            <template v-slot:actions>
                                <v-icon color="green">
                                    mdi-account
                                </v-icon>
                            </template>
                            </v-expansion-panel-header>
                            <v-expansion-panel-content>
                                <v-row>
                                    <v-col>
                                        <v-text-field required :rules="[v => !!v || 'Es requerido un nombre ']" prepend-icon="mdi-account" label="Nombre" v-model="pedido.data.destinatario.nombre" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                    <v-col>
                                        <v-text-field prepend-icon="mdi-map-marker-radius-outline" label="Direccion" v-model="pedido.data.destinatario.direccion" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                    <v-col>
                                        <v-text-field prepend-icon="mdi-phone"  label="Celular" v-model="pedido.data.destinatario.celular" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>
                                        <v-text-field prepend-icon="calendar-clock" label="Hora" v-model="pedido.data.solicitante.hora" readonly filled rounded dense></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-expansion-panel-content>
                    </v-expansion-panel>
                </v-expansion-panels>
            </v-col>
        </v-row>    
    </v-sheet>
</template>
<script>
export default {
    props: {
        informacion: {
            type: Object,
            required: true,
        }
    },
    data(){
        return {
            pedido: null
        }
    },
    watch: {
        informacion(val){
            this.pedido = {...val}
        },
    },
    created(){
        this.pedido = {...this.informacion}
    }
}
</script>