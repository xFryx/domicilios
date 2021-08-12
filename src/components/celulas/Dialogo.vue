<template>
    <v-dialog max-width="450" v-model="$store.state.dialogos.dialogo_respuesta" >
        <v-card>
            <v-card-text>
                <h3 :style="'text-align:center;color:'+$store.state.dialogos.info_dialogo.color"> 
                    {{$store.state.dialogos.info_dialogo.tipo}} 
                </h3>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-text>
                {{$store.state.dialogos.info_dialogo.mensaje}}
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
                <v-btn @click="$store.state.dialogos.dialogo_respuesta=false" block text :color="$store.state.dialogos.info_dialogo.color" >
                    Aceptar
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>