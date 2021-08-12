<template>
  <v-sheet
    elevation="5"
    rounded=""
    max-width="250"
    
    class="mt-8 mx-auto"
  >
   
    <v-container>
      
      <v-sheet
        color="blue darken-4"
        dark
        elevation="5"
        class="mt-n7"
        rounded=""
      >
        <v-row>
          <v-col>
            <div
              v-text="informacion.nombre"
              class="title font-weight-light text-center mt-n1"
            ></div>
          </v-col>
        </v-row>
      </v-sheet>
    </v-container>

    <v-container>
      <v-row align="center" justify="center">
      
          <v-avatar size="150" class="mx-auto">
            <v-img
            
              alt="user"
              src="https://image.freepik.com/vector-gratis/perfil-empresario-dibujos-animados_18591-58479.jpg"
            >
            </v-img>
          </v-avatar>
     
      </v-row>
      <v-row class="pa-1">
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              text
              small
              fab
              class="ml-4 blue-grey lighten-5"
              @click="$emit('editar')"
              elevation=""
              color="blue darken-3"
              v-bind="attrs"
              v-on="on"
            >
              <v-icon> mdi-information </v-icon>
            </v-btn>
          </template>
          <span>Ver Información</span>
        </v-tooltip>
        <v-spacer></v-spacer>
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              text
              small
              fab
              class="mr-4 blue-grey lighten-5"
              @click="$emit('eliminar')"
              color="red"
              v-bind="attrs"
              v-on="on"
            >
              <v-icon> mdi-delete </v-icon>
            </v-btn>
          </template>
          <span>Eliminar Usuario</span>
        </v-tooltip>
      </v-row>
    </v-container>
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
  data() {
    return {};
  },
  methods: {
    ver() {
      console.log(this.informacion);
    },
  },
};
</script>
<style >
#fondo {
  background-image: "../../asset/img.png";
  width: 520;
  height: 300;
  text-align: center;
  background-position: center center;
  background-size: cover;
  background-repeat: repeat;
  position: relative;
}
#fondo::before {
  content: "";
  position: absolute;
  background-color: rgba(0, 0, 0, 0.1);
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  opacity: 0.7;
}
</style>