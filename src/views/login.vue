<template>
    <v-app>
        <v-main class="mx-5">
           <v-container fluid class="fill-height">
                <v-row  align="center" justify="center">
                    <v-col class="d-none d-sm-flex" cols="4">
                        <v-sheet>
                             <h1 style="text-align:center;color:purple"> Delivery P-Web  </h1>
                             <h3 style="text-align:center;color:purple"> Welcome! Let's build amazing things together.  </h3>

                            <v-img contain max-width="400" class="my-15" :src="require('../assets/delivery.png')">

                            </v-img>
                            <h4 style="text-align:center;"> Unimag Grupo: Andree narvaez - Fry simmonds  </h4>
                        </v-sheet>
                    </v-col>
                    <v-col cols="12" sm="8">
                        <v-sheet style="height:100vh" color="#EDEAF1">
                            <v-container fluid class="fill-height">
                                <v-row  align="center" justify="center">
                                    <v-col cols="12" sm="9">
                                         <v-card elevation="8" >
                                        <v-card-text>
                                            <h1 style="text-align:center;color:purple"> Welcome </h1>
                                            <br>
                                        
                                            <h4 style="text-align:center"> Sign in to your account </h4>
                                        </v-card-text>
                                        <v-card-text>
                                            <v-text-field prepend-icon="mdi-account-circle" 
                                                filled rounded dense label="Login">
                                            </v-text-field>
                                            <v-text-field 
                                                prepend-icon="mdi-lock" 
                                                v-model="datos_inicio.password"
                                                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                                                filled 
                                                dense
                                                rounded
                                                :type="show ? 'text' : 'password'"
                                                
                                                label="Password"
                                                
                                                @click:append="show = !show">
                                            </v-text-field>
                                        </v-card-text>
                                        <v-card-actions class="ma-5">
                                            <v-btn @click="login()"   dark large block color="purple darken-1">Login</v-btn>
                                            
                                        </v-card-actions>
                                    </v-card>
                                    </v-col>
                                    
                                </v-row>
                            </v-container>
                           
                        </v-sheet>
                    </v-col>
                    
                </v-row>
            </v-container>
        </v-main>
    </v-app>
</template>
<script>
export default {
    data(){
        return {
            show: false,
            datos_inicio: {
                login: "",
                password: ""
            }
        }
    },
    created(){
        //console.log(document.cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`)))
        
        //console.log(cookie.match(new RegExp(`XSRF-TOKEN=([^;]+)`)))
        //console.log(new RegExp(`XSRF-TOKEN=([^;]+)`))
    },
    methods: {
       async login(){
            
            
            try {
               let token = await this.$store.dispatch('apis/llamado_post',{
                    url: "http://localhost:8081/one/auth/signin",
                    body: {
                            "username": "Andree12",
                            "password": "123123"
                            
                        },
                    tipo_header: "auth"
                })
                console.log(token)
                this.$store.state.apis.token = token.data.accessToken 
                this.$store.state.datos_sesion.datos = token.data
                this.$router.push({name: "pedidos"})
            } catch (error) {
                this.$store.commit('dialogos/respuesta',{
                    mensaje: "WHAT IS THIS POKEMON? IS PIKACHU. NOOO IS CHARMANDER FUCKKKKKKKK",
                    color: "red",
                    tipo:"Usuaario no encontrado"
                })
                console.log(error)
            }
            
            
            
            //
        }
    }
}
</script>