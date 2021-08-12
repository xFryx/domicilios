<template>
  <div class="text-center">
    <v-pagination
      v-model="page"
      :length="cantidad_paginas"
    ></v-pagination>
  </div>
</template>
<script>
export default {
    props: {
        cantidad_paginas: {
            type: Number,
            required: true
        }
    },
    data(){
        return {
            page: 1
        }
    },
    watch: {
        page(val){
            this.$emit('buscar',val)
        }
    },
}
</script>