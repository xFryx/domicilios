<template>
  <v-app>
    <v-main>
      <router-view />
      
    </v-main>
    <Dialogo />
  </v-app>
</template>

<script>
import Dialogo from './components/celulas/Dialogo';

export default {
  name: 'App',

  components: {
    Dialogo,
  },

  data: () => ({
    //
  }),
};
</script>
<style>
  #app {
    font-family: "Bahnschrift";
  }
</style>
