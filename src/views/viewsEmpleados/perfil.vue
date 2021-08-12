<template>
    <EstructuraBasica>
        <template v-slot:main>
            <Perfil />
        </template>
    </EstructuraBasica>
</template>
<script>
import Perfil from '../../components/celulas/Perfil.vue'
export default {
    components: {
        Perfil
    },
    data(){
        return {

        }
    },
}
</script>